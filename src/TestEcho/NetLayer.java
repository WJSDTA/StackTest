package TestEcho;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: WJ
 * \* Date: 2019/3/22
 * \* Time: 9:53
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class NetLayer implements Runnable{
    private String name;
    private Message message;
    private BlockingQueue<Message> queue;
    public Message s;
    public String from;
    private LinkedBlockingDeque<Message> cache;
    public NetLayer() {
    }

    public NetLayer(BlockingQueue<Message> queue) {
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

    public BlockingQueue<Message> getQueue() {
        return queue;
    }

    public void setQueue(BlockingQueue<Message> queue) {
        this.queue = queue;
    }

    public LinkedBlockingDeque<Message> getCache() {
        return cache;
    }

    public void setCache(LinkedBlockingDeque<Message> cache) {
        this.cache = cache;
    }

    @Override
    public void run() {
        while (true){
            synchronized (queue){
                 if (!queue.isEmpty()&&queue.peek()!=null){

                    message = new Message();
                    try {
                        // System.out.println("NetLayer"+queue.size());
                        from =queue.peek().getTo();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if(from==this.getName()){

                        try {

                            s =   queue.take();
                            message.setFrom(this.name);

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (s.getFrom()=="MacLayer"){
                            message.setTo("TransportLayer");
                            /*message.setInfo(s.getInfo()+" from:NetLayer to TransportLayer ");*/
                            message.setInfo(s.getInfo());
                        }
                        if (s.getFrom()=="TransportLayer"){
                            message.setTo("MacLayer");
                            message.setInfo(s.getInfo()+" from:NetLayer to MacLayer ");

                        }
                        try {
                            queue.put(message);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }

        }
    }
}