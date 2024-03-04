package com.example.demo.od.c132;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 计算三叉搜索树的高度
 */
public class Demo055 {

    static class Node{
        int height;
        int val;
        Node[] child = new Node[3];
        public Node(int val, int height){
            this.val = val;
            this.height = height;
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        int[] nums = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        System.out.println(getResult(nums));
    }

    static Node root;
    static int max = 1;
    public static int getResult(int[] nums){
        if (nums.length == 0) return 0;
        root = new Node(nums[0],1);
        Node temp = root;
        int n = nums.length;
        for (int i = 1; i < n; i++) {
            int num = nums[i];
            dfs(num);
            root = temp;
        }
        return max;
    }

    public static void dfs(int num){
        if (num < root.val - 500){
            if (root.child[0] != null){
                root = root.child[0];
                dfs(num);
            }else {
                max = Math.max(max,root.height + 1);
                root.child[0] = new Node(num, root.height + 1);
            }
        }else if (num > root.val + 500){
            if (root.child[2] != null){
                root = root.child[2];
                dfs(num);
            }else {
                max = Math.max(max,root.height + 1);
                root.child[2] = new Node(num, root.height + 1);
            }
        }else {
            if (root.child[1] != null){
                root = root.child[1];
                dfs(num);
            }else {
                max = Math.max(max,root.height + 1);
                root.child[1] = new Node(num, root.height + 1);
            }
        }
    }
}
