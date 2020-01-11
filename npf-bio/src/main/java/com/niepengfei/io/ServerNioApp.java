package com.niepengfei.io;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author Jack
 * @version 1.0.0
 * @since 1/11/2020
 */
public class ServerNioApp {

    private static ByteBuffer buffer = ByteBuffer.allocate(1024);
    public static void main(String[] args) throws Exception{
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(8080));
        //设置服务端等待连接 非阻塞, 意思是在调用accept方法时, 不会再阻塞
        //如果有客户端来连，那么服务端就会创建一个socket与当前连接上来的客户端进行通信
        //如果没有客户端来连，那么该方法立即返回,且返回值是null
        serverSocketChannel.configureBlocking(false);
        while (true){
            SocketChannel clientSocket = serverSocketChannel.accept();
            //设置读取客户端发送过来的数据的动作是非阻塞的
            //如果客户端有发送数据过来,那么自然会读取到
            //如果客户端没有发送数据过来,那么read方法立即返回,不会阻塞
            clientSocket.configureBlocking(false);
            int read = clientSocket.read(buffer);
            if (read > 0) {
                System.out.println(new String(buffer.array()));
            }
        }
    }


}




