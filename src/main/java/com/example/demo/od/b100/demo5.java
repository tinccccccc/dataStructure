package com.example.demo.od.b100;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

//报文回路
public class demo5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = Integer.parseInt(sc.nextLine());
        Map<Integer,HashSet<Integer>> trans = new HashMap<>();
        for (int i = 0; i < num; i++) {
            String[] st = sc.nextLine().split(" ");
            int a = Integer.parseInt(st[0]);
            int b = Integer.parseInt(st[1]);
            trans.putIfAbsent(a,new HashSet<>());
            trans.putIfAbsent(b,new HashSet<>());
            trans.get(a).add(b);
        }
        System.out.println(getResult(trans));

    }

    public static String getResult(Map<Integer,HashSet<Integer>> trans) {

        for (Integer key : trans.keySet()){
            for (Integer num : trans.get(key)) {
                if (!trans.get(num).contains(key)) return "False";
            }
        }

        return "True";
    }
}
