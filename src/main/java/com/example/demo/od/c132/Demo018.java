package com.example.demo.od.c132;

import java.util.Scanner;
import java.util.StringJoiner;

/**
 * 用连续自然数之和来表达整数
 */
public class Demo018 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        System.out.println(getResult(num));
    }

    public static String getResult(int nums){
        StringJoiner joiner = new StringJoiner("\n");
        int[] preSum = new int[nums + 1];
        for (int i = 1; i <= nums; i++) {
            preSum[i] = preSum[i-1] + i;
        }
        joiner.add(nums+"="+nums);
        int l = nums - 2, r = nums - 1, res = 1;
        while (l >= 0){
            int sum = preSum[r] - preSum[l];
            if (sum == nums){
                StringBuilder builder = new StringBuilder();
                builder.append(nums).append("=");
                StringJoiner sj = new StringJoiner("+");
                int temp = l + 1;
                while (temp <= r){
                    sj.add(temp+"");
                    temp ++ ;
                }
                builder.append(sj.toString());
                joiner.add(builder.toString());
                res ++;
                l --;
            }else if (sum > nums){
                r--;
            }else {
                l --;
            }
        }
        joiner.add("Result:"+ res);
        return joiner.toString();
    }
}


