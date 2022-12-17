package com.tianxing.dr.netty.server;

import com.tianxing.dr.netty.handler.ServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @author tianxing
 */
public class Server {

    /**
     * 初始化
     */
    public void init() throws InterruptedException {
        // 1. 创建两个线程组 一个是用于处理服务器端接收客户端连接的 一个是进行网络通信的（网络读写的）
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        // 2. 创建服务器端启动助手来配置参数
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup) // 设置两个线程组
                .channel(NioServerSocketChannel.class) // 使用NioServerSocketChannel作为服务器的通道实现
                .childHandler(new ServerInitializer()); // 创建一个通道初始化对象
        // 3. 启动服务器
        ChannelFuture channelFuture = bootstrap.bind(9999).sync();
        // 4. 关闭通道
        channelFuture.channel().closeFuture().sync();
        // 5. 关闭线程组
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }

    public static void main(String[] args) {
        Server server = new Server();
        try {
            server.init();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
