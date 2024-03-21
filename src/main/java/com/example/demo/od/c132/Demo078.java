package com.example.demo.od.c132;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * 求幸存数之和
 */
public class Demo078 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int[] arr = Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        int jump = Integer.parseInt(sc.nextLine());
        int left = Integer.parseInt(sc.nextLine());
        System.out.println(getResult(arr,jump,arr.length - left));
    }

    public static int getResult(int[] nums, int jump, int left){

        int cnt = 1;
        int n = nums.length;
        int j = 0;
        int cursor = 0;
        int sum = 0;

        while (cnt <= left){
            while (j <= jump){
                cursor ++;
                if (cursor >= n){
                    cursor = 0;
                }
                if (nums[cursor] != -1){
                    j ++;
                }
            }
            nums[cursor] = -1;
            sum+=nums[cursor];
            cnt ++;
            j = 0;
        }
        return Arrays.stream(nums).sum() - sum;

    }
}
