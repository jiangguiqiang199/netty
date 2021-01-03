package com.carrot.jedis.config;

import org.springframework.beans.factory.FactoryBean;

/**
 * @Author: Created by carrot
 * 2020/12/15 16:57
 */
//@Component
public class MyCat implements FactoryBean<Animal> {
    @Override
    public Animal getObject() throws Exception {
        System.out.println("获取对象");
        // 可以在此处自定义bean的创建过程
        return new Cat();
    }

    @Override
    public Class<?> getObjectType() {
        System.out.println("获取对象类型");
        return Animal.class;
    }
}
