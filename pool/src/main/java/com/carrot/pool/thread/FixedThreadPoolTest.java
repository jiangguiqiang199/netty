package com.carrot.pool.thread;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * 阿里java开发规约要求，显示的用ThreadPoolExecutor去创建线程池，以便于看的清楚
 * @Author: Created by carrot
 * 2020/11/25 12:19
 */
@Slf4j
public class FixedThreadPoolTest {

    public static void main(String[] args) {
        ThreadPoolExecutor fixedThreadPool = new ThreadPoolExecutor(10, 15,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(),
                Executors.defaultThreadFactory());
        int activeCount = fixedThreadPool.getActiveCount();
        long completedTaskCount = fixedThreadPool.getCompletedTaskCount();
        int corePoolSize = fixedThreadPool.getCorePoolSize();
        long keepAliveTime = fixedThreadPool.getKeepAliveTime(TimeUnit.SECONDS);
        int largestPoolSize = fixedThreadPool.getLargestPoolSize();
        int maximumPoolSize = fixedThreadPool.getMaximumPoolSize();
        BlockingQueue<Runnable> queue = fixedThreadPool.getQueue();
        RejectedExecutionHandler rejectedExecutionHandler = fixedThreadPool.getRejectedExecutionHandler();
        long taskCount = fixedThreadPool.getTaskCount();
        ThreadFactory threadFactory = fixedThreadPool.getThreadFactory();
        log.info("任务执行前：maximumPoolSize:{},corePoolSize:{},taskCount:{},activeCount:{},completedTaskCount：{},keepAliveTime:{},largestPoolSize:{}",
                maximumPoolSize,corePoolSize,taskCount,activeCount,completedTaskCount,keepAliveTime,largestPoolSize);
        for (int i=0; i<1;i++){
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    log.info("线程名称：{}，任务id:{}",Thread.currentThread().getName());
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        activeCount = fixedThreadPool.getActiveCount();
        completedTaskCount = fixedThreadPool.getCompletedTaskCount();
        corePoolSize = fixedThreadPool.getCorePoolSize();
        keepAliveTime = fixedThreadPool.getKeepAliveTime(TimeUnit.SECONDS);
        largestPoolSize = fixedThreadPool.getLargestPoolSize();
        maximumPoolSize = fixedThreadPool.getMaximumPoolSize();
        queue = fixedThreadPool.getQueue();
        rejectedExecutionHandler = fixedThreadPool.getRejectedExecutionHandler();
        taskCount = fixedThreadPool.getTaskCount();
        threadFactory = fixedThreadPool.getThreadFactory();
        log.info("任务执行后，maximumPoolSize:{},corePoolSize:{},taskCount:{},activeCount:{},completedTaskCount：{},keepAliveTime:{},largestPoolSize:{}",
                maximumPoolSize,corePoolSize,taskCount,activeCount,completedTaskCount,keepAliveTime,largestPoolSize);
        log.info("使用的队列:{}",queue.getClass().getName());
        log.info("使用的线程工厂:{}",threadFactory);
        log.info("使用的饱和策略:{}",rejectedExecutionHandler.getClass().getName());
//        fixedThreadPool.shutdown();
    }
}
