package com.example.demo.od.c132;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * 最多购买宝石数目    滑动窗口或前缀和。这题注意，最后一个用例超时,这题要特别注意
 */
public class Demo061 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(sc.nextLine());
        }
        int val = Integer.parseInt(sc.nextLine());
        System.out.println(getResult(nums,n,val));
    }

    static int res;
    static int sum ;
    static int num;
    static int left;
    static int right;
    public static int getResult(int[] nums, int n, int k){
        sum = 0;
        res = 0;
        left = 0;
        right = 0;
        while (right < n){
            if (sum <= k){
                num = nums[right];
                res = Math.max(res, right - left );
                sum += num;
                if (right == n - 1 && sum <= k){
                    res = Math.max(res, right - left + 1);
                }
                right ++;
            }else {
                sum -= nums[left];
                left ++;
            }
        }
        return res;
    }
}
