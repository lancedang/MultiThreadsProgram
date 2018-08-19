package com.dang.book2.c6taskrun.excutor.lifecircle;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Dangdang on 2018/7/25.
 * 能够与Server通信，并提供stop command的client
 */
public class CommandWebClient {
    public static void main(String[] args) throws IOException, InterruptedException {
        for (int i = 0; i < 10; i++) {
            Socket socket = new Socket("localhost", 8804);

            OutputStream outputStream = socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream);
            if (i == 5) {
                System.out.println("send stop command");
                printWriter.write("stop");
            } else {
                printWriter.write("hello");
            }

            printWriter.flush();
            Thread.sleep(2000);

            outputStream.close();
            printWriter.close();

            socket.close();
        }
    }
}
