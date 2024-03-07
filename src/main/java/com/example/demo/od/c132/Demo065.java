package com.example.demo.od.c132;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * API集群负载统计
 */
public class Demo065 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        int res = 0;
        List<String[]> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] str = sc.nextLine().split("/");
            list.add(str);
        }
        String[] str = sc.nextLine().split(" ");
        System.out.println(getResult(list,Integer.parseInt(str[0]),str[1]));
    }

    public static int getResult(List<String[]> strs, int c, String name){
        int res = 0;
        for (String[] str : strs) {
            if(str.length > c && str[c].equals(name)){
                res ++;
            }
        }
        return res;
    }
}
