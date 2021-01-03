package com.carrot.jdk;

/**
 * @Author: Created by carrot
 * 2021/1/2 9:29
 */
public class TestInterfaceImpl implements TestInterface {

    public void sayHello(String msg) {
        System.out.println("hello:"+msg);
    }
}
