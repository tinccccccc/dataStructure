package com.example;

import java.util.*;

public class Test {

    public static void main(String[] args){
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(0);
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
//        linkedList.add(2,9);
        StringJoiner joiner = new StringJoiner("   ");
        for (Integer a : linkedList) {
            joiner.add(Integer.toString(a));
        }
        System.out.println(linkedList.get(2));
    }
}
