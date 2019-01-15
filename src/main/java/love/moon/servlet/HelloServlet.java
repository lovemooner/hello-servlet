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
                         HttpServletResponse response) throws IOException {
        String output="Hello Servlet";
        //Session Test
        if(request.getSession().getAttribute("count") == null){
            request.getSession().setAttribute("count", 0);
            response.getWriter().write(output+0);
        }else{
            int a = Integer.parseInt(request.getSession().getAttribute("count").toString());
            request.getSession().setAttribute(output+",count", ++a);
            response.getWriter().write(a+"");
        }


        response.getWriter().println(output);
    }
}