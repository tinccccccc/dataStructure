package com.example.demo.od.b100;

import java.util.Arrays;
import java.util.Scanner;

//符合要求的元组的个数(自写通过)
public class demo24 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String nums = sc.nextLine();
        int[] arr = Arrays.stream(nums.split(" ")).mapToInt(Integer::parseInt).toArray();
        String k = sc.nextLine();
        String target = sc.nextLine();
        getResult(arr,Integer.parseInt(k),Integer.parseInt(target));
    }

    public static void getResult(int[] arr, int k, int target){
        int result = 0;
        Arrays.sort(arr);
        System.out.println(kSum(arr,k,0,arr.length -k,target,result,0));
    }

    public static long kSum(int[] arr, int k, int start,int end,int target,long result,long sum){
        if (k == 2){
            return towSum(arr,start,arr.length-1,target,result,sum);
        }

        for (int i = start; i <= end ; i++) {
            if (i > start && arr[i] == arr[i-1]) continue;
            result = kSum(arr,k-1,i +1,end + 1,target,result,sum + arr[i]);
        }
        return result;
    }

    public static long towSum(int[] arr,int l, int r, int target, long result,long sum){

        while (l<r){
            long all = arr[l] + arr[r] + sum;
            if (target == all){
                result ++;
                while (arr[r] == arr[--r]){
                }
            }
            if (all < target){
                l++;
            }

            if (all > target){
                r--;
            }

        }
        return result;
    }
}
