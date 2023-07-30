package com.example.demo.od.b100;

import java.util.*;
import java.util.stream.Collectors;

//阿里巴巴找黄金箱(II)
public class demo13 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] arr = Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        System.out.println(getResult(arr));
    }

    public static int getResult(int[] arr){
        //1.统计数组中每个数字出现的次数
        Map<Integer,Integer> count = new HashMap<>();
        for (int num : arr) {
            count.put(num,count.getOrDefault(num,0) + 1);
        }

        //2.按数字出现的次数进行排序（降序）
        List<Map.Entry<Integer, Integer>> sortByTimesDesc = count.entrySet().stream().sorted((o1, o2) -> o2.getValue() - o1.getValue()).collect(Collectors.toList());

        //3.遍历然后剔除数字，并判断剔除数字数量是否大于等于数组的一半
        int half = arr.length / 2;
        int numType = 0;
        int deleteNum = 0;
        for (Map.Entry<Integer, Integer> map : sortByTimesDesc) {
            numType++;
            deleteNum += map.getValue();
            if (deleteNum >= half){
                //3.1大于等于则直接返回突出数字的类别数量
                return numType;
            }
            //3.2小于则继续剔除
        }

        return -1;
    }
}
