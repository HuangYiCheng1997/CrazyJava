package NetWorkProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServerDemo {
    public static void main(String[] args) {
        // 创建socket对象
        try {
            ServerSocket server = new ServerSocket(6666);
            System.out.println("服务器已启动，正在等待客户端连接。。。。");

            // 阻塞线程直到接收到客户端连接
            Socket socket = server.accept();
            System.out.println("客户端连接成功："+server.getInetAddress().getHostAddress());

            //通过流读取客户端内容，如果没有内容也会造成阻塞
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );
            String msg = br.readLine();
            System.out.println(msg);

            //返回信息给客户端
            PrintStream ps = new PrintStream(socket.getOutputStream());
            ps.println("Hello client");
            ps.flush();

            br.close();
            ps.close();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
