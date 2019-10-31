package cn.gzjt.finance.service.impl;

import cn.gzjt.finance.domain.Contract;
import cn.gzjt.finance.domain.OrgEntity;
import cn.gzjt.finance.domain.PageBean;
import cn.gzjt.finance.domain.Payment;
import cn.gzjt.finance.mapper.ContractMapper;
import cn.gzjt.finance.mapper.OrgEntityMapper;
import cn.gzjt.finance.mapper.PaymentMapper;
import cn.gzjt.finance.service.PaymentService;
import cn.gzjt.finance.utils.MyBatisUtils;
import cn.gzjt.finance.utils.TextUtils;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

/**
 * @author jianwei.zhou
 * @date 2019/10/17 11:46
 */
public class PaymentServiceImpl implements PaymentService {
    @Override
    public String addPayment(Payment payment) {
        SqlSession session = MyBatisUtils.openSession();
        PaymentMapper paymentMapper = session.getMapper(PaymentMapper.class);
        //检查编号是否已存在
        Payment oldPayment = paymentMapper.getByNumber(payment.getNumber());
        if (oldPayment != null) {
            return "凭证号已存在！";
        }
        //检查合同是否存在
        ContractMapper contractMapper = session.getMapper(ContractMapper.class);
        Contract oldContract = contractMapper.getByNumber(payment.getContract().getNumber());
        if (oldContract == null) {
            return "合同不存在！";
        }
        payment.setContract(oldContract);
        //检查交易双方
        OrgEntityMapper orgEntityMapper = session.getMapper(OrgEntityMapper.class);
        OrgEntity f = orgEntityMapper.getByName(payment.getFrom().getName());
        try {
            if (f == null) {
                f = new OrgEntity();
                f.setName(payment.getFrom().getName());
                f.setCreateBy(payment.getCreateBy());
                orgEntityMapper.insert(f);
            }
            payment.setFrom(f);
            OrgEntity t = orgEntityMapper.getByName(payment.getTo().getName());
            if (t == null) {
                t = new OrgEntity();
                t.setName(payment.getTo().getName());
                t.setCreateBy(payment.getCreateBy());
                orgEntityMapper.insert(t);
            } else if (TextUtils.isNotEmpty(payment.getToAccount()) && !payment.getToAccount().equals(t.getBankAccount())) {
                orgEntityMapper.updateBankAccount(t.getId(), payment.getToAccount());
                t.setBankAccount(payment.getToAccount());
            }
            payment.setTo(t);
            System.out.println("addPayment->" + payment);
            paymentMapper.insert(payment);
            contractMapper.updatePaySum(payment.getContract().getId(), payment.getAmount());
            Integer billAmount = payment.getBillAmount();
            if (billAmount != null) {
                contractMapper.updateBillSum(payment.getContract().getId(), billAmount);
            }
            session.commit();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
            return "创建失败";
        } finally {
            session.close();
        }
    }

    @Override
    public PageBean<Payment> getByKey(int curPage, int rowsOfOnePage, String key) {
        SqlSession session = MyBatisUtils.openSession();
        PaymentMapper mapper = session.getMapper(PaymentMapper.class);
        PageBean<Payment> pageBean = new PageBean<>();
        int totalCount;
        int totalPage;
        int start;
        if (TextUtils.isEmpty(key)) {
            totalCount = mapper.getAllCount();
            totalPage = totalCount % rowsOfOnePage == 0 ? totalCount / rowsOfOnePage : totalCount / rowsOfOnePage + 1;
            //防止超出总数
            if (curPage > totalPage) {
                curPage = totalPage;
            }
            start = (curPage - 1) * rowsOfOnePage;
            pageBean.list = mapper.getAll(new RowBounds(start, rowsOfOnePage));
        } else {
            totalCount = mapper.getListByKeyCount(key);
            totalPage = totalCount % rowsOfOnePage == 0 ? totalCount / rowsOfOnePage : totalCount / rowsOfOnePage + 1;
            //防止超出总数
            if (curPage > totalPage) {
                curPage = totalPage;
            }
            start = (curPage - 1) * rowsOfOnePage;
            pageBean.list = mapper.getListByKey(key, new RowBounds(start, rowsOfOnePage));
        }
        pageBean.totalPage = totalPage;
        pageBean.curPage = curPage;
        pageBean.totalCount = totalCount;
        pageBean.rows = rowsOfOnePage;
        session.close();
        return pageBean;
    }

    @Override
    public String deletePaymentById(int id) {
        SqlSession session = MyBatisUtils.openSession();
        PaymentMapper mapper = session.getMapper(PaymentMapper.class);
        Payment payment = mapper.getById(id);
        if (payment == null) {
            return "记录不存在!";
        }
        try {
            ContractMapper contractMapper = session.getMapper(ContractMapper.class);
            contractMapper.updatePaySum(payment.getContract().getId(), -payment.getSafeAmount());
            Integer billAmount = payment.getBillAmount();
            if (billAmount != null) {
                contractMapper.updateBillSum(payment.getContract().getId(), -billAmount);
            }
            mapper.deleteById(id);
            session.commit();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
            return "删除失败！";
        } finally {
            session.close();
        }
    }
}
