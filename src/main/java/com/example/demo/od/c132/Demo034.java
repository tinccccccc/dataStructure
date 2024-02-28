package com.example.demo.od.c132;

import java.util.Arrays;
import java.util.Scanner;

public class Demo034 {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        int[] nums = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int k = Integer.parseInt(sc.nextLine());
        System.out.println(getResult(nums,nums.length,k));
    }

    public static int getResult(int[] nums, int n, int k){
        int sum = Arrays.stream(nums).sum();
        int[] arr = new int[sum];
        int cnt = 0;
        int index = 0;
        for (int i = 0; i < n; i++) {
            if (i == 0){
                cnt = 1;
            }else {
                cnt = cnt << 1;
            }
            int num = nums[i];
            while(num > 0){
                arr[index ++] = cnt;
                num --;
            }
        }

        int res = 0;
        int s = 0;
        int l = 0, r = sum - 1;
        while (l <= r){
            s += arr[r];

            while (s < k && r > l + 1){
                r --;
                s += arr[r];
            }
            s -= arr[r ++];

            if (s >= k){
                s = 0;
                res ++;
                r --;
                continue;
            }

            s += arr[l];
            while (l < r - 1 && s < k){
                l ++;
                s += arr[l];
            }

            if (s >= k){
                s = 0;
                res ++;
                r --;
                l ++;
                continue;
            }
            r --;
            l ++;
        }

        return res;
    }
}
