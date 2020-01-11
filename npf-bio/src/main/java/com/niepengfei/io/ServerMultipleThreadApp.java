package com.niepengfei.io;

import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * <p>
 * 服务端启动类
 * </p>
 *
 * @author Jack
 * @version 1.0.0
 * @since 1/3/2020
 */
public class ServerMultipleThreadApp {
    public static void main(String[] args) throws Exception{
        byte[] buffer = new byte[1024];
        ServerSocket serverSocket = new ServerSocket(8080);
        while (true) {
            System.out.println("等待连接....");
            //在这里会发生阻塞, 等待客户端来连
            Socket clientSocket = serverSocket.accept();
            System.out.println("已经建立连接");
            System.out.println("等待客户端的数据....");
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    try {
                        InputStream inputStream = clientSocket.getInputStream();
                        //在这里也会发生阻塞, 等待客户端发送数据
                        int read = inputStream.read(buffer);
                        if (read > 0) {
                            System.out.println("数据已经被接收");
                            String content = IOUtils.toString(buffer, "UTF-8");
                            System.out.println(content);
                        }
                    }catch (Exception e){

                    }
                }
            };
            Thread t1 = new Thread(task);
            t1.start();
        }
    }
}
