package com.wjsdta.test;

import java.util.Scanner;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: WJ
 * \* Date: 2019/3/21
 * \* Time: 19:23
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class Demo1 {

    public static void main(String[] args) {

        Demo2 demo2 = new Demo2();
       // demo2.setInfo("hello");
        Thread thread = new Thread(demo2);
        thread.start();

        Scanner scanner =new Scanner(System.in);
        for (int i = 0; ; i++) {
            String s = scanner.nextLine();
            demo2.setInfo(s);
            System.out.println(demo2.getInfo());

        }
    }
}
class Demo2 implements Runnable{
    private static String info;
    String temp;

    @Override
    public void run() {
        int i =0;
        while(true){
            //if((!info.equals(temp))&&info!=null){
            if((info!=(temp))&&info!=null){
                temp = info;
                System.out.println("thread:"+info);
            }
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

}