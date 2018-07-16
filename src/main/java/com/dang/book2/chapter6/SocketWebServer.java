package com.dang.book2.chapter6;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Dangdang on 2018/2/18.
 */
public class SocketWebServer {
    public static void main(String[] args) {
        try {
            //定义 Server Socket ， Server Socket 将会监听 8888 端口
            ServerSocket serverSocket = new ServerSocket(8888);

            while (true) {
                //Server Socket 接收到 请求 8888 端口的连接
                Socket socket = serverSocket.accept();

                //从 socket 读取 client 发过来的消息
                InputStream inputStream = socket.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = null;
                while ( (line = bufferedReader.readLine()) != null) {
                    System.out.println("Client 消息：" + line);
                }

                socket.shutdownInput();

                //向 Client 发送消息
                OutputStream outputStream = socket.getOutputStream();
                outputStream.write("我是Server, 命令你听从指挥 ".getBytes());

                socket.shutdownOutput();

                //关闭
                bufferedReader.close();
                inputStream.close();
                outputStream.close();
                socket.close();
                //serverSocket.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void handRequest() {
        System.out.println("Current Thread-" + Thread.currentThread().getName() + " is handling");
    }

}
