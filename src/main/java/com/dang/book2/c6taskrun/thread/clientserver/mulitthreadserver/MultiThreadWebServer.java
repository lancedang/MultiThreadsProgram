package com.dang.book2.c6taskrun.thread.clientserver.mulitthreadserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Dangdang on 2018/2/18.
 */
public class MultiThreadWebServer {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8888);

            while (true) {
                final Socket socket = serverSocket.accept();

                //每个请求分配一个线程，main折返
                multiThreadHandle(socket);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void multiThreadHandle(Socket socket) {
        //所谓的多线程就是为每个socket请求的分配一个线程来处理
        new Thread(() -> handRequest(socket)).start();
    }

    /**
     * 通信过程分为两个步骤，1.接受请求，2.处理请求；
     * 接受请求决定了响应的快慢，处理决定了吞吐量（处理的请求个数）
     *
     * @param socket
     */
    public static void handRequest(Socket socket) {
        try {
            System.out.println("Web Server 接收到请求, 请求来源于 " + socket.hashCode());
            //不对请求做特殊处理，只是模拟处理时长2s, 然后关闭连接
            Thread.sleep(3000);
            socket.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
