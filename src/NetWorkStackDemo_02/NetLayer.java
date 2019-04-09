package NetWorkStackDemo_02;

import java.util.Iterator;
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
public class NetLayer implements Runnable{
    private String name;
    private Message message =new Message();
    private LinkedBlockingDeque<Message> low2high;
    private LinkedBlockingDeque<Message> high2low;
    public Message s;
    public Message s1;
    public Message ss = new Message();
    public String from;
    public NetLayer() {
    }

    public NetLayer(LinkedBlockingDeque<Message> low2high, LinkedBlockingDeque<Message> high2low) {
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
                            Thread.sleep(1);

                            s =   queue.poll();
                          //  System.out.println("net"+s.getInfo());

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        message.setTo(into);
                        message.setFrom("NetLayer");
                        /* message.setInfo(s.getInfo()+" from:PhyLayer to NetLayer ");*/
                        message.setInfo(s.getInfo());
                        //  System.out.println("MAC");
                    }
                    //if(!(ss.getInfo()==message.getInfo()&&ss.getFrom()==message.getFrom()&&ss.getTo()==message.getTo())){
                    try {
                        queue.put(message);
                        //System.out.println(message.getInfo()+"is ok");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }//}
                    /*Iterator<Message> iterator = queue.descendingIterator();
                    while (iterator.hasNext()) {
                        s1 = iterator.next();
                        System.out.println("from:"+s1.getFrom()+" To:"+s1.getTo()+" Info:"+s1.getInfo());
                    }*/
                    ss = message;
                }
            }

        }

    }
    @Override
    public void run() {
        while (true){

         //   Func(low2high,"MacLayer","TransportLayer");
            synchronized (low2high){
                if (!low2high.isEmpty()&&low2high.peek()!=null){

                    try {
                        from =low2high.peek().getTo();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if(from==this.getName()){
                        // System.out.println("ok ?");

                        if (low2high.peek().getFrom()=="MacLayer"){
                            try {
                                Thread.sleep(1);

                                s =   low2high.poll();
                                //  System.out.println("net"+s.getInfo());

                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            message.setTo("TransportLayer");
                            message.setFrom("NetLayer");
                            /* message.setInfo(s.getInfo()+" from:PhyLayer to NetLayer ");*/
                            message.setInfo(s.getInfo());
                            //  System.out.println("MAC");
                        }
                        //if(!(ss.getInfo()==message.getInfo()&&ss.getFrom()==message.getFrom()&&ss.getTo()==message.getTo())){
                        try {
                            low2high.put(message);
                            //System.out.println(message.getInfo()+"is ok");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }//}
                    /*Iterator<Message> iterator = queue.descendingIterator();
                    while (iterator.hasNext()) {
                        s1 = iterator.next();
                        System.out.println("from:"+s1.getFrom()+" To:"+s1.getTo()+" Info:"+s1.getInfo());
                    }*/
                        ss = message;
                    }
                }

            }
           // Func(high2low,"TransportLayer","MacLayer");
        }
    }
}