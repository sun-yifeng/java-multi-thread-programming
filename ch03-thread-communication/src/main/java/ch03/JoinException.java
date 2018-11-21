package ch03;

/**
 * @program: java-multi-thread-programming
 * @description: join遇到interrupt的异常
 *
 * 1、join遇到interrupt，则会出现异常；
 * 2、线程B等待（join）线程A执行完，线程C通知线程B中断；
 * 3、因为线程B在等待线程A，线程B抛出中断异常，但是不停止执行
 *
 * @author: sunyf
 * @create: 2018-11-21 15:06
 **/
public class JoinException  {

    // 线程A：长任务
    public class ThreadA extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                String str = new String();
                Math.random();
            }
            System.out.println("线程A执行完成！");
        }
    }

    // 线程B
    public class ThreadB extends Thread {
        @Override
        public void run() {
            try {
                ThreadA threadA = new ThreadA();
                threadA.start();
                // 线程B排队（等待），等待线程A执行完
                threadA.join();
                //
                System.out.println("线程B执行完成！");
            } catch (InterruptedException e) {
                // TODO 抛出中断异常，线程B停止执行，线程A继续执行
                System.out.println("线程B抛出中断异常");
                Thread.interrupted();
                e.printStackTrace();
            }
        }
    }

    // 线程C
    public class ThreadC extends Thread {
        private ThreadB threadB;

        // 构造方法
        public ThreadC(ThreadB threadB) {
            super();
            this.threadB = threadB;
        }

        @Override
        public void run() {
            // TODO 线程C通知线程B中断
            threadB.interrupt();
            System.out.println("线程C执行完成");
        }
    }

    public void test() {
        try {
            // 新建线程B
            ThreadB threadB = new ThreadB();
            // 启动线程B
            threadB.start();
            // 休眠
            Thread.sleep(500);
            // 新建线程C
            ThreadC threadC = new ThreadC(threadB);
            // 启动线程C
            threadC.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new JoinException().test();
    }


}

/** out
 线程B抛出中断异常
 java.lang.InterruptedException
 at java.lang.Object.wait(Native Method)
 at java.lang.Thread.join(Thread.java:1245)
 at java.lang.Thread.join(Thread.java:1319)
 at ch03.JoinException$ThreadB.run(JoinException.java:39)
 线程A执行完成！
 */
