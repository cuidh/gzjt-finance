package cn.gzjt.finance.service.impl;

import cn.gzjt.finance.domain.Contract;
import cn.gzjt.finance.domain.FinancialApproval;
import cn.gzjt.finance.domain.PageBean;
import cn.gzjt.finance.mapper.ContractMapper;
import cn.gzjt.finance.mapper.FinancialApprovalMapper;
import cn.gzjt.finance.service.ApprovalService;
import cn.gzjt.finance.utils.MyBatisUtils;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

/**
 * @author jianwei.zhou
 * @date 2019/10/21 15:22
 */
public class ApprovalServiceImpl implements ApprovalService {
    @Override
    public String insertApproval(FinancialApproval approval) {
        SqlSession session = MyBatisUtils.openSession();
        ContractMapper contractMapper = session.getMapper(ContractMapper.class);
        Contract contract = contractMapper.getByNumber(approval.getContract().getNumber());
        if (contract == null) {
            session.close();
            return "合同不存在！";
        }
        approval.setContract(contract);
        FinancialApprovalMapper mapper = session.getMapper(FinancialApprovalMapper.class);
        try {
            mapper.insert(approval);
            contractMapper.updateApprovalSum(contract.getId(), approval.getAmount());//修改合同审批累计字段
            session.commit();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
            return "添加失败";
        } finally {
            session.close();
        }
    }

    @Override
    public String deleteApproval(int id) {
        SqlSession session = MyBatisUtils.openSession();
        FinancialApprovalMapper mapper = session.getMapper(FinancialApprovalMapper.class);
        FinancialApproval approval = mapper.getById(id);
        if (approval == null) {
            return "记录不存在!";
        }
        try {
            ContractMapper contractMapper = session.getMapper(ContractMapper.class);
            contractMapper.updateApprovalSum(approval.getContract().getId(), -approval.getAmount());
            mapper.delete(id);
            session.commit();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
            return "删除失败";
        } finally {
            session.close();
        }
    }

    @Override
    public PageBean<FinancialApproval> getApprovalList(int curPage, int rows, int contractId) {
        SqlSession session = MyBatisUtils.openSession();
        FinancialApprovalMapper mapper = session.getMapper(FinancialApprovalMapper.class);
        PageBean<FinancialApproval> pageBean = new PageBean<>();
        int totalCount = mapper.getListByContractCount(contractId);
        int totalPage;
        if (totalCount == 0) {
            curPage = 0;
            totalPage = 0;
        } else {
            totalPage = totalCount % rows == 0 ? totalCount / rows : totalCount / rows + 1;
            if (curPage > totalPage) {
                curPage = totalPage;
            }
            int start = (curPage - 1) * rows;
            pageBean.list = mapper.getListByContract(contractId, new RowBounds(start, rows));
        }
        pageBean.totalPage = totalPage;
        pageBean.curPage = curPage;
        pageBean.totalCount = totalCount;
        pageBean.rows = rows;
        session.close();
        return pageBean;
    }
}
