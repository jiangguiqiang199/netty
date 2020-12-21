package com.carrot.jvm;

import java.util.HashMap;
import java.util.UUID;

/**
 * 使用hashmap的put操作会引起死循环，多线程会导致HashMap的Entry链表形成环形数据结构，
 * 一旦形成环形数据结构，Entry的next节点永不为空，导致死循环
 * (未复现)
 * @Author: Created by carrot
 * 2020/12/7 11:49
 */
public class HashMapTest {

    public static void main(String[] args) throws InterruptedException {
        final HashMap<String,String> map  = new HashMap<>();
        for (int i =0 ;i<10000;i++){
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    map.put(UUID.randomUUID().toString(),"");
                    System.out.println("当前线程id:"+Thread.currentThread().getName());
                }
            });
            t.start();
            t.join();
        }
    }

}
