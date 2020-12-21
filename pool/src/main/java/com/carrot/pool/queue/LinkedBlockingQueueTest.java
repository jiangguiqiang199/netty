package com.carrot.pool.queue;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * 关键要素:
 * 1、capacity 容量
 * 2、count 容器中元素个数
 * 3、ReentrantLock putLock 可重入锁
 * 4、Condition notEmpty 是否为空
 * 5、Node<E> head 头节点
 * 6、Node<E> last 尾结点
 * @Author: Created by carrot
 * 2020/11/27 9:12
 */
@Slf4j
public class LinkedBlockingQueueTest {

    public static void main(String[] args) {
        LinkedBlockingQueue<User> queue = new LinkedBlockingQueue<User>();
        // 0、输出元素
        log.info("【初始化后，队列信息:{}】",queue.toString());
        // 1、添加2个元素
        // add方法 如果添加失败，会抛异常
        queue.add(new User("jiang",36));
        // offer方法 如果添加失败 返回false
        queue.offer(new User("gui",37));
        queue.offer(new User("gui",35));
        queue.offer(new User("qiang",34));
        // 2、输出元素
        log.info("【添加2个元素后，队列信息:{}】",queue.toString());
        // 3、删除元素
        // remove方法 获取并删除头节点，如果队列为空抛出异常
        User header = queue.remove();
        log.info("【获取并删除了头节点{}】",header);
        // poll方法 获取并删除头节点，如果队列为空返回null
//        queue.remove();
//        queue.remove();
//        log.info("【queue.poll():{}】",queue.poll());
//        log.info("【queue.poll():{}】",queue.poll());
        // 4、输出元素
        log.info("【删除元素后，队列信息:{}】",queue.toString());
        // 5、多线程添加元素
        // 6、输出元素
        log.info("【多线程添加元素后，队列信息:{}】",queue.toString());
        // 7、多线程删除元素
        log.info("【多线程删除元素后，队列信息:{}】",queue.toString());
    }

    @Data
    static
    class User{
        private String name;
        private Integer age;

        public User(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
