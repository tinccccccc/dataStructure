package com.example.demo.od.c132;

import java.util.Scanner;
import java.util.StringJoiner;

/**
 *  素数之积
 */
public class Demo012 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        System.out.println(getResult(num));
    }

    public static String getResult(int num){
        StringJoiner joiner = new StringJoiner(" ");
        for (int i = 2; (i * i) <= num; i ++){
            if (num % i == 0){
                if (isPrime(i) && isPrime(num / i)){
                    joiner.add(i + "");
                    joiner.add(num / i + "");
                }
            }
        }
        return joiner.length() == 0 ? "-1 -1": joiner.toString();
    }

    public static boolean isPrime(int num){
        for (int i = 2; (i * i) <= num; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
}
