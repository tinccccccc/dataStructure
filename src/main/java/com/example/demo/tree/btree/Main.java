package com.example.demo.tree.btree;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        BTree<Integer,Integer> bTree = new BTree<>(4);
        Random random = new Random();
        int num = 30;
        for (int i = 18; i >= 0; i--) {
            bTree.put(i,i);
        }
        bTree.print();
        System.out.println("-----------------------------");
        for (int i = 19; i >= 0; i--) {
            int n = random.nextInt(30);
            bTree.remove(n);
        }
        bTree.remove(18);
        bTree.print();
    }

}