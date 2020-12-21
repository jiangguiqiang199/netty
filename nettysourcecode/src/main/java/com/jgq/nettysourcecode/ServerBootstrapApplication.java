package com.jgq.nettysourcecode;

import com.jgq.nettysourcecode.handler.*;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.AttributeKey;
import lombok.extern.slf4j.Slf4j;

/**
 * netty 服务端启动流程
 * 两个问题：
 * 1、服务端的socket在哪里初始化
 * 2、在哪里accept连接
 * 启动步骤
 * 1、创建服务端channel
 *    channelFactory.newChannel();
 * 2、初始化服务端channel
 *    init(channel);
 * 3、注册selector
 * 4、端口绑定
 * ----------------------------------------------
 * 面试题：
 * 1、默认情况下，netty服务端启动多少线程，何时启动
 *    答：默认2倍CPU核数
 * 2、netty是如何解决jdk空轮询bug的
 *    答：通过计数的方式，达到512次
 * 3、netty如何保证异步串行无锁化
 *    答：把所有操作封装到队列
 * 4、netty是在哪里监测有新连接接入
 *    boss线程轮询出accept事件，通过JDK底层的accept方法去创建连接
 * 5、新连接是怎么样注册到NioEventLoop线程的
 *    boss线程通过choose.next()方法拿到一个NioEventLoop，将连接注册NioEventLoop的selector
 * 6、netty是如何判断ChannelHandler类型的
 * 7、对于ChannelHandler的添加应该注意什么样的顺序
 * 8、用户手动触发事件传播，不同的触发方式有什么样的区别
 * 9、内存的类别有哪些
 * 10、如何减少多线程内存分配之间的竞争
 * 11、不同大小的内存是怎么分配的
 * ----------------------------------------------
 * 1、NioEventLoop创建
 *    1.1、构造方法创建: new NioEventLoopGroup()
 *    1.2、保存线程执行器ThreadPerTaskExcutor new ThreadPerTaskExcutor()
 *    1.3、newChild()构造NioEventLoopGroup
 *    1.4、chooserFactory.newChooser()线程选择器
 *    1.5、创建MpscQueue
 *    1.6、创建selector(轮询NioEventLoop上的连接)
 * 2、NioEventLoop启动
 * 3、NioEventLoop执行逻辑
 *    3.1、无限循环
 *    3.2、调用selector方法，检查是否有IO事件
 *         3.2.1、deadline以及任务穿插逻辑处理
 *         3.2.3、阻塞式select
 *         3.3.3、避免JDK空轮询的bug
 *    3.3、调用processSelectedKeys()处理IO事件
 *         3.3.1、selected keySet优化
 *         3.3.2、processSelectedKeysOptimized()
 *    3.4、调用runAllTasks()处理异步任务队列
 *         3.4.1、task分类和添加
 *         3.4.2、任务的聚合
 *         3.4.3、任务的执行
 *
 * ----------------------------------------------
 * ThreadPerTaskExcutor
 * 1、每次执行任务都会创建线程实体
 * 2、NioEventLoop线程命名规则nioEventLoop-1-xxx
 * ----------------------------------------------
 * Netty新连接接入处理逻辑
 * 1、检测新连接，入口：processSelectedKey(key,channle)
 * 2、创建NioSocketChannel
 * 3、分配线程及注册selector
 * 4、向selector注册读事件
 * ------------------------------------------------
 * channel分类
 * 1、NioServerSocketChannel
 * 2、NioSocketChannel
 * 3、Unsafe
 * ------------------------------------------------
 * 1、pipeline的初始化(一个channel对应一个pipeline)
 *    1、pipleline在创建channel的时候被创建
 *    2、Pipleline节点数据结构：ChannelHandlerContext
 *    3、pipeline中的两大哨兵：head 和 tail
 * 2、添加删除ChannelHandler
 *    2.1、添加ChannelHandler addLast
 *         2.1.1、判断是否重复添加
 *         2.1.2、创建节点并添加至链表
 *         2.1.3、回调添加完成事件
 *    2.2、删除channelHandler
 *         2.2.1、找到节点
 *         2.2.2、链表删除
 *         2.2.3、回调删除Handler事件
 * 3、事件和异常的传播
 *    3.1、什么是inBound事件以及ChannelInboundHandler
 *    3.2、ChannelReader事件的传播
 *    3.3、SimpleInBoundHandler处理器
 *    3.4、何为OutBound事件以及ChannelOutBoundHanler
 *    3.5、writer()事件传播
 * --------------------------------------------------
 * Bytebuff的内存结构
 *      +-------------------+------------------+------------------+
 *      | discardable bytes |  readable bytes  |  writable bytes  |
 *      |                   |     (CONTENT)    |                  |
 *      +-------------------+------------------+------------------+
 *      |                   |                  |                  |
 *      0      <=      readerIndex   <=   writerIndex    <=    capacity
 * ByteBuff分类
 * 1、pooled(从预先分配好的一块内存去取一段连续内存封装成bytebuff扔给应用程序) 和 unpooled直接调用系统api向操作系统分配内存
 * 2、Unsafe(直接拿到对象的内存地址，基于内存地址进行读写操作) 和 非Unsafe(不会依赖到JDK底层的unsafe对象)
 * 3、heap(直接在堆上进行内存分配，需要被GC进行管理) 和 direct(直接调用JDK api进行内存分配)
 * -----------------------------------------------------------------------
 * 1、解码器抽象的解码过程
 * 2、netty中有哪些抽象的解码器
 *    2.1、ByteToMessageDecoder
 *         2.1.1、累加字节流
 *         2.1.2、调用子类的decoder方法进行解析
 *         2.1.3、将解析到ByteBuf向下传播
 *    2.2、基于固定长度的解码器FixedLengthFrameDecoder
 *    2.3、行解码器LineBasedFrameDecoder
 *    2.4、分隔符解码器DelimiterBasedFrameDecoder
 *    2.5、长度域解码器LengthFieldBasedFrameDecoder
 * ----------------------------------------------------------------------------
 * 1、如何把对象变成字节流最终写入到socket底层
 * ----------------------------------------------------------------------------
 * netty两大性能优化工具
 * 1、FastThreadLocal(线程变量隔离)
 * 2、Recycler(轻量级对象池)
 * ----------------------------------------------------------------------------
 * 1、如何模拟百万连接
 *    1.0、1024以下智能被root用户使用
 *    1.1、客户端的端口号是1025 - 65535。除了一些常用的端口，实际上可以用的也就6万左右
 *    1.2、多个客户端一起去连接即可突破
 * 2、突破局部文件句柄限制
 *    2.1、[appuser@crmapp02 ~]$ ulimit -n
 *         1024
 *    2.2、修改 vi /etc/security/limits.conf
 *    2.3、文件末尾增加如下信息
 *         *               soft    nofile            1000000
 *         *               hard    nofile            1000000
 * 3、突破全局文件句柄限制
 *    3.1、vi /proc/sys/fs/file-max(所有进程能打开的最大文件数，局部不能大于全局)
 *    3.2、vi /etc/sysctl.conf  将fs.file-max=1000000添加到最后
 *
 *
 * @Author: Created by carrot
 * 2020/11/13 16:48
 */
@Slf4j
public class ServerBootstrapApplication {

    public static void main(String[] args) {
        // 默认创建cpu核数*2个线程
        // 处理新建连接请求，然后将新建立的连接轮询交给workerGroup中的其中一个NioEventLoop来处理
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup,workGroup)
                    .channel(NioServerSocketChannel.class)
                    .childOption(ChannelOption.TCP_NODELAY,Boolean.TRUE)
                    .childAttr(AttributeKey.newInstance("childAttr"),"childAttrValue")
                    .handler(new ServerHandler())
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        // 创建客户端channel
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new ClientHandler0());
                            socketChannel.pipeline().addLast(new ClientHandler1());
                            socketChannel.pipeline().addLast(new ClientHandler4());
                        }
                    });
            // 创建服务端channel
            Channel channel = b.bind(8888).sync().channel();
            channel.closeFuture().sync();
        }catch (Exception e){
            log.error("【netty服务端启动失败】");
        }finally {
            // 优雅退出程序
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
