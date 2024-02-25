package com.example.demo.od.c132;

import java.util.*;

/**
 * 最长的指定瑕疵度的元音子串  (重点，独自做了三小时才做出来。。)
 */
public class Demo024 {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int k = Integer.parseInt(sc.nextLine());
        char[] chars = sc.nextLine().toCharArray();
        System.out.println(getResult(chars,k));
    }

    static Set<Character> set = new HashSet<>(Arrays.asList('a','e','i','o','u','A','E','I','O','U'));
    public static int getResult(char[] chars, int k){
        int cnt = 0;
        int res = 0;
        int n = chars.length;
        int l = 0, r = 0;
        while (l < n && r < n){
            char rc = chars[r];
            if (!set.contains(rc)) {
                cnt ++;
                r ++;
                continue;
            }
            if (cnt <k){
                r ++;
                continue;
            }

            if (set.contains(chars[l]) && cnt == k){
                res = Math.max(res,r - l + 1);
                r ++;
                continue;
            }

            while (!set.contains(chars[l]) || cnt > k){
                char lc = chars[l];
                if (!set.contains(lc)){
                    cnt -- ;
                }
                l ++;
                if (l == n) break;
            }

        }
        return res;
    }


}
