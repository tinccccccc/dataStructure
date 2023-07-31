package com.example.demo.od.b200;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringJoiner;

//阿里巴巴找黄金宝箱(IV)
public class demo09 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] array = Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        System.out.println(getResult(array));
    }

    public static String getResult(int[] array){
        StringJoiner joiner = new StringJoiner(",");
        int length = array.length;
        int[] result = new int[length];
        Arrays.fill(result,-1);
        //单调递减栈  栈底到栈顶 递减
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < length * 2; i++) {
            int index = i % length;
            while (!stack.isEmpty() && array[stack.peek()] < array[index]){
                Integer pop = stack.pop();
                result[pop] = array[index];
            }
            stack.add(index);
        }

        for (int num : result) {
            joiner.add(num+"");
        }
        return joiner.toString();
    }
}
