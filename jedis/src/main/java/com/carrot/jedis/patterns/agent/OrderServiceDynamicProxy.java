package com.carrot.jedis.patterns.agent;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态的生成class
 * @Author: Created by carrot
 * 2020/12/22 10:06
 */
public class OrderServiceDynamicProxy implements InvocationHandler {

    // 需要代理的目标对象
    private Object target;

    // 通过构造器的方式将目标对象传进来
    public OrderServiceDynamicProxy(Object target) {
        this.target = target;
    }

    public Object bind(){
        // 需要被代理的目标类的class
        Class cls = target.getClass();
        return Proxy.newProxyInstance(cls.getClassLoader(),cls.getInterfaces(),this);
    }

    /**
     *
     * @param proxy 动态生成的代理类，里面包含了需要被增强的方法名method和需要被增强的方法的参数args
     * @param method 需要被增强的方法名
     * @param args 需要被增强的方法的参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object argObject = args[0];
        beforeMethod(argObject);
        Object object = method.invoke(target,args);
        afterMethod();
        return object;
    }

    private void beforeMethod(Object obj){
//        int userId = 0;
//        System.out.println("动态代理 before code");
//        if(obj instanceof Order){
//            Order order = (Order)obj;
//            userId = order.getUserId();
//        }
//        int dbRouter = userId % 2;
//        System.out.println("动态代理分配到【db"+dbRouter+"】处理数据");
//
//        //todo 设置dataSource;
//        DataSourceContextHolder.setDBType("db"+String.valueOf(dbRouter));
    }

    private void afterMethod(){
        System.out.println("动态代理 after code");
    }
}
