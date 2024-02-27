package com.example.demo.od.c132;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 解密犯罪时间
 */
public class Demo032 {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        int[] nums = new int[4];
        nums[0] = Integer.parseInt(str.substring(0,1));
        nums[1] = Integer.parseInt(str.substring(1,2));
        nums[2] = Integer.parseInt(str.substring(3,4));
        nums[3] = Integer.parseInt(str.substring(4,5));
        System.out.println(getResult(nums));
    }

    static List<List<Integer>> list = new ArrayList<>();
    static LinkedList<Integer> track = new LinkedList<>();
    public static String getResult(int[] nums){
        String target = Arrays.stream(nums).mapToObj(String::valueOf).collect(Collectors.joining());
        Arrays.sort(nums);
        backtrack(nums,0);

        String[] strs = new String[list.size()];
        int index = 0;
        for (List<Integer> item : list) {
            strs[index ++] = item.stream().map(String::valueOf).collect(Collectors.joining());
        }
        Arrays.sort(strs);

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < strs.length; i++) {
            String str = strs[i];
            if (str.equals(target)){
                if (i == strs.length - 1){
                    builder.append(strs[0], 0, 2);
                    builder.append(":");
                    builder.append(strs[0],2,4);
                    return builder.toString();
                }else {
                    builder.append(strs[i + 1], 0, 2);
                    builder.append(":");
                    builder.append(strs[i + 1],2,4);
                    return builder.toString();
                }
            }
        }
        return "";
    }


    public static void backtrack(int[] nums,int k){
        if (4 < track.size()) return;

        if(4 == track.size()){
            list.add(new ArrayList<>(track));
        }

        for (int i = 0; i < nums.length; i ++){
            if (i != 0 && nums[i] == nums[i - 1]) continue;
            if (isLegal(nums[i],k)){
                track.addLast(nums[i]);
                backtrack(nums,k + 1);
                track.removeLast();
            }
        }
    }

    public static boolean isLegal(int n , int k){
        if (k == 0 && n > 2) return false;
        if (k == 1 && track.getLast() == 2 && n > 3) return false;
        if (k == 2 && n > 5) return false;
        return true;
    }
}
