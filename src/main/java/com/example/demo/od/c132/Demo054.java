package com.example.demo.od.c132;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 执行任务赚积分 这题当时没写出来
 */
public class Demo054 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        int time = Integer.parseInt(sc.nextLine());
        int[][] nums = new int[n][2];
        for (int i = 0; i < n; i++) {
            nums[i] = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        System.out.println(getResult(nums,time));
    }

    public static int getResult(int[][] nums, int time){
        nums = Arrays.stream(nums).sorted((o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o2[1] - o1[1];
            } else {
                return o1[0] - o2[0];
            }
        }).toArray(int[][]::new);
        backtrack(nums,0,time,0,1);
        return max;
    }
    static int max = 0;
    public static void backtrack(int[][] nums, int start, int time, int sum, int cur){
        max = Math.max(max,sum);
        if (start == nums.length){
            return;
        }
        if (cur > time) return;
        for (int i = start; i < nums.length; i++) {
            int need = nums[i][0];
            int fen = nums[i][1];
            if (i != start && need == nums[i-1][0]) continue;
            if (need > time) return;
            if (need >= cur){
                backtrack(nums,i + 1, time, sum + fen, cur + 1);
            }
        }
    }
}
