package TestEcho;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: WJ
 * \* Date: 2019/3/22
 * \* Time: 9:57
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class ApplicationLayer implements Runnable{
    private String name;
    private Message message =new Message();
    private LinkedBlockingDeque<Message> queue;
    private LinkedBlockingDeque<Message> queue1=new LinkedBlockingDeque<>();
    public Message s;
    public String from;
    public ApplicationLayer() {
    }

    public ApplicationLayer(LinkedBlockingDeque<Message> queue) {
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

    public void setQueue(LinkedBlockingDeque<Message> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true){

            synchronized (queue){
                synchronized (queue1){
                    if (!queue.isEmpty()&&queue.getFirst()!=null){

                        try {
                            // System.out.println("TransportLayer"+queue.size());
                            from =queue.getFirst().getTo();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if(from==this.getName()){
                            try {
                                s =   queue.take();
                               // System.out.println("qsize"+queue.size());
                                System.out.println("I am ApplicationLayer ,my info is:"+s.getInfo());

                                //  System.out.println("queue"+queue.peek().getInfo());
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            if (s.getFrom()=="TransportLayer") {
                                message.setFrom("ApplicationLayer");
                                message.setTo("TransportLayer");
                                message.setInfo("I am ApplicationLayer ,my info is:" + s.getInfo());
                               // System.out.println(message.getInfo());
                               // System.out.println(message.getFrom());
                              //  System.out.println(message.getTo());
                                if (message != null) {
                                    try {
                                        queue1.put(message);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    // queue.put(message);
                                }

                              //  System.out.println(queue1.size());
                            }


                        }
                    }
                    if (!queue1.isEmpty()&&queue1.getFirst()!=null){
                        try {
                            queue.put(queue1.take());
                           // System.out.println("Q1Info"+queue.getFirst().getInfo());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }


            }


        }
    }
}