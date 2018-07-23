package com.dang.book2.c6taskrun.thread.clientserver.mulitthreadserver;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by Dangdang on 2018/2/18.
 * web client 模拟发送若干个请求
 */
public class SocketWebClient {

    public static void main(String[] args) {
        //singleClient("");
        multiClient();
    }

    /**
     * 发起100个请求
     */
    public static void multiClient() {
        int i = 10;
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

    /**
     * 建立单个请求
     *
     * @param name
     */
    public static void singleClient(String name) {
        Socket socket = null;
        try {
            //建立连接，且不做特殊任务，模拟请求执行500ms，然后关闭
            socket = new Socket("localhost", 8888);
            Thread.sleep(50);
            socket.close();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
