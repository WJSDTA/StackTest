package TestEcho;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: WJ
 * \* Date: 2019/3/21
 * \* Time: 21:03
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class Message {
    private String from;
    private String to;
    private String info;

    public Message() {
    }

    public Message(String from, String to, String info) {
        this.from = from;
        this.to = to;
        this.info = info;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}