package com.dang.book2.c6taskrun.excutor;

import java.net.Socket;

/**
 * Created by Dangdang on 2018/7/25.
 * 专门用于存放处理请求的方法
 */
public class HandleRequestUtil {
    /**
     * 定义处理 socket 的具体逻辑
     *
     * @param socket
     */
    public static void handleRequest(Socket socket) {
        try {
            System.out.println("线程" + Thread.currentThread().getName() + "开始-处理请求，socket=" + socket.hashCode());
            Thread.sleep(1000);
            System.out.println("线程" + Thread.currentThread().getName() + "结束-处理请求，socket=" + socket.hashCode());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
