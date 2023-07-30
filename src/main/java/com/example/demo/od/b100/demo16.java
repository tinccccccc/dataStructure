package com.example.demo.od.b100;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

//食堂供餐
public class demo16 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(sc.nextInt());
        }
        Integer max = list.stream().sorted().collect(Collectors.toList()).get(n - 1);
        int min = getResult(m, 0, max, list);
        System.out.println(min);
    }

    public static int getResult(int m,int min, int max,List<Integer> list){
        if (min >= max) return min;
        int mid = (min + max) / 2;
        int sum = m;
        for (Integer pi : list) {
            if (pi <= sum){
                sum -= pi;
                sum += mid;
            }else {
                return getResult(m,mid+1,max,list);
            }
        }
        return getResult(m,min,mid-1,list);
    }
}
