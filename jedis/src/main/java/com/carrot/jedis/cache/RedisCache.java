package com.carrot.jedis.cache;

import com.carrot.jedis.config.JedisClusterProperties;
import org.apache.ibatis.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.io.*;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * mybatis自定义缓存
 * 采用Hash的数据结构 Hash: key --> field --> value
 * @Author: Created by carrot
 * 2020/12/21 10:03
 */
public class RedisCache implements Cache {

    private String mybatisKey = "myBatisKey";

    private String nodes;

    private int commandTimeout;

    /**
     *  myBatis自己生成的
     */
    private final String id;

    public RedisCache(String id) {
        this.id = id;
    }

    /**
     * myBatis要求每一份缓存都有一个唯一标识
     * @return
     */
    @Override
    public String getId() {
        return id;
    }

    private byte[] object2Byte(Object obj){
        try {
            ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(obj);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            return null;
        }
    }

    private Object bytes2Object(byte[] bytes){
        try {
            ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(bytes);
            ObjectInputStream objectInputStream=new ObjectInputStream(byteArrayInputStream);
            Object o = objectInputStream.readObject();
            return o;
        } catch (Exception e) {
           return null;
        }
    }

    /**
     * 因为不确定类型是什么，所以需要使用二进制作为key和value
     * @param key
     * @param value
     */
    @Override
    public void putObject(Object key, Object value) {
        if (null == key || value == null){
            return;
        }
        getJedisCluster().hsetnx(mybatisKey.getBytes(),object2Byte(key),object2Byte(value));
    }

    @Override
    public Object getObject(Object key) {
        byte[] result = getJedisCluster().hget(mybatisKey.getBytes(), object2Byte(key));
        return bytes2Object(result);
    }

    @Override
    public Object removeObject(Object key) {
        byte[] result = getJedisCluster().hget(mybatisKey.getBytes(), object2Byte(key));
        getJedisCluster().hdel(mybatisKey.getBytes(), object2Byte(key));
        return bytes2Object(result);
    }

    @Override
    public void clear() {
        getJedisCluster().del(mybatisKey.getBytes());
    }

    @Override
    public int getSize() {
        Map<byte[], byte[]> map = getJedisCluster().hgetAll(mybatisKey.getBytes());
        return map.size();
    }


    public JedisCluster getJedisCluster() {
        //获取redis集群的ip及端口号等相关信息；
        String[] serverArray = nodes.split(",");
        Set<HostAndPort> nodes = new HashSet<>();

        //遍历add到HostAndPort中；
        for (String ipPort : serverArray) {
            String[] ipPortPair = ipPort.split(":");
            nodes.add(new HostAndPort(ipPortPair[0].trim(), Integer.valueOf(ipPortPair[1].trim())));
        }
        //构建对象并返回；
        JedisCluster jedisCluster = new JedisCluster(nodes, commandTimeout);
        Map<String, JedisPool> clusterNodes = jedisCluster.getClusterNodes();
        return jedisCluster;
    }

    public String getNodes() {
        return nodes;
    }

    public void setNodes(String nodes) {
        this.nodes = nodes;
    }

    public int getCommandTimeout() {
        return commandTimeout;
    }

    public void setCommandTimeout(int commandTimeout) {
        this.commandTimeout = commandTimeout;
    }
}
