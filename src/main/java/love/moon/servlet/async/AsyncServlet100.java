package love.moon.servlet.async;

import love.moon.servlet.v3.LongRunningProcess;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
/**
 * @author: lovemooner
 * @Date: 2017/5/18 14:09
 */
@WebServlet(value = "/simpleAsync", asyncSupported = true)
public class AsyncServlet100 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println("下订单开始: " + new Date() + "<br/>");
        out.flush();
        AsyncContext ctx = request.startAsync();
        //异步去执行开通订单
        new Thread(new CheckOrder(ctx)).start();
        out.println("订购成功: " + new Date()+ "<br/>");
        out.flush();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }


}