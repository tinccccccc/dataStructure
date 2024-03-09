package com.example.demo.od.c132;

import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 *  二叉树计算  此题代码量巨大，逻辑也比较复杂，虽然独立做出来，但是所花时间太久
 */
public class Demo068 {
    static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val, TreeNode left, TreeNode right){
            this.val = val;
            this.left = left;
            this.right = right;
        }

        public TreeNode(int val){
            this.val = val;
        }

        public void setVal(int val) {
            this.val = val;
        }
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int[] inSort = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] preSort = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        System.out.println(getResult(inSort,preSort));
    }

    public static String getResult(int[] inSort, int[] preSort){
        //构建二叉树
        TreeNode root = buildTree(inSort,0,inSort.length - 1,
                                     preSort,0,preSort.length - 1);
        if (root == null) return "";
        //求和
        int val = root.val;
        root.setVal(sum(root) - val);
        //中序遍历
        StringJoiner joiner = new StringJoiner(" ");
        inSort(root,joiner);
        return joiner.toString();
    }

    public static void inSort(TreeNode node, StringJoiner joiner){
        if (node == null) return;
        inSort(node.left,joiner);
        joiner.add(Integer.toString(node.val));
        inSort(node.right,joiner);
    }

    public static int sum(TreeNode node){
        if (node == null) return 0;
        int sum = node.val;
        sum += sum(node.left);
        sum += sum(node.right);
        node.setVal(sum - node.val);
        return sum;
    }

    public static TreeNode buildTree(int[] inSort, int inStart, int inEnd, int[] preSort, int preStart, int preEnd){
        if(preStart == preEnd) return new TreeNode(preSort[preStart]);
        if(preStart > preEnd) return null;
        if (inStart > inEnd) return null;
        TreeNode node = new TreeNode(preSort[preStart]);
        int index = -1;
        for (int i = inStart; i <= inEnd; i++) {
            if (inSort[i] == preSort[preStart]){
                //判断有效性
                int leftSize = i - inStart;
                if (isValid(inSort, inStart , i - 1, preSort, preStart + 1, preStart +leftSize)
                        && isValid(inSort, i + 1, inEnd , preSort, preStart + leftSize + 1 , preEnd)){
                    index = i;
                    break;
                }
            }
        }
        int leftSize = index - inStart;
        node.left = buildTree(inSort,inStart , index - 1, preSort, preStart + 1, preStart + leftSize);
        node.right = buildTree(inSort, index + 1, inEnd , preSort, preStart+ leftSize + 1, preEnd);
        return node;
    }

    public static boolean isValid(int[] inSort, int inStart, int inEnd, int[] preSort, int preStart, int preEnd){
        int[] a = Arrays.stream(Arrays.copyOfRange(inSort, inStart, inEnd + 1)).sorted().toArray();
        int[] b = Arrays.stream(Arrays.copyOfRange(preSort, preStart, preEnd + 1)).sorted().toArray();

        if (inEnd - inStart != preEnd - preStart) return false;
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]){
                return false;
            }
        }
        return true;
    }
}
