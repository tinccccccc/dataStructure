package com.example.demo.od.b200;

import java.util.*;

//跳房子II
public class demo14 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        int[] arr = Arrays.stream(str.substring(1, str.length() - 1).split(",")).mapToInt(Integer::parseInt).toArray();
        int target = Integer.parseInt(sc.nextLine());
        getResult(arr,target);
    }

    public static void  getResult(int[] arr, int target){
        StringJoiner joiner = new StringJoiner(",", "[", "]");
        //构建step集合
        List<Step> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            list.add(new Step(i,arr[i]));
        }
        //根据值升序
        list.sort(Comparator.comparing(a -> a.value));
        //存放结果集
        List<Step> result = new ArrayList<>();
        //记录最小的索引和
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < list.size(); i++) {
            //左指针
            int l = i + 1;
            //右指针
            int r = list.size() - 1;
            while (l < r){
                int sum = list.get(i).value + list.get(l).value + list.get(r).value;
                int indexSum = list.get(i).idx + list.get(l).idx + list.get(r).idx;
                if (sum == target){
                    if (indexSum < min){
                        min = indexSum;
                        result.clear();
                        result.add(list.get(i));
                        result.add(list.get(l));
                        result.add(list.get(r));
                    }
                    r --;
                    continue;
                }
                if (sum < target){
                    l ++;
                    continue;
                }
                if (sum > target){
                    r --;
                }
            }
        }
        result.sort(Comparator.comparing(a -> a.idx));
        for (Step step : result) {
            joiner.add(step.value+"");
        }
        System.out.println(joiner);
    }
   static class Step{
        Integer idx;
        Integer value;
       public Step(Integer idx, Integer value) {
           this.idx = idx;
           this.value = value;
       }
   }
}
