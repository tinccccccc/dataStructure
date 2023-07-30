package com.example.demo.od.b100;

import java.util.Scanner;

//需要打开多少监视器
public class demo2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        int j = sc.nextInt();

        int [][] nums = new int[i][j];
        for (int k = 0; k < i; k++) {
            for (int l = 0; l < j; l++) {
                int num = sc.nextInt();
                nums[k][l] = num;
            }
        }
        System.out.println(getResult(nums,i,j));
    }



    public static int getResult(int[][] nums,int i ,int j){
        int num = 0;
        for (int k = 0; k < i; k++) {
            for (int l = 0; l < j; l++) {
                if (nums[k][l] == 1){
                    num++;
                }
                if (nums[k][l] == 0){
                    if (k>0 && nums[k-1][l] == 1){
                        num ++;
                        continue;
                    }
                    if (l>0 && nums[k][l-1] == 1){
                        num ++;
                        continue;
                    }
                    if (k<i - 1 && nums[k+1][l] == 1){
                        num ++;
                        continue;
                    }
                    if (l<j - 1 && nums[k][l + 1] == 1){
                        num ++;
                    }
                }
            }
        }
        return num;
    }
}

