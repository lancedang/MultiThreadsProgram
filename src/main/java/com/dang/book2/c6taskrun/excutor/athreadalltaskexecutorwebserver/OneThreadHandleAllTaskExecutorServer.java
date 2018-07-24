package com.dang.book2.c6taskrun.excutor.athreadalltaskexecutorwebserver;

import com.dang.book2.c6taskrun.excutor.HandleRequestUtil;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Dangdang on 2018/7/25.
 * executor 不提供任何线程执行任务，全部main线程来完成
 */
public class OneThreadHandleAllTaskExecutorServer {
    public static void main(String[] args) throws IOException {
        OneThreadHandleAllTaskExecutor executor = new OneThreadHandleAllTaskExecutor();

        ServerSocket serverSocket = new ServerSocket(8885);
        while (true) {
            Socket socket = serverSocket.accept();
            executor.execute(() -> HandleRequestUtil.handleRequest(socket));
        }

    }
}
