package cn.gzjt.finance.web.servlet.approval;

import cn.gzjt.finance.business.PagingHelper;
import cn.gzjt.finance.domain.FinancialApproval;
import cn.gzjt.finance.domain.PageBean;
import cn.gzjt.finance.service.ApprovalService;
import cn.gzjt.finance.service.impl.ApprovalServiceImpl;
import cn.gzjt.finance.web.servlet.base.BaseHttpServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author jianwei.zhou
 * @date 2019/10/21 10:47
 */
@WebServlet("/main/listApprovalServlet.do")
public class ListApprovalServlet extends BaseHttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int contractId = Integer.parseInt(req.getParameter("contractId"));
        int curPage = getInt(req, "curPage", 1);
        int rows = getInt(req, "row", 5);
        ApprovalService service = new ApprovalServiceImpl();
        PageBean<FinancialApproval> pageBean = service.getApprovalList(curPage, rows, contractId);
        PagingHelper.out(req, pageBean, "/main/listApprovalServlet.do",
                "?contractId=" + contractId + "&rows=5&curPage=");
        req.getRequestDispatcher("/main/financial_approval.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
