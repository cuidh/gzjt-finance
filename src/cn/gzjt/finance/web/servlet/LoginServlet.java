package cn.gzjt.finance.web.servlet;

import cn.gzjt.finance.domain.User;
import cn.gzjt.finance.service.LoginRegisterService;
import cn.gzjt.finance.service.impl.LoginRegisterServiceImpl;
import javafx.util.Pair;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/loginServlet.do")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String usrName = request.getParameter("usr_name");
        String usrKey = request.getParameter("usr_key");
        System.out.println("LoginServlet doPost()->" + usrName + "," + usrKey);
        LoginRegisterService service = new LoginRegisterServiceImpl();
        Pair<String, User> result = service.login(usrName, usrKey);
        if (null != result.getKey()) {
            request.setAttribute("error_info", result.getKey());
            request.getRequestDispatcher("/login.jsp").forward(request, response);//转发路径：  /资源名称
        } else {
            request.getSession().setAttribute("usr", result.getValue());
//            request.getRequestDispatcher("/listProjectServlet.do").forward(request, response);
            response.sendRedirect(request.getContextPath() + "/main/listProjectServlet.do");//转发路径： 项目名称/资源名称
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
