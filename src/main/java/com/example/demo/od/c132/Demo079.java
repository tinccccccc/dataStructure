package com.example.demo.od.c132;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 部门人力分配 左边界的二分搜索
 */
public class Demo079 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int months = Integer.parseInt(sc.nextLine());
        int[] nums = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        System.out.println(getResult(nums,months));
    }


    public static int getResult(int[] nums, int months){
        Arrays.sort(nums);
        int n = nums.length;
        int left = nums[n - 1];
        int right = nums[n - 1] + nums[n - 2];
        while (left <= right){
            int mid = left + (right - left) / 2;
            int need = needMonths(nums,mid);
            if (need <= months){
                right = mid - 1;
            }else {
                left = mid + 1;
            }
        }
        return right + 1;
    }


    public static int needMonths(int[] nums, int people){
        int months = 0;
        int l = 0;
        int r = nums.length - 1;
        while (l <= r){
            if (nums[l] + nums[r] <= people){
                l ++;
            }
            months ++;
            r --;
        }
        return months;
    }
}
