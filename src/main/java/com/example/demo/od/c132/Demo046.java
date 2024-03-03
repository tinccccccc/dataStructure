package com.example.demo.od.c132;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringJoiner;

/**
 *  转盘寿司 (单调栈),注意题目是转盘
 */
public class Demo046 {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int[] nums = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        System.out.println(getResult(nums));
    }

    public static String getResult(int[] nums){
        int n = nums.length;
        Stack<int[]> stack = new Stack<>();
        int[] res = new int[n];
        for (int i = n -1 ; i >= 0; i--) {
            stack.add(new int[]{nums[i],i});
        }
        for (int i = n - 1; i >= 0; i--) {
            int num = nums[i];
            while (!stack.isEmpty() && stack.peek()[0] >= num){
                stack.pop();
            }
            if (!stack.isEmpty() && stack.peek()[1] != i){
                res[i] = num + stack.peek()[0];
            }else {
                res[i] = num;
            }
            stack.push(new int[]{num,i});
        }
        StringJoiner joiner = new StringJoiner(" ");
        for (int num : res) {
            joiner.add(num+"");
        }
        return joiner.toString();
    }
}
