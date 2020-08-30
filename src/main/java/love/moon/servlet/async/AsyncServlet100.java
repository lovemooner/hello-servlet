package love.moon.servlet.async;

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
 * @author dongnan
 * @date 2020/8/27 15:41
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
        ctx.setTimeout(10 * 60 * 1000);
        //异步去执行下订单
        ctx.start(() -> {
            // 模拟开通等待
            try {
                int i=0;
                while (++i<20){
                    Thread.sleep(5000);
                    String content="test push i : " + i+ "<br/>";
                    System.out.println("output content:"+content);
                    out.println(content);
                    out.flush();
                }
                out.flush();
                ctx.complete();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        out.println("订购成功: " + new Date() + "<br/>");
        out.flush();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }


}
