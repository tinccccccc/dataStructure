package com.example.demo.od.c132;


import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 整数对最小和
 */
public class Demo030 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int[] arr1 = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] arr2 = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int k = Integer.parseInt(sc.nextLine());
        System.out.println(getResult(arr1,arr2,k));

    }
    static PriorityQueue<Integer> queue = new PriorityQueue<>();
    public static int getResult(int[] arr1, int[] arr2, int k){
        int n = arr1.length;
        int m = arr2.length;
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                int sum = arr1[i] + arr2[j];
                queue.add(sum);
            }
        }
        int res = 0;
        for (int i = 0; i < k; i++) {
            res += queue.poll();
        }
        return res;
    }
}
