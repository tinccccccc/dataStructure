package com.example.demo.tree.mytree;

public interface Tree<E> {

    /**
     * 获取节点数量
     */
    int size();

    /**
     * 添加元素
     */
    void add(E e);

    /**
     * 删除元素
     */
    void remove(E e);

    /**
     * 元素比较
     */
    int compare(E o1, E o2);
}
