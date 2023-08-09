package com.example.demo.od.b200;


import java.util.*;

//评论转换输出
public class demo18 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        String[] arr = str.split(",");
        getResult(arr);
    }
    public static void getResult(String[] arr){
        //记录结果集，下标为层级，对应的list为改成结果集
        List<List<String>> tree= new ArrayList<>();
        //用于遍历队列里所有的数值
        LinkedList<String> queue = new LinkedList<>(Arrays.asList(arr));
        //层级
        int level = 1;

        while (!queue.isEmpty()){
            String commend = queue.removeFirst();

            //添加第一级
            //表示当前遍历到层级，还没添加到true中
            if (tree.size() < level){
                tree.add(new ArrayList<>());
            }
            tree.get(0).add(commend);

            //第一级下的子集数
            int child = Integer.parseInt(queue.removeFirst());

            reverse(tree,queue,level+1,child);
        }

        System.out.println(tree.size());
        for (List<String> itemList : tree) {
            StringJoiner joiner = new StringJoiner(" ");
            for (String str : itemList) {
                joiner.add(str);
            }
            System.out.println(joiner);
        }
    }

    public static void reverse(List<List<String>> tree,LinkedList<String> queue,int level,int child){
        for (int i = 0; i < child; i++) {
            //表示当前遍历到层级，还没添加到true中
            if (tree.size() < level){
                tree.add(new ArrayList<>());
            }

            String command = queue.removeFirst();
            tree.get(level-1).add(command);

            int count = Integer.parseInt(queue.removeFirst());
            if (count > 0){
                reverse(tree,queue,level + 1,count);
            }
        }
    }
}
