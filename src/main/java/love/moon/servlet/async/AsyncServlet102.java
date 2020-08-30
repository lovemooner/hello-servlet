package love.moon.servlet.async;

import javax.servlet.AsyncContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author: lovemooner
 * @Date: 2017/5/18 14:09
 */
@WebServlet(value = "/threadPoolAsync", asyncSupported = true)
public class AsyncServlet102 extends HttpServlet {

    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(100, 200,
            50000L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(100));

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

        AsyncContext asyncContext = request.startAsync();
        executor.execute(() -> {
            new LongRunningProcess().run();
            try {
                asyncContext.getResponse().getWriter().write("Hello World!");
            } catch (IOException e) {
                e.printStackTrace();
            }
            asyncContext.complete();
        });
    }


    public class LongRunningProcess {

        public void run() {
            try {
                int millis = ThreadLocalRandom.current().nextInt(2000);
                String currentThread = Thread.currentThread().getName();
                System.out.println(currentThread + " sleep for " + millis + " milliseconds.");
                Thread.sleep(millis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
