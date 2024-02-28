package com.example.demo.od.c132;

import java.util.*;

/**
 * 模拟目录管理功能
 */
public class Demo037 {

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        cur = new Node("");
        Node temp = cur;
        StringJoiner joiner = new StringJoiner("/");
        while (sc.hasNextLine()){
            String str = sc.nextLine();
            if (str.equals("pwd")){
                joiner = new StringJoiner("/");
                Stack<String> stack = new Stack<>();
                stack.add("");
                temp = cur;
                while (temp != null){
                    stack.add(temp.name);
                    temp = temp.parent;
                }

                while (!stack.isEmpty()){
                    joiner.add(stack.pop());
                }
                continue;
            }
            buildtree(str.split(" "));
        }
        System.out.println(joiner.toString());

    }

    public static void buildtree(String[] strs){
        String sign = strs[0];
        String directory = strs[1];

        if (sign.equals("mkdir")){
            Node node = new Node(directory);
            node.parent = cur;
            cur.children.put(directory,node);
        }else if (sign.equals("cd")){
            if (directory.equals("..")){
                if (cur.parent == null) return;
                cur = cur.parent;
            }
            if (!cur.children.containsKey(directory)) return;
            cur = cur.children.get(directory);
        }
    }

    static Node cur;
    public static class Node{
        String name;
        Node parent;
        Map<String,Node> children = new HashMap<>();

        public Node(){

        }

        public Node(String name){
            this.name = name;
        }
    }
}
