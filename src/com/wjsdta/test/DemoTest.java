package com.wjsdta.test;

import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: WJ
 * \* Date: 2019/3/21
 * \* Time: 16:16
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
/*

public class DemoTest {
    public static void main(String[] args) {

        BlockingQueue<String> queue = new LinkedBlockingDeque<String>();
        queue.add("ss");
        Demo1 demo1 = new Demo1(queue);
        //demo1.setInfo("hello");
        Thread thread = new Thread(demo1);
        thread.start();
        */
/*try {
            Thread.sleep(1000);
            demo1.setInfo("world");

            Thread.sleep(3000);
            demo1.setInfo("!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*//*

        Scanner scanner =new Scanner(System.in);
        for (int i = 0; i < 4; i++) {
            String s = scanner.nextLine();
            queue.add(s);
           // System.out.println(queue.size());


        }
    }
}
class Demo1 implements Runnable{
    private static String info;
    String temp;
    String ss;
    private BlockingQueue<String> queue;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Demo1(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        int i =0;
        while(true){
            ss= this.queue.peek();
           // System.out.println(ss+"jkk");
            if(ss!=temp&&ss!=null){
                temp = ss;
                System.out.println(ss);
                */
/*try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*//*

                queue.poll();
            }
           // System.out.println(i++);
            */
/*if(i>=10000){
                break;
            }*//*

        }

    }
}*/
