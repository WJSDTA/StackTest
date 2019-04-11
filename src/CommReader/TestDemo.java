package CommReader;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: WJ
 * \* Date: 2019/4/11
 * \* Time: 19:06
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class TestDemo {

    public static void main(String[] args) {

        Thread thread=new Thread(new SerialPortTest1());
        thread.start();
    }

}