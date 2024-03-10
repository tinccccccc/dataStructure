package com.example.demo.heap;

import java.util.*;

public class Test {
    public static void main(String[] args){
        TreeSet<Integer> treeSet = new TreeSet<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 == null) return -1;
                if (o2 == null) return 1;
                return 0;
            }
        });
        treeSet.add(null);
        treeSet.add(null);

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(null);
//        PriorityQueue<Integer> queue = new PriorityQueue<>(treeSet);
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return 0;
            }
        });
        queue.add(null);
        queue.add(1);

        System.out.println("");
    }
}
