package com.example.demo.od.c132;

import java.util.Arrays;
import java.util.Scanner;

/**
 *  小华最多能得到多少克黄金、小华地图寻宝
 */
public class Demo056 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int[] a = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[][] nums = new int[a[0]][a[1]];
        System.out.println(getResult(nums,a[2]));
    }

    public static int getResult(int[][] nums, int k){
        return bfs(nums,0,0,k);
    }

    static int[][] steps = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
    public static int bfs(int[][] nums, int x, int y, int k){
        if (x < 0 || x >= nums.length || y < 0 || y >= nums[0].length) return 0;
        if (nums[x][y] == -1) return 0;
        int res = 0;
        if (sum(x,y) <= k){
            res ++;
            nums[x][y] = -1;
            for (int[] step : steps) {
                res += bfs(nums,step[0] + x, step[1] + y, k);
            }
            return res;
        }
        return 0;
    }

    public static int sum(int x, int y){
        String a = String.valueOf(x);
        String b = String.valueOf(y);
        int sum = 0;
        for (int i = 0; i < a.length(); i++) {
            sum += Integer.parseInt(a.charAt(i) + "");
        }
        for (int i = 0; i < b.length(); i++) {
            sum += Integer.parseInt(b.charAt(i) + "");
        }
        return sum;
    }
}

