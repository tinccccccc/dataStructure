package com.example.demo.od.c132;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 园区参观路径 动态规划，注意数据类型大小，使用bfs超时
 */
public class Demo052 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int[] arr = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int m = arr[0];
        int n = arr[1];
        int[][] nums = new int[m][n];
        for (int i = 0; i < m; i++) {
            nums[i] = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        System.out.println(getResult(nums));
    }

    public static long getResult(int[][] nums){
        int n = nums.length;
        int m = nums[0].length;
        if (nums[n-1][m-1] == 1)return 0;
        long[][] dp = new long[n][m];
        dp[n - 1][m - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i][m-1] == 1){
                dp[i][m-1] = 0;
            }else {
                dp[i][m-1] = dp[i + 1][m-1];
            }
        }
        for (int i = m - 2; i >= 0; i--) {
            if (nums[n-1][i] == 1){
                dp[n-1][i] = 0;
            }else {
                dp[n-1][i] = dp[n - 1][i + 1];
            }
        }
        for (int i = n - 2; i >= 0; i--) {
            for (int j = m - 2; j >= 0; j--) {
                if (nums[i][j] == 1){
                    dp[i][j] = 0;
                }else {
                    dp[i][j] = dp[i + 1][j] + dp[i][j+1];
                }
            }
        }
        return dp[0][0];
    }
}
