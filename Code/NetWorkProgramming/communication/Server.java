package NetWorkProgramming.communication;


import java.io.IOException;
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

        // 创建线程池
        ExecutorService es = Executors.newFixedThreadPool(5);

        try {
            ServerSocket server = new ServerSocket(8888);
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


/*
 * 客户端处理的线程
 * */

class UserThread implements Runnable {
    private String name; // 客户端的名字，唯一的。
    private Socket socket;
    private Vector<UserThread> vector;
    private boolean logout;


    private ObjectInputStream ois;
    private ObjectOutputStream oos;

    // 构造器
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


            while (!logout) {
                // 读取消息对象
                Message msg = (Message) ois.readObject();
                // 从消息对象获取消息类型
                int type = msg.getType();
                switch (type) {
                    case MessageType.TYPE_LOGIN:
                        name = msg.getFrom();
                        msg.setInfo("登录成功:");
                        oos.writeObject(msg);
                        break;


                    case MessageType.TYPE_SEND:
                        // 发送给
                        String to = msg.getTo();
                        UserThread ut;

                        //  遍历用户集合
                        int size = vector.size();
                        for (int i = 0; i < size; i++) {
                            ut = vector.get(i);
                            if (ut != this && ut.name.equals(to)) {
                                ut.oos.writeObject(msg);
                                break;
                            }

                        }
                        break;
                }

            }
            oos.close();
            ois.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
