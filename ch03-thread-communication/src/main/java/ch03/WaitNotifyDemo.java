package ch03;

/**
 * @program: java-multi-thread-programming
 * @description: 等待通知机制
 * @author: sunyf
 * @create: 2018-11-24 18:38
 **/
public class WaitNotifyDemo {

    // 线程A
    public class MyThread1 extends Thread {
        private Object lock; // 对象锁

        public MyThread1(Object lock) {
            super();
            this.lock = lock;
        }

        @Override
        public void run() {
            try {
                synchronized (lock) { // TODO 对象锁
                    System.out.println("开始wait,time:" + System.currentTimeMillis());
                    lock.wait(); // 等待
                    System.out.println("结束wait,time:" + System.currentTimeMillis());

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public class MyThread2 extends Thread {
        private Object lock;

        public MyThread2(Object lock) {
            super();
            this.lock = lock;
        }

        @Override
        public void run() {
            synchronized (lock) { // TODO 对象锁
                System.out.println("开始notify,time:" + System.currentTimeMillis());
                lock.notify();
                System.out.println("要等到notify()方法内的代码完成之后，就是退出synchronized代码块之后，当前线程才释放");
                System.out.println("结束notify,time:" + System.currentTimeMillis());
            }
        }
    }

    public void test() {
        try {
            Object lock = new Object();
            MyThread1 myThread1 = new MyThread1(lock);
            myThread1.start();
            // 休眠
            Thread.sleep(3000);
            MyThread2 myThread2 = new MyThread2(lock);
            myThread2.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new WaitNotifyDemo().test();
    }


}
