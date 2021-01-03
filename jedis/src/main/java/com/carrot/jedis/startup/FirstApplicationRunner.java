package com.carrot.jedis.startup;

import com.carrot.jedis.aware.Flag;
import com.carrot.jedis.aware.MyAware;
import com.carrot.jedis.config.Dog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @Author: Created by carrot
 * 2020/12/16 8:55
 */
@Slf4j
@Component
@Order(2)
public class FirstApplicationRunner implements ApplicationRunner, EnvironmentAware, MyAware, BeanFactoryAware {

    private Environment environment;

    private Flag flag;

    private BeanFactory beanFactory;

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        System.out.println(flag.isCanOperate());
//        System.out.println(environment.getProperty("jiang"));
//        System.out.println(environment.getProperty("jiang.random"));
//        System.out.println(environment.getProperty("jiang.os.path"));
//        System.out.println(environment.getProperty("jiang.java.path"));
        // 模拟springboot启动banner
        log.info("\u001B[32m >> startup first application runner <<<");
        Dog dog = beanFactory.getBean("dog", Dog.class);
        if(beanFactory instanceof DefaultListableBeanFactory){
            DefaultListableBeanFactory factory = (DefaultListableBeanFactory)beanFactory;
            BeanDefinition beanDefinition = factory.getBeanDefinition("lockTableMapper");
            System.out.println();
        }
        log.info("【存在{}】",Dog.class.getName());
    }

    @Override
    public void setEnvironment(Environment environment) {
       this.environment = environment;
    }

    @Override
    public void setFlag(Flag flag) {
        this.flag = flag;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
}
