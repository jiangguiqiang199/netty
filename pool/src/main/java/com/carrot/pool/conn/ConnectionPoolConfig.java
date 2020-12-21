package com.carrot.pool.conn;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 * {@link org.apache.commons.pool2.impl.GenericObjectPool}支持个性化配置，
 * 我们可以配置对象池中总共的对象数，最大、最小空闲对象数等等
 *
 * 这边继承{@link GenericObjectPoolConfig}是为了ConnectionPool也可以进行个性化的配置
 * @Author: Created by carrot
 * 2020/11/24 10:59
 */
public class ConnectionPoolConfig extends GenericObjectPoolConfig {

    public ConnectionPoolConfig() {
        // defaults to make your life with connection pool easier :)
        setMinIdle(5);
        setTestOnBorrow(true);
    }
}
