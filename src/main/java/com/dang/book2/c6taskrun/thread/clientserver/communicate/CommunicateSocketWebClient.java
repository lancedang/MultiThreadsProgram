package com.dang.book2.c6taskrun.thread.clientserver.communicate;

import java.io.*;
import java.net.Socket;

/**
 * Created by Dangdang on 2018/2/18.
 * web client 模拟发送若干个请求,有具体通信的过程（读写socket stream）
 */
public class CommunicateSocketWebClient {

    public static void main(String[] args) {
        //singleClient("");
        multiClient();
    }

    /**
     * 发起100个请求
     */
    public static void multiClient() {
        int i = 100;
        for (int j = 0; j < i; j++) {
            try {
                singleClient("" + j);
                //client 以每 1ms 时间发送一个请求，看 Web Server 能不能每 1ms 响应一个请求
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void singleClient(String name) {
        Socket socket = null;
        try {
            //建立连接
            socket = new Socket("localhost", 8888);

            //2、获取输出流，向服务器端发送信息
            OutputStream os = socket.getOutputStream();//字节输出流
            PrintWriter pw = new PrintWriter(os);//将输出流包装成打印流
            pw.write("Client-" + name);
            pw.flush();
            socket.shutdownOutput();
            //3、获取输入流，并读取服务器端的响应信息
            InputStream is = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String info = null;
            while ((info = br.readLine()) != null) {
                System.out.println("我是客户端，服务器说：" + info);
            }

            //4、关闭资源
            br.close();
            is.close();
            pw.close();
            os.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
