package com.example.demo.od.b200;

import java.util.*;

//数字游戏(这题的数学问题优化很巧妙)
public class demo13 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<int[]> list = new ArrayList<>();
        List<Integer> targetList = new ArrayList<>();
        boolean flag = false;
        while (sc.hasNextLine()){
            String str = sc.nextLine();
            if (str.equals("")) break;
            int[] a = Arrays.stream(str.split(" ")).mapToInt(Integer::parseInt).toArray();
            if (flag){
                list.add(a);
            }else {
                targetList.add(a[1]);
            }
            flag = !flag;
        }
        getResult(list,targetList);
    }

    public static void getResult(List<int[]> list, List<Integer> targetList){
        StringJoiner joiner = new StringJoiner("\n");
        for (int i = 0; i < list.size(); i++) {
            joiner.add(isExist(list.get(i),targetList.get(i)) + "");
        }
        System.out.println(joiner);
    }

    public static int isExist(int[] nums, int m) {
        HashSet<Integer> remain = new HashSet<>();
        remain.add(0);
        int sum = 0;
        for (int num : nums) {
            sum += num;
             //如果两个数a,b，他们的差a - b可以被k整除，则必然 a % k == b % k。
             //反之，如果a % k == b % k，那么 a - b 必然可以被k整除。
            if (remain.contains(sum % m)) {
                return 1;
            } else {
                remain.add(sum % m);
            }
        }
        return 0;
    }
}
