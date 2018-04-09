package NetWorkProgramming.communication;

import jdk.nashorn.internal.ir.WhileNode;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    public static void main(String[] args) {
        Vector<UserThread> vector = new Vector<>();
        ExecutorService es = Executors.newFixedThreadPool(5);


        try {
            ServerSocket server = new ServerSocket(6666);
            System.out.println("服务器已经启动，正在等待连接....");

            while (true) {
                Socket socket = server.accept();
                UserThread user = new UserThread(socket, vector);
                es.execute(user);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class UserThread implements Runnable {
    private String name; // 客户端的名字，唯一的。
    private Socket socket;
    private Vector<UserThread> vector;
    private boolean logout;


    private ObjectInputStream ois;
    private ObjectOutputStream oos;

    public UserThread(Socket socket, Vector<UserThread> vector) {
        this.socket = socket;
        this.vector = vector;
        vector.add(this);
    }

    @Override
    public void run() {
        try {
            System.out.println("客户端" + socket.getInetAddress().getHostAddress() + "已连接.");
            ois = new ObjectInputStream(socket.getInputStream());
            oos = new ObjectOutputStream(socket.getOutputStream());

            while(!logout)
            {
                // 读取消息类型
                Message msg = (Message) ois.readObject();
                int type = msg.getType();
                switch (type){
                    case MessageType.TYPE_LOGIN:

                        break;


                    case MessageType.TYPE_SEND:
                        break;
                }

            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
