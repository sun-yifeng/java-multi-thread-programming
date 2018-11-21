package ch03;

/**
 * @program: java-multi-thread-programming
 * @description: join方法
 * @author: sunyf
 * @create: 2018-11-21 14:39
 **/
public class JoinDemo extends Thread {

    @Override
    public void run() {
        try {
            int secondValue = (int) (Math.random() * 10000);
            System.out.println("休眠随机数：" + secondValue);
            Thread.sleep(secondValue);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        try {

            JoinDemo myThread = new JoinDemo();
            myThread.setName("我要开始休眠了");
            myThread.start();
            // 线程对象执行完
            myThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //
        System.out.println("我想myThread执行完之后，我再执行! 但是myThread要执行多长时间，不知道！");
    }



}
