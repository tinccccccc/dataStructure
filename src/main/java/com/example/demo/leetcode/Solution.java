package com.example.demo.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        //链式向前星构建图
        int cnt = 1;
        int[] head = new int[n + 1];
        int[] next = new int[times.length + 1];
        int[] to = new int[times.length + 1];
        int[] weight = new int[times.length + 1];
        for(int[] time : times){
            int f = time[0];
            int t = time[1];
            int w = time[2];
            next[cnt] = head[f];
            head[f] = cnt;
            to[cnt] = t;
            weight[cnt] = w;
            cnt ++;
        }

        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        int[] res = new int[n + 1];
        Arrays.fill(res, Integer.MAX_VALUE);
        res[k] = 0;
        boolean[] used = new boolean[n + 1];
        queue.add(new int[]{k,0});
        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            int a = cur[0];
            if(used[a]) continue;
            used[a] = true;
            int b = cur[1];
            int edge = head[a];
            while(edge != 0){
                int t = to[edge];
                int w = weight[edge];

                if(!used[t] && res[t] > b + w){
                    res[t] = b + w;
                    queue.add(new int[]{t,b + w});
                }
                edge = next[edge];
            }
        }

        int max = 0;
        for(int i = 1; i < res.length; i ++){
            if(res[i] == Integer.MAX_VALUE) return -1;
            max = Math.max(max, res[i]);
        }
        return max;
    }
}
