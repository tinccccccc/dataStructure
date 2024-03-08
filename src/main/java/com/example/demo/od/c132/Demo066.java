package com.example.demo.od.c132;

import java.util.*;

/**
 * 万能字符单词拼写、掌握的单词个数     字符串统计问题
 */
public class Demo066 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int num = Integer.parseInt(sc.nextLine());
        String[] words = new String[num];
        for (int i = 0; i < num; i++) {
            words[i] = sc.nextLine();
        }
        String chars = sc.nextLine();
        System.out.println(getResult(words,chars));
    }


    public static int getResult(String[] words, String chars){
        Map<Character,Integer> wnt = new HashMap<>();
        Map<Character,Integer> cnt = new HashMap<>();
        char[] carr = chars.toCharArray();
        for (char c : carr) {
            Integer count = cnt.getOrDefault(c, 0);
            cnt.put(c,count + 1);
        }
        //万能符
        int wan = cnt.getOrDefault('?',0);
        int res = 0;
        for (String word : words) {
            wnt = new HashMap<>();
            for (char c : word.toCharArray()) {
                Integer count = wnt.getOrDefault(c, 0);
                wnt.put(c,count + 1);
            }
            int diff = 0;
            for (Character c : wnt.keySet()) {
                int count = wnt.get(c);
                if (count > cnt.getOrDefault(c,0)){
                    diff += count - cnt.getOrDefault(c,0);
                }
            }
            if (diff == 0 || diff <= wan) res ++;
        }
        return res;
    }


}
