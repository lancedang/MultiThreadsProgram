package com.dang.book2.c6taskrun.thread.clientserver.mulitthreadserver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Dangdang on 2018/2/18.
 */
public class SingleThreadWebServer {

    public static void main(String[] args) {
        try {
            //定义 Server Socket, Server Socket 将会监听 8888 端口
            ServerSocket serverSocket = new ServerSocket(8888);

            while (true) {

                //Server Socket 接收到 请求 8888 端口的连接
                final Socket socket = serverSocket.accept();
                //Web Server 每 2s 才能处理一个请求，Client 每 1s 发送一个，后面发送的请求应该得不到响应，现做验证
                System.out.println("Web Server 接收到请求, 请求来源于 " + socket.hashCode());

                //main主线程处理
                singleThreadHandle(socket);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void singleThreadHandle(Socket socket) {
        handRequest(socket);
    }

    /**
     * 通信过程分为两个步骤，1.接受请求，2.处理请求；
     * 接受请求决定了响应的快慢，处理决定了吞吐量（处理的请求个数）
     *
     * @param socket
     */
    public static void handRequest(Socket socket) {
        try {
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
