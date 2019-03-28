package NetWorkStackDemo_02;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: WJ
 * \* Date: 2019/3/25
 * \* Time: 18:50
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class ApplicationLayer implements Runnable{
    private String name;
    private Message message =new Message();
    private BlockingQueue<Message> low2high;
    private BlockingQueue<Message> high2low;
    public Message s;
    public String from;
    public ApplicationLayer() {
    }

    public ApplicationLayer(BlockingQueue<Message> low2high, BlockingQueue<Message> high2low) {
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
    @Override
    public void run() {
        while (true){
            synchronized (low2high){
                if (!low2high.isEmpty()&&low2high.peek()!=null){

                    try {
                        from =low2high.peek().getTo();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if(from==this.getName()){
                        // System.out.println("ok ?");

                        if (low2high.peek().getFrom()=="TransportLayer"){
                            try {
                                s =   low2high.take();
                               // System.out.println("S"+s.getInfo());
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            message.setTo("TransportLayer");
                            message.setFrom("ApplicationLayer");
                            /* message.setInfo(s.getInfo()+" from:PhyLayer to NetLayer ");*/

                            message.setInfo(s.getInfo());
                            try {
                                high2low.put(message);
                                System.out.println( message.getInfo());
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                             // System.out.println("MAC");
                            //System.out.println(message.getTo());
                        }


                    }
                }

            }

        }
    }
}