package com.example.demo.tree.rbtree;

import com.example.demo.tree.mytree.util.BinaryTrees;

public class Main {
    public static void main(String[] args){
        RBTree<Integer> rbTree = new RBTree<>();
        for (int i = 0; i < 10; i++) {
            rbTree.add(i);
        }
        for (int i = 0; i < 10; i++) {
            rbTree.remove(i);
        }
//        BinaryTrees.print(rbTree);
    }
}
