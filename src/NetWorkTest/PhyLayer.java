package NetWorkTest;

import java.util.concurrent.ConcurrentLinkedQueue;

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
    private Message message = new Message();
    ;
    private ConcurrentLinkedQueue<Message> queue;
    public Message s;
    public String from;

    public PhyLayer() {
    }

    public PhyLayer(ConcurrentLinkedQueue<Message> queue) {
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


        for (int i = 0; i < 500; i++) {
            if (i % 2 == 1) {
                message = new Message("PhyLayer", "MacLayer", String.valueOf(i));
                try {
                    queue.add(message);
                    //System.out.println(queue.peek().getInfo()+"is ok");
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(queue.size());
        }
    }
}