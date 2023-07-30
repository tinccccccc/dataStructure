package com.example.demo.od.b100;

import java.util.*;

//跳房子I(自写通过)
public class demo23 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        int[] steps = Arrays.stream(str.substring(1, str.length() - 1).split(",")).mapToInt(Integer::parseInt).toArray();
        String count = sc.nextLine();
        getResult(steps,Integer.parseInt(count));
    }

    public static void getResult(int[] steps,int count){
        //Key:step  value:ndex
        HashMap<Integer,Integer> map = new HashMap<>();
        int min = Integer.MAX_VALUE;
        int num1 =  Integer.MAX_VALUE;
        int num2 =  Integer.MAX_VALUE;

        for (int i = 0; i < steps.length-1; i++) {
            int step1 = steps[i];
            int need = count - step1;
            for (int j = i+1; j < steps.length; j++) {
                if (steps[j] == need && (j + i) < min){
                   min = j + i;
                   num1 = steps[i];
                   num2= steps[j];
                }
            }
        }

        StringBuilder builder = new StringBuilder();
        builder.append("[");
        builder.append(num1).append(", ");
        builder.append(num2).append("]");
        System.out.println(builder);
    }
}
