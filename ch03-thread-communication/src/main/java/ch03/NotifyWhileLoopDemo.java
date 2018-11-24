package ch03;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: java-multi-thread-programming
 * @description: 多线程通讯
 *
 * 1、两个线程分别执行任务（Mylist）
 * 2、线程B只有通过轮询记住来检查某个条件，浪费CPU资源；
 *
 * @author: sunyf
 * @create: 2018-11-23 14:42
 **/
public class NotifyWhileLoopDemo {

    //
    public class Mylist {
        private List list = new ArrayList<String>();

        public void add() {
            list.add("sunyf");
        }

        public int size() {
            return list.size();
        }
    }

    //
    public class ThreadA extends Thread {
        private Mylist mylist;

        public ThreadA(Mylist mylist) {
            super();
            this.mylist = mylist;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < 10; i++) {
                    mylist.add();
                    System.out.println("添加了" + (i + 1) + "个元素");
                    Thread.sleep(2000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //
    public class ThreadB extends Thread {
        private Mylist mylist;

        public ThreadB(Mylist mylist) {
            super();
            this.mylist = mylist;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    if (mylist.size() == 5) {
                        System.out.println("线程B要退出了");
                        throw new InterruptedException();
                    }
                    Thread.sleep(1000);
                    System.out.println("list's size:" + mylist.size());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void test() {
        Mylist mylist = new Mylist();
        //
        ThreadA threadA = new ThreadA(mylist);
        threadA.setName("线程A");
        threadA.start();
        //
        ThreadB threadB = new ThreadB(mylist);
        threadB.setName("线程B");
        threadB.start();
    }

    public static void main(String[] args) {
        new NotifyWhileLoopDemo().test();
    }


}
