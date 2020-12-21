package com.jgq.nettysourcecode.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Created by carrot
 * 2020/11/13 16:57
 */
@Slf4j
public class ClientHandler2 extends ChannelOutboundHandlerAdapter {

//    @Override
//    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
//        log.debug("【客户端channel2:{},OutboundHandler被成功加入】",ctx.channel().id());
//    }
}
