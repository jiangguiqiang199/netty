package com.carrot.classloader;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Created by carrot
 * 2020/12/4 16:54
 */
@Slf4j
public class B extends A {

    static {
        log.info("B - 3");
    }
    public B(){
        log.info("B - 4");
    }

    public static void main(String[] args) {
        // 输出结果
//        2020-12-04 16:56:17 INFO  main  com.carrot.classloader.A:12 >>> A - 1
//        2020-12-04 16:56:17 INFO  main  com.carrot.classloader.B:12 >>> B - 3
//        2020-12-04 16:56:17 INFO  main  com.carrot.classloader.A:15 >>> A - 2
//        2020-12-04 16:56:17 INFO  main  com.carrot.classloader.B:15 >>> B - 4
        // 结论：子类构造函数调用，先调用父类的static，在调用子类的static 然后调用父类的构造方法，最后调用子类的构造方法
        new B();
    }
}
