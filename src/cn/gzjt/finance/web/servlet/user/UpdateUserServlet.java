package cn.gzjt.finance.web.servlet.user;

import cn.gzjt.finance.domain.Department;
import cn.gzjt.finance.domain.JsonResponse;
import cn.gzjt.finance.domain.User;
import cn.gzjt.finance.service.UserService;
import cn.gzjt.finance.service.impl.UserServiceImpl;
import cn.gzjt.finance.utils.TextUtils;
import cn.gzjt.finance.web.servlet.base.BaseUpdateJsonServlet;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author jianwei.zhou
 * @date 2019/10/19 15:05
 */
@WebServlet("/main/updateUserServlet.do")
public class UpdateUserServlet extends BaseUpdateJsonServlet {

    @Override
    public void doAdd(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String account = getString(req, "account");
        String password = getString(req, "password");
        String realName = getString(req, "realName");
        String dept = getString(req, "dept");
        User user = new User();
        user.setName(account);
        user.setPassword(password);
        user.setRealName(realName);
        Department department = new Department();
        department.setName(dept);
        user.setDepartment(department);
        UserService service = new UserServiceImpl();
        String ret = service.addUser(user);
        JsonResponse<String> jsonResponse;
        if (TextUtils.isEmpty(ret)) {
            jsonResponse = JsonResponse.obtainOk();
        } else {
            jsonResponse = JsonResponse.obtainError(ret);
        }
        new ObjectMapper().writeValue(resp.getWriter(), jsonResponse);
    }

    @Override
    public void doModify(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer id = getInt(req, "id");
        String newPassword = req.getParameter("password");
        UserService service = new UserServiceImpl();
        String ret = service.updatePassword(id, newPassword, getCurUsrId(req));
        JsonResponse.write(ret, resp);
    }

    @Override
    public void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer id = getInt(req, "id");
        UserService service = new UserServiceImpl();
        String ret = service.deleteUser(id, getCurUsrId(req));
        JsonResponse.write(ret, resp);
    }
}
