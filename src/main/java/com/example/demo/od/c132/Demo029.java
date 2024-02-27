package com.example.demo.od.c132;

import java.util.*;

/**
 * 数组去重和排序
 */
public class Demo029 {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] strs = sc.nextLine().split(",");
        System.out.println(getResult(strs));
    }

    public static String getResult(String[] strs){
        Map<String,Integer> valueToIndex = new HashMap<>();
        Map<String,Integer>  valueToCnt= new HashMap<>();
        int n = strs.length;
        for (int i = 0; i < n; i++) {
            valueToIndex.putIfAbsent(strs[i],i);
            Integer cnt = valueToCnt.getOrDefault(strs[i], 0);
            valueToCnt.put(strs[i],cnt + 1);
        }

        String[][] arr = new String[n][2];
        for (String[] str : arr) {
            Arrays.fill(str,"0");
        }
        for (String value : valueToIndex.keySet()) {
            int index = valueToIndex.get(value);
            int cnt = valueToCnt.get(value);
            arr[index][0] = value;
            arr[index][1] = cnt+"";
        }
        arr =Arrays.stream(arr).sorted((a, b) -> Integer.parseInt(b[1]) - Integer.parseInt(a[1])).toArray(String[][]::new);
        StringJoiner joiner = new StringJoiner(",");
        for (int i = 0; i < valueToIndex.size(); i++) {
            joiner.add(arr[i][0]+"");
        }
        return joiner.toString();
    }

}
