package com.carrot.pool.queue;

import java.util.Arrays;

/**
 * 基于数组的方式实现队列
 * @Author: Created by carrot
 * 2020/11/25 14:33
 */
public class ArrayQueue<E> implements Queue<E>{
    // 标识最后数组中最后一个元素的位置
    int size = 0;
    Object[] queue;

    /**
     * 初始化容量为10
     */
    public ArrayQueue() {
        queue = new Object[10];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E poll() {
        if(queue.length == 0){
            return null;
        }
        // 获取当前数组第0号元素
        E data = (E) queue[0];
        // 复制当前数组剩余的数组至新的数组
        System.arraycopy(queue, 1, queue, 0, size-1);
        return data;
    }

    @Override
    public boolean offer(E data) {
        // 在数组尾部添加元素
        if(size >  queue.length){
            // 数据已满，对数组进行扩容
            int len = queue.length + 10;
            //复制指定的数组内容以达到扩容的目的
            queue = Arrays.copyOf(queue, len);
        } else {
            queue[size++] = data;
        }
        // 2、将数组length位置赋值为 E data
        return false;
    }

}
