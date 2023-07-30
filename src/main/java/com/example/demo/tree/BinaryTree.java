package com.example.demo.tree;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * 二叉树代码实现
 */
public class BinaryTree<E> implements BinaryTreeInfo {

    //节点数量
    public int size;

    //根节点
    public Node<E> root;

    /**
     * 返回元素数量
     */
    public int size() {
        return size;
    }

    /**
     * 清空所有元素
     */
    public void clear(){
        root = null;
        size = 0;
    }

    /**
     * 增强遍历接口
     *
     * @param <E>
     */
    public static abstract class Visitor<E> {
        boolean stop;
        //用来控制遍历是否停止
        public abstract boolean visit(E element);
    }

    //内部节点类
    public static class Node<E> {
        //元素值
        public E element;
        //左节点
        public Node<E> left;
        //右节点
        public Node<E> right;
        //父节点
        public Node<E> parent;

        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }

        /**
         * 是否为叶子结点
         *
         * @return
         */
        public boolean isLeaf() {
            return left == null && right == null;
        }

        /**
         * 是否有两个子节点
         *
         * @return
         */
        public boolean hasTwoChildren() {
            return left != null && right != null;
        }

        /**
         * 判断当前节点是否为左子树
         *
         * @return
         */
        public boolean isLeftChild() {
            return parent != null && parent.left == this;
        }

        /**
         * 判断当前节点是否为右子树
         *
         * @return
         */
        public boolean isRightChild() {
            return parent != null && parent.right == this;
        }

        /**
         * 返回兄弟节点
         *
         * @return
         */
        public Node<E> brother() {
            if (isRightChild()) return parent.left;
            if (isLeftChild()) return parent.right;
            return null;
        }
    }


    /**
     * 前序遍历
     *
     * @param visitor   遍历增强器
     */
    public void preOrder(Visitor<E> visitor){
        if (Objects.isNull(visitor)) return;
        preOrder(root,visitor);
    }

    public void preOrder(Node<E> node, Visitor<E> visitor){
        if (node == null || visitor.stop) return;
        //根节点
        visitor.stop = visitor.visit(node.element);
        //左节点
        preOrder(node.left,visitor);
        //右节点
        preOrder(node.right,visitor);
    }


    /**
     * 中序遍历
     *
     * @param visitor
     */
    public void midOrder(Visitor<E> visitor){
        if (Objects.isNull(visitor)) return;
        midOrder(root,visitor);
    }

    public void midOrder(Node<E> node,Visitor<E> visitor){
        if (node == null || visitor.stop) return;
        //左节点
        midOrder(node.left,visitor);
        //中间节点
        if (visitor.stop) return;
        visitor.stop = visitor.visit(node.element);
        //右节点
        midOrder(node.right,visitor);
    }


    /**
     * 后续遍历
     *
     * @param visitor
     */
    public void postOrder(Visitor<E> visitor){
        if (Objects.isNull(visitor)) return;
        postOrder(root,visitor);
    }

    public void postOrder(Node<E> node,Visitor<E> visitor){
        if (node == null || visitor.stop) return;

        //左节点
        postOrder(node.left,visitor);

        //右节点
        postOrder(node.right,visitor);

        //中间节点
        if (visitor.stop) return;
        visitor.stop = visitor.visit(node.element);
    }

    /**
     * 层序遍历
     *
     * @param visitor
     */
    public void levelOrder(Visitor<E> visitor){
        if (root == null || visitor.stop) return;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()){
            Node<E> node = queue.poll();
            if (visitor.visit(node.element)) return;

            if (node.left != null) {
                queue.offer(node.left);
            }

            if (node.right != null){
                queue.offer(node.right);
            }
        }
    }


    /**
     * 求树的高度（递归）
     *
     * @return
     */
    public int height(){
        return height(root);
    }

    public int height(Node<E> root){
        if (root == null) return 0;
        return 1 + Math.max(height(root.left),height(root.right));
    }

    /**
     * 求树的高度高度(迭代)
     */
    public int height1() {
        if (root == null) return 0;
        int levelSize = 1; // 存储每一层的元素数量
        int height = 0; // 树的高度
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            levelSize--;
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
            if (levelSize == 0) { // 即将要访问下一层
                levelSize = queue.size();
                height++;
            }
        }
        return height;
    }

    /**
     * 是否是完全二叉树
     *
     * @return
     */
    public boolean isComplete(){
        if (root == null) return false;

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        boolean leaf = false;
        while (!queue.isEmpty()){
            Node<E> node = queue.poll();

            //要求当前节点为叶子节点，单当前节点不是叶子节点
            if (leaf && !node.isLeaf()){
                return false;
            }

            if (node.left != null){
                queue.offer(node.left);
            }else if (node.right != null){
                return false;
            }

            if (node.right != null){
                queue.offer(node.right);
            }else {
                leaf = true;
            }
        }
        return true;
    }

    /**
     * 前驱结点：中序遍历时的前驱结点
     *
     * @param node
     * @return
     */
    public Node<E> predecessor(Node<E> node){
        if (node == null) return null;

        /**
         *      a
         *     b
         *       c
         *         d
         *           e
         *    e - > a
         */
        Node<E> p = node.left;
        if (p != null){
            while ( p.right != null){
                p = p.right;
            }
            return p;
        }

        /**
         *            a
         *              b
         *            c
         *  a -> c
         */
        while (node.parent != null && node.parent.left == node){
            node = node.parent;
        }

        return node.parent;
    }

    /**
     * 后继节点，中序遍历的后一个结点
     *
     * @param node
     * @return
     */
    public Node<E> successor(Node<E> node){
        if (node == null) return null;

        //后继节点和前驱结点正好相反
        Node<E> p = node.right;
        if (p != null){
            while (p.left != null){
                p = p.left;
            }
            return p;
        }

        //也是和前驱结点情况相反
        while (node.parent != null && node.parent.right == node){
            node = node.parent;
        }
        return node.parent;
    }

    /**
     * 创建节点的方法，用于给Avl 数创建节点
     *
     * @param element
     * @param parent
     * @return
     */
    private Node<E> createNode(E element, Node<E> parent){
        return new Node<>(element,parent);
    }

    /**
     * BinaryTreeInfo 工具，用来打印二叉树
     *
     * @return
     */
    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((Node<E>) node).left;
    }

    @Override
    public Object right(Object node) {
        return ((Node<E>) node).right;
    }

    @Override
    public Object string(Object node) {
        Node<E> myNode = (Node<E>) node;
        String parentStr = "null";
        if (myNode.parent != null){
            parentStr = myNode.parent.element.toString();
        }
        return myNode.element + "_p(" + parentStr +")";
    }
}
