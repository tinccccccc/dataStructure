package com.example.demo.tree.mytree;


import com.example.demo.tree.mytree.util.BinaryTreeInfo;

import java.util.Comparator;

/**
 * 二叉搜索树
 */
public class BinarySearchTree<E> implements Tree<E>, BinaryTreeInfo {

    int size;

    Node<E> root;

    Comparator<E> comparator;

    BinarySearchTree(){

    }

    BinarySearchTree(Comparator<E> comparator){
        this.comparator = comparator;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(E e) {
        //第一次添加元素
        if (root == null){
            root = new Node<>(e);
            size ++;
            return;
        }

        //非第一次添加元素，查找应该插入的位置
        int comp = 0;
        Node<E> node = root;
        Node<E> parent = root;
        while (node != null){
            parent = node;
            comp = compare(e,node.val);
            if (comp > 0){
                node = node.right;
            }else if (comp < 0){
                node = node.left;
            }else {
                node.val = e;
                return;
            }
        }
        size ++;
        if (comp > 0){
            parent.right = new Node<>(e,parent);
        }else {
            parent.left = new Node<>(e,parent);
        }
    }

    @Override
    public void remove(E e) {
        remove(node(e));
    }

    private void remove(Node<E> node){
        if (node == null) return;
        size --;
        //删除度为2的节点
        if (node.left != null && node.right != null){
            Node<E> succesor = successor(node);
            node.val = succesor.val;
            //转化为删除度为1 或 0 的节点
            node = succesor;
        }

        //删除度为1 或 0 的节点
        Node<E> replace = node.left == null ? node.right : node.left;
        if (replace != null){
            //度 为 1
            replace.parent = node.parent;
            if (node.parent == null){
                root = replace;
            }else if (node == node.parent.left){
                node.parent.left = replace;
            }else {
                node.parent.right = replace;
            }
        }else if (node.parent == null){
            //是叶子节点并且是跟节点
            root = null;
        }else {
            //是叶子节点，但不是跟节点
            if (node.parent.left == node){
                node.parent.left = null;
            }else {
                node.parent.right = null;
            }
        }
    }

    /**
     * 根据元素值查找节点
     */
    public Node<E> node(E e){
        Node<E> node = root;
        while (node != null){
            int comp = compare(e,node.val);
            if (comp > 0){
                node = node.right;
            }else if (comp < 0){
                node = node.left;
            }else {
                return node;
            }
        }
        return null;
    }

    @Override
    public int compare(E o1, E o2) {
        if (comparator != null){
            return comparator.compare(o1,o2);
        }
        Comparable<? super E> key = (Comparable<? super E>) o1;
        return key.compareTo(o2);
    }


    /**
     * 获取 node 节点的前驱节点
     * 中序遍历的前一个节点
     */
    private Node<E> predecessor(Node<E> node){
        if (node == null)
            return null;
        //1.有左子树，前驱节点在左子树中
        Node<E> target = node.left;
       if (target != null){
           while (node.right != null){
               node = node.right;
           }
           return node;
       }

        //2.无左子树，则前驱节点在祖父节点中
        while (node.parent != null && node.parent.left == node){
            node = node.parent;
        }
        return node.parent;
    }

    /**
     * 获取 node 节点的后驱节点
     * 中序遍历的后一个节点
     */
    public Node<E> successor(Node<E> node){
        if (node == null)
            return null;

        //1.有右子树，后驱节点在右子树中
        Node<E> target = node.right;
        if (target != null){
            while (target.left != null){
                target = target.left;
            }
            return target;
        }

        //2.无右子树，后驱节点在祖父节点中
        while (node.parent != null && node.parent.right == node){
            node = node.parent;
        }
        return node.parent;
    }

    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((Node<E>)node).left;
    }

    @Override
    public Object right(Object node) {
        return ((Node<E>)node).right;

    }

    @Override
    public Object string(Object node) {
        Node<E> myNode = (Node<E>) node;
        String parentStr = "null";
        if (myNode.parent != null) {
            parentStr = myNode.parent.val.toString();
        }
        return myNode.val + "_p(" + parentStr + ")";
    }

    public static class Node<E>{
        public E val;
        public Node<E> left;
        public Node<E> right;
        public Node<E> parent;

        Node(){

        }

        Node(E val){
            this(val,null,null,null);
        }

        Node(E val, Node<E> parent){
            this(val,null,null,parent);
        }

        Node(E val, Node<E> left, Node<E> right, Node<E> parent){
            this.val = val;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }
    }
}
