package com.example.demo.od.b200;

import java.util.Arrays;

public class test {
    public static void main(String[] args) {

        System.out.println(maxDistance(new int[]{1,2,3,4,7},3));
    }

    public static int maxDistance(int[] position, int m) {
        Arrays.sort(position);

        int minDis = 0;
        int maxDis = position[position.length-1] - position[0];
        int ans = 0;

        while(minDis <= maxDis) {
            int mid = (minDis + maxDis) >> 1;
            if(check(position, m, mid)) {
                ans = mid;
                minDis = mid + 1;
            } else {
                maxDis = mid - 1;
            }
        }

        return ans;
    }

    public static boolean check(int[] position, int m, int dis) {
        int count = 1;
        int curPos = position[0];

        for(int i=1; i<position.length; i++) {
            if(position[i] - curPos >= dis) {
                count++;
                curPos = position[i];
            }
        }

        return count >= m;
    }
}
