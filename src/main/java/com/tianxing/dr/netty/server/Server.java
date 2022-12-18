package com.tianxing.dr.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author tianxing
 */
public class Server {
    private static final Logger log = LoggerFactory.getLogger(Server.class);

    private final NioEventLoopGroup bossGroup;
    private final NioEventLoopGroup workerGroup;
    private final ServerBootstrap bootstrap;
    private ChannelFuture channelFuture;

    public Server() {
        // 1. 创建两个线程组 一个是用于处理服务器端接收客户端连接的 一个是进行网络通信的（网络读写的）
        bossGroup = new NioEventLoopGroup(1);
        workerGroup = new NioEventLoopGroup();
        // 2. 创建服务器端启动助手来配置参数
        bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup) // 设置两个线程组
                .channel(NioServerSocketChannel.class) // 使用NioServerSocketChannel作为服务器的通道实现
                .childHandler(new ServerInitializer()); // 创建一个通道初始化对象
    }

    /**
     * 启动服务器
     */
    public void start() {
        try {
            channelFuture = bootstrap.bind(9999).sync();
            channelFuture.channel();
        } catch (InterruptedException e) {
            log.error("启动服务器失败", e);
            // 5. 关闭线程组
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    /**
     * shutdown server
     */
    public void shutdown() {
        try {
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            log.info("shutdown server error", e);
        }finally {
            // 5. 关闭线程组
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }



    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }
}
