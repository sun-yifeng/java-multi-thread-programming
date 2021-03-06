package com.sunyf.ch02;

/**
 * @program: java-multi-thread-programming
 * @description: 多个锁
 * @author: sunyf
 * @create: 2018-11-21 17:37
 **/
public class MultiLockDemo {

    // 任务
    public class PrivateNum {
        // 共享变量
        private volatile int num = 0;
        // 同步的方法
        synchronized public void addInt(String username) {
            try {
                if ("a".equals(username)) {
                    num = 100;
                    System.out.println("a set over!");
                    Thread.sleep(2000);
                } else {
                    num = 200;
                    System.out.println("b set over!");
                }
                // 打印结果(出现不同步的情况)
                System.out.println(username + " num = " + num);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // 线程A
    public class ThreadA extends Thread {
        private PrivateNum privateNum;

        public ThreadA(PrivateNum privateNum) {
            super();
            this.privateNum = privateNum;
        }

        @Override
        public void run() {
            super.run();
            privateNum.addInt("a");
        }
    }

    // 线程B
    public class ThreadB extends Thread {
        private PrivateNum privateNum;

        public ThreadB(PrivateNum privateNum) {
            super();
            this.privateNum = privateNum;
        }

        @Override
        public void run() {
            super.run();
            privateNum.addInt("b");
        }
    }

    public void test() {
        PrivateNum privateNum1 = new PrivateNum();
        PrivateNum privateNum2 = new PrivateNum();
        //
        ThreadA threadA = new ThreadA(privateNum1);
        ThreadB threadB = new ThreadB(privateNum2);
        //
        threadA.start();
        threadB.start();
    }


    public static void main(String[] args) {
        new MultiLockDemo().test();
    }

}

/*** out
 a set over!
 b set over!
 b num = 200
 a num = 100
 */
