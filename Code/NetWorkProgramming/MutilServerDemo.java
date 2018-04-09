package NetWorkProgramming;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MutilServerDemo {
    public static void main(String[] args) {

        ExecutorService es = Executors.newFixedThreadPool(3);

        try {
            ServerSocket server = new ServerSocket(6666);

            while (true) {
                Socket s = server.accept();
                es.execute(new UserThread(s));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

class UserThread implements Runnable {
    private Socket s;

    public UserThread(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(s.getInputStream())
            );

            PrintStream writer = new PrintStream(
                    new BufferedOutputStream(s.getOutputStream())
            );

            String msg = reader.readLine();
            System.out.println(msg);
            reader.close();

            writer.println(msg);
            writer.flush();
            writer.close();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}