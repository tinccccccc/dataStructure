package com.example.demo.od.c132;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 */
public class Demo038 {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        int[] money = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        if (n == 1){
            System.out.println(money[0]);
            return;
        }
        int[][] nums = new int[n-1][2];
        for (int i = 0; i < n - 1; i++) {
            int[] arr = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            nums[i][0] = arr[0];
            nums[i][1] = arr[1];
        }
        System.out.println(getResult(nums,money));
    }

    public static int getResult(int[][] nums, int[] money){
        Map<Integer, List<int[]>> map = Arrays.stream(nums).collect(Collectors.groupingBy(a -> a[0]));
        int res = 0;
        for (Integer key : map.keySet()) {
            int sum = money[key-1];
            List<int[]> ints = map.get(key);
            for (int[] a : ints) {
                sum += money[a[1] - 1];
            }
            res = Math.max(res,sum);
        }
        return res;
    }
}
