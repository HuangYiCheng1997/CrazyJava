package NetWorkProgramming.communication;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Clinet {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        final ExecutorService es = Executors.newSingleThreadExecutor();

        try {

            Socket socket = new Socket("localhost", 8888);
            System.out.println("成功连接服务器");

            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

            // 登录
            System.out.println("请输入用户名:");
            String name = input.nextLine();
            Message msg = new Message(name, null, MessageType.TYPE_LOGIN, null);
            oos.writeObject(msg);

            // 读取服务器返回的信息
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            msg = (Message) ois.readObject();
            System.out.println(msg.getInfo() + msg.getFrom());

            //启动读取消息的线程
            es.execute(new ReadInfoThread(ois));

            // 发送消息
            boolean flag = true;
            while (flag) {
                msg = new Message();
                msg.setFrom(name);
                System.out.println("To:");
                msg.setTo(input.nextLine());
                msg.setType(MessageType.TYPE_SEND);
                System.out.println("Info:");
                msg.setInfo(input.nextLine());
                oos.writeObject(msg);

            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}

class ReadInfoThread implements Runnable {
    private ObjectInputStream ois;
    private boolean flag = true;

    public ReadInfoThread(ObjectInputStream ois) {
        this.ois = ois;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }


    @Override
    public void run() {
        try {
            while (flag) {
                Message msg = (Message) ois.readObject();
                System.out.println("[From " + msg.getFrom() + "]: " + msg.getInfo());
            }
            if (ois != null) {
                ois.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}


