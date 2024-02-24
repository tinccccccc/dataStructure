package com.example.demo.od.c132;

import java.util.*;

public class Demo015 {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int[] array = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = array[0];
        int m = array[1];
        int[][] nums = new int[n][m];
        for (int i = 0; i < n; i++) {
            nums[i] = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        System.out.println(getResult(nums));
    }

    static class UF{
        int count;
        int[] parent;

        public UF(int n){
            count = n;
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public void union(int x, int y){
            int px = find(x);
            int yx = find(y);
            if (px == yx) return;
            parent[px] = yx;
            count --;
        }

        public int find(int x){
            if (parent[x] != x){
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }
    }
    static int[][] steps = new int[][]{{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
    public static int getResult(int[][] nums){
        int n = nums.length;
        int m = nums[0].length;
        //收集所有边界
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (nums[i][j] == 5){
                    for (int[] step : steps) {
                        int x = step[0] + i;
                        int y = step[1] + j;
                        if (x < 0 || x >= n || y < 0 || y >= m || nums[x][y] == 5){
                            continue;
                        }
                        set.add(m * x + y);
                    }
                }
            }
        }
        int size = set.size();
        UF uf = new UF(size);
        //合并边界、边界与边界相邻则合并
        int[] arr = set.stream().mapToInt(Integer::valueOf).toArray();
        for (int i = 0; i < size; i++) {
            int xi = arr[i] / m;
            int yi = arr[i] % m;
            for (int j = i + 1; j < size; j++) {
                int xj = arr[j] / m;
                int yj = arr[j] % m;
                if (Math.abs(xi - xj) <= 1 && Math.abs(yi - yj) <= 1){
                    uf.union(i,j);
                }
            }
        }

        return uf.count;
    }

}
