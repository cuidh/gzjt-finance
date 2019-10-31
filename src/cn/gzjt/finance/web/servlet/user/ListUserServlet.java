package cn.gzjt.finance.web.servlet.user;

import cn.gzjt.finance.business.PagingHelper;
import cn.gzjt.finance.domain.PageBean;
import cn.gzjt.finance.domain.User;
import cn.gzjt.finance.service.UserService;
import cn.gzjt.finance.service.impl.UserServiceImpl;
import cn.gzjt.finance.utils.TextUtils;
import cn.gzjt.finance.web.servlet.base.BaseHttpServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author jianwei.zhou
 * @date 2019/10/19 15:06
 */
@WebServlet("/main/listUserServlet.do")
public class ListUserServlet extends BaseHttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String key = getString(req, "key");
        int curPage = getInt(req, "curPage", 1);
        int rows = getInt(req, "row", 5);
        UserService service = new UserServiceImpl();
        PageBean<User> pageBean = service.getListByKey(curPage, rows, key);
        if (TextUtils.isNotEmpty(key)) {
            req.setAttribute("key", key);
        }
        PagingHelper.out(req, pageBean, "/main/listUserServlet.do",
                "?key=" + (key == null ? "" : key) + "&rows=5&curPage=");
        req.getRequestDispatcher("/main/user.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
