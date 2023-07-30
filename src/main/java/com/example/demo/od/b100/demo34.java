package com.example.demo.od.b100;

import java.util.*;

// 支持优先级的队列(自写通过)
public class demo34 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();

        int[][] arr = Arrays.stream(str.substring(1, str.length() - 1).split("\\),\\("))
                .map(item -> Arrays.stream(item.split(",")).mapToInt(Integer::parseInt).toArray())
                .toArray(int[][]::new);
        System.out.println(getResult(arr));
    }

    public static String getResult(int[][] arr){
        StringJoiner joiner = new StringJoiner(",");
        //key:priority优先级 value:当前优先级所包含的所有去重后的数
        HashMap<Integer, LinkedHashSet<Integer>> map = new HashMap<>();
        for (int[] ints : arr) {
            int priority = ints[1];
            int num = ints[0];
            map.putIfAbsent(priority,new LinkedHashSet<>());
            map.get(priority).add(num);
        }
        //排序获取结果
        map.keySet().stream().sorted((a,b) -> b - a).forEach(key -> map.get(key).forEach(num -> joiner.add(num+"")));
        return joiner.toString();
    }
}
