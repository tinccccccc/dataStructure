package com.example.demo.od.c132;

import java.util.*;

/**
 * 分配土地
 */
public class Demo045 {

    public static class Node{
        int maxi = Integer.MIN_VALUE;
        int mini = Integer.MAX_VALUE;
        int maxy = Integer.MIN_VALUE;
        int miny = Integer.MAX_VALUE;

        public void setRow(int x){
            maxi = Math.max(x,maxi);
            mini = Math.min(x,mini);
        }
        public void setCol(int y){
            maxy = Math.max(y,maxy);
            miny = Math.min(y,miny);
        }
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int[] arr = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = arr[0];
        int m = arr[1];
        int[][] nums = new int[n][m];
        for (int i = 0; i < n; i++) {
            nums[i] =  Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        System.out.println(getResult(nums));
    }

    public static int getResult(int[][] nums){

        int n = nums.length;
        int m = nums[0].length;
        Map<Integer,Node> cnt = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int num = nums[i][j];
                if (num != 0){
                    Node node = cnt.getOrDefault(num, new Node());
                    node.setRow(i);
                    node.setCol(j);
                    cnt.put(num,node);
                }
            }
        }
        int max = 0;
        for (Integer key : cnt.keySet()) {
            Node node = cnt.get(key);
            max = Math.max(Math.abs((node.maxi - node.mini + 1) * (node.maxy - node.miny + 1)),max);
        }
        HashMap<Integer, Integer> map = new HashMap<>();

        return max;
    }
}
