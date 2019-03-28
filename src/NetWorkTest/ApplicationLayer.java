package NetWorkTest;

import java.util.concurrent.ConcurrentLinkedQueue;
//import java.util.concurrent.LinkedBlockingDeque;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: WJ
 * \* Date: 2019/3/22
 * \* Time: 9:57
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class ApplicationLayer implements Runnable {
    private String name;
    private Message message = new Message();
    ;
    private ConcurrentLinkedQueue<Message> queue;
    public Message s;
    public String from;

    public ApplicationLayer() {
    }

    public ApplicationLayer(ConcurrentLinkedQueue<Message> queue) {
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

                    try {
                        // System.out.println("TransportLayer"+queue.size());
                        if (!queue.peek().getTo().isEmpty()) {
                            from = queue.peek().getTo();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (from == this.getName()) {
                        try {
                            s = queue.poll();
                            System.out.println("I am ApplicationLayer ,my info is:" + s.getInfo());
                            Thread.sleep(300);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    }
                }

            }

        }
    }
}