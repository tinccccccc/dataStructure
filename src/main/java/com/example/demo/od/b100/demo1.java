package com.example.demo.od.b100;

import java.util.Arrays;
import java.util.Scanner;

//矩阵稀疏扫描
public class demo1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] s = sc.nextLine().split(" ");
        int h = Integer.parseInt(s[0]);
        int l = Integer.parseInt(s[1]);

        int[] rowZeroCount = new int[h];
        int[] colZeroCount = new int[l];
        for (int i = 0; i < h; i++) {
            String[] nums = sc.nextLine().split(" ");
            for (int j = 0; j < l; j++) {
                if (Integer.parseInt(nums[j]) == 0){
                    rowZeroCount[i] ++;
                    colZeroCount[j] ++;
                }
            }

        }

        System.out.println(Arrays.stream(rowZeroCount).filter(val -> val >= l/2).count());
        System.out.println(Arrays.stream(colZeroCount).filter(val -> val >= h/2).count());

    }
}
