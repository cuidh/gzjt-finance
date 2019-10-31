package cn.gzjt.finance.web.servlet.base;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author jianwei.zhou
 * @date 2019/10/15 15:01
 */
public abstract class BaseUpdateJsonServlet extends BaseHttpServlet {

    private static final String type_add = "1";
    private static final String type_modify = "2";
    private static final String type_delete = "3";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=utf-8");
        String type = req.getParameter("op_type");
        switch (type) {
            case type_add: {
                doAdd(req, resp);
                break;
            }
            case type_modify: {
                doModify(req, resp);
                break;
            }
            case type_delete: {
                doDelete(req, resp);
                break;
            }
            default: {
                System.out.println("UpdateJsonServlet->unexepect type parameter!");
                break;
            }
        }
    }

    public abstract void doAdd(HttpServletRequest req, HttpServletResponse resp) throws IOException;

    public abstract void doModify(HttpServletRequest req, HttpServletResponse resp) throws IOException;

    public abstract void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException;
}
