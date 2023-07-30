package com.example.demo.test;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Scanner;

public class test {
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        Integer[] arr = Arrays.stream(s.split(" ")).map(Integer::parseInt).toArray(Integer[]::new);
        System.out.println(getResult(arr));
        PrintWriter printWriter = new PrintWriter("saaa", "utf");
    }

    public static int getResult(Integer[] arr){

        int l = arr.length;

        Double tmp = 128.0;

        int result = Integer.MAX_VALUE;

        for (int k = -127 ; k <= 128 ;k ++){
            int sum = 0;
            for (int a : arr) {
                int newA = a + k;
                if (newA < 0) newA = 0;
                if (newA > 255) newA = 255;
                sum += newA;
            }
            BigDecimal sub = new BigDecimal(sum).divide(new BigDecimal(l), BigDecimal.ROUND_UP, RoundingMode.CEILING).subtract(new BigDecimal(128));
            double abs = Math.abs(Double.parseDouble(sub.toString()));
            if (abs < tmp){
                tmp = abs;
                result = k;
            }
        }

        return result;
    }
}
