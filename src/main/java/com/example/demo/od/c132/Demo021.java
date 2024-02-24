package com.example.demo.od.c132;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 连续字母长度
 */
public class Demo021 {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        char[] cs = sc.nextLine().toCharArray();
        int k = Integer.parseInt(sc.nextLine());
        System.out.println(getResult(cs,k));
    }

    public static int getResult(char[] cs, int k){
        if (k <= 0) return -1;
        int n = cs.length;
        Map<Character,Integer> map = new HashMap<>();
        int cnt = 1;
        char front = cs[0];
        for (int i = 1; i < n; i++) {
            if (cnt == 0){
                front = cs[i];
                cnt = 1;
                continue;
            }
            char cur = cs[i];
            if (front == cur){
                cnt ++;
            }else {
                Integer count = map.getOrDefault(front, 1);
                if (cnt > count){
                    map.put(front,cnt);
                }else {
                    map.put(front,count);
                }
                front = cur;
                cnt = 1;
            }
        }
        Integer count = map.getOrDefault(front, 1);
        if (cnt > count){
            map.put(front,cnt);
        }else {
            map.put(front,count);
        }
        if (map.size() < k) return -1;
        int[] arr = new int[map.size()];
        int index = 0;
        for (Character key : map.keySet()) {
            arr[index ++] = map.get(key);
        }
        Arrays.sort(arr);
        return arr[map.size() - k];
    }

}
