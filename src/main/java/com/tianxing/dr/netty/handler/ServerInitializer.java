package com.tianxing.dr.netty.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

/**
 * @author tianxing
 */
public class ServerInitializer extends ChannelInitializer<SocketChannel>  {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        // 获取管道
        ChannelPipeline pipeline = ch.pipeline();
        // 使用 LengthFieldBasedFrameDecoder 拆分消息，长度字段在消息头的第 2 字节，长度偏移量为 0，长度字段长度为 2 字节
        pipeline.addLast(new LengthFieldBasedFrameDecoder(65535, 0, 2, 0, 2));
        // 使用 LengthFieldPrepender 在消息头添加长度字段
        pipeline.addLast(new LengthFieldPrepender(2)); // 解决粘包问题
        // 添加编码器
        pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
        pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
        // 添加自定义处理器
        pipeline.addLast(new ServerHandler());
    }
}
