package http;

import love.moon.common.HttpResponse;
import love.moon.util.HttpUtil;


public class MockClient {

    public static void main(String[] args) {
        String url = "http://localhost:8080/servlet/nonBlockingThreadPoolAsync";
        for (int i = 0; i < 4; i++) {
            new Thread(()->{
                String threadName="Thread "+Thread.currentThread();
                System.out.println(threadName+" start");
               try{
                   HttpResponse response = HttpUtil.sendGet(url);
                   System.out.println(threadName+"==>"+response.getContent());
               }catch (Exception e){
                   System.out.println(threadName+" "+e.getMessage());
               }
            }).start();
        }

       while (Thread.activeCount()>2){
           try {
               Thread.sleep(1000L);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }

    }

}
