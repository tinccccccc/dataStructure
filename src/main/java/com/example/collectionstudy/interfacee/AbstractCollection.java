///*
// * Copyright (c) 1997, 2013, Oracle and/or its affiliates. All rights reserved.
// * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
// *
// *
// *
// *
// *
// *
// *
// *
// *
// *
// *
// *
// *
// *
// *
// *
// *
// *
// *
// *
// */
//
//package com.example.collectionstudy.interfacee;
//
//import java.util.Arrays;
//import java.util.Objects;
//
//
//
//public abstract class AbstractCollection<E> implements Collection<E> {
//
//    /**
//     * protected 限制该方法只能被子类或者同一包下调用，但是当前类是抽象类，其实不管用public 或者 protected 修饰都是这能子类调用
//     * 但是：抽象类的构造函数一般隐式的，规范写，不然写个public多奇怪。
//     */
//    protected AbstractCollection() {
//    }
//
//    /**
//     * 返回一个迭代器
//     */
//    public abstract Iterator<E> iterator();
//
//    /**
//     * 返回集合的大小
//     */
//    public abstract int size();
//
//    /**
//     *  判断集合是否为空
//     */
//    public boolean isEmpty() {
//        return size() == 0;
//    }
//
//    /**
//     * 查询集合中是否包含某一元素，该元素包含null
//     *
//     */
//    public boolean contains(Object o) {
//        Iterator<E> it = iterator();
//        //这里对null 做了单独的处理，也说明了是支持查询null的，因为有些集合是允许存储null的。
//        if (o==null) {
//            while (it.hasNext())
//                if (it.next()==null)
//                    return true;
//        } else {
//            while (it.hasNext())
//                if (o.equals(it.next()))
//                    return true;
//        }
//        return false;
//    }
//
//    /**
//     * 将元素转化为数组返回，虽然当并发场景下迭代器中的元素减少或增多可以被感知并进行响应的处理，
//     * 但是仍然有并发问题，该元素已经被填充了，后续还有数据，这时虽然元素数量减少了，但是会认为是后续的元素被删了，
//     * 但其实不是，就会导致并发问题。再举个例子，不修改元素数量，修改已被遍历的元素值，也会有并发问题。
//     */
//    public Object[] toArray() {
//        // Estimate size of array; be prepared to see more or fewer elements
//        Object[] r = new Object[size()];
//        Iterator<E> it = iterator();
//        for (int i = 0; i < r.length; i++) {
//            if (! it.hasNext()) // fewer elements than expected
//                //检查元素是否少了
//                return Arrays.copyOf(r, i);
//            r[i] = it.next();
//        }
//        //检查元素是否变多了
//        return it.hasNext() ? finishToArray(r, it) : r;
//    }
//
//    /**
//     *  将集合中的元素放入数组 a 中并返回
//     */
//    @SuppressWarnings("unchecked")
//    public <T> T[] toArray(T[] a) {
//        // Estimate size of array; be prepared to see more or fewer elements
//        int size = size();
//        T[] r = a.length >= size ? a :
//                  (T[])java.lang.reflect.Array
//                  .newInstance(a.getClass().getComponentType(), size);
//        Iterator<E> it = iterator();
//
//        for (int i = 0; i < r.length; i++) {
//            if (! it.hasNext()) { // fewer elements than expected
//                if (a == r) {
//                    r[i] = null; // null-terminate
//                } else if (a.length < i) {
//                    return Arrays.copyOf(r, i);
//                } else {
//                    System.arraycopy(r, 0, a, 0, i);
//                    if (a.length > i) {
//                        a[i] = null;
//                    }
//                }
//                return a;
//            }
//            r[i] = (T)it.next();
//        }
//        // more elements than expected
//        return it.hasNext() ? finishToArray(r, it) : r;
//    }
//
//    /**
//     * 为了避免出现整数溢出的情况，MAX_ARRAY_SIZE 的值被设定为 Integer.MAX_VALUE - 8。
//     * 通过将8个空位预留出来，可以确保在新数组容量接近 Integer.MAX_VALUE 时，仍然有足够的空间进行扩容。
//     * 需要注意的是，此处选择 Integer.MAX_VALUE - 8 作为 MAX_ARRAY_SIZE 的值是相对合理的，并不是固定的规定。
//     * 在不同的实现或不同的上下文中，可能会选择其他的值来避免整数溢出问题。
//     */
//    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
//
//    /**
//     * 当迭代器中的元素比数组中的元素多时，继续遍历迭代器，将元素添加到数组中
//     */
//    @SuppressWarnings("unchecked")
//    private static <T> T[] finishToArray(T[] r, Iterator<?> it) {
//        int i = r.length;
//        while (it.hasNext()) {
//            int cap = r.length;
//            if (i == cap) {
//                //将容量扩容50%，这里+1，是为了防止初始容量为1， 1 + （cap>>1）+1 = 1,相当于没扩容，所以要 +1 防止这种情况
//                int newCap = cap + (cap >> 1) + 1;
//                // overflow-conscious code
//                if (newCap - MAX_ARRAY_SIZE > 0)
//                    newCap = hugeCapacity(cap + 1);
//                r = Arrays.copyOf(r, newCap);
//            }
//            r[i++] = (T)it.next();
//        }
//        // trim if overallocated
//        return (i == r.length) ? r : Arrays.copyOf(r, i);
//    }
//
//    /**
//     *  当容量过大是，重新调整容量
//     */
//    private static int hugeCapacity(int minCapacity) {
//        if (minCapacity < 0) // overflow
//            throw new OutOfMemoryError
//                ("Required array size too large");
//        return (minCapacity > MAX_ARRAY_SIZE) ?
//            Integer.MAX_VALUE :
//            MAX_ARRAY_SIZE;
//    }
//
//    // Modification Operations
//
//    /**
//     * 添加元素
//     * @param e element whose presence in this collection is to be ensured
//     * @return
//     */
//    public boolean add(E e) {
//        throw new UnsupportedOperationException();
//    }
//
//    /**
//     * 移除元素，同样支持 null
//     */
//    public boolean remove(Object o) {
//        Iterator<E> it = iterator();
//        if (o==null) {
//            while (it.hasNext()) {
//                if (it.next()==null) {
//                    it.remove();
//                    return true;
//                }
//            }
//        } else {
//            while (it.hasNext()) {
//                if (o.equals(it.next())) {
//                    it.remove();
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
//
//
//    // Bulk Operations
//
//    /**
//     * 判断集合中是否包含 c集合中所有的元素
//     */
//    public boolean containsAll(Collection<?> c) {
//        for (Object e : c)
//            if (!contains(e))
//                return false;
//        return true;
//    }
//
//    /**
//     * 将集合 c 中的所有元素添加到当前集合集合中
//     */
//    public boolean addAll(Collection<? extends E> c) {
//        boolean modified = false;
//        for (E e : c)
//            if (add(e))
//                modified = true;
//        return modified;
//    }
//
//    /**
//     * 将目标集合中所有包含 集合c 的元素全部删除, 求差集
//     */
//    public boolean removeAll(Collection<?> c) {
//        Objects.requireNonNull(c);
//        boolean modified = false;
//        Iterator<?> it = iterator();
//        while (it.hasNext()) {
//            if (c.contains(it.next())) {
//                it.remove();
//                modified = true;
//            }
//        }
//        return modified;
//    }
//
//    /**
//     * 将目标集合中所有 不包含 集合c 的元素全部删除, 求交集
//     */
//    public boolean retainAll(Collection<?> c) {
//        Objects.requireNonNull(c);
//        boolean modified = false;
//        Iterator<E> it = iterator();
//        while (it.hasNext()) {
//            if (!c.contains(it.next())) {
//                it.remove();
//                modified = true;
//            }
//        }
//        return modified;
//    }
//
//    /**
//     *  将当前集合元素全部删除
//     */
//    public void clear() {
//        Iterator<E> it = iterator();
//        while (it.hasNext()) {
//            it.next();
//            it.remove();
//        }
//    }
//
//    public String toString() {
//        Iterator<E> it = iterator();
//        if (! it.hasNext())
//            return "[]";
//
//        StringBuilder sb = new StringBuilder();
//        sb.append('[');
//        for (;;) {
//            E e = it.next();
//            sb.append(e == this ? "(this Collection)" : e);
//            if (! it.hasNext())
//                return sb.append(']').toString();
//            sb.append(',').append(' ');
//        }
//    }
//
//}
