package com.carrot.pool.conn;

import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;

/**
 * Conn对象管理池，这里利用 GenericObjectPool 作为对象池
 * @Author: Created by carrot
 * 2020/11/24 10:58
 */
public class ConnectionPool extends GenericObjectPool<Connection> {

    /**
     * 调用{@link GenericObjectPool}的构造方法，构造ConnPool
     */
    public ConnectionPool(ConnectionPoolConfig connPoolConfig) {
        super(new ConnectionFactory(), connPoolConfig);
    }

    /**
     * 调用{@link GenericObjectPool}的构造方法，构造ConnPool
     */
    public ConnectionPool() {
        super(new ConnectionFactory(), new ConnectionPoolConfig());
    }
}
