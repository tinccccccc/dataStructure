package com.example.demo.od.b200;

import java.util.Scanner;

//查字典
public class demo15 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        String[] arr = str.split(" ");
        boolean flag = true;
        for (int i = 2; i < arr.length; i++) {
            String a = arr[i];
            if (a.startsWith(arr[0])){
                flag = false;
                System.out.println(a);
            }
        }
        if (flag){
            System.out.println(-1);
        }
    }
}
