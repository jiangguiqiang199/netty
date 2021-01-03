package com.carrot.classloader;

/**
 * @Author: Created by carrot
 * 2021/1/2 10:00
 */
public class MyClassLoader extends ClassLoader {

    public Class<?> defineMyClass(String name,byte[] b,int off,int len){
        return super.defineClass(name,b,off,len);
    }

    public Class<?> defineMyClass(byte[] b,int off,int len){
        return super.defineClass(b,off,len);
    }
}
