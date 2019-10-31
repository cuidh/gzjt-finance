package cn.gzjt.finance.service.impl;

import cn.gzjt.finance.domain.Contract;
import cn.gzjt.finance.domain.OrgEntity;
import cn.gzjt.finance.domain.PageBean;
import cn.gzjt.finance.domain.SubProject;
import cn.gzjt.finance.mapper.*;
import cn.gzjt.finance.service.ContractService;
import cn.gzjt.finance.utils.MyBatisUtils;
import cn.gzjt.finance.utils.TextUtils;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

/**
 * @author jianwei.zhou
 * @date 2019/10/15 14:49
 */
public class ContractServiceImpl implements ContractService {
    @Override
    public String addContract(Contract contract, String partyA, String partyB, String partyBCode, String projectKey) {
        SqlSession session = MyBatisUtils.openSession();
        ContractMapper mapper = session.getMapper(ContractMapper.class);
        Contract oldContract = mapper.getByNumber(contract.getNumber());
        if (oldContract != null) {
            return "合同编号已存在！";
        }
        //检查关联项目
        SubProjectMapper subProjectMapper = session.getMapper(SubProjectMapper.class);
        SubProject project = subProjectMapper.getByNumber(projectKey);
        if (project == null) {
            project = subProjectMapper.getByName(projectKey);
            if (project == null) {
                return "所属项目不存在！";
            }
        }
        contract.setProject(project);
        //关联合同组织实体
        OrgEntityMapper orgEntityMapper = session.getMapper(OrgEntityMapper.class);
        OrgEntity orgA = orgEntityMapper.getByName(partyA);
        OrgEntity orgB = orgEntityMapper.getByName(partyB);
        try {
            if (orgA == null) {
                orgA = new OrgEntity();
                orgA.setName(partyA);
                orgA.setInternal(true);
                orgA.setCreateBy(contract.getCreateBy());
                orgEntityMapper.insert(orgA);
            }
            if (orgB == null) {
                orgB = new OrgEntity();
                orgB.setName(partyB);
                orgB.setCreditCode(partyBCode);
                orgB.setInternal(false);
                orgB.setCreateBy(contract.getCreateBy());
                orgEntityMapper.insert(orgB);
            }
            contract.setPartyA(orgA);
            contract.setPartyB(orgB);
            mapper.insert(contract);
            session.commit();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
            return "创建失败！";
        } finally {
            session.close();
        }
    }

    @Override
    public PageBean<Contract> getContractsByKey(int curPage, int rowsOfOnePage, String key) {
        SqlSession session = MyBatisUtils.openSession();
        ContractMapper mapper = session.getMapper(ContractMapper.class);
        PageBean<Contract> pageBean = new PageBean<>();
        int totalCount;
        int totalPage;
        int start;
        if (TextUtils.isEmpty(key)) {
            totalCount = mapper.getAllContractCount();
            totalPage = totalCount % rowsOfOnePage == 0 ? totalCount / rowsOfOnePage : totalCount / rowsOfOnePage + 1;
            //防止超出总数
            if (curPage > totalPage) {
                curPage = totalPage;
            }
            start = (curPage - 1) * rowsOfOnePage;
            pageBean.list = mapper.getAllContract(new RowBounds(start, rowsOfOnePage));
        } else {
            totalCount = mapper.getByLikeNumberOrProjectCount(key);
            totalPage = totalCount % rowsOfOnePage == 0 ? totalCount / rowsOfOnePage : totalCount / rowsOfOnePage + 1;
            //防止超出总数
            if (curPage > totalPage) {
                curPage = totalPage;
            }
            start = (curPage - 1) * rowsOfOnePage;
            pageBean.list = mapper.getByLikeNumberOrProject(key, new RowBounds(start, rowsOfOnePage));
        }
        pageBean.totalPage = totalPage;
        pageBean.curPage = curPage;
        pageBean.totalCount = totalCount;
        pageBean.rows = rowsOfOnePage;
        session.close();
        for (Contract contract : pageBean.list) {
            contract.existWarning();
        }
        return pageBean;
    }

    @Override
    public String updateContract(Contract contract, String partyA, String partyB, String partyBCode, String projectKey) {
        SqlSession session = MyBatisUtils.openSession();
        ContractMapper mapper = session.getMapper(ContractMapper.class);
        Contract oldContract = mapper.getByNumber(contract.getNumber());
        if (oldContract == null) {
            return "合同编号不存在！";
        }
        //检查关联项目
        SubProjectMapper subProjectMapper = session.getMapper(SubProjectMapper.class);
        SubProject project = subProjectMapper.getByNumber(projectKey);
        if (project == null) {
            project = subProjectMapper.getByName(projectKey);
            if (project == null) {
                return "所属项目不存在！";
            }
        }
        contract.setProject(project);
        //关联合同组织实体
        OrgEntityMapper orgEntityMapper = session.getMapper(OrgEntityMapper.class);
        OrgEntity orgA = orgEntityMapper.getByName(partyA);
        OrgEntity orgB = orgEntityMapper.getByName(partyB);
        try {
            if (orgA == null) {
                orgA = new OrgEntity();
                orgA.setName(partyA);
                orgA.setInternal(true);
                orgA.setCreateBy(contract.getCreateBy());
                orgEntityMapper.insert(orgA);
            }
            if (orgB == null) {
                orgB = new OrgEntity();
                orgB.setName(partyB);
                orgB.setCreditCode(partyBCode);
                orgB.setInternal(false);
                orgB.setCreateBy(contract.getCreateBy());
                orgEntityMapper.insert(orgB);
            }
            contract.setPartyA(orgA);
            contract.setPartyB(orgB);
            mapper.update(contract);
            session.commit();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
            return "创建失败！";
        } finally {
            session.close();
        }
    }

    @Override
    public String deleteContractByNumber(String number) {
        SqlSession session = MyBatisUtils.openSession();
        ContractMapper contractMapper = session.getMapper(ContractMapper.class);
        Contract byNumber = contractMapper.getByNumber(number);
        if (byNumber == null) {
            return "合同不存在，无法删除！";
        }
        PaymentMapper paymentMapper = session.getMapper(PaymentMapper.class);
        int paymentCount = paymentMapper.getListByKeyCount(number);
        if (paymentCount > 0) {
            return "存在关联支付记录，无法删除！";
        }
        FinancialApprovalMapper approvalMapper = session.getMapper(FinancialApprovalMapper.class);
        int approvalCount = approvalMapper.getListByContractCount(byNumber.getId());
        if (approvalCount > 0) {
            return "存在关联审批记录，无法删除！";
        }
        try {
            contractMapper.delete(byNumber.getId());
            session.commit();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
            return "删除失败!";
        } finally {
            session.close();
        }
    }
}
