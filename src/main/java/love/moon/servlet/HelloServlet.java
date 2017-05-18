package love.moon.servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * Author: lovemooner
 * Date: 2017/5/18 14:09
 */
public class HelloServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        System.out.print("Hello Servlet");

        response.getWriter().println("Hello Servlet");
    }
}