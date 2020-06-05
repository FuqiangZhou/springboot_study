package com.zhou.springbootnetty.server;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019/11/27 6:14 下午
 */
public class MyContext {

    private static Map<Integer, ChannelHandlerContext> onlineMap = new ConcurrentHashMap<>();

    public static void add(Integer id, ChannelHandlerContext ctx){
        onlineMap.put(id, ctx);
    }

    public static void remove(Integer id){
        onlineMap.remove(id);
    }

    public static void removeByCtx(ChannelHandlerContext ctx){
        Collection<ChannelHandlerContext> values = onlineMap.values();
        while (values.contains(ctx)){
            values.remove(ctx);
        }
    }

    public static Integer getId(ChannelHandlerContext ctx){
        Set<Map.Entry<Integer, ChannelHandlerContext>> entries = onlineMap.entrySet();
        for (Map.Entry<Integer, ChannelHandlerContext> next : entries) {
            if (ctx.equals(next.getValue())) {
                return next.getKey();
            }
        }
        return null;
    }

    public static ChannelHandlerContext getContext(Integer id){
        return onlineMap.get(id);
    }

    public static void writeMessage(byte[] message, ChannelHandlerContext ctx){
        ctx.writeAndFlush(Unpooled.buffer(message.length).writeBytes(message));
    }
}
