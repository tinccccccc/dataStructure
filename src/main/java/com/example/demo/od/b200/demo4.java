package com.example.demo.od.b200;

import java.util.Arrays;
import java.util.Scanner;

//代表团坐车
public class demo4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] arr = Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        int car = Integer.parseInt(sc.nextLine());
        System.out.println(getResult2(arr,car));
    }

    public static int getResult(int[] arr, int car){
        int length = arr.length;
        int[][] dp = new int[car + 1][length + 1];
        dp[0][0] = 1;

        for (int i = 0; i <= car; i++) {
            for (int j = 1; j <= length; j++) {
                int num = arr[j -1];
                if (num > i){
                    dp[i][j] = dp[i][j-1];
                }else {
                    dp[i][j] = dp[i][j-1] + dp[i-num][j-1];
                }
            }
        }
        return dp[car][length];
    }

    //使用滚动数组优化
    public static int getResult2(int[] arr, int car){
        int length = arr.length;
        int[] dp = new int[car+1];
        dp[0] = 1;

        for (int i = 1; i <= length; i++) {
            int num = arr[i-1];
            for (int j = car; j >= num; j--){
                dp[j] = dp[j] + dp[j-num];
            }
        }
        return dp[car];
    }



}
