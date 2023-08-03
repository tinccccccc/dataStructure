package com.example.demo.od.b200;

import java.util.Arrays;
import java.util.Scanner;

//跳格子1
public class demo16 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] arr = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        System.out.println(getResult(arr));
    }

    public static int getResult(int[] arr){
        int length = arr.length;
        if (length == 1){
            return arr[0];
        }
        int[] dp = new int[length + 1];
        dp[0] = 0;
        dp[1] = arr[0];
        dp[2] = Math.max(arr[0],arr[1]);

        for (int i = 3; i <= length ; i++) {
            //分两种情况：1.选择当前格子 2.不选择当前格子，取两种情况的最大值
            dp[i] = Math.max(dp[i-2] + arr[i-1],dp[i-1]);
        }
        return dp[length];
    }
}
