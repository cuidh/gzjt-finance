package cn.gzjt.finance.service;

import cn.gzjt.finance.domain.FinancialApproval;
import cn.gzjt.finance.domain.PageBean;

/**
 * @author jianwei.zhou
 * @date 2019/10/21 15:13
 */
public interface ApprovalService {

    String insertApproval(FinancialApproval approval);

    String deleteApproval(int id);

    PageBean<FinancialApproval> getApprovalList(int curPage, int rows, int contractId);
}
