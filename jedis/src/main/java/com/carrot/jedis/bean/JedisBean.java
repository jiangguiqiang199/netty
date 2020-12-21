package com.carrot.jedis.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;

/**
 * @Author: Created by carrot
 * 2020/12/13 14:27
 */
@Slf4j
public class JedisBean implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("【bean：{}，初始化完毕】",JedisBean.class);
    }
}
