package com.carrot.jedis.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: Created by carrot
 * 2020/12/9 17:20
 */
@Component
@ConfigurationProperties(prefix = "spring.redis.cluster")
@Data
public class JedisClusterProperties {

    private String nodes;

    private int maxRedirects;

    private int expireSeconds;

    private int commandTimeout;
}
