package ThreadDemo;

public class ThreadInterrupt {
    public static void main(String[] args) {
        Thread rt = new Thread(new MyRunnable2());
        rt.start();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        rt.interrupt();
    }

}

class MyRunnable2 implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            if (Thread.currentThread().isInterrupted()) {
                break;
            }
            System.out.println(i);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}