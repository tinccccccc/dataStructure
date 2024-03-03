package com.example.demo.od.c132;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 爱吃蟠桃的孙悟空。 就是阿珂吃香蕉一样的题。。  二份查找，且为左边界
 */
public class Demo049 {


    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int[] nums = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int leave = Integer.parseInt(sc.nextLine());
        System.out.println(getResult(nums,leave));
    }


    public static int getResult(int[] nums,int leave){
        int max = Arrays.stream(nums).max().orElse(-1);
        int right = max;
        int left = 1;
        while (left <= right){
            int mid = left + (right - left) / 2;
            int h = needHour(nums,mid);
            if (h <= leave){
                right = mid - 1;
            }else {
                left = mid + 1;
            }
        }

        if (right == max && needHour(nums,right) > leave) return 0;
        return right + 1;
    }

    public static int needHour(int[] nums, int k){
        int h = 0;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            while (num > 0){
                h ++;
                num -= k;
            }
        }
        return h;
    }
}
