package ThreadDemo;

public class ProducterConsumer {
    public static void main(String[] args) {
        Food food = new Food();
        Thread pt = new Thread(new Producter(food));
        Thread ct = new Thread(new Consumer(food));
        pt.start();
        ct.start();
    }
}

class Food {
    private String name;
    private String desc;
    public boolean isReady = false;

    public synchronized void set(String name, String desc) {
        // 判断
        if (this.isReady) {
            try {
                System.out.println("别急呢，食物还没吃完");
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 设值
        this.name = name;
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.desc = desc;
        // 修改
        this.isReady = true;
        this.notify();
    }

    public synchronized void get() {
        // 判断
        if (!this.isReady) {
            try {
                System.out.println("别急呢，食物还没做好呢");
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 输出
        System.out.println(this.name + "--" + this.desc);
        // 设值
        this.isReady = false;
        this.notify();
    }


}

class Producter implements Runnable {
    private Food food;
    private String name;
    private String desc;
    public Producter(Food food) {
        this.food = food;
    }

    @Override
    public void run() {

        for (int i = 0; i < 10; i++) {
            name = i % 2 == 0 ? "叉烧" : "饼干";
            desc = i % 2 == 0 ? "美味可口" : "充饥神器";
            this.food.set(name, desc);
        }


    }
}

class Consumer implements Runnable {
    private Food food;

    public Consumer(Food food) {
        this.food = food;
    }

    @Override
    public void run() {

        for (int i = 0; i < 10; i++) {
            this.food.get();
        }

    }
}