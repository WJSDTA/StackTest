package com.wjsdta;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: WJ
 * \* Date: 2019/3/21
 * \* Time: 14:54
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class BlockingQueueExample {
    public static void main(String[] args) {
        MessageDemo messageDemo;
        BlockingQueue<MessageDemo> queue = new LinkedBlockingDeque<MessageDemo>();
        /*for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                messageDemo = new MessageDemo("A", "B", String.valueOf(i));
                try {
                    queue.put(messageDemo);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                messageDemo = new MessageDemo("B", "A", String.valueOf(i));
                try {
                    queue.put(messageDemo);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

        while (!queue.isEmpty()) {
            if (queue.peek().getFrom() != "A") {
                System.out.println("B->A:" + queue.poll().getInfo());
            } else {
                System.out.println("A->B:" + queue.poll().getInfo());
            }
        }*/
        /*BlockingQueue queue = new ArrayBlockingQueue(1024);*/
    A a = new A(queue);
    B b = new B(queue);
    Thread thread = new Thread(a);
    Thread thread1 = new Thread(b);
    thread.start();
    thread1.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("all is ok!"+queue.size());
        while (!queue.isEmpty()) {
            if (queue.peek().getFrom() != "A") {
                System.out.println("B->A:" + queue.poll().getInfo());
            } else {
                System.out.println("A->B:" + queue.poll().getInfo());
            }
        }
    }
}

class A implements Runnable {
    private String name;
    private MessageDemo messageDemo;
    private BlockingQueue<MessageDemo> queue;

    public A(BlockingQueue<MessageDemo> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        synchronized (queue){
        for (int i = 0; i < 1000; i++) {
            if (i % 2 == 0) {
                messageDemo = new MessageDemo("A", "B", String.valueOf(i));
                try {
                    queue.put(messageDemo);
                   // System.out.println(queue.take().getInfo()+"is ok");
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }}
    }

}
class B implements Runnable {
    private String name;
    private MessageDemo messageDemo;
    private BlockingQueue<MessageDemo> queue;

    public B(BlockingQueue<MessageDemo> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        synchronized (queue) {
            for (int i = 0; i < 100; i++) {
                if (i % 2 == 1) {
                    messageDemo = new MessageDemo("B", "A", String.valueOf(i));
                    try {
                        queue.put(messageDemo);
                        // System.out.println(queue.take().getInfo()+"is ok");
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
            System.out.println(queue.size());
        }
    }
}