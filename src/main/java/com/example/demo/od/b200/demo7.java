package com.example.demo.od.b200;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

//寻找最大价值的矿堆
public class demo7 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] array = new int[300][300];
        int row = 0;
//        while (sc.hasNextLine()){
//            String str = sc.nextLine();
//            if (str.equals("")) break;
//            int[] item = new int[300];
//            for (int i = 0; i < str.length(); i++) {
//                int num = Integer.parseInt(str.charAt(i) + "");
//                item[i] = num;
//            }
//            array[row++] = item;
//        }
        for (int i = 0; i < array.length; i++) {
            Arrays.fill(array[i], 1);
        }
        System.out.println(getResult1(array));
    }


    /**
     * DFS 递归求解 (有栈溢出风险)
     *
     * @param array
     * @return
     */
    public static int getResult1(int[][] array){
        int result = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] != 0){
                    result = Math.max(result,stackDfs(array,i,j,array[i][j]));
                }
            }
        }
        return result;
    }

    public static int dfs(int[][] array, int i, int j, int price){
        //遍历过得矿赋值0，防止重复遍历
        array[i][j] = 0;
        int[][] offset = new int[][]{{1,0},{-1,0},{0,-1},{0,1}};
        for (int[] ints : offset) {
            int x = i + ints[0];
            int y = j + ints[1];
            if (x >= 0 && x < 300 && y >= 0 && y < 300 && array[x][y] != 0){
                price = dfs(array,x,y,price + array[x][y]);
            }
        }
        return price;
    }

    /**
     * DFS 栈求解
     *
     * @param array
     * @return
     */
    public static int stackDfs(int[][] array, int i, int j, int price) {
        array[i][j] = 0;
        int[][] offset = new int[][]{{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
        Stack<int[]> stack = new Stack<>();
        stack.add(new int[]{i, j});
        while (!stack.isEmpty()) {
            int[] pop = stack.pop();
            for (int[] ints : offset) {
                int x = pop[0] + ints[0];
                int y = pop[1] + ints[1];
                if (x >= 0 && x < 300 && y >= 0 && y < 300 && array[x][y] != 0) {
                    price += array[x][y];
                    array[x][y] = 0;
                    stack.add(new int[]{x, y});
                }
            }
        }
        return price;
    }

    /**
     * 并查集 求解
     */
    public static int getResult3(int[][] array){

        return 0;
    }


    /**
     * 本体不推荐并查集解
     *  因为还要求最大价值，感觉麻烦
     */
    class UnionFindSet{
        int fa[];

        public UnionFindSet(int n){
            this.fa = new int[n];
            for (int i = 0; i < n; i++) {
                this.fa[i] = i;
            }
        }
        public int find(int x){
            if (x != this.fa[x]){
                return (this.fa[x] = this.find(this.fa[x]));
            }
            return x;
        }
        public void union(int x, int y){
            int fx = this.find(x);
            int fy = this.find(y);
            if (fx != fy){
                this.fa[fy] = fx;
            }
        }
    }


}
