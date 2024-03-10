package com.example.demo.od.c132;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 攀登者2     正反向都跑一遍，并记录已经攀登过的山
 */
public class Demo073 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int[] nums = Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        int k = Integer.parseInt(sc.nextLine());
        used = new boolean[nums.length];
        int a = getResult(nums, k);
        int[] arr = new int[nums.length];
        boolean[] next = new boolean[nums.length];
        int index = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            arr[index] = nums[i];
            //记录已经攀登过的山，防止重复累计
            next[index] = used[i];
            index ++;
        }
        used = next;
        int b = getResult(arr,k);
        System.out.println(a + b);
    }
   static boolean[] used;
    public static int getResult(int[] nums, int k){

        int res = 0;
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            int num = nums[i];
            //山脚
            if (num == 0){
                int up = 0;
                int down = 0;
                int pre = num;
                int high = -1;
                boolean isUp = true;
                for (int j = i + 1; j < n; j++) {
                    if (nums[j] >= pre){
                        isUp = true;
                        up += (nums[j] - pre) * 2;
                        down += (nums[j] - pre);
                        pre = nums[j];
                        if (j == n - 1){
                            high = j;
                        }
                    }else {
                        if (isUp){
                            high = j - 1;
                            if ((up + down) < k && high != -1 && !used[high]){
                                used[high] = true;
                                res ++;
                            }
                        }
                        down += (pre - nums[j]);
                        up += (pre - nums[j]) * 2;
                        isUp = false;
                        pre = nums[j];
                    }
                }

            }
        }
        return res;
    }

}
