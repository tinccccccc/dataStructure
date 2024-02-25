package com.example.demo.od.c132;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 *  最大N个数与最小N个数的和
 */
public class Demo028 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        int[] nums = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int k = Integer.parseInt(sc.nextLine());
        System.out.println(getResult(nums,k));
    }

    public static int getResult(int[] nums, int k){
        if (k <= 0)return -1;
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (num < 0 || num > 1000) return -1;
            set.add(num);
        }

        if (set.size() < k * 2) return -1;
        int[] arr = set.stream().mapToInt(Integer::valueOf).toArray();
        Arrays.sort(arr);
        int ans = 0, l = 0, r = set.size() - 1;
        while (k > 0){
            ans += arr[l] + arr[r];
            k --;
            r --;
            l ++;
        }
        return ans;

    }
}
