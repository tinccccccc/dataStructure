package com.example.demo.od.c132;

import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 *  按身高和体重排队
 */
public class Demo031 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        int[] arr1 = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] arr2 = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[][] nums = new int[n][3];
        for (int i = 0; i < n; i++) {
            //身高
            nums[i][0] = arr1[i];
            //体重
            nums[i][1] = arr2[i];
            //编号
            nums[i][2] = i;
        }
        System.out.println(getResult(nums));
    }

    public static String getResult(int[][] nums){
        nums = Arrays.stream(nums).sorted((a,b) ->{
           if (a[0] == b[0] && a[1] == b[1]){
               return a[2] - b[2];
           }
           if (a[0] == b[0]){
               return a[1] - b[1];
           }
           return a[0] - b[0];
        }).toArray(int[][]::new);
        StringJoiner joiner = new StringJoiner(" ");
        for (int[] item : nums) {
            joiner.add(item[2]+1+"");
        }
        return joiner.toString();
    }

}
