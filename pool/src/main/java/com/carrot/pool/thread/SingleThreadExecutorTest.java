package com.carrot.pool.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * 阿里java开发规约要求，显示的用ThreadPoolExecutor去创建线程池，以便于看的清楚
 * @Author: Created by carrot
 * 2020/11/25 12:19
 */
@Slf4j
public class SingleThreadExecutorTest {

    public static void main(String[] args) {
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        for (int i=0; i<20;i++){
            singleThreadExecutor.execute(new Runnable() {
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
//        singleThreadExecutor.shutdown();
    }
}
