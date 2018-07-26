package com.dang.book2.c6taskrun.excutor.threadpertaskexecutorwebserver;

import com.dang.book2.c6taskrun.excutor.HandleRequestUtil;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Dangdang on 2018/7/25.
 */
public class ThreadPerTaskExecutorServer {

    public static void main(String[] args) throws IOException {

        ThreadPerTaskExecutor threadPerTaskExecutor = new ThreadPerTaskExecutor();

        ServerSocket serverSocket = new ServerSocket(8882);
        while (true) {
            Socket socket = serverSocket.accept();
            //本质和直接用每个线程处理socket一样，只不过封装到Executor 实现里面了
            threadPerTaskExecutor.execute(() -> HandleRequestUtil.handleRequest(socket));
        }

    }


}


