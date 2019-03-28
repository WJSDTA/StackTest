package NetWorkTest;

import java.util.concurrent.ConcurrentLinkedQueue;
//import java.util.concurrent.LinkedBlockingDeque;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: WJ
 * \* Date: 2019/3/22
 * \* Time: 9:53
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class MacLayer implements Runnable {
    private String name;
    private Message message = new Message();
    ;
    private ConcurrentLinkedQueue<Message> queue;
    public Message s;
    public String from;
    String Ms;

    public MacLayer() {
    }

    public MacLayer(ConcurrentLinkedQueue<Message> queue) {
        this.queue = queue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public ConcurrentLinkedQueue<Message> getQueue() {
        return queue;
    }

    public void setQueue(ConcurrentLinkedQueue<Message> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {

        while (true) {
            synchronized (queue) {
                if (queue != null && !queue.isEmpty() && queue.peek() != null) {


                    //   message = new Message();
                    // System.out.println(queue.peek().getTo()+this.name);
                    try {
                        if (queue.peek().getTo() != null) {
                            from = queue.peek().getTo();
                        }

                        //  System.out.println("MacLayer"+queue.size());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (from == this.getName()) {
                        System.out.println("ok ?");
                        try {

                            s = queue.poll();
                            Ms = s.getFrom();
                            System.out.println(Ms);
                            System.out.println(s.getInfo() + "MacLayer" + s.getFrom());
                            message.setFrom("MacLayer");

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        System.out.println(Ms + "MAC");
                        if (Ms == "PhyLayer") {
                            message.setTo("NetLayer");
                            message.setInfo(s.getInfo() + " from:PhyLayer to NetLayer ");
                            System.out.println("MAC");
                        }
                        if (s.getFrom() == "NetLayer") {
                            message.setTo("PhyLayer");
                            message.setInfo(s.getInfo() + " from:NetLayer to PhyLayer ");

                        }
                        try {
                            queue.add(message);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

            }

        }
    }
}