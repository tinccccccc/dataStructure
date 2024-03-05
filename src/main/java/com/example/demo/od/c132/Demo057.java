package com.example.demo.od.c132;

import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * 智能成绩表
 */
public class Demo057 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int[] arr = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        String[] projects = sc.nextLine().split(" ");
        String[][] students = new String[arr[0]] [arr[1] + 1];
        for (int i = 0; i < arr[0]; i++) {
            students[i] = sc.nextLine().split(" ");
        }
        String sort = sc.nextLine();
        System.out.println(getResult(projects,students,sort));
    }

    public static String getResult(String[] projects, String[][] students, String sort){
        int index = -1;
        for (int i = 0; i < projects.length; i++) {
            if (sort.equals(projects[i])) index = i;
        }

        int p = index + 1;
        students = Arrays.stream(students).sorted((o1, o2) -> {
            if (p == 0){
                int as = 0;
                int bs = 0;
                for (int i = 1; i < o1.length; i++) {
                    as += Integer.parseInt(o1[i]);
                }
                for (int i = 1; i < o2.length; i++) {
                    bs += Integer.parseInt(o2[i]);
                }
                if (bs == as){
                    return o1[0].compareTo(o2[0]);
                }
                return bs - as;
            }else {
                if (Integer.parseInt(o2[p]) == Integer.parseInt(o1[p])){
                    return o1[0].compareTo(o2[0]);
                }
                return Integer.parseInt(o2[p]) - Integer.parseInt(o1[p]);
            }
        }).toArray(String[][]::new);

        StringJoiner joiner = new StringJoiner(" ");
        for (String[] student : students) {
            joiner.add(student[0]);
        }
        return joiner.toString();
    }
}

