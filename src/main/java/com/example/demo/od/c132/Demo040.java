package com.example.demo.od.c132;

import java.util.Arrays;
import java.util.Scanner;

/**
 *  找座位
 */
public class Demo040 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int[] nums = Arrays.stream(sc.nextLine().split("")).mapToInt(Integer::parseInt).toArray();
        System.out.println(getResult(nums));
    }

    public static int getResult(int[] nums){
        int n = nums.length;
        int pre = 0;
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (i == n - 1){
                if (nums[i] == 0 && pre == 0){
                    res ++;
                }
            }else {
                if (nums[i] == 0 && pre == 0 && nums[i + 1] == 0){
                    res ++;
                    pre = 1;
                    continue;
                }
                pre = nums[i];
            }
        }

        return res;
    }
}
