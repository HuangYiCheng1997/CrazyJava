package ThreadDemo;

public class ThreadSynchromized {
    public static void main(String[] args) {
        MyRunnable3 mr3 = new MyRunnable3();
        Thread t1 = new Thread(mr3);
        Thread t2 = new Thread(mr3);
        t1.start();
        t2.start();
        t2.setPriority(7);
    }
}


//使用同步代码快
class MyRunnable3 implements Runnable {
    private int i = 10;

    @Override
    public void run() {
        synchronized (this) {
            while (i > 0) {
                System.out.println(Thread.currentThread().getName() + "--" + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i--;
            }
        }
    }
}