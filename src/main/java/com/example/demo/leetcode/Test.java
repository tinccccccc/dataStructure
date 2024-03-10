package com.example.demo.leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringJoiner;

public class Test {
    public static void main(String[] args){

        int[][] deges = new int[][]{{1,2,1},{1,3,2},{1,4,5},{2,3,2},{2,4,1},{3,2,1}};

        int[] head = new int[4 + 1];

        int[] next = new int[deges.length + 1];

        int[] to = new int[deges.length + 1];

        int[] wight = new int[deges.length + 1];

        int cnt = 1;

        for (int[] dege : deges) {
            int f = dege[0];
            int t = dege[1];
            int w = dege[2];

            next[cnt] = head[f];
            head[f] = cnt;
            to[cnt] = t;
            wight[cnt] = w;
            cnt ++;
        }
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
        int[][] nums = new int[deges.length][deges[0].length];
        int index = 0;
        for (int i = 0; i < head.length - 1; i++) {
            if (head[i + 1] != 0){
                int bian = head[i + 1];
                nums[index ++] = new int[]{i + 1,to[head[i + 1]],wight[head[i + 1]]};

                while (next[bian] != 0){
                    bian = next[bian];
                    nums[index ++] = new int[]{i + 1,to[bian],wight[bian]};
                }

            }
        }
        for (int[] num : nums) {
            StringJoiner joiner = new StringJoiner(" ");
            for (int i : num) {
                joiner.add(i +"");
            }
            System.out.println(joiner.toString());
        }

    }




}
