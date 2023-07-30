package com.example.demo.tree.bst;

import com.example.demo.tree.BinaryTree;

import java.util.Comparator;


/**
 * 二叉搜索树
 */
public class BinarySearchTree<E> extends BinaryTree<E> {

    //比较器
    private Comparator<E> comparable;

    public BinarySearchTree(Comparator<E> comparable){
        this.comparable = comparable;
    }

    public BinarySearchTree(){
        this(null);
    }

    /**
     * 检查参数是否异常
     *
     * @param element
     */
    private void elementNotNullCheck(E element){
        if (element == null){
            throw new IllegalArgumentException("element must not null");
        }
    }

    /**
     * 节点元素比较
     *
     * @param e1
     * @param e2
     * @return
     */
    private int comparableTo(E e1, E e2){
        if (comparable == null){
            return comparable.compare(e1,e2);
        }
        //没传比较器，则用内部自行实现的compare方法
        return ((Comparable<E>) e1).compareTo(e2);
    }

    /**
     * 更具元素值获取节点元素
     *
     * @param element
     * @return
     */
    private Node<E> node(E element){
        elementNotNullCheck(element);

        Node<E> node = root;
        while (node != null){
            int cmp = comparableTo(element, node.element);
            if (cmp < 0){
                node = node.left;
            }else if (cmp > 0){
                node = node.right;
            }else {
                return node;
            }
        }
        return null;
    }

    /**
     * 是否包含某元素
     *
     * @param element
     * @return
     */
    public boolean contains(E element){
        return node(element) != null;
    }

    /**
     * 添加节点
     *
     * @param element
     */
    public void add(E element){
        elementNotNullCheck(element);

        //当前没有节点，则当前传入节点为根节点
        if (root == null){
            root = new Node<>(element,null);
            size ++;
            return;
        }

        //当前已有节点，找到该插入到哪个父节点下面
        Node<E> node = root;
        Node<E> parent = root;
        int cmp = 0;

        while (node != null){
            parent = root;
            //传入元素值 与 父节点元素值 比较
            cmp = comparableTo(element,node.element);

            //传入节点比父节点要大，则往右
            if (cmp > 0){
                node = node.right;
            }else if (cmp < 0){
                //小，则往左
                node = node.right;
            }else {
                //相等
                node.element = element;
                return;
            }
        }

        //到这则node为null了，父节点为parent，判断当前是左节点还是右节点
        Node<E> newNode = new Node<>(element, parent);
        if (cmp > 0){
            parent.right = newNode;
        }else {
            parent.left = newNode;
        }
        size ++;
    }

    /**
     *  更具节点值删除节点
     *
     * @param element
     */
    public void remove(E element){
        remove(node(element));
    }

    /**
     * 更具节点删除节点
     *
     * @param node
     */
    public void remove(Node<E> node){
        if (node == null) return ;
        size --;

        //删除的节点 度为2
        if (node.hasTwoChildren()){
            //找到后继节点（或者找前驱节点）
            Node<E> successor = successor(node);
            //用后继节点的值覆盖当前节点的值
            node.element = successor.element;
            //删除后继节点(node 表示要删除的节点，因为后继或前驱节点度必然为1或0，后面统一删除度为1或0的节点，这步很妙)
            node = successor;
        }

        //删除 node 节点（此时的 node 节点的度必然为 1 或 0）
        Node<E> replace = node.left != null? node.left : node.right;

        if (replace != null){
            //此时度为1,跟该parent
            replace.parent = node.parent;

            //node 是度为1，且为根节点
            if (node.parent == null){
                root = replace;
            }else if (node == node.parent.left){
                node.parent.left = replace;
            }else {
                node.left.right = replace;
            }
        }else if (node.parent == null){
            //node 是叶子结点，并且还是跟节点
            root = null;
        }else {
            //node 是叶子结点，但不是跟节点
            if (node == node.parent.left){
                node.parent.left = null;
            }else {
                node.parent.right = null;
            }
        }
    }

}
