package com.example.demo.heap;

import java.util.Comparator;

/**
 * 大顶堆的简单实现
 *
 * @param <E>
 */
public class BinaryHeap<E> implements Heap<E> {

    private E[] elements;

    private int size;

    private Comparator<E> comparator;

    private static final int DEFAULT_CAPACITY = 10;

    public BinaryHeap(Comparator<E> comparator){
        this.comparator = comparator;
        this.elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public BinaryHeap(){
        this(null);
    }

    private int compare(E e1, E e2){
        return comparator != null? comparator.compare(e1,e2): ((Comparable<E> )e1).compareTo(e2);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    @Override
    public void add(E element) {
        //检查扩容(略)
        //将元素添加到数组末尾
        elements[size ++] = element;
        //调整新添加元素子在堆中的位置(上浮）
        siftUp(size - 1);
    }

    /**
     * 从 index 位置开始进行上滤操作,其实就是不停的和父节点比较和调整，找到属于自己的位置
     */
    private void siftUp(int index){
        E e = elements[index];
        while (index > 0){
            //父节点
            int pi = (index - 1) >> 1;
            E pv = elements[pi];
            if (compare(e,pv) <= 0) return;
            swap(index,pi);
            index = pi;
        }
    }

    private void swap(int a, int b){
        E temp = elements[a];
        elements[a] = elements[b];
        elements[b] = temp;
        //拓展下，数据交换(数字类型)也可以用位运算代替
//        elements[a] ^= elements[b];
//        elements[b] ^= elements[a];
//        elements[a] ^= elements[b];
    }
    //获取堆顶元素
    @Override
    public E get() {
        emptyCheck();
        return elements[0];
    }

    //删除堆顶元素。 交换删除 + 下浮
    @Override
    public E remove() {
        emptyCheck();
        E e = elements[0];
        swap(0, -- size);
        elements[size] = null;
        //下浮
        siftDown(0);
        return e;
    }

    // 下浮
    public void siftDown(int index){
        E e = elements[index];
        //完全二叉树的非叶子节点数量 公式  n = size / 2
        int half = size >> 1;
        while (index < half){

            //和左右子节点中最大的节点进行交换
            int left = (index << 1) + 1;
            E leftVal = elements[left];
            //判断有无右子节点
            int right = left + 1;
            if (right < size && compare(elements[right],leftVal) > 0){
                left = right;
                leftVal = elements[right];
            }

            //判断当前节点和子节点是否需要交换
            if (compare(e, leftVal) < 0) break;

            //交换
            elements[index] = leftVal;
            index = left;
        }
        //找到了合适的位置了
        elements[index] = e;
    }


    @Override
    public E replace(E element) {
        elementNotNullCheck(element);
        E remove = null;
        if (size == 0){
            elements[0] = element;
            size ++;
        }else {
            remove = elements[0];
            elements[0] = element;
            siftDown(0);
        }
        siftDown(0);
        return remove;
    }

    /**
     * 批量建堆： 直接给一对无规律的数据建堆
     *      方法一: 自上而下的上浮（相对较慢，每个节点都要进行上浮）
     *      方法二：自下而上的下浮（更快，因为叶子节点无需下浮，可直接跳过）,我们采用此方法
     */
    public void heapify(){
//        //自上而下的上滤
//        for (int i = 1; i < size; i++) {
//            siftUp(i);
//        }
        //自上而下的下虑
        for (int i = (size >> 1) - 1; i >= 0; i -- ) {
            siftDown(i);
        }
    }

    private void emptyCheck(){
        if (size == 0)
            throw new IndexOutOfBoundsException("Heap is Empty");
    }

    private void elementNotNullCheck(E element){
        if (element == null)
            throw new IllegalArgumentException("element must not be null");
    }


}
