package com.tianxing.dr.netty.client;

import com.tianxing.dr.netty.handler.ClientInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.DefaultByteBufHolder;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;

/**
 * @author tianxing
 */
public class Client {

    private Channel channel;

    /**
     * 初始化
     */
    public void init() throws InterruptedException {
        // 1. 创建两个线程组 一个是用于处理服务器端接收客户端连接的 一个是进行网络通信的（网络读写的）
        NioEventLoopGroup group = new NioEventLoopGroup();
        // 2. 创建客户端启动助手来配置参数
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group) // 设置线程组
                .channel(NioSocketChannel.class) // 使用NioSocketChannel作为客户端的通道实现
                .handler(new ClientInitializer()); // 创建一个通道初始化对象
        // 3. 启动客户端
        ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 9999).sync();
        channel = channelFuture.channel();
        channel.writeAndFlush("hello world");
        // 4. 关闭通道
        channelFuture.channel().closeFuture().sync();
        // 5. 关闭线程组
        group.shutdownGracefully();
    }

    // 发送消息
    public void sendMsg(String message) {
        channel.writeAndFlush(new DefaultByteBufHolder(Unpooled.copiedBuffer(message, CharsetUtil.UTF_8)));
    }

    public static void main(String[] args) throws InterruptedException {
        Client client = new Client();
        new Thread(() -> {
            try {
                client.init();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        /*new Thread(() -> {
            while (true) {

                try {
                    client.sendMsg("hello world");
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();*/
    }
}
