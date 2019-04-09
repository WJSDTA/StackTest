package NetWorkStackDemo_02;

import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: WJ
 * \* Date: 2019/3/25
 * \* Time: 18:49
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class PhyLayer implements Runnable{
    private String name;
    private Message message;
    private BlockingQueue<Message> low2high;
    private BlockingQueue<Message> high2low;
    private LinkedBlockingDeque<Message> deque = new LinkedBlockingDeque<>();
    public Message s;
    public String from;
  //  static int i = 0;
    public PhyLayer() {
    }

    public PhyLayer(BlockingQueue<Message> low2high, BlockingQueue<Message> high2low) {
        this.low2high = low2high;
        this.high2low = high2low;
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

    public BlockingQueue<Message> getLow2high() {
        return low2high;
    }

    public void setLow2high(BlockingQueue<Message> low2high) {
        this.low2high = low2high;
    }

    public BlockingQueue<Message> getHigh2low() {
        return high2low;
    }

    public void setHigh2low(BlockingQueue<Message> high2low) {
        this.high2low = high2low;
    }

    public Message getS() {
        return s;
    }

    public void setS(Message s) {
        this.s = s;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
    public void init(){
        for(int i=0; i < 10; i++) {

            if (i % 2 == 1) {
                message = new Message("PhyLayer", "MacLayer", String.valueOf(i));

                try {
                    deque.put(message);
                    //System.out.println(message.getInfo()+"is ok");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // System.out.println(low2high.size());
        }
    }

    @Override
    public void run() {
        synchronized (low2high){
            while (!deque.isEmpty()){

                try {
                    low2high.put(deque.take());
                   // System.out.println(low2high.peek().getInfo());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
            //int i=0;
      /*  while (true){


            synchronized (high2low){
                if (!high2low.isEmpty()&&high2low.peek()!=null){

                    try {
                        // System.out.println("TransportLayer"+queue.size());
                        from =high2low.peek().getTo();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if(from==this.getName()){
                        try {
                            s =   high2low.take();
                            System.out.println("I am PhyLayer ,my info is:"+s.getInfo());
                            *//*message.setFrom("ApplicationLayer");
                            message.setTo("TransportLayer");
                            message.setInfo("I am ApplicationLayer ,my info is:"+s.getInfo());*//*
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }


                    }
                }

            }

        }*/
    }
}