package com.example;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

public class Test {

    public static void main(String[] args){
//        Vector<User> vector = new Vector<>(5);
//        vector.add(new User());
//        vector.add(new User());
//        Iterator<User> viterator = vector.iterator();
//        System.out.println(viterator.next());
//        System.out.println(viterator.next());
//        System.out.println(viterator.next());
        System.out.println("----------------");
        ArrayList<User> arrayList = new ArrayList<>(5);
        arrayList.add(new User());
        arrayList.add(new User());
        Iterator<User> aiterator = arrayList.iterator();
        System.out.println(aiterator.next());
        System.out.println(aiterator.next());
        System.out.println(aiterator.next());
    }
}
