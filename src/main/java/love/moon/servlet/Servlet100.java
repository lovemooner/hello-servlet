package love.moon.servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * @author: lovemooner
 * @Date: 2017/5/18 14:09
 */
public class Servlet100 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws IOException {
        System.out.println("coming request");
        BufferedReader br = request.getReader();
        String str, wholeStr = "";
        while ((str = br.readLine()) != null) {
            wholeStr += str;
        }
        System.out.println(wholeStr);

        String output = "Hello Servlet";
        response.getWriter().println(output);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}