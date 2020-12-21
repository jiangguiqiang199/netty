package com.carrot.pool.queue;

/**
 * @Author: Created by carrot
 * 2020/11/25 14:37
 */
public interface Queue<E> {

    /**
     * 队列是否为空
     * @return
     */
    public boolean isEmpty();

    /**
     * 获取并删除队列头节点 如果队列为空则返回null
     * @return
     */
    public E poll();

    /**
     * 添加元素，如果队列已满，则返回false
     * @param data
     * @return
     */
    public boolean offer(E data);
}
