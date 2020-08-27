package love.moon.servlet.async;

import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.AsyncContext;

/**
 * @author dongnan
 * @date 2020/8/27 14:59
 */
public class CheckOrder implements Runnable {
    private AsyncContext ctx = null;

    public CheckOrder(AsyncContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public void run() {
        try {
            // 模拟开通等待
            Thread.sleep(5000);
            PrintWriter out = ctx.getResponse().getWriter();
            out.println("已经有权限了，let's go! : " + new Date());
            out.flush();
            ctx.complete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
