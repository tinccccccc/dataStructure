package com.example.demo.od.b200;

import java.util.Scanner;

//通过软盘拷贝文件
public class demo10 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = Integer.parseInt(sc.nextLine());
        int[] array = new int[num];
        for (int i = 0; i < num; i++) {
            array[i] = Integer.parseInt(sc.nextLine());
        }
        getResult(array);
    }

    public static void getResult(int[] array){
        //最大快数
        int max = 1474560 / 512;
        //文件数量
        int num = array.length;
        int[][] dp = new int[num+1][max+1];
        for (int i = 1; i <= num; i++) {
            //当前文件需要多少快
            int k = (int) Math.ceil(array[i - 1] / 512.0);
            for (int j = 1; j <= max; j ++) {
                //当前用了多少块
                if (k > j){
                    //当前不可选
                    dp[i][j] = dp[i-1][j];
                }else {
                    //当前可选也可不选
                    dp[i][j] = Math.max(dp[i-1][j],dp[i-1][j-k]+ array[i-1]);
                }
            }
        }
        System.out.println(dp[num][max]);
    }
}
