package cn.gzjt.finance.web.servlet.contract;

import cn.gzjt.finance.business.PagingHelper;
import cn.gzjt.finance.domain.Contract;
import cn.gzjt.finance.domain.PageBean;
import cn.gzjt.finance.service.ContractService;
import cn.gzjt.finance.service.impl.ContractServiceImpl;
import cn.gzjt.finance.utils.TextUtils;
import cn.gzjt.finance.web.servlet.base.BaseHttpServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author jianwei.zhou
 * @date 2019/10/15 14:52
 */
@WebServlet("/main/listContractServlet.do")
public class ListContractServlet extends BaseHttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String key = req.getParameter("key");
        int curPage = getInt(req, "curPage", 1);
        int rows = getInt(req, "row", 5);
        if (!TextUtils.isEmpty(key)) {
            req.setAttribute("key", key);
        }
        ContractService service = new ContractServiceImpl();
        PageBean<Contract> pageBean = service.getContractsByKey(curPage, rows, key);
        PagingHelper.out(req, pageBean, "/main/listContractServlet.do",
                "?key=" + (key == null ? "" : key) + "&rows=5&curPage=");
        req.getRequestDispatcher("/main/contract.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
