package com.example.demo.od.c132;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 攀登者1
 */
public class Demo072 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int[] nums = Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        System.out.println(getResult(nums));
    }

    public static int getResult(int[] nums){

        int n = nums.length;
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (i == 0 && i + 1 < n && nums[i] > nums[i + 1]){
                res ++;
            }else if (i == n - 1 && i - 1 >= 0 && nums[i] > nums[i - 1]){
                res ++;
            }else if (i + 1 < n && i - 1 >= 0 && nums[i] > nums[i - 1] && nums[i] > nums[i +1]){
                res ++;
            }

        }

        return res;
    }
}
