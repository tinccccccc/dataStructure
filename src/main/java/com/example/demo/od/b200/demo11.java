package com.example.demo.od.b200;

import java.util.Arrays;
import java.util.Scanner;

//数据最节约的备份方法
public class demo11 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Integer[] arr = Arrays.stream(sc.nextLine().split(",")).map(Integer::new).toArray(Integer[]::new);
        System.out.println(getResult(arr));
    }

    /**
     * 1.使用二分法查找出可能适合的光盘的数量
     * 2.使用回溯算法判断当前光盘数是否符合条件
     *
     * @param arr 判断的数组
     * @return
     */
    public static int getResult(Integer[] arr) {
        //每个文件放一个光盘，可的最大光盘数
        int max = arr.length;
        //最小光盘数
        int min = 1;
        //记录结果
        int result = arr.length;

        //将arr倒叙，提高效率
        Arrays.sort(arr, (a, b) -> b - a);

        while (min <= max) {
            //假设中位数满足条件
            int mid = (max + min) / 2;
            if (backtrack(arr, 0, 500, new int[mid], mid)) {
                //mid 可能是一个解
                result = mid;
                //继续尝试更小的
                max = mid - 1;
                continue;
            }
            min = mid + 1;
        }
        return result;
    }

    /**
     * 判断是nums数组能否平均分配给k个子集
     *
     * @param nums   判断的数组
     * @param index  当前深度，其实就是表示当前已经使用了多少个 nums 中的数
     * @param target 目标值
     * @param bucket 记录每个子集的数值
     * @param k      要求的子集数
     * @return
     */
    public static boolean backtrack(Integer[] nums, int index, int target, int[] bucket, int k) {
        //当前数都用完了，还没问题，当然返回true了
        if (nums.length == index) return true;

        //当前四个子集，index 出的元素，可以有四种选择，当前选择的前提体检是满足要求，数值<=target
        for (int i = 0; i < k; i++) {
            //减枝
            if (i > 0 && bucket[i] == bucket[i - 1]) continue;

            if (bucket[i] + nums[index] <= target) {
                bucket[i] += nums[index];
                if (backtrack(nums, index + 1, target, bucket, k)) return true;
                //当前子集不选，则需要将刚刚添加的在取出来
                bucket[i] -= nums[index];
            }
        }
        //都遍历完了，还没找到合适的，说明没合适的！！！
        return false;
    }
}
