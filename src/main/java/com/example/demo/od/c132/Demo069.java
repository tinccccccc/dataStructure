package com.example.demo.od.c132;

import java.util.*;

/**
 * 分披萨  递归，没做出来
 */
public class Demo069 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(sc.nextLine());
        }
        memo = new long[n][n];
        System.out.println(getResult(nums));
    }

    static int[] nums;
    static long[][] memo;
    public static long getResult(int[] nums){
        int n = nums.length;
        long max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, reverse(next(i - 1), next(i + 1)) + nums[i]);
        }
        return max;
    }

    public static long reverse(int l , int r){
        if (memo[l][r] != 0) return memo[l][r];
        if (nums[l] > nums[r]){
            l = next(l - 1);
        }else {
            r = next(r + 1);
        }
        if (memo[l][r] != 0) return memo[l][r];
        if (l == r){
            memo[l][r] = nums[l];
        }else {
            memo[l][r] = Math.max(reverse(next(l - 1),r) + nums[l],reverse(l,next(r + 1)) + nums[r]);
        }
        return memo[l][r];
    }

    public static int next(int index){
        if (index < 0){
            return nums.length - 1;
        }
        if (index == nums.length){
            return 0;
        }
        return index;
    }
}
