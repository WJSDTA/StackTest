package NetWorkStackDemo_02;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.locks.Lock;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: WJ
 * \* Date: 2019/3/25
 * \* Time: 18:49
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class MacLayer implements Runnable{
    private String name;
    private Message message = new Message();
    private  LinkedBlockingDeque<Message> low2high=null;
    private LinkedBlockingDeque<Message> high2low;
    public Message s;
    public String from;
    public String come;
    String Ms;
    public MacLayer() {
    }

    public MacLayer(LinkedBlockingDeque<Message> low2high, LinkedBlockingDeque<Message> high2low) {
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

    public void setLow2high(LinkedBlockingDeque<Message> low2high) {
        this.low2high = low2high;
    }

    public BlockingQueue<Message> getHigh2low() {
        return high2low;
    }

    public void setHigh2low(LinkedBlockingDeque<Message> high2low) {
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

    public String getMs() {
        return Ms;
    }

    public void setMs(String ms) {
        Ms = ms;
    }
    public void Func(LinkedBlockingDeque<Message> queue,String fr,String into){
        synchronized (queue){


            if (!queue.isEmpty()&&queue.peek()!=null){

                try {
                    from =queue.peek().getTo();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if(from==this.getName()){
                    // System.out.println("ok ?");

                    if (queue.peek().getFrom()==fr){
                        try {
                            s =   queue.take();
                           // System.out.println("Mac"+queue.peek().getInfo());
                           // Thread.sleep(1);
                            System.out.println("Qsize"+queue.size());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        message.setTo(into);
                        message.setFrom("MacLayer");
                        /* message.setInfo(s.getInfo()+" from:PhyLayer to NetLayer ");*/
                        message.setInfo(s.getInfo());
                         // System.out.println("MAC");
                    }

                    try {

                        System.out.println(queue.size());
                        queue.put(message);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }

    }
    @Override
    public void run() {
        while (true) {

           // Func(low2high,"PhyLayer","NetLayer");
            synchronized (low2high){


                if (!low2high.isEmpty()&&low2high.peek()!=null){

                    try {
                        from =low2high.peek().getTo();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if(from==this.getName()){
                        // System.out.println("ok ?");

                        if (low2high.peek().getFrom()=="PhyLayer"){
                            try {
                                s =   low2high.take();
                                // System.out.println("Mac"+queue.peek().getInfo());
                                // Thread.sleep(1);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            message.setTo("NetLayer");
                            message.setFrom("MacLayer");
                            /* message.setInfo(s.getInfo()+" from:PhyLayer to NetLayer ");*/
                            message.setInfo(s.getInfo());
                            // System.out.println("MAC");
                            try {

                                System.out.println("size"+low2high.size());
                                low2high.put(message);

                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }


                    }
                }

            }


            // Func(high2low,"NetLayer","PhyLayer");
        }
    }
}