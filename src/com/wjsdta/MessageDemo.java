package com.wjsdta;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: WJ
 * \* Date: 2019/3/21
 * \* Time: 15:24
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class MessageDemo {
    private String from;
    private String to;
    private String info;

    public MessageDemo(String from, String to, String info) {
        this.from = from;
        this.to = to;
        this.info = info;
    }

    public MessageDemo() {
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