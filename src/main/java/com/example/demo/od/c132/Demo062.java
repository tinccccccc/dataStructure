package com.example.demo.od.c132;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 剩余银饰的重量
 */
public class Demo062 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String n = sc.nextLine();
        int[] nums = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        System.out.println(getResult(nums));
    }


    public static int getResult(int[] nums){
        PriorityQueue<Integer> stack = new PriorityQueue<>((a,b) -> b-a);
        for (int num : nums) {
            stack.add(num);
        }

        int[] weight = new int[3];
        while (!stack.isEmpty()){
            int index = 0;
            int cnt = 0;
            while (cnt < 3){
                if (stack.isEmpty()) break;
                weight[index ++] = stack.poll();
                cnt ++;
            }

            if (cnt == 2 || cnt == 1){
                return weight[0];
            }

            if (cnt == 0) return 0;

            int x = weight[0];
            int y = weight[1];
            int  z = weight[2];

            if (x == y && y != z){
                stack.add(y - z);
            }else if (x != y && y == z){
                stack.add(x - y);
            }else if (x != y && y != z){
                int sub = Math.abs((y - z) - (x - y));
                if (sub == 0) continue;
                stack.add(sub);
            }
        }
        return 0;
    }
}
