package com.example.demo.od.b200;

import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;

//相同数字组成图形的周长
public class demo5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = Integer.parseInt(sc.nextLine());
        int [][] angle = new int[64][64];
        int [][] array = new int[num][];
        for (int i = 0; i < num; i++) {
            int[] arr = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            array[i] = arr;
            for (int j = 1; j < arr.length;j++) {
                angle[arr[j]][arr[++j]] = arr[0];
            }
        }

        System.out.println(getResult(angle,array));
    }


    public static String getResult(int[][] angle,int[][] array){
        StringJoiner joiner = new StringJoiner(" ");

        int[][] offset = new int[][] {{1,0},{-1,0},{0,1},{0,-1}};

        for (int[] ints : array) {
            int count = 0;
            for (int i = 1; i < ints.length; i++) {
                int target = ints[0];
                int x = ints[i];
                int y = ints[++i];

                for (int[] step : offset) {
                    int newX = x + step[0];
                    int newY = y + step[1];

                    //未越界
                    if (newX >= 0 && newX < 64 && newY >= 0 && newY < 64){
                        if (angle[newX][newY] != target) count ++;
                    }else {
                        //越界
                        count ++;
                    }
                }
            }
            joiner.add(count+"");
        }
        return joiner.toString();
    }
}
