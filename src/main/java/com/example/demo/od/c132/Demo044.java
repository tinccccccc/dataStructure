package com.example.demo.od.c132;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * 游戏分组
 */
public class Demo044 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] nums = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        backtrack(nums,0,nums.length / 2);
        System.out.println(min);
    }

    static LinkedList<Integer> stack = new LinkedList<>();
    static int min = Integer.MAX_VALUE;
    public static void backtrack(int[] nums, int start, int k){
        if (k == stack.size() && min > getAbs(nums)){
            min = Math.min(min,getAbs(nums));
        }
        for (int i = start; i < nums.length; i++) {
            if (i != start && nums[i] == nums[i - 1]) continue;
            stack.addLast(i);
            backtrack(nums,i + 1, k);
            stack.removeLast();
        }
    }

    public static int getAbs(int[] nums){
        int a = 0;
        int b = 0;
        for (int i = 0; i < nums.length; i++) {
            if (stack.contains(i)){
                a += nums[i];
            }else {
                b += nums[i];
            }
        }
        return Math.abs(a-b);
    }
}
