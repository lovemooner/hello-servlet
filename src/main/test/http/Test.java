package http;

import love.moon.common.HttpResponse;
import love.moon.util.HttpUtil;

import java.io.PrintWriter;
import java.net.Socket;

public class Test {

    public static void main(String[] args) {
//        HttpResponse response= HttpUtil.sendBrowserGet("http://slc11fsp.us.oracle.com:8080/servlet/HelloServlet");
//        System.out.println(response.getContent());

        try {
//            String host = "slc11fsp.us.oracle.com";
            String host = "localhost";
            Socket socket = new Socket(host, 8080);
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            String postData = "imsi=460123&nonce=111111+&deviceid=135&status=1";
            writer.println("POST /servlet/HelloServlet HTTP/1.1\r\n");
            writer.flush();
            writer.println("HOST: "+host+"\r\n");
            writer.println("Content-Type:application/x-www-form-urlencoded\r\n");
            writer.println("Content-Length:" + postData.length());
            writer.println("Connection:Close");
            writer.flush();
            writer.println(postData);
            writer.flush();
            writer.close();
            // 以上只进行了发送操作
            socket.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
