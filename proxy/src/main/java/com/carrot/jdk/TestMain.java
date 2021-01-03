package com.carrot.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 在java运行期生成二进制字节码，转换成响应的类
 * @Author: Created by carrot
 * 2021/1/2 9:30
 */
public class TestMain implements InvocationHandler {
    
    public static void main(String[] args) {
        // 生成代理对象
        Object o = Proxy.newProxyInstance(TestMain.class.getClassLoader(), new Class[]{TestInterface.class}, new TestMain());
        // 调用代理对象上的代理方法
        TestInterface object = (TestInterface)o;
        object.sayHello("jiangguiqiang");
    }

    // 代理方法
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("进入了代理方法");
        TestInterface target = new TestInterfaceImpl();
        return method.invoke(target,args);
    }
}
