package com.carrot.jedis.processor;

import com.carrot.jedis.config.Dog;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @Author: Created by carrot
 * 2020/12/15 17:17
 */
public class MyBeanImporter implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        System.out.println("【调用ImportBeanDefinitionRegistrar.registerBeanDefinitions】");
        RootBeanDefinition rootBeanDefinition = new RootBeanDefinition();
        rootBeanDefinition.setBeanClass(Dog.class);
        registry.registerBeanDefinition("dog",rootBeanDefinition);
        System.out.println("【完成注入Dog类】");
    }
}
