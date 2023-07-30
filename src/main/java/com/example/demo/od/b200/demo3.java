package com.example.demo.od.b200;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//宜居星球改造计划
public class demo3 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String[]> list = new ArrayList<>();
        while (sc.hasNextLine()){
            String str = sc.nextLine();
            if (str.equals("")){
                System.out.println(getResult(list));
                break;
            }else {
                list.add(str.split(" "));
            }
        }
    }

    public static int getResult(List<String[]> list){
        //记录一共多少个NO
        int count = 0;
        //记录所有YES的位置
        List<int[]> yesList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).length; j++) {
                if (list.get(i)[j].equals("YES")){
                    int[] yes = new int[]{i, j};
                    yesList.add(yes);
                }
                if (list.get(i)[j].equals("NO")){
                    count ++;
                }
            }
        }
        //矩形长度
        int xLength = list.size();
        //矩形宽度
        int yLength = list.get(0).length;

        //如果没有宜居则直接返回-1
        if (yesList.size() == 0) return -1;

        //如果全是宜居，则直接返回0
        if (yesList.size() == xLength * yLength) return 0;

        //位移
        int[][] offset = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
        //所需天数
        int day = 0;
        while (yesList.size() > 0){
            List<int[]> newYes = new ArrayList<>();
            //遍历所有的YES
            for (int[] yes : yesList) {
                for (int[] ints : offset) {
                    int x = ints[0] + yes[0];
                    int y = ints[1] + yes[1];
                    if (x >= 0 && x < xLength && y >= 0 && y < yLength){
                        if (list.get(x)[y].equals("NO")){
                            list.get(x)[y] = "YES";
                            count --;
                            newYes.add(new int[]{x,y});
                        }
                    }
                }
            }
            if (newYes.size() > 0){
                //说明今天是有发生改造的，如果为0，则说明今天未发生改造，已经结束了，今天不算
                day++;
            }
            yesList = newYes;
        }
        return count != 0 ? -1:day;
    }
}
