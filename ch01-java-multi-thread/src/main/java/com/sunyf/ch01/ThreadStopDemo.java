package com.sunyf.ch01;

/**
 * @program: java-multi-thread-programming
 * @description: 暴力停止线程stop()
 *
 * 使用stop()释放锁，会带来数据不一致的问题
 *
 * @author: sunyf
 * @create: 2018-11-21 17:00
 **/
public class ThreadStopDemo {

    // 任务
    public class SynchronizedObject {

        private String username = "a";
        private String password = "aa";

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        // 同步方法
        synchronized public void printString(String username, String password) {
            try {
                this.username = username;
                Thread.sleep(100000);
                this.password = password;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // 线程
    public class MyThread extends Thread {
        private SynchronizedObject object;

        public MyThread(SynchronizedObject object) {
            super();
            this.object = object;
        }

        @Override
        public void run() {
            object.printString("b", "bb");
        }
    }

    public void test() {
        try {
            // 同步对象
            SynchronizedObject object = new SynchronizedObject();
            // 新建线程
            MyThread myThread = new MyThread(object);
            // 启动线程
            myThread.start();
            // 主线程休眠
            Thread.sleep(1000);
            // 暴力停止线程
            myThread.stop();
            System.out.println(object.getUsername() + ", " + object.getPassword());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ThreadStopDemo().test();
    }

}
