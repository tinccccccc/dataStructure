package com.example.demo.od.c132;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 计算面积、绘图机器
 */
public class Demo033 {

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        int[] array = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = array[0];
        int e = array[1];
        int[][] nums = new int[n][2];
        for (int i = 0; i < n; i++) {
            nums[i] = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        System.out.println(getResult(nums,n,e));
    }



    public static int getResult(int[][] nums, int n, int e){
        int res = 0;
        int h = 0;
        for (int i = 0; i < n; i++) {
            h += nums[i][1];
            if (i == n - 1){
                int w = e - nums[i][0];
                res += Math.abs(w * h);
                continue;
            }
            int w = nums[i + 1][0] - nums[i][0];
            res += Math.abs(w * h);
        }

        return res;
    }
}
