package MutiThreadTest;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: WJ
 * \* Date: 2019/3/27
 * \* Time: 16:05
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class Main {
    public static void main(String[] args) {
        Thread2 mTh1=new Thread2("A");
        Thread2 mTh2=new Thread2("B");
       // mTh1.start();
       //mTh2.start();
        new Thread(new Thread3("C")).start();
        new Thread(new Thread3("D")).start();

    }
}
class Thread2 extends Thread{
    private String name;
    public Thread2(String name) {
        this.name=name;
    }
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(name + "运行  :  " + i);
            try {
                sleep((int) Math.random() * 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

class Thread3 implements Runnable{
    private String name;

    public Thread3(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(name + "运行  :  " + i);
            try {
                Thread.sleep((int) Math.random() * 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}