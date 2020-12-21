package com.carrot.pool.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * 阿里java开发规约要求，显示的用ThreadPoolExecutor去创建线程池，以便于看的清楚
 * @Author: Created by carrot
 * 2020/11/25 12:19
 */
@Slf4j
public class CachedThreadPoolTest {

    public static void main(String[] args) {
//        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        ExecutorService cachedThreadPool =  new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());
        for (int i=0; i<20;i++){
            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    log.info("线程名称：{}，任务id:{}",Thread.currentThread().getName());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
//        singleThreadExecutor.shutdown();
    }
}
