package com.example.demo.od.c132;

import java.util.*;

/**
 * 内存冷热标记
 */
public class Demo058 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        int[] nums = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int k = Integer.parseInt(sc.nextLine());
        System.out.println(getResult(nums,k));
    }

    public static String getResult(int[] nums, int k){
        Map<Integer,int[]> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            int[] cnt = map.getOrDefault(num, new int[2]);
            if (cnt[1] == 0){
                cnt[0] = i;
            }
            cnt[1] += 1;
            map.put(num,cnt);
        }

        int[][] arr = new int[map.size()][3];
        int index = 0;
        for (Integer num : map.keySet()) {
            int[] ints = map.get(num);
            int[] item = new int[3];
            item[0] = ints[0];
            item[1] = ints[1];
            item[2] = num;
            arr[index ++] = item;
        }
        int[][] res = Arrays.stream(arr).filter(a -> a[1] >= k).sorted((o1, o2) -> {
            if (o1[1] == o2[1]){
                return o1[2] - o2[2];
            }else {
                return o2[1] - o1[1];
            }
        }).toArray(int[][]::new);
        if (res.length == 0) return "0";
        StringJoiner joiner = new StringJoiner("\n");
        joiner.add(res.length +"");
        for (int[] re : res) {
            joiner.add(re[2] +"");
        }
        return joiner.toString();
    }
}
