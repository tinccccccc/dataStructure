package com.example.demo.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 图构建的三种方式
 *  1.邻接矩阵
 *  2.邻接表
 *  3.链式向前星
 */
public class Graph {

    // 点的最大数量
    public static int MAXN = 11;

    static int[][] graph1 = new int[MAXN][MAXN];

    static List<List<int[]>> graph2 = new ArrayList<>();

    //长度应该是 点的个数 + 1 。 index : 点    val : 边
    static int[] head = new int[MAXN];

    //长度应该是 边的个数    index: 边  val ：边
    static int[] next = new int[MAXN];

    //长度应该是 边的个数    index: 边  val ：点
    static int[] to = new int[MAXN];

    //长度应该是 边的个数    index: 边  val ：权重
    static int[] weight = new int[MAXN];

    //第一条边，就是一号边
    static int cnt = 1;

    /**
     * 邻接矩阵 [from,to,weight]
     */
    public static void createGraph1(int[][] edges){
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            int w = edge[2];
            graph1[from][to] = w;
        }
    }

    /**
     * 邻接表 [from,to,weight]
     */
    public static void createGraph2(int[][] edges){
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            int w = edge[2];
            graph2.get(from).add(new int[]{to,w});
        }
    }

    /**
     * 链式向前星 [from,to,weight]
     *
     * 清空的话只需要将cnt = 1即可，而 head 要重置为都为0
     */
    public static void createGraph3(int[][] edges){
        for (int[] edge : edges) {
            int from = edge[0];
            int too = edge[1];
            int w = edge[2];
            //cnt号边的下一个边是哪条边,其实是到上一条该点的其他边
            next[cnt] = head[from];
            //更新点的头号边（即最新的边）为当前边
            head[from] = cnt;
            //更新当前边去往的点.
            to[cnt] = too;
            //更新当前边的权重
            weight[cnt ++] = w;
        }
    }

    public static void main(String[] args){
        int[][] edges = new int[][]{{1,2,2},{1,3,4},{1,4,6},{2,3,1},{2,4,8},{3,4,3},{4,1,9}};
        createGraph3(edges);
        int[][] res = new int[edges.length][edges[0].length];
        int index = 0;
        for (int i = 1; i < head.length; i++) {
            int edge = head[i];
            while (edge != 0){
                res[index ++] = new int[]{i,to[edge],weight[edge]};
                edge = next[edge];
            }
        }
        System.out.println("");
    }
}
