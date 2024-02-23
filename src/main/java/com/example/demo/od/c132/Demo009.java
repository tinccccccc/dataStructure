package com.example.demo.od.c132;


import java.util.Arrays;
import java.util.Scanner;

/**
 * 连续出牌数量、最长连续手牌
 */
public class Demo009 {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int[] nums = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        String[] word = sc.nextLine().split(" ");
        System.out.println(getResult(nums,word));

        System.out.println(getResult(new int[]{7,4,4,4,1,4,3,6},new String[]{"b","y","b","g","y","r","r","b"}));
    }
    static boolean[] used;
    static int max;
    public static int getResult(int[] nums, String[] words){
        int n = nums.length;
        used = new boolean[n];
        max = 1;
        for (int i = 0; i < n; i++) {
            used[i] = true;
            dfs(nums,words,1,i);
            used[i] = false;
        }
        return max;
    }
    public static void dfs(int[] nums, String[] words, int cnt, int last){

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;
            if (nums[i] == nums[last] || words[i].equals(words[last])){
                cnt ++;
            }else {
                continue;
            }
            used[i] = true;
            max = Math.max(max,cnt);
            dfs(nums,words,cnt,i);
            cnt --;
            used[i] = false;
        }
    }

}
