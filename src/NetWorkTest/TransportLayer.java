package NetWorkTest;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: WJ
 * \* Date: 2019/3/22
 * \* Time: 9:56
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class TransportLayer implements Runnable {
    private String name;
    private Message message = new Message();
    ;
    private ConcurrentLinkedQueue<Message> queue;
    public Message s;
    public String from;

    public TransportLayer() {
    }

    public TransportLayer(ConcurrentLinkedQueue<Message> queue) {
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
                if (queue != null && (!queue.isEmpty()) && (queue.peek() != null)) {

                    // message = new Message();
                    try {
                        // System.out.println("TransportLayer"+queue.size());
                        if (queue.peek().getTo() != null) {
                            from = queue.peek().getTo();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (from == this.getName()) {
                        try {

                            s = queue.poll();
                            message.setFrom(this.name);


                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (s.getFrom() == "NetLayer") {
                            message.setTo("ApplicationLayer");
                            message.setInfo(s.getInfo() + " from:TransportLayer to ApplicationLayer ");
                        }
                        if (s.getFrom() == "ApplicationLayer") {
                            message.setTo("NetLayer");
                            message.setInfo(s.getInfo() + " from:TransportLayer to NetLayer ");

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