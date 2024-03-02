package com.example.demo.od.c132;

import java.util.Scanner;

/**
 * 最长子字符串的长度    (这题主机官方题解，更加巧妙)
 */
public class Demo039 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
    }


    public static int getResult(String str){
        int n = str.length();
        char[] chars = new char[n*2];
        for (int i = 0; i < n * 2; i++) {
            chars[i] = str.charAt(i % n);
        }
        int res = 0;
        int len = 0;
        //o的次数
        int num = 0;
        int left = 0, right = 0;
        while (right < n * 2){
            len ++;
            char c = chars[right];
            if (c == 'o'){
                num ++;
            }
            if (num % 2 == 0 && len > res){
                res = len;
            }
            //窗口达到最大值了，left 要收缩了
            if (right - left == n - 1){
                char lc = chars[left];
                len --;
                if (lc == 'o'){
                    num --;
                }
                left ++;
                //因为要求最大值，收缩的窗口要大于已经求得的res 值才有意义
                while (right - left  + 1 > res){
                    char rc = chars[right];
                    len --;
                    if (rc == 'o'){
                        num --;
                    }
                    right --;
                }
            }
            right ++;
        }
        return res;
    }
}
