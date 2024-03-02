package com.example.collectionstudy;


import java.util.*;

public class Test {
    public static void main(String[] args){
        Vector<Integer> vector = new Vector<>();
        vector.add(1);
        vector.add(2);
        vector.add(3);
        vector.add(4);
        vector.add(5);
        Integer[] objects = vector.toArray(new Integer[3]);
        ArrayList<Integer> li = new ArrayList<>(vector);
        objects[0] = 9;

        for (Integer a : vector) {
            System.out.println(a);
        }
        for (Object a : objects) {
            System.out.println(a);
        }
    }


    public static String minWindow(String s, String t) {
        int left = 0, right = 0;
        Map<Character,Integer> map = new HashMap<>();
        Map<Character,Integer> window = new HashMap<>();
        int n = t.length();
        for(int i = 0;i < n; i++){
            char c = t.charAt(i);
            int count = map.getOrDefault(c,0);
            map.put(c,count +1);
        }
        int valid = 0;
        int minLen = Integer.MAX_VALUE;
        StringJoiner joiner = new StringJoiner("");
        //[ )
        while(right < s.length()){
            char c = s.charAt(right);
            Integer cnt = window.getOrDefault(c, 0);
            window.put(c,cnt + 1);
            //判断是否有效
            int need = map.getOrDefault(c,0);
            if(need > cnt){
                valid ++;
            }
            while(valid == n){
                if(minLen > right - left){
                    minLen = right - left;
                    int temp = left;
                    joiner = new StringJoiner("");
                    while(temp <= right){
                        joiner.add(s.charAt(temp) + "");
                        temp ++;
                    }
                }
                char lc = s.charAt(left);
                int cur = window.get(lc);
                if (map.containsKey(lc) && cur <= map.get(lc)){
                    valid --;
                }
                window.put(lc,cur - 1);
                left ++;
            }
            right ++;
        }
        return joiner.toString();
    }
}
