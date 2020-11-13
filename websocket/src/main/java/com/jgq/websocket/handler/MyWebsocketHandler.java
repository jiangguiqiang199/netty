package com.jgq.websocket.handler;

import com.jgq.websocket.config.NettyConfig;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * 接收/处理/响应客户端websocket请求的核心业务处理类
 * @Author: Created by carrot
 * 2020/11/13 10:20
 */
@Slf4j
public class MyWebsocketHandler extends SimpleChannelInboundHandler<Object> {

    private WebSocketServerHandshaker handshaker;

    private static final String WEB_SOCKET_URL = "ws://localhost:8888/websocket";
    /**
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void messageReceived(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.debug("【处理消息:{}】",msg.toString());
        // 1、处理客户端向服务端发起http握手请求的业务
        if(msg instanceof FullHttpRequest){
            log.debug("【处理客户端向服务端发起http握手请求的业务：channelId:{},{}】",ctx.channel().id(),msg.toString());
            handHttpResquest(ctx,(FullHttpRequest) msg);
        }
        // 2、处理websocket连接业务
        if(msg instanceof WebSocketFrame){
            log.debug("【处理websocket连接业务：channelId:{},{}】",ctx.channel().id(),msg.toString());
            handWebsocketFrame(ctx,(WebSocketFrame)msg);
        }
        if(msg instanceof DefaultFullHttpResponse){
            DefaultFullHttpResponse response = (DefaultFullHttpResponse)msg;
            System.out.println(response.toString());
//            ChannelFuture channelFuture = ctx.channel().writeAndFlush(response);
//            if (response.status().code() != 200){
//                channelFuture.addListener(ChannelFutureListener.CLOSE);
//            }
        }
    }

    /**
     * 处理客户端与服务端之间的websocket业务
     * @param ctx
     * @param frame
     */
    private void handWebsocketFrame(ChannelHandlerContext ctx,WebSocketFrame frame){
        // 1、判断是否是关闭websocket
        if(frame instanceof CloseWebSocketFrame){
             log.debug("关闭websocket,channelId:{}",ctx.channel().id());
             handshaker.close(ctx.channel(),(CloseWebSocketFrame)frame.retain());
        }
        // 2、判断是否是ping消息
        if(frame instanceof PingWebSocketFrame){
            log.debug("处理ping消息,channelId:{}",ctx.channel().id());
           ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
           return;
        }
        // 3、暂时不支持二进制
        if(!(frame instanceof TextWebSocketFrame)){
            log.debug("【暂时不支持二进制消息】");
            throw new RuntimeException("【"+this.getClass().getName()+"】不支持消息");
        }
        // 4、获取客户端向服务端发送的消息
        String requestMsg = ((TextWebSocketFrame)frame).text();
        log.debug("【服务端收到客户端消息：{}】",requestMsg);
        // 5、群发消息
        TextWebSocketFrame tws = new TextWebSocketFrame(new Date().toString()+ctx.channel().id()+"=======>"+requestMsg);
        NettyConfig.group.writeAndFlush(tws);
        log.debug("完成消息群发,channelId:{}",ctx.channel().id());
    }

    /**
     * 处理客户端向服务端发起http握手请求的业务
     * @param ctx
     * @param request
     */
    private void handHttpResquest(ChannelHandlerContext ctx, FullHttpRequest request){
           if (!request.getDecoderResult().isSuccess()
                   ||!"websocket".equals(request.headers().get("Upgrade"))){
               log.debug("不是websocket请求:{}",ctx.channel().id());
               sendHttpResponse(ctx,request,new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
               return;
           }
           WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(WEB_SOCKET_URL,
                   null,false);
           handshaker = wsFactory.newHandshaker(request);
           if (handshaker == null){
               WebSocketServerHandshakerFactory.sendUnsupportedWebSocketVersionResponse(ctx.channel());
           }else{
               handshaker.handshake(ctx.channel(),request);
               log.debug("【完成握手操作,channel:{}】",ctx.channel().id());
           }
    }

    /**
     * 服务端向客户端发起响应
     * @param ctx
     * @param request
     * @param response
     */
    private void sendHttpResponse(ChannelHandlerContext ctx,
                                  FullHttpRequest request, DefaultFullHttpResponse response){
        log.debug("【服务端向客户端发起响应,channel:{}】",ctx.channel().id());
       if(response.getStatus().code() != 200){
           ByteBuf byteBuf = Unpooled.copiedBuffer(response.getStatus().toString(), CharsetUtil.UTF_8);
           response.content().writeBytes(byteBuf);
           byteBuf.release();
       }
       // 服务端向客户端发送数据
        ChannelFuture channelFuture = ctx.channel().writeAndFlush(response);
       if (response.getStatus().code() != 200){
           channelFuture.addListener(ChannelFutureListener.CLOSE);
       }
    }

    /**
     * 工程出现异常处理
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("【出现了错误,channel:{}】",ctx.channel().id(),cause);
        ctx.close();
    }

    /**
     * 客户端与服务端建立连接的处理
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        boolean add = NettyConfig.group.add(channel);
        log.debug("【客户端与服务端建立连接,channelId:“"+channel.id()+"”.......】");
    }

    /**
     * 客户端与服务端连接断开后的处理
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        NettyConfig.group.remove(channel);
        log.debug("【客户端与服务端连接关闭,channelId:“"+channel.id()+"”.......】");
    }

    /**
     * 服务端接收完客户端消息结束后的处理
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        log.debug("【服务端接收完客户端消息结束后的处理,channel:{}】",ctx.channel().id());
        ctx.flush();
    }
}
