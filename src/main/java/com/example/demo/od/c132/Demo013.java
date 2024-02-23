package com.example.demo.od.c132;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 数组连续和
 */
public class Demo013 {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int[] k = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] nums = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        System.out.println(getResult(nums,k[1]));
    }

    public static long getResult(int[] nums, int k){
        int n = nums.length;
        int[] preSum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }

        int l = 0,r = 0;
        long sum;
        long res = 0;
        while (r < n){
            sum = preSum[r + 1] - preSum[l];
            if (sum >= k){
                res += n - r;
                l ++;
                if (l == r + 1){
                    r++;
                }
            }else {
                r ++;
            }
        }
        return res;
    }
}
