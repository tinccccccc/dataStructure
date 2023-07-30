package com.example.demo.od.b100;

import java.util.*;

//告警抑制
public class demo3 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int i = Integer.parseInt(sc.nextLine());

        Map<String, HashSet<String>> map = new HashMap<>();
        for (int j = 0; j < i; j++) {
            String[] st = sc.nextLine().split(" ");
            String s1 = st[0];
            String s2 = st[1];
            map.putIfAbsent(s2,new HashSet<>());
            map.get(s2).add(s1);
        }
        String[] st = sc.nextLine().split(" ");
        System.out.println(getResult(map,st));
    }

    public static String getResult(Map<String, HashSet<String>> map,String[] st){
        StringJoiner sj = new StringJoiner(" ");
        List<String> right = new ArrayList<>();
        for (int i = 0; i < st.length; i++) {
            right.add(st[i]);
            if (i == 0) {
                sj.add(st[i]);
                continue;
            }

            boolean flag = true;
            for (String s : st) {
                if (map.get(st[i]) != null && map.get(st[i]).contains(s)){
                    flag = false;
                }
            }

            if (flag){
                sj.add(st[i]);
            }
        }

        return sj.toString();
    }
}
