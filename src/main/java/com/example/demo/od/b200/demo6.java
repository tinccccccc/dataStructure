package com.example.demo.od.b200;

import java.util.*;

//组装最大可靠性设备
public class demo6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] arr = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int num = Integer.parseInt(sc.nextLine());
        int[][] all = new int[num][];
        for (int i = 0; i < num; i++) {
            int[] one = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int[] item = new int[]{one[0],one[1],one[2]};
            all[i] = item;
        }
        System.out.println(getResult(arr,all));
//        System.out.println(getResult(new int[]{500,3},new int[][]{{0,80,100},{0,90,200},{1,50,50},{1,70,210},{2,50,100},{2,60,150}}));
//        System.out.println(getResult(new int[]{100,1},new int[][]{{0,90,200}}));
    }

    public static int getResult(int[] arr, int[][] all){
        //更具价值排序
        Arrays.sort(all, Comparator.comparingInt(a -> a[1]));
        //最大价值的下标
        int max = all.length-1;
        //最小价值的下标
        int min = 0;
        //中间价值的下标
        int mid = (min + max) >> 1;
        //记录结果
        int result = -1;
        while (min < max){
            if (check(arr,all,all[mid][1])){
                result = Math.max(result,all[mid][1]);
                min  = mid + 1;
            }else {
                max = mid - 1;
            }
            mid = (min + max) >> 1;
        }
        return result;
    }

    public static boolean check(int[] arr, int[][] all,int mid){
        int[][] sort = Arrays.stream(all).sorted(Comparator.comparingInt(a -> a[2])).toArray(int[][]::new);
        int count =0;
        int max = 0;
        //记录以存放的种类
        Set<Integer> set =new HashSet<>();
        for (int[] ints : sort) {
            if (ints[1] >= mid && count < arr[1] && !set.contains(ints[0])){
                set.add(ints[0]);
                max += ints[2];
                if (max > arr[0]) return false;
                count ++;
            }
        }
        if (count == arr[1] && max <= arr[0]){
            return true;
        }
        return false;
    }

}
