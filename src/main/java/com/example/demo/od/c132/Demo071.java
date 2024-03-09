package com.example.demo.od.c132;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * 5G网络建设 最小生成树 Kruskal
 */
public class Demo071 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        int m = Integer.parseInt(sc.nextLine());
        int[][] nums = new int[m][4];
        for (int i = 0; i < m; i++) {
            nums[i] = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        System.out.println(getResult(nums,n));
    }

    public static int getResult(int[][] nums, int n){
        UF uf = new UF(n);
        int[][] connected = Arrays.stream(nums).filter(a -> a[3] == 1).toArray(int[][]::new);
        int[][] other = Arrays.stream(nums).filter(a -> a[3] != 1).toArray(int[][]::new);
        for (int[] arr : connected) {
            uf.union(arr[0]-1,arr[1]-1);
        }
        if (uf.count == 1) return 0;
        int[][] sort = Arrays.stream(other).sorted(Comparator.comparingInt(a -> a[2])).toArray(int[][]::new);
        int res = 0;
        for (int[] arr : sort) {
            if (!uf.connected(arr[0] - 1, arr[1] - 1)){
                uf.union(arr[0]-1,arr[1]-1);
                res += arr[2];
                if (uf.count == 1){
                    return res;
                }
            }
        }
        return -1;

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

        public int find(int x){
            if (parent[x] != x){
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public void union(int x, int y){
            int p = find(x);
            int q = find(y);
            if (p == q) return;
            parent[p] = q;
            count --;
        }

        public boolean connected(int x, int y){
            return find(x) == find(y);
        }
    }
}
