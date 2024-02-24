package com.example.demo.od.c132;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 英文输入法
 */
public class Demo016 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        String k = sc.nextLine();
        System.out.println(getResult(str,k));

    }

    public static String getResult(String str,String k){
        String[] arr = str.split("[^a-zA-Z]");
        TreeSet<String> sets = Arrays.stream(arr).filter(a -> a.startsWith(k)).collect(Collectors.toCollection(TreeSet::new));
        StringJoiner joiner = new StringJoiner(" ");
        for (String item : sets) {
            joiner.add(item);
        }
        if (joiner.length() == 0) joiner.add(k);
        return joiner.toString();
    }
}
