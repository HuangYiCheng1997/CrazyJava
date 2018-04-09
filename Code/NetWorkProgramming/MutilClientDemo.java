package NetWorkProgramming;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class MutilClientDemo {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        try {
            Socket s = new Socket("localhost", 6666);

            //发送信息到服务器
            PrintStream writer = new PrintStream(
                    new BufferedOutputStream(s.getOutputStream())
            );

            // 获取服务器输入
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(s.getInputStream())
            );

            System.out.println("请输入：");
            String msg = input.nextLine();

            writer.println(msg);
            writer.flush();
            writer.close();

            msg = reader.readLine();
            System.out.println(msg);
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
