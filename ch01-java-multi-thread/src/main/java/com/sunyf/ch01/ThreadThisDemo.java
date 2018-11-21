package com.sunyf.ch01;

/**
 * @program: java-multi-thread-programming
 * @description: this和Thread.currentThread()
 *
 * this指向的还是new ThreadThisDemo()创建的那个线程实例，而不是new Thread(thread)创建的那个实例即t1。
 *
 * @author: sunyf
 * @create: 2018-11-21 16:24
 **/
public class ThreadThisDemo extends Thread {

    public ThreadThisDemo() {
        System.out.println("----构造方法开始----");
        System.out.println("由Thread.currentThread()得到线程名称:" + Thread.currentThread().getName());
        System.out.println("由this  得到线程名称:" +   this.getName());
        System.out.println("----构造方法结束----");
    }

    @Override
    public void run(){
        System.out.println("----run方法开始----");
        System.out.println("由Thread.currentThread()得到线程名称:" + Thread.currentThread().getName());
        System.out.println("由this  得到线程名称:" +   this.getName());
        System.out.println("----run方法结束----");
    }

    public static void main(String[] args) {
        // main线程调用构造方法
        ThreadThisDemo myThread = new ThreadThisDemo();
        // 线程A作为参数调用run()方法
        Thread thread = new Thread(myThread);
        thread.setName("A");
        thread.start();
    }
}

/*** out
 ----构造方法开始----
 由Thread.currentThread()得到线程名称:main
 由this  得到线程名称:Thread-0
 ----构造方法结束----
 ----run方法开始----
 由Thread.currentThread()得到线程名称:A
 由this  得到线程名称:Thread-0
 ----run方法结束----
 */