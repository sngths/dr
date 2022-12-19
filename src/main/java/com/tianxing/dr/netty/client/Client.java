package com.tianxing.dr.netty.client;

import com.tianxing.dr.netty.server.Server;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.DefaultByteBufHolder;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.concurrent.ExecutionException;

/**
 * @author tianxing
 */
public class Client {

    private static final Logger log = LoggerFactory.getLogger(Server.class);

    private final NioEventLoopGroup group;
    private final Bootstrap bootstrap;
    private Channel channel;

    public Client() {
        // 1. 创建两个线程组 一个是用于处理服务器端接收客户端连接的 一个是进行网络通信的（网络读写的）
        group = new NioEventLoopGroup();
        // 2. 创建客户端启动助手来配置参数
        bootstrap = new Bootstrap();
        bootstrap.group(group) // 设置线程组
                .channel(NioSocketChannel.class) // 使用NioSocketChannel作为客户端的通道实现
                .handler(new ClientInitializer()); // 创建一个通道初始化对象
    }

    /**
     * start client
     */
    public void start() {
        try {
            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 9999).sync();
            channel = channelFuture.channel();
        } catch (InterruptedException e) {
            log.error("client start error", e);
            // 5. 关闭线程组
            group.shutdownGracefully();
        }
    }

    /**
     * stop client
     */
    public void stop() {
        if (channel != null) {
            try {
                channel.closeFuture().sync();
            } catch (InterruptedException e) {
                log.error("client stop error", e);
            }finally {
                // 5. 关闭线程组
                group.shutdownGracefully();
            }
        }
    }

    // 发送消息
    public void sendMsg(String message) {
        ChannelFuture future = channel.writeAndFlush(message);
        // 等待响应完成
        future.awaitUninterruptibly();
        try {
            String m = String.valueOf(future.get());
            log.info("client receive message: {}", m);
        } catch (InterruptedException | ExecutionException e) {
            log.error("client send message error", e);
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.start();
        new Thread(() -> {
            while (true) {
                try {
                    client.sendMsg(new Date(System.currentTimeMillis()).toString());
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
