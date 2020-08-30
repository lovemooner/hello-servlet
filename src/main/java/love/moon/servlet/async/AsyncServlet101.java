package love.moon.servlet.async;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author: lovemooner
 * @Date: 2017/5/18 14:09
 */
@WebServlet(value = "/asyncSample2", asyncSupported = true)
public class AsyncServlet101 extends HttpServlet {

    private List<AsyncContext> contexts = new LinkedList<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println("下订单开始: " + new Date() + "<br/>");
        out.flush();
        AsyncContext ctx = request.startAsync();
        //add listener
        ctx.addListener(new AsyncListener() {
            @Override
            public void onTimeout(AsyncEvent arg0) throws IOException {
                System.out.println("onTimeout...");
            }
            @Override
            public void onStartAsync(AsyncEvent arg0) throws IOException {
                System.out.println("onStartAsync...");
            }
            @Override
            public void onError(AsyncEvent arg0) throws IOException {
                System.out.println("onError...");
            }
            @Override
            public void onComplete(AsyncEvent arg0) throws IOException {
                System.out.println("onComplete...");
            }
        });
        //异步去执行开通订单
        new Thread(new CheckOrder(ctx)).start();
        out.println("订购成功: " + new Date()+ "<br/>");
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }


    public class CheckOrder implements Runnable {
        private AsyncContext ctx = null;

        public CheckOrder(AsyncContext ctx) {
            this.ctx = ctx;
        }

        @Override
        public void run() {
            try {
                // 模拟开通等待
                Thread.sleep(3000);
                PrintWriter out = ctx.getResponse().getWriter();
                out.println("已经有权限了，let's go! : " + new Date());
                out.flush();
                ctx.complete();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}