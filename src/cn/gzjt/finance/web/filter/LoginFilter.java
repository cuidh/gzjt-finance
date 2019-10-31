package cn.gzjt.finance.web.filter;

import cn.gzjt.finance.domain.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String uri = request.getRequestURI();
        if (uri.contains("/js/") || uri.contains("/css/") || uri.contains("/img/")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        User user = (User) request.getSession().getAttribute("usr");
        boolean isVisitLoginPage = uri.endsWith("/login.jsp") || uri.endsWith("/loginServlet.do");
        if (null == user && !isVisitLoginPage) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        } else if (null != user && isVisitLoginPage) {
            //            已登录, 访问登录页直接跳转主页
            response.sendRedirect(request.getContextPath() + "/listProjectServlet.do");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
    }
}
