package com.example.datastructure.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Set;

/**
 * className NIOSocket
 * packageName com.example.datastructure.nio
 * description
 *
 * @author CCC
 * @date 2020/9/29 23:32
 */
public class NIOServerSocket {

    public static void main(String[] args) {

        try {
            ServerSocketChannel ssc = ServerSocketChannel.open();
            ssc.configureBlocking(false);

            ssc.socket().bind(new InetSocketAddress(8888));

            Selector selector = Selector.open();

            ssc.register(selector, SelectionKey.OP_ACCEPT);

            Calendar ca = Calendar.getInstance();
            System.out.println("服务端开启了");
            System.out.println("=========================================================");

            while (true) {

                //选择准备好的事件
                selector.select();
                //已选择的键集
                Iterator<SelectionKey> it = selector.selectedKeys().iterator();
                //处理已选择键集事件
                while (it.hasNext()) {
                    SelectionKey key = it.next();
                    //处理掉后将键移除，避免重复消费(因为下次选择后，还在已选择键集中)
                    it.remove();
                    //处理连接请求
                    if (key.isAcceptable()) {
                        //处理请求
                        SocketChannel socket = ssc.accept();
                        socket.configureBlocking(false);
                        //注册read，监听客户端发送的消息
                        socket.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                        //keys为所有键，除掉serverSocket注册的键就是已连接socketChannel的数量
                        String message = "连接成功 你是第" + (selector.keys().size() - 1) + "个用户";
                        //向客户端发送消息
                        socket.write(ByteBuffer.wrap(message.getBytes()));
                        InetSocketAddress address = (InetSocketAddress) socket.getRemoteAddress();
                        //输出客户端地址
                        System.out.println(ca.getTime() + "\t" + address.getHostString() +
                                ":" + address.getPort() + "\t");
                        System.out.println("客戶端已连接");
                        System.out.println("=========================================================");
                    }

                    if (key.isReadable()) {
                        SocketChannel socket = (SocketChannel) key.channel();
                        InetSocketAddress address = (InetSocketAddress) socket.getRemoteAddress();
                        System.out.println(ca.getTime() + "\t" + address.getHostString() +
                                ":" + address.getPort() + "\t");
                        ByteBuffer bf = (ByteBuffer) key.attachment();
                        int len = 0;
                        byte[] res = new byte[1024 * 4];
                        //捕获异常，因为在客户端关闭后会发送FIN报文，会触发read事件，但连接已关闭,此时read()会产生异常
                        try {
                            while ((len = socket.read(bf)) != 0) {
                                bf.flip();
                                bf.get(res, 0, len);
                                System.out.println(new String(res, 0, len));
                                bf.clear();
                            }
                            System.out.println("=========================================================");
                        } catch (IOException e) {
                            //客户端关闭了
                            key.cancel();
                            socket.close();
                            System.out.println("客戶端已断开");
                            System.out.println("=========================================================");
                        }
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("服务器异常，即将关闭..........");
            System.out.println("=========================================================");
        }
    }


}
