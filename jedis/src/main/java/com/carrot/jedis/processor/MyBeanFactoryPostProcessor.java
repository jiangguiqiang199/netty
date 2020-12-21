package com.carrot.jedis.processor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * @Author: Created by carrot
 * 2020/12/14 13:49
 */
@Slf4j
@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    /**
     * 所有的bean加载之后，实例化之前调用
     * @param beanFactory
     * @throws BeansException
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        log.info("【进入到MyBeanFactoryPostProcessor.postProcessBeanFactory】");
        // 获取bean的定义信息
        BeanDefinition jedisClusterProperties = beanFactory.getBeanDefinition("jedisClusterProperties");
        // 输出属性值
        log.info("【属性值:{}】",jedisClusterProperties.getPropertyValues().toString());
        //
        MutablePropertyValues propertyValues = jedisClusterProperties.getPropertyValues();
        if(propertyValues.contains("")){
            propertyValues.add("","");
        }
        jedisClusterProperties.setScope(BeanDefinition.SCOPE_PROTOTYPE);
    }
}
