package com.tianxing.dr.netty.client;

import com.tianxing.dr.netty.client.ClientHandler;
import com.tianxing.dr.netty.handler.HeartbeatHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;

/**
 * @author tianxing
 */
public class ClientInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        // 获取管道
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new IdleStateHandler(0, 5, 0, TimeUnit.MINUTES));
        ch.pipeline().addLast(new HeartbeatHandler());
        pipeline.addLast(new LengthFieldBasedFrameDecoder(65535, 0, 2, 0, 2)); // 解决分包问题
        pipeline.addLast(new LengthFieldPrepender(2)); // 解决粘包问题
        // 添加编码器
        pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
        pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
        // 添加自定义处理器
        pipeline.addLast(new ClientHandler());
    }
}
