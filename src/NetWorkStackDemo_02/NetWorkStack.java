package NetWorkStackDemo_02;



import java.util.concurrent.LinkedBlockingDeque;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: WJ
 * \* Date: 2019/3/25
 * \* Time: 18:47
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class NetWorkStack {
    public static void main(String[] args) {
        LinkedBlockingDeque<Message> low2high = new LinkedBlockingDeque<Message>();
        LinkedBlockingDeque<Message> high2low = new LinkedBlockingDeque<Message>();

        PhyLayer phyLayer = new PhyLayer(low2high,high2low);
        MacLayer macLayer = new MacLayer(low2high,high2low);
        NetLayer netLayer = new NetLayer(low2high,high2low);
        TransportLayer transportLayer = new TransportLayer(low2high,high2low);
        ApplicationLayer applicationLayer = new ApplicationLayer(low2high,high2low);
        phyLayer.setName("PhyLayer");
        macLayer.setName("MacLayer");
        netLayer.setName("NetLayer");
        phyLayer.init();
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