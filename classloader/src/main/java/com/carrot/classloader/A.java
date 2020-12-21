package com.carrot.classloader;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Created by carrot
 * 2020/12/4 16:53
 */
@Slf4j
public class A {
    static {
       log.info("A - 1");
    }
    public A(){
        log.info("A - 2");
    }
}
