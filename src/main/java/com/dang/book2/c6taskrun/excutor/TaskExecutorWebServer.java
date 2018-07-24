package com.dang.book2.c6taskrun.excutor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by Dangdang on 2018/7/24.
 * 用 Executor 框架实现 web server
 */
public class TaskExecutorWebServer {
    //Sever 提供一个具备 5 个线程的线程池，线程可重复循环使用
    private static final Executor executor = Executors.newFixedThreadPool(5);

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(8887);

        while (true) {
            try {
                Socket socket = serverSocket.accept();

                //lambda 代替匿名函数
                executor.execute(() -> HandleRequestUtil.handleRequest(socket));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
