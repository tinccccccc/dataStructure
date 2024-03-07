package com.example.demo.od.c132;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 最大坐标值、小明的幸运数
 */
public class Demo063 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        if (n < 1 || n > 100){
            System.out.println(12345);
            return;
        }
        int lucky = Integer.parseInt(sc.nextLine());
        int[] nums = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        System.out.println(getResult(nums,lucky));
    }


    public static int getResult(int[] nums, int lucky){
        int res = 0;
        int cur = 0;
        if (lucky < -100 || lucky > 100){
            return 12345;
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < -100 || nums[i] > 100) return 12345;
            cur += nums[i];
            if (lucky == 0) {
                res = Math.max(cur,res);
                continue;
            }
            if (nums[i] < 0 && nums[i] == lucky){
                cur -= 1;
            }else if (nums[i] == lucky){
                cur += 1;
            }
            res = Math.max(cur,res);
        }

        return res;
    }
}
