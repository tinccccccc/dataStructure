package com.example.demo.od.c132;

import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 *
 */
public class Demo027 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int k = Integer.parseInt(sc.nextLine());
        int[] nums = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        System.out.println(getResult(nums,k));
    }

    public static String getResult(int[] nums, int k){
        StringJoiner joiner = new StringJoiner(" ");
        int n = nums.length;
        int[] preSum = new int[n+1];
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }

        int max = 0;
        for (int i = 0; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                //[i,j-1]
                int sum = preSum[j] - preSum[i];
                int length = j - i;
                int target = length * k;
                if (sum <= target){
                    if (length < max) continue;
                    if (length > max){
                        joiner = new StringJoiner(" ");
                        max = length;
                    }
                    StringJoiner js = new StringJoiner("-");
                    js.add(i+"");
                    js.add((j-1) +"");
                    joiner.add(js.toString());
                }
            }
        }
        return joiner.length() == 0? "NULL" : joiner.toString();
    }
}
