package ch03;

/**
 * @program: java-multi-thread-programming
 * @description: 等待通知的经典栗子：生产者消费者模式
 * @author: sunyf
 * @create: 2018-11-24 21:30
 **/
public class ProduceConsumePattern {

    public static String value = "";

    // 生产者（任务）
    public class Producer {
        private String lock;

        public Producer(String lock) {
            super();
            this.lock = lock;
        }

        public void setValue() {
            try {
                synchronized (lock) {
                    if (!value.equals("")) {
                        lock.wait();
                    }
                    value = System.currentTimeMillis() + "_" + System.nanoTime();
                    System.out.println("生产的值为：" + value);
                    lock.notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // 消费者（任务）
    public class Customer {
        private String lock ;

        public Customer(String lock) {
            super();
            this.lock = lock;
        }

        public void getValue() {
            try {
                synchronized (lock) {
                    if (value.equals("")) {
                        lock.wait();
                    }
                    System.out.println("消费的值为：" + value);
                    value = "";
                    lock.notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // 生产者（线程）
    public class ThreadP extends Thread {
        private Producer producer;

        public ThreadP(Producer producer) {
            super();
            this.producer = producer;
        }

        @Override
        public void run() {
            try {
                // TODO
                while (true) {
                    Thread.sleep(2000);
                    producer.setValue();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    // 消费者（线程）
    public class ThreadC extends Thread {
        private Customer customer;

        public ThreadC(Customer customer) {
            super();
            this.customer = customer;
        }

        @Override
        public void run() {
            try {
                // TODO
                while (true) {
                    Thread.sleep(2000);
                    customer.getValue();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void test() {
        String lock = new String("");
        // 任务
        Producer producer = new Producer(lock);
        Customer customer = new Customer(lock);
        // 线程
        ThreadP threadP = new ThreadP(producer);
        ThreadC threadC = new ThreadC(customer);
        // 启动
        threadP.start();
        threadC.start();
    }

    public static void main(String[] args) {
        new ProduceConsumePattern().test();
    }


}
