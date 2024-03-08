package com.example.demo.od.c132;

import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * 虚拟理财游戏   回溯算法
 */
public class Demo067 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        arr1 = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        arr2 = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        arr3 = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        arr4 = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        used = new int[arr4.length];
        backtrack(arr1[1],0,0);
        System.out.println(res);
    }

    static int[] arr1;
    static int[] arr2;
    static int[] arr3;
    static int[] arr4;

    static int min = Integer.MAX_VALUE;
    static int max = 0;
    static int[] used;
    static String res = "";

    public static void backtrack(int money, int risk, int cur){
        if (risk > arr1[2]) return;
        if (cur <= 2 && money >= 0){
            int sum = 0;
            int fen = 0;
            for (int i = 0; i < used.length; i++) {
                int num = used[i];
                if (num > 0){
                    sum += num * arr2[i];
                    fen += num * arr2[i] * arr3[i];
                }
            }
            if (sum > max || (sum == max && fen <= min)){
                max = sum;
                min = fen;
                StringJoiner joiner = new StringJoiner(" ");
                for (int i : used) {
                    joiner.add(Integer.toString(i));
                }
                res = joiner.toString();
            }
        }
        if (cur + 1 > 2) return;
        for (int i = 0; i < arr4.length; i++) {
            if (used[i] > 0) continue;
            if (risk + arr3[i] > arr1[2]) continue;
            int num = arr4[i];
            if (num > money){
                used[i] = money;
                backtrack( 0,risk + arr3[i], cur +1);
                used[i] = 0;
            }else {
                used[i] = num;
                backtrack( money - num,risk + arr3[i], cur +1);
                used[i] = 0;
            }
        }
    }
}
