package com.example.demo.od.c132;


import java.util.Arrays;
import java.util.Scanner;

/**
 * 小明找位置 (二份查找)
 */
public class Demo050 {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int[] nums = Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        int k = Integer.parseInt(sc.nextLine());
        System.out.println(getResult(nums,k));
    }


    public static int getResult(int[] nums, int k){
        int n = nums.length;
        int l = 0, r = n - 1;
        while (l <= r){
            int mid = l + (r - l) / 2;
            int num = nums[mid];
            if (num == k){
                return mid + 1;
            }else if (num < k){
                l = mid + 1;
            }else {
                r = mid - 1;
            }
        }
        return l + 1;
    }
}
