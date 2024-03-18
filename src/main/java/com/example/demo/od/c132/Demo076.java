package com.example.demo.od.c132;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 考古学家  组合,答案要排除
 */
public class Demo076 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        String[] strs = sc.nextLine().split(" ");
        System.out.println(getResult(strs));
    }

    public static String getResult(String[] strs){
        used = new boolean[strs.length];
        backtrack(strs);
        List<String> list = set.stream().sorted().collect(Collectors.toList());
        StringJoiner joiner = new StringJoiner("\n");
        for (String str : list) {
            joiner.add(str);
        }
        return joiner.toString();
    }

    static LinkedList<String> stack = new LinkedList<>();
    static Set<String> set = new HashSet<>();
    static boolean[] used;
    public static void backtrack(String[] strs){
        if (stack.size() == strs.length){
            set.add(reverse(stack));
            return;
        }
        for (int i = 0; i < strs.length; i++) {
            if (used[i]) continue;
            used[i] = true;
            stack.addLast(strs[i]);
            backtrack(strs);
            used[i] = false;
            stack.removeLast();
        }
    }

    public static String reverse(LinkedList<String> stack){
        StringBuilder builder = new StringBuilder();
        for (String a : stack) {
            builder.append(a);
        }
        return builder.toString();
    }
}
