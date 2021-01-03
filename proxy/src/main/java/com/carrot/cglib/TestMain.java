package com.carrot.cglib;

import net.sf.cglib.proxy.Enhancer;

/**
 * 1、查找A上的所有非final 的public类型的方法定义
 * 2、将这些方法的定义转换成字节码
 * 3、将组成的字节码转换成相应的代理的class对象
 * 4、实现 MethodInterceptor接口，用来处理 对代理类上所有方法的请求（这个接口和JDK动态代理InvocationHandler的功能和角色是一样的）
 * @Author: Created by carrot
 * 2021/1/3 11:38
 */
public class TestMain {

    public static void main(String[] args) {
        Programmer progammer = new Programmer();

        Hacker hacker = new Hacker();
        //cglib 中加强器，用来创建动态代理
        Enhancer enhancer = new Enhancer();
        //设置要创建动态代理的类
        enhancer.setSuperclass(progammer.getClass());
        // 设置回调，这里相当于是对于代理类上所有方法的调用，都会调用CallBack，而Callback则需要实行intercept()方法进行拦截
        enhancer.setCallback(hacker);
        Programmer proxy =(Programmer)enhancer.create();
        proxy.code();
    }
}
