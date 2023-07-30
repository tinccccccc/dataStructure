package com.example.demo.od.b100;

import java.util.Arrays;
import java.util.Scanner;

//计算最接近的数(自写通过)
public class demo33 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        int index = str.lastIndexOf(",");
        int[] array = Arrays.stream(str.substring(1, index - 1).split(",")).mapToInt(Integer::parseInt).toArray();
        int num = Integer.parseInt(str.substring(index + 1));
        System.out.println(getResult(array,num));
    }

    public static int getResult(int[] arr, int num){
        int[] sort = Arrays.stream(arr).sorted().toArray();
        int mid = sort[arr.length / 2];

        //记录结果
        int result = 0;
        //当前窗口的值
        int win = arr[0];
        for (int i = 1; i < num; i++) {
            win -= arr[i];
        }
        //距离中位数的差值
        int abs = Math.abs(win - mid);

        for (int i = 1; i < arr.length - num + 1; i++) {
            win = win - arr[i - 1] + arr[i] * 2 + - arr[i + num - 1];
            int curAbs = Math.abs(win - mid);

            //如果相同，需要保留最大的i
            if (curAbs <= abs){
                result = i;
                abs = curAbs;
            }
        }
        return result;
    }
}
