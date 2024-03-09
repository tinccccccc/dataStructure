package com.example.demo.od.c132;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * 机场航班调度程序
 */
public class Demo070 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] strs = sc.nextLine().split(",");
        System.out.println(getResult(strs));
    }

    public static String getResult(String[] strs){
        String[] res = Arrays.stream(strs).sorted(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String a = o1.substring(0,2);
                String b = o2.substring(0,2);
                int c = Integer.parseInt(o1.substring(2));
                int d = Integer.parseInt(o2.substring(2));
                if (a.equals(b)){
                    return c - d;
                }else {
                    return a.compareTo(b);
                }
            }
        }).toArray(String[]::new);
        StringJoiner joiner = new StringJoiner(",");
        for (String str : res) {
            joiner.add(str);
        }
        return joiner.toString();
    }
}

//0H7444,0H6305,0N2428,157051,346610,3J8761,3T6772,3X8323,4R4050,5M6575,7O0713,AB7750,AY2446,B36884,BF0085,BW4727,EF7616,F50854,FE6068,FT4716,HX5830,JE2682,NL2536,NY2601,PM0734,QK2220,RO5762,UP8823,US0364,UT2506,V70757,VM4454,WL3212,X41047,ZE8743
//0H6305,0H7444,0N2428,157051,346610,3J8761,3T6772,3X8323,4R4050,5M6575,7O0713,AB7750,AY2446,B36884,BF0085,BW4727,EF7616,F50854,FE6068,FT4716,HX5830,JE2682,NL2536,NY2601,PM0734,QK2220,RO5762,UP8823,US0364,UT2506,V70757,VM4454,WL3212,X41047,ZE8743
