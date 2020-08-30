package love.moon.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author: lovemooner
 * @Date: 2017/5/18 14:09
 */
@WebServlet(value = "/servlet101")
public class Servlet101 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws IOException {
        System.out.println("coming request");
        PrintWriter out = response.getWriter();

        int i=0;
        while (++i< 20){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String content="test push i : " + i+ "<br/>";
            System.out.println("output content:"+content);
            out.println(content);
            out.flush();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}