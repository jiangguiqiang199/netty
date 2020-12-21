package com.carrot.pool.conn;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

/**
 * common-pool2 使用方式:
 * <p/>
 *  为了使用common-pool2对象池管理，我们必须实现{@link org.apache.commons.pool2.PooledObjectFactory}或者其子类
 *  这是一个工厂模式，告诉对象池怎样去创建要管理的对象
 *  <p/>
 *  BasePooledObjectFactory 是对{@link org.apache.commons.pool2.PooledObjectFactory}的一个基本实现，我们可以继承该类，减少一些方法的实现
 *  <p/>
 *  在实现{@link org.apache.commons.pool2.PooledObjectFactory}接口时，
 *  我们一定要实现的接口方法是{@link org.apache.commons.pool2.PooledObjectFactory#makeObject()}方法。
 * @Author: Created by carrot
 * 2020/11/24 10:54
 */
public class ConnectionFactory extends BasePooledObjectFactory<Connection> {

    /**
     * 间接实现{@link org.apache.commons.pool2.PooledObjectFactory#makeObject()}方法，表明怎样创建需要管理对象
     */
    @Override
    public Connection create() throws Exception {
        return new Connection();
    }

    /**
     * 在common-pool2中为了统计管理的对象的一些信息，
     * 比如调用次数，空闲时间，上次使用时间等，需要对管理的对象进行包装，然后在放入到对象池中
     *
     * @param obj 对象池要管理的对象
     * @return 返回包装后的PooledObject对象
     */
    @Override
    public PooledObject<Connection> wrap(Connection obj) {
        return new DefaultPooledObject<Connection>(obj);
    }
}
