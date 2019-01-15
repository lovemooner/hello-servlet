package love.moon.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SecurityFilter implements Filter {

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //session test
//        HttpSession session = request.getSession(false);
//        if (session == null) { //第一次请求没有session
//            response.sendRedirect(request.getContextPath() + "/login.jsp");
//        }else {
//            session.setAttribute("key1", "test1");
//        }
        chain.doFilter(servletRequest, response);
    }


    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void destroy() {

    }
}
