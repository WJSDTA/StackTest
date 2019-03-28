package TestEcho;

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
        LinkedBlockingDeque<Message> queue = new LinkedBlockingDeque<Message>();

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
        new Thread(phyLayer).start();
        new Thread(macLayer).start();
        new Thread(netLayer).start();
        new Thread(transportLayer).start();
        new Thread(applicationLayer).start();
        System.out.println("Start successful1");

    }
}