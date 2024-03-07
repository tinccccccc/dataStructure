package com.example.demo.od.c132;

import java.util.*;

/**
 * 电脑病毒感染       Dijkstra 最短路径算法
 */
public class Demo059 {


    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        int m = Integer.parseInt(sc.nextLine());
        int[][] nums = new int[m][3];
        for (int i = 0; i < m; i++) {
            nums[i] = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        int start = Integer.parseInt(sc.nextLine());
        System.out.println(getResult(nums,n,start));
    }


    public static int getResult(int[][] nums, int num, int start){
        List<List<int[]>> list = new ArrayList<>();
        for (int i = 0; i <= num; i++) {
            list.add(new ArrayList<>());
        }
        //构建邻接表
        for (int i = 0; i < nums.length; i++) {
            int[] point = nums[i];
            int from = point[0];
            int to = point[1];
            int time = point[2];
            list.get(from).add(new int[]{to,time});
        }

        //小顶堆
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        //添加起始位置,自己传播给自己所需时间为0
        queue.add(new int[]{start,0});

        boolean[] used = new boolean[num + 1];
        int[] times = new int[num + 1];
        Arrays.fill(times, Integer.MAX_VALUE);
        times[start] = 0;
        while (!queue.isEmpty()){
            int[] first = queue.poll();
            int to = first[0];
            int time = first[1];
            //已经从栈中弹出过了
            if (used[to]) continue;
            used[to] = true;

            List<int[]> points = list.get(to);
            for (int[] point : points) {
                int a = point[0];
                int b = point[1];
                if (!used[a] && times[a] > time + b){
                    times[a] = time + b;
                    queue.add(new int[]{a, time + b});
                }
            }
        }
        int max = 0;
        for (int i = 1; i < times.length; i++) {
            int t = times[i];
            if (t == Integer.MAX_VALUE) return -1;
            max = Math.max(max,t);
        }

        return max;
    }
}
