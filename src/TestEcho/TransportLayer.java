package TestEcho;

import java.util.concurrent.BlockingQueue;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: WJ
 * \* Date: 2019/3/22
 * \* Time: 9:56
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class TransportLayer implements Runnable{
    private String name;
    private Message message;
    private BlockingQueue<Message> queue;
    public Message s;
    public String from;
    public TransportLayer() {
    }

    public TransportLayer(BlockingQueue<Message> queue) {
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

    @Override
    public void run() {

        while (true){
            synchronized (queue){
                if (!queue.isEmpty()&&queue.peek()!=null){

                    message = new Message();
                    try {
                        // System.out.println("TransportLayer"+queue.peek().getInfo());
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

                        if (s.getFrom()=="ApplicationLayer"){
                            try {
                                Thread.sleep(2);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                           // System.out.println("s"+s.getInfo());
                            message.setTo("NetLayer");
                            message.setInfo(s.getInfo()+" from:TransportLayer to NetLayer ");

                            //System.out.println("TransportLayer");
                           // System.out.println(queue.size());
                        }
                        if (s.getFrom()=="NetLayer"){
                            message.setTo("ApplicationLayer");
                            /*message.setInfo(s.getInfo()+" from:TransportLayer to ApplicationLayer ");*/
                            message.setInfo(s.getInfo());

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