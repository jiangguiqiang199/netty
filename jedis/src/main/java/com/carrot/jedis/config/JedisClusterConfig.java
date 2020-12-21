package com.carrot.jedis.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Author: Created by carrot
 * 2020/12/9 16:32
 */
@Slf4j
@Configuration
public class JedisClusterConfig {

    @Autowired
    private JedisClusterProperties redisProperties;

    @Bean
    public JedisCluster getJedisCluster(){
        //获取redis集群的ip及端口号等相关信息；
        String[] serverArray = redisProperties.getNodes().split(",");
        Set<HostAndPort> nodes = new HashSet<>();

        //遍历add到HostAndPort中；
        for (String ipPort : serverArray) {
            String[] ipPortPair = ipPort.split(":");
            nodes.add(new HostAndPort(ipPortPair[0].trim(), Integer.valueOf(ipPortPair[1].trim())));
        }
        //构建对象并返回；
        JedisCluster jedisCluster = new JedisCluster(nodes, redisProperties.getCommandTimeout());
        Map<String, JedisPool> clusterNodes = jedisCluster.getClusterNodes();
        log.info("clustNodes:{}",clusterNodes);
        return jedisCluster;
    }
}
