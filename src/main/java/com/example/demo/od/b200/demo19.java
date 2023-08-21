package com.example.demo.od.b200;

import java.util.*;

//战场索敌
public class demo19 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] arr = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        List<String[]> list = new ArrayList<>();
        for (int i = 0; i < arr[0]; i++) {
            String[] str = sc.nextLine().split("");
            list.add(str);
        }
        bfsByQueue(list,arr);
//
//        List<String[]> list = new ArrayList<>();
//        list.add(new String[]{".",".","#","E","E"});
//        list.add(new String[]{"E",".","#","E","."});
//        list.add(new String[]{"#","#","#",".","."});
//        bfsByQueue(list,new int[]{3,5,2});
    }

    /**
     * 通过栈实现深度优先
     *
     * @param list
     * @param arr
     */
    public static void dfsByStack(List<String[]> list, int[] arr){
        //要小于的目标数
        int target = arr[2];

        //存放要访问的数据
        Stack<int[]> stack = new Stack<>();

        //位移
        int[][] offset = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};

        //存放对应的list数据是否已经访问，true：已访问 false：未访问
        boolean[][] visited = new boolean[arr[0]][arr[1]];

        //记录大于target的区域数量
        int result = 0;
        //记录每个区域内的敌人数量
        int count = 0;
        //遍历数据
        for (int i = 0; i < list.size(); i++) {
            String[] str = list.get(i);
            for (int j = 0; j < str.length; j++) {
                //已经访问过的跳过或是墙壁则跳过
                if (visited[i][j] || list.get(i)[j].equals("#")) continue;
                //将当前数据设置为已遍历
                visited[i][j] = true;
                //添加到栈中
                stack.add(new int[]{i,j});
                while (!stack.isEmpty()){
                    int[] pop = stack.pop();
                    if (list.get(pop[0])[pop[1]].equals("E")) count ++;
                    for (int[] ints : offset) {
                        int x = ints[0] + pop[0];
                        int y = ints[1] + pop[1];
                        //越界的跳过
                        if (x < 0 || x > arr[0] -1 || y < 0 || y > arr[1] -1) continue;
                        //位移到数据未访问，且不是墙壁，则添加到栈中进行下轮遍历
                        if (!visited[x][y] && !list.get(x)[y].equals("#")){
                            visited[x][y] = true;
                            stack.add(new int[]{x,y});
                        }
                    }
                }
                if (count < target) result ++;
                //每个区域遍历完之后，计数器归0
                count = 0;
            }
        }
        System.out.println(result);
    }

    /**
     * 通过队列实现广度优先
     *
     * @param list
     * @param arr
     */
    public static void bfsByQueue(List<String[]> list, int[] arr){
        //要小于的目标数
        int target = arr[2];

        //存放要访问的数据
        LinkedList<int[]> queue = new LinkedList<>();

        //位移
        int[][] offset = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};

        //存放对应的list数据是否已经访问，true：已访问 false：未访问
        boolean[][] visited = new boolean[arr[0]][arr[1]];

        //记录大于target的区域数量
        int result = 0;
        //记录每个区域内的敌人数量
        int count = 0;
        //遍历数据
        for (int i = 0; i < list.size(); i++) {
            String[] str = list.get(i);
            for (int j = 0; j < str.length; j++) {
                //已经访问过的跳过或是墙壁则跳过
                if (visited[i][j] || list.get(i)[j].equals("#")) continue;
                //将当前数据设置为已遍历
                visited[i][j] = true;
                //添加到栈中
                queue.add(new int[]{i,j});
                while (!queue.isEmpty()){
                    int[] pop = queue.pop();
                    if (list.get(pop[0])[pop[1]].equals("E")) count ++;
                    for (int[] ints : offset) {
                        int x = ints[0] + pop[0];
                        int y = ints[1] + pop[1];
                        //越界的跳过
                        if (x < 0 || x > arr[0] -1 || y < 0 || y > arr[1] -1) continue;
                        //位移到数据未访问，且不是墙壁，则添加到栈中进行下轮遍历
                        if (!visited[x][y] && !list.get(x)[y].equals("#")){
                            visited[x][y] = true;
                            queue.add(new int[]{x,y});
                        }
                    }
                }
                if (count < target) result ++;
                //每个区域遍历完之后，计数器归0
                count = 0;
            }
        }
        System.out.println(result);
    }
}
