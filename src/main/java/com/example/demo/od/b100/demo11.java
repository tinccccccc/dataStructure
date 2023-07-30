package com.example.demo.od.b100;

//恢复数字序列
import java.util.HashMap;
import java.util.Scanner;

public class demo11 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String s = sc.next(); // 打乱字符的字符串
        int k = sc.nextInt(); // 连续正整数序列的长度

        System.out.println(getResult(s, k));
    }

    public static int getResult(String s, int k) {
        // base：统计打乱字符的字符串中 各字符的数量
        HashMap<Character, Integer> base = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            base.put(c, base.getOrDefault(c, 0) + 1);
        }

        // 初始滑窗（长度k）中各字符的数量
        HashMap<Character, Integer> count = new HashMap<>();
        for (int i = 1; i <= k; i++) {
            countNumChar(i + "", count, true);
        }

        // 比较滑窗各字符数量，和base统计的各字符数量是否一致，若一致，则说明初始滑窗就是一个符合要求的连续整数数列，该数列的最小值为1
        if (cmp(base, count)) return 1;

        // 否则继续尝试后续滑窗，注意题目说正整数不超过1000，因此我们可以尝试连续正整数序列取值范围就是1~1000
        for (int i = 2; i <= 1000 - k + 1; i++) {
            // 相较于上一个滑窗失去的数字
            String remove = i - 1 + "";
            countNumChar(remove, count, false);

            // 相较于上一个滑窗新增的数字
            String add = i + k - 1 + "";
            countNumChar(add, count, true);

            // 比较
            if (cmp(base, count)) {
                return i;
            }
        }

        return -1; // 题目说存在唯一的序列满足条件，因此这里返回语句是走不到的
    }

    public static void countNumChar(String num, HashMap<Character, Integer> count, boolean isAdd) {
        for (int j = 0; j < num.length(); j++) {
            char c = num.charAt(j);
            count.put(c, count.getOrDefault(c, 0) + (isAdd ? 1 : -1));
        }
    }

    public static boolean cmp(HashMap<Character, Integer> base, HashMap<Character, Integer> count) {
        for (Character c : base.keySet()) {
            if (!count.containsKey(c) || count.get(c) - base.get(c) != 0) {
                return false;
            }
        }
        return true;
    }
}