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
//package com.example.collectionstudy.interfacee;
//
//import java.util.Objects;
//import java.util.Spliterator;
//import java.util.Spliterators;
//import java.util.function.Predicate;
//import java.util.stream.Stream;
//import java.util.stream.StreamSupport;
//
//
//public interface Collection<E> extends Iterable<E> {
//
//    int size();
//
//    boolean isEmpty();
//
//    boolean contains(Object o);
//
//    Iterator<E> iterator();
//
//    Object[] toArray();
//
//    <T> T[] toArray(T[] a);
//
//
//    boolean add(E e);
//
//    boolean remove(Object o);
//
//
//    boolean containsAll(Collection<?> c);
//
//    boolean addAll(Collection<? extends E> c);
//
//    boolean removeAll(Collection<?> c);
//
//    default boolean removeIf(Predicate<? super E> filter) {
//        Objects.requireNonNull(filter);
//        boolean removed = false;
//        final Iterator<E> each = iterator();
//        while (each.hasNext()) {
//            if (filter.test(each.next())) {
//                each.remove();
//                removed = true;
//            }
//        }
//        return removed;
//    }
//
//    boolean retainAll(Collection<?> c);
//
//    void clear();
//
//
//    // Comparison and hashing
//
//    boolean equals(Object o);
//
//    int hashCode();
//
//    @Override
//    default Spliterator<E> spliterator() {
//        return Spliterators.spliterator(this, 0);
//    }
//
//    default Stream<E> stream() {
//        return StreamSupport.stream(spliterator(), false);
//    }
//
//    default Stream<E> parallelStream() {
//        return StreamSupport.stream(spliterator(), true);
//    }
//}
