package com.example.demo.leetcode;

import java.util.Arrays;

//698. 划分为k个相等的子集
public class demo1 {
    public static void main(String[] args) {

        System.out.println(canPartitionKSubsets(new int[]{2,2,2,2,3,4,5},4));
    }


    /**
     * 1.使用二分法。查找适合的k （本体直接给了k，无需再求了）
     * 2.使用回溯算法，判断该可是否满足条件
     *
     * @param nums
     * @param k
     * @return
     */
    public static boolean canPartitionKSubsets(int[] nums, int k) {
        //每个子集都相等，说明可以被均分
        int sum = Arrays.stream(nums).sum();
        if (sum % k != 0) return false;

        //被均分的每个子集值
        int target = sum / k;
        return backtrack(nums,0,target,new int[k], k);
    }

    /**
     *  判断是nums数组能否平均分配给k个子集
     *
     * @param nums      判断的数组
     * @param index     当前深度，其实就是表示当前已经使用了多少个 nums 中的数
     * @param target    目标值
     * @param bucket    记录每个子集的数值
     * @param k         要求的子集数
     * @return
     */
    public static boolean backtrack(int[] nums, int index, int target, int[] bucket, int k){
        //当前数都用完了，还没问题，当然返回true了
        if (nums.length == index) return true;

        //当前四个子集，index 出的元素，可以有四种选择，当前选择的前提体检是满足要求，数值<=target
        for (int i = 0; i < k; i++) {

            //减枝
            if(i > 0 && bucket[i] == bucket[i -1]) continue;

            if (bucket[i] + nums[index] <= target){
                bucket[i] += nums[index];
                if (backtrack(nums,index + 1,target,bucket,k)) return true;
                //当前子集不选，则需要将刚刚添加的在取出来
                bucket[i] -= nums[index];
            }
        }
        //都遍历完了，还没找到合适的，说明没合适的！！！
        return false;
    }
}
