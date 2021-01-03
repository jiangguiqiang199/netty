package com.carrot.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Author: Created by carrot
 * 2021/1/3 11:34
 */
public class Hacker implements MethodInterceptor {

    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("**** I am a hacker,Let's see what the poor programmer is doing Now...");
        methodProxy.invokeSuper(obj, args);
        System.out.println("****  Oh,what a poor programmer.....");
        return null;
    }
}
