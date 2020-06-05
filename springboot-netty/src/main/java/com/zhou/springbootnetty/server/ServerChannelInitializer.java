package com.zhou.springbootnetty.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import io.netty.handler.codec.bytes.ByteArrayEncoder;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019/11/19 5:17 下午
 */
public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        socketChannel.pipeline().addLast("decoder", new ByteArrayDecoder());
        socketChannel.pipeline().addLast("encoder", new ByteArrayEncoder());
        socketChannel.pipeline().addLast(new IdleStateHandler(10, 0, 0, TimeUnit.SECONDS));
        socketChannel.pipeline().addLast(new ServerHandler());
    }
}
