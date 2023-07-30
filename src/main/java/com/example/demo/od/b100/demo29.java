package com.example.demo.od.b100;

import java.util.*;
import java.util.stream.Collectors;

//乘坐保密电梯 （有点难）
public class demo29 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 期望的楼层, 序列总个数
        int m = sc.nextInt(), n = sc.nextInt();

        // 序列
        Integer[] nums = new Integer[n];
        for (int i = 0; i < n; i++) nums[i] = sc.nextInt();

        System.out.println(getResult(m, n, nums));
    }

    static int expectUpCount; // 上升序列组合的元素个数
    static int limitUpSum; // 上升序列组合的元素之和的上限
    static int maxUpSumBelowLimit = 0; // 记录不超过limitUpSum的最大的上升序列的和，该变量用于帮助ans剔除不是最优解的上升序列
    static ArrayList<boolean[]> paths = new ArrayList<>(); // paths记录求组合过程中发现的符合要求的序列

    public static String getResult(int m, int n, Integer[] nums) {
        // 计算expectUpCount、limitUpSum
        int sum = Arrays.stream(nums).reduce(Integer::sum).orElse(0);
        expectUpCount = n / 2 + n % 2;
        limitUpSum = (sum + m) / 2;

        // 将原始序列降序
        Arrays.sort(nums, (a, b) -> b - a);

        // 求组合
        dfs(nums, 0, new boolean[n], 0, 0);

        // 本题没有说明异常处理，我理解应该不会存在paths.size == 0的情况，但是为了保险，还是处理一下
        if (paths.size() == 0) {
            return "-1";
        }

        // 首先将paths转化为结果序列，然后对结果序列进行排序，排序规则是：按先处理大值的原则, 最后取出最优解
        ArrayList<Integer> ans =
                paths.stream()
                        .map(path -> getSeq(path, nums))
                        .sorted((seq1, seq2) -> compare(seq1, seq2))
                        .collect(Collectors.toList())
                        .get(0);

        // 打印最优序列
        StringJoiner sj = new StringJoiner(" ");
        for (Integer v : ans) sj.add(v + "");
        return sj.toString();
    }

    /**
     * 该方法用于选取出上升序列的组合
     *
     * @param nums 原始序列（降序排序后）
     * @param index 当前层级能选取的元素范围的起始索引
     * @param path
     *     记录上升序列组合，注意path这里采用的是nums.length长度的boolean[]数组，如果path[i]为true，则代表nums[i]是上升，否则nums[i]就是下降
     * @param sum 上升序列组合的元素和
     * @param count 上升序列组合的元素个数
     */
    public static void dfs(Integer[] nums, int index, boolean[] path, int sum, int count) {
        // 如果上升序列元素个数超过了expectUpCount, 则结束对应递归
        if (count > expectUpCount) return;

        if (count == expectUpCount) {
            // 非更优解，直接剔除
            if (sum < maxUpSumBelowLimit) return;

            // 发现更优解，则清空paths
            if (sum > maxUpSumBelowLimit) {
                maxUpSumBelowLimit = sum;
                paths.clear();
            }

            // 记录对应解
            paths.add(Arrays.copyOf(path, path.length));
            return;
        }

        for (int i = index; i < nums.length; i++) {
            int num = nums[i];

            if (sum + num > limitUpSum) continue;

            path[i] = true;
            dfs(nums, i + 1, path, sum + num, count + 1);
            path[i] = false;
        }
    }

    // 根据path解析出上升序列和下降序列，并对上升序列和下降序列进行交叉合并，形成目标序列
    public static ArrayList<Integer> getSeq(boolean[] path, Integer[] nums) {
        // 上升序列
        LinkedList<Integer> up = new LinkedList<>();
        // 下降序列
        LinkedList<Integer> down = new LinkedList<>();

        // 根据path解析出上升序列、下降序列
        for (int i = 0; i < nums.length; i++) {
            if (path[i]) up.add(nums[i]);
            else down.add(nums[i]);
        }

        // 目标序列容器
        ArrayList<Integer> seq = new ArrayList<>();

        // 交叉合并
        for (int i = 0; i < nums.length / 2; i++) {
            seq.add(up.removeFirst());
            seq.add(down.removeFirst());
        }

        if (up.size() > 0) {
            seq.add(up.removeFirst());
        }

        return seq;
    }

    // 比较相同长度的两个集合的大小, 按逐个元素比较
    public static int compare(ArrayList<Integer> list1, ArrayList<Integer> list2) {
        for (int i = 0; i < list1.size(); i++) {
            int v1 = list1.get(i);
            int v2 = list2.get(i);

            if (v1 > v2) return -1;
            if (v1 < v2) return 1;
        }
        return 0;
    }
}
