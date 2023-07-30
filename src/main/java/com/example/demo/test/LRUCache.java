package com.example.demo.test;

import java.util.HashMap;
import java.util.Map;

class LRUCache {

    public class Node{
        private int key;
        private int value;

        private Node pre;
        private Node next;

        public Node(){}
        public Node(int key,int value){
            this.key = key;
            this.value = value;
        }
    }

    private int size;
    private int capacity;
    private Map<Integer,Node> cache = new HashMap<>();
    private Node head,tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.head = new Node();
        this.tail = new Node();
        head.next = tail;
        tail.pre = head;
    }

    public int get(int key) {
        Node n = cache.get(key);
        if(n == null){
            return -1;
        }else{
            moveToHead(n);
            return n.value;
        }
    }

    public void put(int key, int value) {
        Node n = cache.get(key);
        if (n == null){
            Node node = new Node(key,value);
            addToHead(node);
            if (size > capacity){
                removeNode(tail.pre);
            }
        }else {
            n.value = value;
            moveToHead(n);
        }

    }

    private void addToHead(Node node){
        node.next = head.next;
        head.next.pre = node;
        head.next = node;
        node.pre = head;
        ++ size;
        cache.put(node.key,node);
    }
    private void moveToHead(Node node){
        removeNode(node);
        addToHead(node);
    }

    private void removeNode(Node node){
        node.pre.next = node.next;
        node.next.pre = node.pre;
        cache.remove(node.key);
        --size;
    }

}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */