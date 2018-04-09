package NetWorkProgramming;

import java.io.*;
import java.net.Socket;

public class EchoClientDemo {
    public static void main(String[] args) {
        try {
            Socket client = new Socket("localhost", 6666);
            //发送信息到服务器
            PrintStream ps = new PrintStream(
                    new BufferedOutputStream(client.getOutputStream())
            );
            // 获取服务器输入
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(client.getInputStream())
            );

            ps.println("hello server");
            ps.flush();


            String msg = br.readLine();
            System.out.println(msg);
            ps.close();
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
