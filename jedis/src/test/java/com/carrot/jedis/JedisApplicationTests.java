package com.carrot.jedis;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.TestPropertySource;
import redis.clients.jedis.JedisCluster;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Slf4j
//@TestPropertySource({"demo.properties"})
@SpringBootTest(properties = {"baidu=www.baidu.com"})
class JedisApplicationTests {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private JedisCluster jedisCluster;

    @Test
    void contextLoads() {
        int count = 0;
        while (true){
            try {
            count++;
            int identifiedStr = new Random().nextInt(1000000);
            String key = "k-"+identifiedStr;
            String value = "v-"+identifiedStr;
            jedisCluster.set(key,value);
            jedisCluster.get(key);
            if(count % 5 == 0) {
                log.info("{} value is {}", key, value);
            }
            TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                log.error("【线程被中断】",e);
            } catch (Exception e){
                log.error("【集群出问题了】",e);
            }
        }
    }

    @Test
    void pipleLineTest(){
        long start = System.currentTimeMillis();
        for(int i=0;i<200000;i++){

        }
        log.info("【普通设置方式，20万条耗时:{}ms】",System.currentTimeMillis()-start);
        long start1 = System.currentTimeMillis();
        log.info("【pipleline设置方式，20万条耗时:{}ms】",System.currentTimeMillis()-start1);
    }

    public void testProperties(){
        System.out.println();
    }

}
