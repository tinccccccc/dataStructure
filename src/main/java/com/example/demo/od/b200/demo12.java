package com.example.demo.od.b200;

import java.util.*;

//周末爬山
public class demo12 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] arr = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::new).toArray();
        int[][] hill = new int[arr[0]][arr[1]];

        for (int i = 0; i < arr[0]; i++) {
            int[] item = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            hill[i] = item;
        }
        System.out.println(getResult(arr,hill));
    }


    public static String getResult(int[] arr, int[][] hill){
        StringJoiner joiner = new StringJoiner(" ");
        //key：山峰高度  value：到达该山峰所需要的最短路径
        HashMap<Integer, Integer> map = new HashMap<>();
        //广度优先遍历
        bfs(arr,hill,map);
        Integer max = map.keySet().stream().max(Comparator.comparingInt(a -> a)).orElse(0);
        joiner.add(max+"");
        joiner.add(map.get(max)+"");
        return joiner.toString();
    }


    /**
     * 广度优先遍历
     *
     * @param arr
     * @param hill
     * @param map
     */
    public static void bfs(int[] arr, int[][] hill, HashMap<Integer, Integer> map){
        //偏移量
        int[][] offset = {{1,0},{-1,0},{0,1},{0,-1}};
        //存放是否遍历过
        boolean[][] visited = new boolean[arr[0]][arr[1]];
        //存放下一次可以到达的位置
        LinkedList<int[]> queue = new LinkedList<>();
        //据题意，从0,0开始
        visited[0][0] = true;
        //到达开始位置的山峰，位置为0
        map.put(hill[0][0],0);
        //路径
        int step = 0;
        queue.add(new int[]{0,0});
        while (!queue.isEmpty()){
            step += 1;
            LinkedList<int[]> next = new LinkedList<>();
            for (int[] now : queue) {
                for (int[] ints : offset) {
                    int x = ints[0] + now[0];
                    int y = ints[1] + now[1];
                    //越界了
                    if (x < 0 || x >= arr[0] || y < 0 || y >= arr[1]) continue;
                    //遍历过了
                    if (visited[x][y]) continue;
                    int height = Math.abs(hill[now[0]][now[1]] - hill[x][y]);
                    //高度差超过了要求
                    if (height > arr[2]) continue;
                    next.add(new int[]{x,y});
                    visited[x][y] = true;
                    //高度为0无需考虑
                    if (hill[x][y] == 0) continue;
                    if (!map.containsKey(hill[x][y]) || map.get(hill[x][y]) >step){
                        //更新该高度的最新最短路径
                        map.put(hill[x][y],step);
                    }
                }
            }
            //进行下一轮
            queue = next;
        }
    }
}
