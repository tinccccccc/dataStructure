package com.example.demo.od.b200;

import java.util.*;

//找出两个整数数组中同时出现的整数
public class demo8 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] array = new int[2][];

        int index = 0;
        while (sc.hasNextLine()){
            String str = sc.nextLine();
            if (str.equals(""))break;
            int[] item = Arrays.stream(str.split(",")).mapToInt(Integer::parseInt).toArray();
            array[index++] = item;
        }
        getResult(array);
    }

    public static void getResult(int[][] array){
        //记录同时出现的次数和对应的数字
        Map<Integer,TreeSet<Integer>> result = new HashMap<>();

        //记录第一个数组每个数字出现的次数
        Map<Integer,Integer> one = new HashMap<>();

        //记录第二个数组每个数字出现的次数
        Map<Integer,Integer> two = new HashMap<>();

        //遍历第一个数组
        for (int num : array[0]) {
            one.put(num,one.getOrDefault(num,0)+1);
        }

        //遍历第二个数组
        for (int num : array[1]) {
            two.put(num,two.getOrDefault(num,0)+1);
        }

        for (Integer key : one.keySet()) {
            if (two.containsKey(key)){
                //以最少次数为准
                int min = Math.min(one.get(key),two.get(key));

                //将当前数字加入到结果集里
                TreeSet<Integer> has = result.getOrDefault(min, new TreeSet<>());
                has.add(key);
                result.put(min,has);
            }
        }

        //拼接结果字符串输出
        if (result.size() == 0) {
            System.out.println("NULL");
            return;
        }

        //排序输出
        result.keySet().stream().sorted().forEach(count -> {
            StringJoiner joiner = new StringJoiner(",",count + ":","");
            for (Integer num : result.get(count)) {
                joiner.add(num + "");
            }
            System.out.println(joiner);
        });
    }
}
