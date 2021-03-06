package TestEcho;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: WJ
 * \* Date: 2019/3/21
 * \* Time: 21:36
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class PhyLayer implements Runnable {
    private String name;
    private Message message;
    private BlockingQueue<Message> queue;
    public Message s;
    public String from;
    private LinkedBlockingDeque<Message> cache;
    public PhyLayer() {
    }

    public PhyLayer(BlockingQueue<Message> queue) {
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
       /* while (true){
            if (queue.peek()!=null){
                if(queue.peek().getTo()==this.getName()){
                    try {
                        s =   queue.take();
                        message.setFrom(this.name);
                        message.setTo("TransportLayer");

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (queue.peek().getFrom()=="MacLayer"){
                        message.setInfo(s.getInfo()+" from:NetLayer to TransportLayer ");
                    }
                    if (queue.peek().getFrom()=="TransportLayer"){
                        message.setInfo(s.getInfo()+" from:NetLayer to TransportLayer ");

                    }
                    try {
                        queue.put(message);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }*/

        synchronized (queue){
        for (int i = 0; i < 10; i++) {

                message = new Message("PhyLayer", "MacLayer", String.valueOf(i));
                /*try {
                    queue.put(message);
                     //System.out.println(queue.peek().getInfo()+"is ok");
                    // Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/


        }
        }

        while (true){
            synchronized (queue){
                if (!cache.isEmpty()&&cache.peek()!=null){
                    try {
                        message = new Message("PhyLayer", "MacLayer", cache.take().getInfo());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try {
                        queue.put(message);
                        //System.out.println(queue.peek().getInfo()+"is ok");
                        // Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
               // System.out.println(cache.size());
                if (!queue.isEmpty()&&queue.peek()!=null){

                    try {
                        // System.out.println("TransportLayer"+queue.size());
                        from =queue.peek().getTo();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if(from==this.getName()){
                        try {
                            s =   queue.take();
                             System.out.println("I am PhyLayer ,my info is:"+s.getInfo());
                            /*message.setFrom("ApplicationLayer");
                            message.setTo("TransportLayer");
                            message.setInfo("I am ApplicationLayer ,my info is:"+s.getInfo());*/
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }


                    }
                }

            }

        }
    }
}