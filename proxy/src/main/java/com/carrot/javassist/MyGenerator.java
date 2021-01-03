package com.carrot.javassist;

import javassist.*;

import java.io.IOException;

/**
 * @Author: Created by carrot
 * 2021/1/3 11:18
 */
public class MyGenerator {

    public static void main(String[] args) throws CannotCompileException, IOException {
        ClassPool pool = ClassPool.getDefault();
        //创建Programmer类
        CtClass cc= pool.makeClass("com.carrot.Programmer");
        //定义code方法
        CtMethod method = CtNewMethod.make("public void code(){}", cc);
        //插入方法代码
        method.insertBefore("System.out.println(\"I'm a Programmer,Just Coding.....\");");
        cc.addMethod(method);
        //保存生成的字节码
        cc.writeFile("d://temp");
    }
}
