package com.example.demo.od.c132;

import java.util.Scanner;

/**
 * 字符串序列判定
 */
public class Demo023 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String str1 = sc.nextLine();
        String str2 = sc.nextLine();
        System.out.println(getResult(str1,str2));
    }

    public static int getResult(String str1, String str2){
        int l = 0;
        int r = -1;
        int n = str1.length();
        int m = str2.length();
        for (int i = 0; i < m; i++) {
            if (str2.charAt(i) == str1.charAt(l)){
                l ++;
                r =  i;
                if (l == n) return i;
            }
        }
        return r;
    }
}
