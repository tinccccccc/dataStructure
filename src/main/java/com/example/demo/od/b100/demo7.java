package com.example.demo.od.b100;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

//阿里巴巴找黄金宝箱(V)
public class demo7 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> nums = Arrays.stream(sc.nextLine().split(",")).map(Integer::parseInt).collect(Collectors.toList());
        int n = Integer.parseInt(sc.nextLine());
        getResult(nums,n);
    }

    public static void getResult(List<Integer> nums, int n){
        int size = nums.size();
        if (size <= n){
            System.out.println(nums.stream().reduce(0, Integer::sum));
            return;
        }
        int max = nums.get(0) + nums.get(1) + nums.get(2) + nums.get(3);

        for (int i = n; i < size; i++) {
            max = Math.max(max,max-nums.get(i-n)+nums.get(i));
        }
        System.out.println(max);

    }
}
