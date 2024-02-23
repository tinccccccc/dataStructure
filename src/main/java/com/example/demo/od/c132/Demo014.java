package com.example.demo.od.c132;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 求最多可以派出多少支团队
 */
public class Demo014 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        int[] nums = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        long k = Long.parseLong(sc.nextLine());
        System.out.println(getResult(nums,k));
    }

    public static int getResult(int[] nums, long k){
        Arrays.sort(nums);
        int n = nums.length;
        int res = 0;
        int l = 0, r = n - 1;
        while (l <= r){
            if (nums[r] >= k){
                res ++;
                r --;
            }else {
                long sum = l == r? nums[l] : nums[r] + nums[l];
                if (sum < k){
                    l++;
                    continue;
                }
                res ++;
                r --;
                l ++;
            }
        }
        return res;
    }
}
