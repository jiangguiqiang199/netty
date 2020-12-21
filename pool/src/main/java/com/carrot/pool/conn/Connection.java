package com.carrot.pool.conn;

import lombok.extern.slf4j.Slf4j;

/**
 * 连接对象
 * @Author: Created by carrot
 * 2020/11/24 10:52
 */
@Slf4j
public class Connection {

    /**
     * 记录对象的创建时间
     */
    private long createTime;

    /**
     * 初始化Connection对象，模拟创建Conn对象平均消耗500ms
     * @throws InterruptedException
     */
    public Connection() throws InterruptedException {
        Thread.sleep(500);
        createTime = System.currentTimeMillis();
        log.debug(" init conn suc... " + createTime);
    }

    /**
     * 报告Conn对象信息
     */
    public void report() {
        log.info("this is a available conn " + createTime);
    }

}
