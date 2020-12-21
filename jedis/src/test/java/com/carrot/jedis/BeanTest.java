package com.carrot.jedis;

import com.carrot.jedis.config.Animal;
import com.carrot.jedis.processor.MyBeanImporter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

/**
 * @Author: Created by carrot
 * 2020/12/15 17:00
 */
@Slf4j
@SpringBootTest
@Import(MyBeanImporter.class)
public class BeanTest {

    @Autowired
    @Qualifier("dog")
    private Animal animal;

    @Test
    public void testBean(){
        log.info(animal.getName());
    }

}
