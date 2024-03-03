package com.example.demo.od.c132;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 分割均衡字符串 (贪心思维)
 */
public class Demo051 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        System.out.println(getResult(str));
    }

    static Map<Character,Integer> window = new HashMap<>();
    public static int getResult(String str){
        int res = 0;
        int n = str.length();
        char[] chars = str.toCharArray();
        for (int i = 0; i < n; i++) {
            char c = chars[i];
            Integer cnt = window.getOrDefault(c, 0);
            window.put(c,cnt +1);
            if (isBalance()){
                res ++;
                window = new HashMap<>();
            }
        }
        return res;
    }

    public static boolean isBalance(){
        if (window.size() < 2) return false;
        int xcnt = window.getOrDefault('X',0);
        int ycnt = window.getOrDefault('Y',0);
        return ycnt > 0 && xcnt == ycnt;
    }
}
