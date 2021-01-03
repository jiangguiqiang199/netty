package com.carrot.classloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * 类加载器器试验：手动加载class文件字节码到JVM中，转换成class对象，进行实例化
 * 1、读取本地class文件
 * 2、通过本地class文件用类加载器生成类对象
 * 3、获取该类对象上的方法，进行调用
 * @Author: Created by carrot
 * 2021/1/2 10:10
 */
public class MyClassLoaderMain {

    public static void main(String[] args) throws IOException {
        // 1、读取本地class文件内的字节码，转换成字节码数组
        File file = new File("D:\\git\\netty\\proxy\\target\\classes\\com\\carrot\\classloader\\Programmer.class");
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] bytes = new byte[1024];
        int count = fileInputStream.read(bytes);
        // 2、使用类加载器将字节码数组转换为对应的class对象
        MyClassLoader myClassLoader = new MyClassLoader();
//        Class<?> clazz = myClassLoader.defineMyClass(bytes, 0, count);
        Class<?> clazz = myClassLoader.defineMyClass("com.carrot.classloader.Programmer",bytes, 0, count);
        System.out.println(clazz.getCanonicalName());
        try {
            Object o = clazz.newInstance();
            clazz.getMethod("code",null).invoke(o,null);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
