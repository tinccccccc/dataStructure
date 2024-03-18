package com.example.demo.od.c132;

import java.util.Scanner;

/**
 * 密码解密  没啥技巧，但是需要记一下
 */
public class Demo074 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String s = sc.nextLine();

        for (int i = 26; i >= 1; i--) {
            String key = i + (i > 9 ? "\\*" : "");
            String val = String.valueOf((char) ('a' + i - 1));
            s = s.replaceAll(key, val);
        }

        System.out.println(s);
    }
}