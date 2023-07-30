package com.example.demo.od.b100;

import java.util.Arrays;
import java.util.Scanner;

//矩阵元素的边界值
public class demo30 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        int[][] ints = Arrays.stream(str.substring(2, str.length() - 2).split("],\\["))
                .map(item -> Arrays.stream(item.split(","))
                        .mapToInt(Integer::parseInt).toArray()).toArray(int[][]::new);
        System.out.println(getResult(ints));
    }

    public static int getResult(int[][] arr){
        int[] cur = arr[0];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                cur[j] = Math.max(cur[j],arr[i][j]);
            }
        }
        return Arrays.stream(cur).min().orElse(0);
    }

    public static int getResult1(int[][] arr){
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < arr[0].length; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = 0; j < arr.length; j++) {
                max = Math.max(max,arr[j][i]);
            }
            result = Math.min(result,max);
        }
        return result;
    }

}
