package com.example.demo.od.c132;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 */
public class Demo043 {

    static int res = 0;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int[] nums = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        getResult(nums[1],nums[0]);
        System.out.println(res);
    }

    public static void getResult(int n, int p){
        for (int i = 1; i <= n/p; i++) {
            dfs(n-i,p-1,i);
        }
    }
    /**
     *
     * @param n 还可分配的月饼数
     * @param p 还可分配的人数
     * @param pre 上一个人分配的月饼数
     * @return
     */
    public static void dfs(int n, int p, int pre){
        //月饼比人少
        if (n < p) return;
        if (n < 0) return;
        if (n == 0 && p == 0)  {
            res ++;
            return;
        }
        if (n > 0 && p == 0) {
            return;
        }
        for (int i = pre; i <= pre+3; i ++) {
            dfs(n - i,p-1,i);
        }
    }
}
