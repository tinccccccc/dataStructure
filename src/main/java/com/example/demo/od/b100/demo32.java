package com.example.demo.od.b100;

import java.util.Arrays;
import java.util.Scanner;

//最长公共后缀(自做)
public class demo32 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        String[] arr = Arrays.stream(str.substring(1, str.length() - 1).split(",")).map(item -> item.substring(1,item.length()-1)).toArray(String[]::new);
        getResult(arr);
    }
//["aadfbc","bbc","bc"]
    public static void getResult(String[] arr){
        //默认第一个为最长公共后缀
        String suffix = arr[0];
        for (int i = 1; i < arr.length; i++) {
            suffix  = getSuffix(suffix,arr[i]);
        }
        System.out.println(suffix);
    }

    public static String getSuffix(String str1,String str2){
        int length = Math.min(str1.length(),str2.length());
        if (str1.charAt(str1.length()-1) != str2.charAt(str2.length()-1)) return "@Zero";
        for (int i = 0; i < length; i++) {
            if (str1.charAt(str1.length()-1-i) != str2.charAt(str2.length()-1-i)) {
                return str1.substring(str1.length()-i);
            }
        }
        return str1.length()>str2.length()?str2:str1;
    }

}
