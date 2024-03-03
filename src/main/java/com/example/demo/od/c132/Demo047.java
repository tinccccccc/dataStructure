package com.example.demo.od.c132;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * 开源项目热榜、开源项目热度榜单
 */
public class Demo047 {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        int[] weight = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        String[][] projects = new String[n][2];
        for (int i = 0; i < n; i++) {
            String[] strs = Arrays.stream(sc.nextLine().split(" ")).toArray(String[]::new);
            StringJoiner joiner = new StringJoiner(" ");
            projects[i][0] = strs[0];
            for (int i1 = 1; i1 < strs.length; i1++) {
                joiner.add(strs[i1]);
            }
            projects[i][1] = joiner.toString();
        }
        System.out.println(getResult(weight,projects));
    }

    public static String getResult(int[] weight,String[][] projects){
        StringJoiner joiner = new StringJoiner("\n");
        String[] strs = Arrays.stream(projects).sorted(new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                int[] nums1 = Arrays.stream(o1[1].split(" ")).mapToInt(Integer::parseInt).toArray();
                int weight1 = 0;
                for (int i = 0; i < nums1.length; i++) {
                    weight1 += nums1[i] * weight[i];
                }
                int[] nums2 = Arrays.stream(o2[1].split(" ")).mapToInt(Integer::parseInt).toArray();
                int weight2 = 0;
                for (int i = 0; i < nums2.length; i++) {
                    weight2 += nums2[i] * weight[i];
                }
                if (weight1 != weight2) {
                    return weight2 - weight1;
                } else {
                    return o1[0].compareTo(o2[0]);
                }
            }
        }).map(a -> a[0]).toArray(String[]::new);
        for (String str : strs) {
            joiner.add(str);
        }
        return joiner.toString();
    }
}
