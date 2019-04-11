package CommReader;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: WJ
 * \* Date: 2019/4/11
 * \* Time: 17:46
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JDesktopPane;

import java.awt.FlowLayout;

import javax.swing.JTextArea;
import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.TooManyListenersException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ScrollPaneConstants;

public class Reader extends JFrame {

    /**
     *
     */
    Read reader;
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    public JTextArea textArea_2;
    StringBuilder builder;
    private JTextArea textArea_3;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Reader frame = new Reader();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public void settext(String text){


        builder.append(text+"\n");
        if(textArea_2!=null)
            textArea_2.setText(builder.toString());

    }
    /**
     * Create the frame.
     */
    public Reader() {

        builder = new StringBuilder();
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 345);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(48, 10, 376, 131);
        panel.add(scrollPane);

        textArea_2 = new JTextArea();
        textArea_2.setEditable(false);
        scrollPane.setViewportView(textArea_2);

        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(48, 151, 376, 93);
        panel.add(scrollPane_1);

        textArea_3 = new JTextArea();

        scrollPane_1.setViewportView(textArea_3);

        JTextArea textArea = new JTextArea();
        textArea.setEnabled(false);
        textArea.setEditable(false);
        textArea.setText("\u63A5\r\n\u6536\r\n\u533A\r\n\u57DF");
        textArea.setBounds(10, 10, 28, 131);
        panel.add(textArea);

        JTextArea textArea_1 = new JTextArea();
        textArea_1.setEnabled(false);
        textArea_1.setEditable(false);
        textArea_1.setText("\u53D1\r\n\u9001\r\n\u533A\r\n\u57DF");
        textArea_1.setBounds(10, 151, 28, 93);
        panel.add(textArea_1);

        JButton button = new JButton("\u5F00\u59CB");
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                reader.StartGetData();
            }
        });
        button.setBounds(65, 264, 93, 23);
        panel.add(button);
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addComponent(panel, GroupLayout.PREFERRED_SIZE, 434, GroupLayout.PREFERRED_SIZE)
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addComponent(panel, GroupLayout.PREFERRED_SIZE, 309, GroupLayout.PREFERRED_SIZE)
        );

        JButton button_1 = new JButton("\u53D1\u9001");
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        button_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                WriteToCOM();
            }
        });
        button_1.setBounds(313, 264, 93, 23);
        panel.add(button_1);

        JButton btnNewButton = new JButton("\u6E05\u7A7A");
        btnNewButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                textArea_2.setText("");
                builder.setLength(0);
            }
        });
        btnNewButton.setBounds(186, 264, 93, 23);
        panel.add(btnNewButton);
        contentPane.setLayout(gl_contentPane);
        InitCom(this);//初始化串口
    }




    protected void WriteToCOM() {

        String text =textArea_3.getText();
        reader.write(text);
        textArea_3.setText("");
    }


    private void InitCom(Reader reader2) {
        // 初始化串口
        try {
            CommPortIdentifier portid = CommPortIdentifier.getPortIdentifier("COM1");
            reader = new Read(portid,reader2);
        } catch (NoSuchPortException e) {
            // TODO Auto-generated catch block
            reader2.settext("没有发现COM0");
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
class Read implements Runnable, SerialPortEventListener {
    static String retValue = "000000";
    InputStream inputStream;
    SerialPort serialPort;
    Thread readThread;
    JFrame reader;
    private OutputStream outputstream;
    private PrintWriter pw;

    public void StartGetData() {
        try {
            serialPort.addEventListener(this);
            //添加监听器
        } catch (TooManyListenersException e) {
        }

        serialPort.notifyOnDataAvailable(true);
        /*
         * 侦听到串口有数据,触发串口事件
         */
        try {
            serialPort.setSerialPortParams(115200,//波特率
                    SerialPort.DATABITS_8,//数据位数
                    SerialPort.STOPBITS_1,//停止位
                    SerialPort.PARITY_NONE);//校验
        } catch (UnsupportedCommOperationException e) {
        }
    }

    public void CloseThread() {

        readThread.start();
        //启动线程
    }

    public Read(CommPortIdentifier portId, Reader reader2) throws InterruptedException {
        reader = reader2;
        try {
            serialPort = (SerialPort) portId.open("MyReader", 2000);
            //portId.open("串口所有者名称", 超时等待时间);
        } catch (PortInUseException e) {
            //如果端口被占用就抛出这个异常
            e.printStackTrace();

        }

        try {
            inputStream = serialPort.getInputStream();
            outputstream = serialPort.getOutputStream();

            //从COM3获取数据
        } catch (IOException e) {

        }


        readThread = new Thread(this);

    }

    public void write(String out) {
        System.out.println(out);
        try {
            outputstream.write(out.getBytes(), 0, out.getBytes().length);
            outputstream.flush();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("失败");
        }

    }

    public void run() {
        try {
            Thread.sleep(1000);
            serialPort.close();
            System.out.println("COM1:关闭");
            //设定30秒后端口关闭，程序随之结束
        } catch (InterruptedException e) {

        }
    }

    /**
     * BI -通讯中断. CD -载波检测. CTS -清除发送. DATA_AVAILABLE -有数据到达. DSR -数据设备准备好.
     * FE -帧错误. OE -溢位错误. OUTPUT_BUFFER_EMPTY -输出缓冲区已清空. PE -奇偶校验错. RI -
     * 振铃指示. 一般最常用的就是DATA_AVAILABLE--串口有数据到达事件。
     */
    public void serialEvent(SerialPortEvent event) {

        switch (event.getEventType()) {
            case SerialPortEvent.BI:
            case SerialPortEvent.OE:
            case SerialPortEvent.FE:
            case SerialPortEvent.PE:
            case SerialPortEvent.CD:
            case SerialPortEvent.CTS:
            case SerialPortEvent.DSR:
            case SerialPortEvent.RI:
            case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
                break;
            case SerialPortEvent.DATA_AVAILABLE:
                byte[] readBuffer = new byte[20];

                try {
                    while (inputStream.available() > 0) {
                        //int numBytes = inputStream.read(readBuffer);
                        //System.out.println("numBytes" + numBytes);
                        //retValue = new String(readBuffer).trim();
                        //System.out.println(retValue);
                        BufferedReader comReader = new BufferedReader(new InputStreamReader(inputStream));
                        String temp = comReader.readLine().trim();
                        ((Reader) reader).settext(temp);
                        System.out.println(comReader.read() + ":" + temp);

                        if (temp.indexOf("#0") >= 0 || temp.indexOf("#8") >= 0) {
                            int p = temp.indexOf("#");
                            temp = temp.substring(p + 3, temp.length()).replace(" ", "").trim();
                            try {
                                Integer.parseInt(temp);
                            } catch (Exception e) {
                                System.out.println("忽略次数据:" + temp);

                                retValue = "0";
                                return;
                            }
                            System.out.println(temp);
                            retValue = String.valueOf(Integer.parseInt(temp.substring(1, 5))) + "." + temp.substring(5, 6);
                            System.out.println(retValue);
                        }
                    }
                    //strWGT = new String(readBuffer).trim();
                    //System.out.println(strWGT);
                    //输出读入的字符
                } catch (IOException e) {

                }
                break;
        }
    }
}