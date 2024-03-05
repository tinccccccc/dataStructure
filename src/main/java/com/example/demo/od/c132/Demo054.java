package com.example.demo.od.c132;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 执行任务赚积分 这题当时没写出来， 优先级队列
 */
public class Demo054 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        int time = Integer.parseInt(sc.nextLine());
        int[][] nums = new int[n][2];
        for (int i = 0; i < n; i++) {
            nums[i] = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        System.out.println(getResult(nums,time));
    }

    public static int getResult(int[][] nums, int time){
        int[][] array = Arrays.stream(nums).sorted(Comparator.comparingInt(a -> a[0])).toArray(int[][]::new);
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a));
        int res = 0;
        int cur = 1;
        for (int[] item : array) {
            int need = item[0];
            int fen = item[1];
            if (need >= cur){
                //说明可以执行
                queue.add(fen);
                cur ++;
                res += fen;
            }else {
                //说明超时了
                if (queue.size() == 0) continue;
                if (queue.peek() < fen){
                    Integer pre = queue.poll();
                    res += fen;
                    res -= pre;
                    queue.add(fen);
                }
            }
        }
        while (time < queue.size()){
            Integer pre = queue.poll();
            res -= pre;
        }
        return res;
    }
}
