package cn.gzjt.finance.web.servlet.subproject;

import cn.gzjt.finance.domain.JsonResponse;
import cn.gzjt.finance.domain.User;
import cn.gzjt.finance.service.SubProjectService;
import cn.gzjt.finance.service.impl.SubProjectServiceImpl;
import cn.gzjt.finance.web.servlet.base.BaseUpdateJsonServlet;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author jianwei.zhou
 * @date 2019/10/14 8:46
 */
@WebServlet("/main/updateSubProjectServlet.do")
public class UpdateSubProjectServlet extends BaseUpdateJsonServlet {

    @Override
    public void doAdd(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String number = req.getParameter("number");
        String name = req.getParameter("name");
        String mainProject = req.getParameter("mainProject");
        String note = req.getParameter("note");
        User currentUsr = (User) req.getSession().getAttribute("usr");

        SubProjectService service = new SubProjectServiceImpl();
        String ret = service.addSubProject(number, name, mainProject, note, currentUsr.getId());
        JsonResponse<String> json = ret == null ? JsonResponse.obtainOk() : JsonResponse.obtainError(ret);
        new ObjectMapper().writeValue(resp.getWriter(), json);
    }

    @Override
    public void doModify(HttpServletRequest req, HttpServletResponse resp) throws IOException {

    }

    @Override
    public void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String number = req.getParameter("number");
        SubProjectService service = new SubProjectServiceImpl();
        String ret = service.deleteSubProject(number);
        JsonResponse.write(ret, resp);
    }

}
