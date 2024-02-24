package com.example.demo.od.c132;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * 寻找身高相近的小朋友
 */
public class Demo020 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int[] arr = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] nums = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        System.out.println(getResult(nums,arr[0]));
    }

    public static String getResult(int[] nums, int k){
        Arrays.sort(nums);
        int[][] arr = new int[nums.length][2];
        for (int i = 0; i < nums.length; i++) {
            arr[i] = new int[]{nums[i],Math.abs(nums[i] - k)};
        }

        Arrays.sort(arr, Comparator.comparingInt(a -> a[1]));
        StringJoiner joiner = new StringJoiner(" ");
        for (int[] item : arr) {
            joiner.add(item[0] +"");
        }
        return joiner.toString();
    }
}
