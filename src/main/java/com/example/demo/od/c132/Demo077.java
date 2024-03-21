package com.example.demo.od.c132;

/**
 * 数的分解 滑动窗口，需考虑溢出
 */

import java.util.Scanner;

public class Demo077 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Long a = Long.valueOf(sc.nextLine());
        if (a > Integer.MAX_VALUE) {
            System.out.println("N");
            return;
        }
        int x = Integer.parseInt(a.toString());
        System.out.println(getResult(x));
    }

    static StringBuilder builder = new StringBuilder();
    public static String getResult(int x) {
        if (x == 2) return "N";
        if (x % 2 != 0) {
            int a = x / 2;
            int b = x / 2 + 1;
            builder.append(x);
            builder.append("=");
            builder.append(a);
            builder.append("+");
            builder.append(b);
            return builder.toString();
        }

        int left = 1, right = 1;
        int min = Integer.MAX_VALUE;
        long sum = 0;

        while (right <= x / 2) {
            sum += right;
            if (sum == x && right - left < min) {
                append(x,left,right);
            } else if (sum > x) {
                while (sum > x) {
                    sum -= left;
                    left++;
                }
                if (sum == x && right - left < min) {
                    append(x,left,right);
                }
            }

            right++;
        }
        return builder.length() == 0? "N" : builder.toString();
    }

    static public void append(int x,int left,int right){
        builder = new StringBuilder();
        builder.append(x);
        builder.append("=");
        for (int i = left; i <= right; i++) {
            builder.append(i);
            if (i != right){
                builder.append("+");
            }
        }
    }

}
