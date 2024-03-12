package com.example.demo.tree.mytree;

import com.example.demo.tree.mytree.util.BinaryTrees;

public class Test {
    public static void main(String[] args){
        BinarySearchTree<Integer> searchTree = new BinarySearchTree<>();
        searchTree.add(1);
        searchTree.add(2);
        searchTree.add(3);
        searchTree.add(7);
        searchTree.add(9);
        searchTree.add(5);
        searchTree.add(4);
        searchTree.add(6);
        searchTree.remove(7);
        BinaryTrees.print(searchTree);
    }
}
