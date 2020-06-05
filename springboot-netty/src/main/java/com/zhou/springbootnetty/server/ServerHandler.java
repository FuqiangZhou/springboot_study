package com.zhou.springbootnetty.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.zhou.springbootnetty.utils.Byte2StrUtil;
import com.zhou.springbootnetty.utils.ObjectAndByte;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019/11/19 5:31 下午
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServerHandler.class);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        LOGGER.info("===============channelActive===============");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        LOGGER.info("==========Server channelRead==========");
        LOGGER.info("{} =====>Server {}", ctx.channel().remoteAddress(), Byte2StrUtil.printHexString((byte[]) msg, false));

        /*Map<String, Object> map = JSON.parseObject(msg.toString(), new TypeReference<Map<String, Object>>() {
        });
        MyContext.add((Integer) map.get("id"), ctx);*/
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Integer id = MyContext.getId(ctx);
        LOGGER.info("==========客户端{}断开连接==========", id);
        MyContext.removeByCtx(ctx);
    }
}
