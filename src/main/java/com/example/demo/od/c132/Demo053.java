package com.example.demo.od.c132;

import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * 围棋的气  广度优先
 */
public class Demo053 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int[] black = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] white = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        System.out.println(getResult(black,white));
    }

    static int[][] steps = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
    public static String getResult(int[] black, int[] white){
        StringJoiner joiner = new StringJoiner(" ");
        int[][] pan = new int[19][19];
        for (int i = 0; i < black.length; i++) {
            int x = black[i];
            int y = black[++i];
            pan[x][y] = 2;
        }
        for (int i = 0; i < white.length; i++) {
            int x = white[i];
            int y = white[++i];
            pan[x][y] = 3;
        }

        int res = 0;
        for (int i = 0; i < black.length; i++) {
            int x = black[i];
            int y = black[++i];
            for (int[] step : steps) {
                int nx = step[0] + x;
                int ny = step[1] + y;
                if (nx < 0 || nx >= 19 || ny < 0 || ny >= 19) continue;
                if (pan[nx][ny] == 0) {
                    res ++;
                    pan[nx][ny] = 4;
                }
            }
        }
        joiner.add(res+"");

        res = 0;
        for (int i = 0; i < white.length; i++) {
            int x = white[i];
            int y = white[++i];
            for (int[] step : steps) {
                int nx = step[0] + x;
                int ny = step[1] + y;
                if (nx < 0 || nx >= 19 || ny < 0 || ny >= 19) continue;
                if (pan[nx][ny] == 0 || pan[nx][ny] == 4) {
                    res ++;
                    pan[nx][ny] = 5;
                }
            }
        }
        joiner.add(res + "");
        return joiner.toString();
    }
}
