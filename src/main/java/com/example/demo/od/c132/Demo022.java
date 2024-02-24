package com.example.demo.od.c132;

import java.util.Scanner;
import java.util.StringJoiner;

/**
 * 拼接URL
 */
public class Demo022 {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] str = sc.nextLine().split(",");
        if (str.length == 0){
            System.out.println("/");
            return;
        }
        if (str.length == 1){
            StringJoiner joiner = new StringJoiner("");
            String str1 = str[0];
            int i = str1.length() - 1;
            if ( str1.charAt(i) == '/'){
                while (i >= 0 && str1.charAt(i) == '/'){
                    i --;
                }
                joiner.add(str1.substring(0,i + 2));
            }else {
                joiner.add(str1);
                joiner.add("/");
            }
            System.out.println(joiner.toString());
            return;
        }
        System.out.println(getResult(str[0],str[1]));
    }

    public static String getResult(String str1, String str2){
        StringJoiner joiner = new StringJoiner("");
        int n = str1.length();
        int m = str2.length();
        if (n == 0){
            int i = 0;
            if ( str2.charAt(i) == '/'){
                while (i < m && str2.charAt(i) == '/'){
                    i ++;
                }
                joiner.add(str2.substring(i -1));
            }else {
                joiner.add("/");
                joiner.add(str2);
            }
            return joiner.toString();
        }
        char a = str1.charAt(n - 1);
        char b = str2.charAt(0);
        if (a != '/' && b != '/'){
            joiner.add(str1);
            joiner.add("/");
            joiner.add(str2);
        }else if (a == '/' && b == '/'){
            int i = n - 1;
            while (i >= 0 && str1.charAt(i) == '/'){
                i --;
            }
            joiner.add(str1.substring(0,i + 2));
            int j = 0;
            while (j < m && str2.charAt(j) == '/'){
                j ++;
            }
            joiner.add(str2.substring(j));
        }else {
            joiner.add(str1);
            joiner.add(str2);
        }
        return joiner.toString();
    }
}
