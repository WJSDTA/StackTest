package APITest;

import java.util.concurrent.BlockingQueue;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: WJ
 * \* Date: 2019/4/9
 * \* Time: 19:57
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class MainClass implements Runnable{
    private String name;
    private Message message;
    private BlockingQueue<Message> queue;
    private String low;
    private String high;
    public Message s;
    public String from;

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

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
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

    public MainClass(BlockingQueue<Message> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {

    }
}