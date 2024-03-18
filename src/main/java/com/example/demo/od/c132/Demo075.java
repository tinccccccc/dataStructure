package com.example.demo.od.c132;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * 找城市  并查集
 */
public class Demo075 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        int[][] nums = new int[n - 1][2];
        for (int i = 0; i < n - 1; i++) {
            nums[i] = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        System.out.println(getResult(nums, n));
    }

    public static String getResult(int[][] nums, int n){
        int[] res = new int[n];
        Arrays.fill(res,-1);
        for (int i = 0; i < n; i++) {
            UF uf = new UF(n);
            for (int[] num : nums) {
                int a = num[0] - 1;
                int b = num[1] - 1;
                if (a == i || b == i) continue;
                uf.union(a,b);
            }
            res[i] = uf.max();
        }
        int min = Arrays.stream(res).min().getAsInt();
        StringJoiner joiner = new StringJoiner(" ");
        for (int i = 0; i < n; i++) {
            if (res[i] == min){
                joiner.add(i + 1+"");
            }
        }
        return joiner.toString();
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
            if (x != parent[x]){
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public void union(int x, int y){
            int p = find(x);
            int q = find(y);
            if (p == q) return;
            parent[q] = p;
            count--;
        }

        public int max(){
            HashMap<Integer,Integer> map = new HashMap<>();
            for (int i : parent) {
                int a = find(i);
                Integer count = map.getOrDefault(a, 0);
                map.put(a,count + 1);
            }
            int max = Integer.MIN_VALUE;
            for (Integer i : map.keySet()) {
                if (map.get(i) > max){
                    max = map.get(i);
                }
            }
            return max;
        }
    }
}
