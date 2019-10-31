package cn.gzjt.finance.web.servlet.project;

import cn.gzjt.finance.domain.JsonResponse;
import cn.gzjt.finance.domain.Project;
import cn.gzjt.finance.domain.User;
import cn.gzjt.finance.service.ProjectService;
import cn.gzjt.finance.service.impl.ProjectServiceImpl;
import cn.gzjt.finance.web.servlet.base.BaseUpdateJsonServlet;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/main/projectServlet.do")
public class UpdateProjectServlet extends BaseUpdateJsonServlet {


    @Override
    public void doAdd(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("ProjectServlet->doProjectAdd");
        Map<String, String[]> map = req.getParameterMap();
        Project project = new Project();
        try {
            BeanUtils.populate(project, map);
        } catch (Exception e) {
            e.printStackTrace();
            new ObjectMapper().writeValue(resp.getWriter(), JsonResponse.obtainParameterError());
            return;
        }
        User currentUsr = (User) req.getSession().getAttribute("usr");
        project.setCreateBy(currentUsr.getId());
        ProjectService service = new ProjectServiceImpl();
        String ret = service.addProject(project);
        JsonResponse.write(ret, resp);
    }

    @Override
    public void doModify(HttpServletRequest req, HttpServletResponse resp) throws IOException {

    }

    @Override
    public void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String number = getString(req, "number");
        ProjectService service = new ProjectServiceImpl();
        String ret = service.deleteProject(number);
        JsonResponse.write(ret, resp);
    }
}
