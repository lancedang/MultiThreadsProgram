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
                executor.execute(() -> handleRequest(socket));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 定义处理 socket 的具体逻辑
     *
     * @param socket
     */
    private static void handleRequest(Socket socket) {
        try {
            System.out.println("线程" + Thread.currentThread().getName() + "开始-处理请求，socket=" + socket.hashCode());
            Thread.sleep(1000);
            System.out.println("线程" + Thread.currentThread().getName() + "结束-处理请求，socket=" + socket.hashCode());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
