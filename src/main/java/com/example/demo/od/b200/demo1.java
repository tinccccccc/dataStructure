package com.example.demo.od.b200;

import java.util.Arrays;
import java.util.Scanner;

//最佳植树距离
public class demo1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String num = sc.nextLine();
        String positions = sc.nextLine();
        String trees = sc.nextLine();
        int[] arr = Arrays.stream(positions.split(" ")).mapToInt(Integer::parseInt).toArray();
        System.out.println(getResult(arr,Integer.parseInt(trees)));
    }

    public static int getResult(int[] arr, int trees){
        int result = 0;
        //排好序后，方便求最大间距
        Arrays.sort(arr);
        //最大距离为
        int max = arr[arr.length - 1] - arr[0];
        //具题目可知最小距离为1
        int min = 1;
        //间距的中位数,用于假设每棵树苗的最小间距为该数
        int mid = (min + max) >> 1;

        while (min <= max){

            if (check(arr,trees,mid)){
                //当前满足，则间距mid可以尝试加大
                min = mid + 1;
                result = mid;
            }else {
                //当前不满足，则间距mid尝试减小减小
                max = mid - 1;
            }
            mid = (min + max) >> 1;
        }
        return result;
    }

    public static boolean check(int[] arr, int trees, int mid){
        //第一课树苗的位置
        int one = arr[0];
        //用于计算当前已经插入了多少课树苗
        int count = 1;

        for (int i = 1; i < arr.length; i++) {
            //说明当前位置可以插入树苗
            if (arr[i] - one >= mid){
                one = arr[i];
                count ++;
            }
        }

        //如果可插入的树苗大于等于要求的树苗，则当前结果满足情况，是一个可能解
        return count >= trees;
    }
}
