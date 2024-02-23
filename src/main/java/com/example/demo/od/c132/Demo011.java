package com.example.demo.od.c132;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 执行时长
 */
public class Demo011 {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int k = Integer.parseInt(sc.nextLine());
        int n = Integer.parseInt(sc.nextLine());
        int[] nums = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        System.out.println(getResult(nums,k));
    }

    public static int getResult(int[] nums, int k){
        int res = 0;
        int n = nums.length;
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            cnt += nums[i];
            if (cnt <= k){
                cnt = 0;
                res ++;
            }else {
                cnt -= k;
                res ++;
            }
        }
        while (cnt > 0){
            cnt -= k;
            res ++;
        }
        return res;
    }
}
