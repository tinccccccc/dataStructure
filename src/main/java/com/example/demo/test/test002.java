package com.example.demo.test;

import java.util.Arrays;
import java.util.Scanner;

public class test002 {
    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int n  = Integer.parseInt(scanner.nextLine());
//        Integer[][] arr = new Integer[n][n];
//        for (int i = 0; i < n; i ++){
//            String s = scanner.nextLine();
//            Integer[] ar = Arrays.stream(s.split(" ")).map(Integer::parseInt).toArray(Integer[]::new);
//            arr[i] = ar;
//        }
//        int i = scanner.nextInt();

        System.out.println(getResult(new Integer[][]{{1,0,0,0,0},{0,2,0,0,0},{1,1,3,0,0},{1,1,0,4,0},{0,0,1,1,5}},5,1));
//        System.out.println(getResult(arr,i));
    }

    public static int getResult(Integer[][] arr, int i,int index){

        Integer[] ar = arr[i-1];
        int result = arr[i-1][i-1];
        if (i == 1 || index == i) return result;
        int tmp = 0;
        for (int a = 0 ; a < ar.length; a ++){
            if (ar[a] == 1){
                tmp = Math.max(tmp,getResult(arr,a+1,index + 1));
            }
        }

        return result + tmp;
    }





}
