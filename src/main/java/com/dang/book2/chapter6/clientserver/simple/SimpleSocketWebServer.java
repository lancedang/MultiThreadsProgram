package com.dang.book2.chapter6.clientserver.simple;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Dangdang on 2018/2/18.
 */
public class SimpleSocketWebServer {

    public static void main(String[] args) {
        try {
            //定义 Server Socket ， Server Socket 将会监听 8888 端口
            ServerSocket serverSocket = new ServerSocket(8888);

            while (true) {
                //Server Socket 接收到 请求 8888 端口的连接
                Socket socket = serverSocket.accept();
                handRequest(socket);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void handRequest(Socket socket) {
        try {
            //不对请求做特殊处理，只是模拟处理时长2s, 然后关闭连接
            System.out.println("开始-处理请求，socket=" + socket.hashCode());
            Thread.sleep(30);
            System.out.println("结束-处理请求，socket=" + socket.hashCode());
            socket.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
