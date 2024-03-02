package com.example.demo.od.c132;

/**
 * 学生重新排队   哎，这题目前oj没有答案可以通过
 */
import java.util.*;

public class Demo042 {
    // 分块（即连续的相同组的小朋友）
    static class Block {
        int groupId;
        int count;
        public Block(int groupId, int count) {
            this.groupId = groupId;
            this.count = count;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 初始小朋友（序号）排队顺序
        int[] nums = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = nums.length;

        // 序号->组号 映射关系
        int[] group = new int[n + 1];
        for (int i = 0; i < n; i++) {
            int num = sc.nextInt();
            group[num] = i / 3;
        }

        // 相邻相同组号合并统计
        LinkedList<Block> queue = new LinkedList<>();
        for (int num : nums) {
            int groupId = group[num];

            if (queue.isEmpty() || queue.getLast().groupId != groupId) {
                queue.addLast(new Block(groupId, 1));
            } else {
                queue.getLast().count++;
            }
        }

        // 记录调整次数
        int moved_count = 0;
        while (!queue.isEmpty()) {
            Block first = queue.removeFirst();

            // 当first.count = 1 时，情况如下
            // 1 x 1 1 y z
            // 1 x 1 y 1 z
            if (first.count == 1) {
                Block x = queue.getFirst();

                // 判断x是否存在连续完整分组
                while (x.count == 3) {
                    queue.removeFirst();
                    x = queue.getFirst();
                }

                if (x.groupId == first.groupId && x.count == 2) {
                    // 情况：1 2 2 2 x[1 1]
                    // 将开头1，移动进x中
                    moved_count++;
                    queue.removeFirst();
                } else {
                    // 情况如下：
                    // 1 x[2 2] 1 1
                    // 1 x[2] 1 2 1
                    // 将后面的两个1移动到开头
                    moved_count += 2;
                    queue = confirm(queue, first.groupId);
                }
            } else if (first.count == 2) {
                // 当first.count == 2 时，情况如下：
                // 1 1 x 1 y z
                moved_count += 1;
                queue = confirm(queue, first.groupId);
            }
        }

        System.out.println(moved_count);
    }

    public static LinkedList<Block> confirm(LinkedList<Block> queue, int confirmed_groupId) {
        LinkedList<Block> back_queue = new LinkedList<>();

        while (!queue.isEmpty()) {
            Block first = queue.removeFirst();

            if (first.groupId == confirmed_groupId) {
                continue;
            }

            if (back_queue.isEmpty() || back_queue.getLast().groupId != first.groupId) {
                back_queue.addLast(new Block(first.groupId, first.count));
            } else {
                back_queue.getLast().count += first.count;
            }
        }
        return back_queue;
    }
}
