package com.example.demo.od.c132;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 传递悄悄话  层序遍历
 */
public class Demo060 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int[] nums = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        System.out.println(getResult(nums));
    }

    public static int getResult(int[] nums){
        int res  = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == -1) continue;
            int parent = (i - 1) / 2;
            int sum = nums[parent] + nums[i];
            nums[i] = sum;
            res = Math.max(res,sum);
        }
        return res;
    }
}
