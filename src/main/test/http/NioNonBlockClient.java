package http;

/**
 * User: lovemooner
 * Date: 17-4-5
 * Time: 下午4:08
 */


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NioNonBlockClient {



    private static Selector selector;

    public static void main(String[] args) throws IOException, InterruptedException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        selector = Selector.open();
        // 注册连接服务端socket动作
        socketChannel.register(selector, SelectionKey.OP_CONNECT);
        InetSocketAddress SERVER_ADDRESS = new InetSocketAddress("slc11fsp.us.oracle.com", 8080);
        socketChannel.connect(SERVER_ADDRESS);
        NioNonBlockClient client = new NioNonBlockClient();
        client.listen();

    }

    /**
     * @param selectionKey
     * @throws IOException
     */
    private void dispatch(SelectionKey selectionKey) throws IOException, InterruptedException {
        if (selectionKey.isConnectable()) {
            System.out.println("client connect");
            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
            if (socketChannel.isConnectionPending()) {
                socketChannel.finishConnect();

            }
            //mock http server
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            buffer.put(getHttpHeaders().getBytes());
            buffer.put(getHttpBody().getBytes());
            buffer.flip();
            socketChannel.write(buffer);
            socketChannel.register(selector, SelectionKey.OP_READ|SelectionKey.OP_WRITE);
        } else if (selectionKey.isReadable()) {
            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
            //将缓冲区清空以备下次读取
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            //读取服务器发送来的数据到缓冲区中
            int count = socketChannel.read(buffer);
            if (count > 0) {
                String receiveText = new String(buffer.array(), 0, count);
                System.out.println("Client->Receive:" + receiveText);
            }

        }else if (selectionKey.isWritable()) {
            System.out.println("isWritable");
        }
    }


    public void listen() throws IOException, InterruptedException {
        while (true) {
            int val = selector.select();
            if (val > 0) {
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    dispatch(selectionKey);
                    iterator.remove();
                }
            }
        }
    }

    String content = "{id:\"1\",name:\"nan\",age:\"18\"}";

    public String getHttpHeaders() {
        StringBuilder sb = new StringBuilder();
        sb.append("GET /servlet/HelloServlet HTTP/1.1").append("\r\n");
        sb.append("cache-control: no-cache").append("\r\n");
        sb.append("Postman-Token: 0814a1e9-ee17-4c6f-9467-57772090d5b5").append("\r\n");
        sb.append("Content-Type: text/plain").append("\r\n");
        sb.append("User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.121 Safari/537.36").append("\r\n");
        sb.append("Accept: */*").append("\r\n");
        sb.append("Host: slc11fsp.us.oracle.com:8080").append("\r\n");
        sb.append("accept-encoding: gzip, deflate").append("\r\n");
        sb.append("content-length: ").append(content.length()).append("\r\n");
        sb.append("Connection: keep-alive");
        sb.append("\r\n");
        return sb.toString();

    }


    public String getHttpBody() {
        StringBuilder sb = new StringBuilder();
        sb.append(content);
        return sb.toString();

    }


}
