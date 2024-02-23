package com.example.demo.od.c132;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**
 *  字符串筛选排序
 */
public class Demo010 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        int k = sc.nextInt();
        System.out.println(getResult(str,k));
    }

    public static int getResult(String str,int k){
        HashMap<Character,Integer> map = new HashMap<>();
        int res = 0;
        int n = str.length();
        char[] chars = str.toCharArray();
        for (int i = 0; i < n; i++) {
            map.putIfAbsent(chars[i],i);
        }
        Arrays.sort(chars);
        if (k > n){
            return map.get(chars[n - 1]);
        }
        return map.get(chars[k - 1]);
    }
}
