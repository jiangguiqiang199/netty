package com.jgq.nettysourcecode;

import io.netty.util.concurrent.FastThreadLocal;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Created by carrot
 * 2020/11/19 13:33
 */
@Slf4j
public class FastThreadLocalTest {

    private static FastThreadLocal threadLocal = new FastThreadLocal<Object>(){
        @Override
        protected Object initialValue() throws Exception {
            return new Object();
        }
    };

    private static Object obj = new Object();

    public static void main(String[] args) {
        new Thread(){
            @Override
            public void run() {
                Object object = threadLocal.get();
                log.info("【当前A线程ThreadLocal对象地址:{}】",object);
                log.info("【当前A线程对象地址:{}】",obj);
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                Object object = threadLocal.get();
                log.info("【当前B线程ThreadLocal对象地址:{}】",object);
                log.info("【当前B线程对象地址:{}】",obj);
            }
        }.start();
    }
}
