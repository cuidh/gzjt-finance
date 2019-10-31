package cn.gzjt.finance.web.servlet.subproject;

import cn.gzjt.finance.business.PagingHelper;
import cn.gzjt.finance.domain.PageBean;
import cn.gzjt.finance.domain.SubProject;
import cn.gzjt.finance.service.SubProjectService;
import cn.gzjt.finance.service.impl.SubProjectServiceImpl;
import cn.gzjt.finance.web.servlet.base.BaseHttpServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author jianwei.zhou
 * @date 2019/10/14 8:46
 */
@WebServlet("/main/listSubProjectServlet.do")
public class ListSubProjectServlet extends BaseHttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String subKey = req.getParameter("subKey");
        String mainKey = req.getParameter("mainKey");
        int curPage = getInt(req, "curPage", 1);
        int rows = getInt(req, "row", 5);
        System.out.println("ListSubProjectServlet->post:subKey" + subKey + " mainKey:" + mainKey);
        System.out.println("ListSubProjectServlet->post:curPage" + curPage + " rows:" + rows);
        req.setAttribute("subKey", subKey == null ? "" : subKey);
        req.setAttribute("mainKey", mainKey == null ? "" : mainKey);
        SubProjectService service = new SubProjectServiceImpl();
        PageBean<SubProject> pageBean = service.findSubProjectByPage(curPage, rows, subKey, mainKey);
        PagingHelper.out(req, pageBean, "/main/listSubProjectServlet.do",
                "?subKey=" + (subKey == null ? "" : subKey) + "&mainKey=" + (mainKey == null ? "" : mainKey) + "&rows=5&curPage=");
        req.getRequestDispatcher("/main/sub_project.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
