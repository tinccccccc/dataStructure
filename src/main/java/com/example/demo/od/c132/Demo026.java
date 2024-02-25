package com.example.demo.od.c132;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 字符串变换最小字符串
 */
public class Demo026 {
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        System.out.println(gerResult(str));
    }

    public static String gerResult(String str){
        char[] chars1 = str.toCharArray();
        char[] chars2 = str.toCharArray();
        Arrays.sort(chars2);
        if (String.valueOf(chars2).equals(str)){
            return str;
        }
        int n = str.length();
        for (int i = 0; i < n; i++) {
            if (chars1[i] != chars2[i]){
                char t = chars2[i];
                int index = str.lastIndexOf(t);
                char temp = chars1[i];
                chars1[i] = t;
                chars1[index] = temp;
                return String.valueOf(chars1);
            }
        }
        return str;
    }


}
