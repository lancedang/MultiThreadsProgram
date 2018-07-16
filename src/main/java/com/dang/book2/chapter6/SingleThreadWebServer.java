package com.dang.book2.chapter6;

import javax.jws.soap.SOAPBinding;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

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

                //handRequest(socket);


                new Thread(new Runnable() {
                    public void run() {
                        handRequest(socket);
                    }
                }).start();


            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

    public static void handRequest2(Socket socket) {
        //System.out.println("Web Server 开始 处理连接，处理线程为 " + Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
            //3、获取输入流，并读取客户端信息
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String info = null;
            while ((info = br.readLine()) != null) {
                System.out.println("我是服务器，客户端说：" + info);
            }
            socket.shutdownInput();//关闭输入流

            //4、获取输出流，响应客户端的请求
            OutputStream os = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(os);
            pw.write("欢迎您！");
            pw.flush();

            //5、关闭资源
            pw.close();
            os.close();
            br.close();
            isr.close();
            is.close();
            socket.close();
            //serverSocket.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
