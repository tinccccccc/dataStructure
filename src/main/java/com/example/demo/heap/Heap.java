package com.example.demo.heap;

public interface Heap<E>{

    /**
     * 元素个数
     */
    int size();

    /**
     * 是否为空
     */
    boolean isEmpty();

    /**
     * 清空
     */
    void clear();

    /**
     * 添加元素
     */
    void add(E element);

    /**
     * 获取堆顶元素
     */
    E get();

    /**
     * 删除堆顶元素
     */
    E remove();

    /**
     * 删除对顶元素的同时插入一个新的元素
     */
    E replace(E element);
}
