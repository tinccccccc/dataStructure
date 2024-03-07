package com.example.demo.od.c132;

import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * CPU算力分配
 */
public class Demo064 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String n = sc.nextLine();
        int[] a = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] b = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        System.out.println(getResult(a,b));
    }

    public static String getResult(int[] a, int[] b){
        int as = Arrays.stream(a).sum();
        int bs = Arrays.stream(b).sum();
        a = Arrays.stream(a).sorted().toArray();
        b = Arrays.stream(b).sorted().toArray();

        StringJoiner joiner = new StringJoiner(" ");
        for (int ai : a) {
            for (int bi : b) {
                if (as - ai + bi == bs + ai - bi){
                    joiner.add(ai+"");
                    joiner.add(bi+"");
                    return joiner.toString();
                }
            }
        }
        return "";
    }
}
