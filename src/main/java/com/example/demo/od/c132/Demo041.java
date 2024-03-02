package com.example.demo.od.c132;

import java.util.Scanner;
import java.util.StringJoiner;

/**
 * 密码输入检测
 */
public class Demo041 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        System.out.println(getResult(str));
    }

    public static String getResult(String str){
        int big = 0;
        int sma = 0;
        int num = 0;
        int nEmpty = 0;
        char[] chars = str.toCharArray();
        char[] res = new char[chars.length];
        int index = 0;
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c == '<'){
                if (i > 0){
                    index --;
                    char pre = chars[i-1];
                    if ('A' <= pre && 'Z' >= pre){
                        big --;
                    }else if ('a' <= pre && 'z' >= pre){
                        sma --;
                    }else if (Character.isDigit(pre)){
                        num --;
                    }else if (pre != ' '){
                        nEmpty --;
                    }
                }
            }else {
                res[index ++] = c;
                if ('A' <= c && 'Z' >= c){
                    big ++;
                }else if ('a' <= c && 'z' >= c){
                    sma ++;
                }else if (Character.isDigit(c)){
                    num ++;
                }else if (c != ' '){
                    nEmpty ++;
                }
            }
        }
        StringJoiner joiner = new StringJoiner(",");
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < index; i++) {
            builder.append(res[i]);
        }
        joiner.add(builder);
        if (builder.length() >= 8 && sma >= 1 && big >= 1 && num >= 1 && nEmpty >= 1){
            joiner.add("true");
        }else {
            joiner.add("false");
        }
        return joiner.toString();
    }


}
