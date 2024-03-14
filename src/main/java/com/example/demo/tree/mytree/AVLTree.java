package com.example.demo.tree.mytree;

import java.util.Comparator;

/**
 * 最早的平衡二叉搜索树，和二叉搜索树几乎一样，区别在于：
 *  1.在添加节点之后增加了    afterAdd()  用于调整平衡
 *  2.在删除节点之后增加了    afterRemove()   用于调整平衡。
 */
public class AVLTree<E> extends BinarySearchTree<E> {

    private Comparator<E> comparator;

    AVLTree(){
    }

    AVLTree(Comparator<E> comparator){
        this.comparator = comparator;
    }

    public void add(E e){
        //第一次添加元素
        if (root == null){
            root = new AVLNode<>(e);
            size ++;
            //调整平衡
            afterAdd(root);
            return;
        }

        //非第一次添加元素，查找应该插入的位置
        int comp = 0;
        Node<E> node = root;
        Node<E> parent = root;
        while (node != null){
            comp = compare(e,node.val);
            parent = node;
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
        Node<E> newNode = new AVLNode<>(e, parent);
        if (comp > 0){
            parent.right = newNode;
        }else {
            parent.left = newNode;
        }
        //调整平衡
        afterAdd(newNode);
    }

    public void remove(E e){
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
        // 删除节点后的调整
        afterRemove(node);
    }

    /**
     * 添加节点后调整平衡
     *  分四种情况
     *      LL、LR、RR、RL
     */
    public void afterAdd(Node<E> node){
        while ((node = node.parent) != null){
            //已经平衡
            if (isBalanced(node)){
                updateHeight(node);
            }else{
                //恢复平衡
                reBalance(node);
                break;
            }
        }
    }

    /**
     * 删除节点后调整平衡
     *  分四种情况
     *      LL、LR、RR、RL
     */
    public void afterRemove(Node<E> node){
        while ((node = node.parent) != null){
            if (isBalanced(node)){
                updateHeight(node);
            }else {
                reBalance(node);
                break;
            }
        }
    }

    /**
     * 恢复平衡
     */
    public void reBalance(Node<E> grand){
        Node<E> parent = ((AVLNode<E>) grand).tallerChild();
        Node<E> child = ((AVLNode<E>) parent).tallerChild();
        if (((AVLNode<E>) parent).isLeftChild()){
            //L
            if (((AVLNode<E>) child).isLeftChild()){
                //LL
                rightRotate(grand);
            }else {
                //LR
                rightRotate(parent);
                leftRotate(grand);
            }
        }else {
            //R
            if (((AVLNode<E>) child).isLeftChild()){
                //RL
                leftRotate(parent);
                rightRotate(grand);
            }else {
                //RR
                leftRotate(grand);
            }
        }
    }

    /**
     * 判断是否平衡
     */
    public boolean isBalanced(Node<E> node){
        return ((AVLNode<E>)node).factory() <= 1;
    }

    /**
     * 左旋
     */
    private void leftRotate(Node<E> grand){
        Node<E> parent = grand.right;
        Node<E> child = parent.left;
        grand.right = child;
        parent.left = grand;
        afterRotate(grand,parent,child);
    }

    /**
     * 右旋
     */
    private void rightRotate(Node<E> grand){
        Node<E> parent = grand.left;
        Node<E> child = parent.right;
        grand.left = child;
        parent.right = grand;
        afterRotate(grand,parent,child);
    }

    /**
     * 选装之后节点关系调整
     */
    private void afterRotate(Node<E> grand, Node<E> parent, Node<E> child){
        //更新parent的parent
        parent.parent = grand.parent;
        if (null == grand.parent){
            root = parent;
        }else if(grand == grand.parent.right){
            grand.parent.right = parent;
        }else {
            grand.parent.left = parent;
        }
        //更新child的parent
        if (child != null){
            child.parent = grand;
        }
        //更新grand 的 parent
        grand.parent = parent;
        //更新高度
        updateHeight(parent);
        updateHeight(grand);
    }

    /**
     * 更行节点高度
     */
    private void updateHeight(Node<E> node){
        int lh = node.left == null? 0 : ((AVLNode<E>)node.left).height;
        int rh = node.right == null? 0 : ((AVLNode<E>)node.right).height;
        ((AVLNode<E>)node).height = Math.max(lh,rh) + 1;
    }

    private static class AVLNode<E> extends Node<E>{
        int height = 1;

        public AVLNode(){
            super();
        }

        public AVLNode(E val){
            super(val);
        }

        public AVLNode(E e, Node<E> parent){
            super(e,parent);
        }

        public boolean isLeftChild(){
            if (this == this.parent.left) return true;
            return false;
        }

        //返回高度更高子孩子
        public Node<E> tallerChild(){
            int lh = left == null ? 0 : ((AVLNode<E>) left).height;
            int rh = right == null ? 0 : ((AVLNode<E>) right).height;
            if (lh > rh) return left;
            if (lh < rh) return right;
            return isLeftChild()? left : right;
        }
        public int factory(){
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
            return Math.abs(leftHeight - rightHeight);
        }
    }

}
