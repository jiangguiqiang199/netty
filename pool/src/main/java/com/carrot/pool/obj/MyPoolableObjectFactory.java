package com.carrot.pool.obj;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.DestroyMode;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

/**
 * @Author: Created by carrot
 * 2020/11/24 10:16
 */
@Slf4j
public class MyPoolableObjectFactory extends BasePooledObjectFactory<Resource> {


    @Override
    public Resource create() throws Exception {
        Resource resource = new Resource();
        log.info("在对象池中创建对象：{}】",resource);
        return resource;
    }

    @Override
    public PooledObject<Resource> wrap(Resource obj) {
        DefaultPooledObject<Resource> resourceDefaultPooledObject = new DefaultPooledObject<Resource>(obj);
        log.info("在线程池中包装对象:{}",obj);
        return resourceDefaultPooledObject;
    }

    public void destroyObject(PooledObject<Resource> p, DestroyMode mode) throws Exception {

    }
}
