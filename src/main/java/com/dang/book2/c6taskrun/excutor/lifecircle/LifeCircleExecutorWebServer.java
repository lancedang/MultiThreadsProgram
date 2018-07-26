package com.dang.book2.c6taskrun.excutor.lifecircle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

/**
 * Created by Dangdang on 2018/7/25.
 * 具备生命周期，可被关闭的Web Server，生命周期由ExecutorService来实现<br>
 * 关闭方式：1.请求包含关闭指令，解析之；2.直接调用server提供的关闭接口<br>
 * ExecutorService 生命周期：运行、关闭、已终止, shutdown 关闭状态，关闭后再提交任务execute会抛拒绝异常
 */
public class LifeCircleExecutorWebServer {

    //ExecutorService 提供生命周期处理方法，可利用之
    private static ExecutorService executorService = Executors.newFixedThreadPool(5);

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(8804);

        while (!executorService.isShutdown()) {
            Socket socket = serverSocket.accept();
            //executorService.execute(() -> LifeCircleExecutorWebServer.handleRequest(socket));
            try {
                //关闭状态、队列满等情况下抛异常
                executorService.execute(() -> LifeCircleExecutorWebServer.handleRequest(socket));
            } catch (RejectedExecutionException executionException) {

                //server 已被关闭
                if (executorService.isShutdown()) {
                    System.out.println("已关闭");
                } else {
                    System.out.println("稍后尝试");
                }

                executionException.printStackTrace();

            } catch (Exception e) {

            }
        }

        //当线程池被关闭时，关闭server socket
        serverSocket.close();
        System.out.println("Server closed");
    }

    /**
     * 关闭server的对外接口，关闭server的实际是通过关闭线程池来实现的
     */
    public static void stopServer() {
        executorService.shutdown();
    }

    /**
     * 解析socket字节流，判断是否有"stop"指令
     *
     * @param socket
     * @throws IOException
     */
    public static void handleRequest(Socket socket) {
        InputStream inputStream = null;
        try {
            inputStream = socket.getInputStream();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            //读取请求指令
            String command = bufferedReader.readLine();
            System.out.println("sever read command: " + command);
            if (command != null && command.equalsIgnoreCase("stop")) {
                System.out.println("stop server");
                stopServer();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
