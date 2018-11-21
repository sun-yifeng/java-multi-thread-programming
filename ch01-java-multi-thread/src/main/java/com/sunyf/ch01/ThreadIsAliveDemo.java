package com.sunyf.ch01;

/**
 * @program: java-multi-thread-programming
 * @description: Thread类中的方法
 * @author: sunyf
 * @create: 2018-11-21 16:01
 **/
public class ThreadIsAliveDemo {

    //isAlive:判定线程是否为活动的（销毁）
    public class IsAlive extends Thread {

        public IsAlive() {
            System.out.println("1 this.getName()=" + this.getName());
            System.out.println("1 this.isAlive()=" + this.isAlive());
        }

        @Override
        public void run() {
            System.out.println("Thread.currentThread().getName()=" + Thread.currentThread().getName());
            System.out.println("Thread.currentThread().isAlive()=" + Thread.currentThread().isAlive());
            //
            System.out.println("2 this.getName()=" + this.getName());
            System.out.println("2 this.isAlive()=" + this.isAlive());
        }
    }

    public void test() {
        IsAlive is = new IsAlive();
        Thread thread = new Thread(is);
        //
        System.out.println("main begin thread isAlive:" + thread.isAlive());
        thread.setName("A");
        thread.start();
        //
        System.out.println("main end thread isAlive:" + thread.isAlive());

    }

    public static void main(String[] args) {
        ThreadIsAliveDemo threadIsAliveDemo = new ThreadIsAliveDemo();
        threadIsAliveDemo.test();
    }

}
