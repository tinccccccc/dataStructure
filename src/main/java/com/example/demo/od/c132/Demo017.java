package com.example.demo.od.c132;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 可以组成网络的服务器
 */
public class Demo017 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int[] array = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = array[0];
        int m = array[1];
        int[][] nums = new int[n][m];
        for (int i = 0; i < n; i++) {
            nums[i] = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        System.out.println(getResult(nums));
    }

    static int[][] steps = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
    public static int getResult(int[][] nums){
        int max = 0;
        int n = nums.length;
        int m = nums[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (nums[i][j] == 1){
                    max = Math.max(max,bfs(nums,i,j));
                }
            }
        }
        return max;

    }

    public static int bfs(int[][] nums, int i, int j){
        if (i < 0 || i >= nums.length || j < 0 || j >= nums[0].length || nums[i][j] == 0) return 0;
        nums[i][j] = 0;
        int res = 1;
        for (int[] step : steps) {
            int x = step[0] + i;
            int y = step[1] + j;
            res += bfs(nums,x,y) ;
        }
        return res;
    }

}
