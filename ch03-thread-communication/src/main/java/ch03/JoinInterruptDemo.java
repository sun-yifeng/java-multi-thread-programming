package ch03;

/**
 * @program: java-multi-thread-programming
 * @description: join方法
 *
 * 1、join方法的作用是使用线程对象x执行run方法中的任务，而使当前线程无限期的阻塞，直到x执行完run()方法后面的代码；
 * 2、join方法有使线程排队的作用，类似同步（synchronized）的效果；
 * 3、join与synchronized的区别：join内部使用wait方法(wait内部也是用synchronized)，而synchronized使用的是对象监视器同步；
 *
 * @author: sunyf
 * @create: 2018-11-21 14:39
 **/
public class JoinInterruptDemo extends Thread {

    @Override
    public void run() {
        try {
            // 休眠随机时间的线程体
            int secondValue = (int) (Math.random() * 10000);
            System.out.println("休眠随机数：" + secondValue);
            Thread.sleep(secondValue);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        try {
            // 创建一线程
            JoinInterruptDemo myThread = new JoinInterruptDemo();
            myThread.setName("我要开始休眠了");
            myThread.start();
            // TODO main线程要等到myThread线程执行完
            myThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //
        System.out.println("我想myThread执行完之后，我再执行! 但是myThread要执行多长时间，不知道！");
    }

}

/** out
 休眠随机数：9144
 我想myThread执行完之后，我再执行! 但是myThread要执行多长时间，不知道！
 * */
