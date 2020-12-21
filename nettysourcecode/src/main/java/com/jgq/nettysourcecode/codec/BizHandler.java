package com.jgq.nettysourcecode.codec;

import com.jgq.nettysourcecode.model.User;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Created by carrot
 * 2020/11/20 19:33
 */
@Slf4j
public class BizHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        User user = new User(19,"jiangguiqiang");
        ctx.channel().writeAndFlush(user);
    }
}
