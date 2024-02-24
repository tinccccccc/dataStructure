package com.example.demo.od.c132;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 靠谱的车
 */
public class Demo019 {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int[] nums = Arrays.stream(sc.nextLine().split("")).mapToInt(Integer::parseInt).toArray();
        System.out.println(getResult(nums));
    }

    public static int getResult(int[] arr) {
        int res = 0;
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            int num = arr[i];
            if (num > 4) num --;
            for (int j = i; j < n - 1; j++) {
                num *= 9;
            }
            res += num;
        }
        return res;
    }

}
