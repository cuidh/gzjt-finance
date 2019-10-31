package cn.gzjt.finance.web.servlet.base;

import cn.gzjt.finance.domain.User;
import cn.gzjt.finance.utils.TextUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

/**
 * @author jianwei.zhou
 * @date 2019/10/18 9:46
 */
public class BaseHttpServlet extends HttpServlet {

    protected int getCurUsrId(HttpServletRequest req) {
        return (((User) req.getSession().getAttribute("usr"))).getId();
    }

    protected User getCurUsr(HttpServletRequest req) {
        return (User) req.getSession().getAttribute("usr");
    }

    protected String getString(HttpServletRequest req, String name) {
        return getString(req, name, null);
    }

    protected Integer getInt(HttpServletRequest req, String name) {
        return getInt(req, name, null);
    }

    protected String getString(HttpServletRequest req, String name, String defaultValue) {
        String value = req.getParameter(name);
        return TextUtils.isEmpty(value) ? defaultValue : value;
    }

    protected Integer getInt(HttpServletRequest req, String name, Integer defaultValue) {
        Integer value = defaultValue;
        try {
            value = Integer.valueOf(req.getParameter(name));
        } catch (NumberFormatException e) {
//            e.printStackTrace();
        }
        return value;
    }
}
