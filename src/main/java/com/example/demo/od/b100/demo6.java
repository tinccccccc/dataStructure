package com.example.demo.od.b100;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

//阿里巴巴找黄金香（I)
public class demo6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> nums = Arrays.stream(sc.nextLine().split(",")).map(Integer::parseInt).collect(Collectors.toList());

        int leftSum = 0;
        Integer rightSum = nums.stream().reduce(0,Integer::sum);
        if (nums.size() == 0) {
            System.out.println(-1);
            return;
        }
        if (nums.size() == 1){
            System.out.println(0);
            return;
        }

        for (int point = 0; point < nums.size(); point ++){
            if (point != 0){
                leftSum += nums.get(point-1);
            }
            rightSum -= nums.get(point);
            if (leftSum == rightSum){
                System.out.println(point);
                return;
            }
        }

        System.out.println(-1);

    }
}
