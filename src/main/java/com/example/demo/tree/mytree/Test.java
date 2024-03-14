package com.example.demo.tree.mytree;

import com.example.demo.tree.mytree.util.BinaryTrees;

public class Test {
    public static void main(String[] args){
        BinarySearchTree<Integer> avl = new AVLTree<>();
        avl.add(1);
        avl.add(3);
        avl.add(5);
        avl.add(2);
        avl.add(4);
        avl.add(9);
        avl.add(8);
        avl.remove(2);
        avl.remove(8);
        avl.remove(9);
        BinaryTrees.print(avl);
    }
}
