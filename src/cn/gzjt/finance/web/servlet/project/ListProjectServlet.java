package cn.gzjt.finance.web.servlet.project;

import cn.gzjt.finance.business.PagingHelper;
import cn.gzjt.finance.domain.PageBean;
import cn.gzjt.finance.domain.Project;
import cn.gzjt.finance.service.ProjectService;
import cn.gzjt.finance.service.impl.ProjectServiceImpl;
import cn.gzjt.finance.utils.TextUtils;
import cn.gzjt.finance.web.servlet.base.BaseHttpServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 获取所有工程，同时支持模糊查询
 *
 * @author jianwei.zhou
 * @date 2019/10/12 12:55
 */
@WebServlet("/main/listProjectServlet.do")
public class ListProjectServlet extends BaseHttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String key = getString(req, "key");
        int curPage = getInt(req, "curPage", 1);
        int rows = getInt(req, "row", 5);
        System.out.println("ListProjectServlet->post:key" + key + " curPage:" + curPage + " rows:" + rows);
        ProjectService projectService = new ProjectServiceImpl();
        PageBean<Project> pageBean = null;
        if (TextUtils.isEmpty(key)) {
            pageBean = projectService.findProjectByPage(curPage, rows);
        } else {
            pageBean = projectService.findProjectByPage(curPage, rows, key);
            req.setAttribute("key", key);
        }
        String pageParamPref = (TextUtils.isEmpty(key) ? "?row=5&curPage=" : "?key=" + key + "&row=5&curPage=");
        PagingHelper.out(req, pageBean, "/main/listProjectServlet.do", pageParamPref);
        req.getRequestDispatcher("/main/project.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
