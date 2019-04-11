package ComEcho;

import java.util.concurrent.LinkedBlockingDeque;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: WJ
 * \* Date: 2019/3/21
 * \* Time: 21:37
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class NetWorkDemo {
    public static void main(String[] args) {
        LinkedBlockingDeque<Message> queue = new LinkedBlockingDeque<Message>(1024);
        LinkedBlockingDeque<Message> cache = new LinkedBlockingDeque<Message>(1024);

        /*try {
            queue.put(new Message("PhyLayer", "MacLayer", "hello"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        PhyLayer phyLayer = new PhyLayer(queue);
        MacLayer macLayer = new MacLayer(queue);
        NetLayer netLayer = new NetLayer(queue);
        TransportLayer transportLayer = new TransportLayer(queue);
        ApplicationLayer applicationLayer = new ApplicationLayer(queue);
        phyLayer.setName("PhyLayer");
        macLayer.setName("MacLayer");
        netLayer.setName("NetLayer");
        transportLayer.setName("TransportLayer");
        applicationLayer.setName("ApplicationLayer");
        phyLayer.setCache(cache);
        for (int i = 0; i < 20; i++) {
            try {
                cache.put(  new Message("PhyLayer", "MacLayer", String.valueOf(i)));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        new Thread(phyLayer).start();
        new Thread(macLayer).start();
        new Thread(netLayer).start();
        new Thread(transportLayer).start();
        new Thread(applicationLayer).start();
        /*for (int i = 20; i < 40; i++) {
            try {
                Thread.sleep(20);
                cache.put(  new Message("PhyLayer", "MacLayer", String.valueOf(i)));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }*/
        int i=0,j=0,k=0;
        while (true){
            i++;
            if(i>1000000){
                j++;
                i=0;
            }
            if(j>1000000){
                k++;
                j=0;
            }
            try {
                Thread.sleep(500);
                //cache.put(  new Message("PhyLayer", "MacLayer", String.valueOf(k)+String.valueOf(j)+String.valueOf(i)));
                cache.put(  new Message("PhyLayer", "MacLayer", String.format("%07d",k)+String.format("%07d",j)+String.format("%07d",i)));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
       // System.out.println("Start successful1");

    }
}