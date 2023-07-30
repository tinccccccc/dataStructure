package com.example.demo.test;

import java.util.ArrayList;
import java.util.List;

public class test003 {
    public static void main(String[] args) {

        getResult("Life is painting a  picture, not doing 'a  sum'.", new int[][]{{8, 15}, {20, 26}, {43, 45}});
    }

    public static void getResult(String str, int[][] arr) {
        int l = str.length();
        List<Integer> index = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {

            int[] ar = arr[i];
            int a = ar[0];
            int b = ar[1];

            int count1 = 0;
            for (int j = a - 1; j >= 0; j--) {
                if (String.valueOf(str.charAt(j)).equals(" ")) {
                    count1++;
                    if (count1 == 2) {
                        index.add(j);
                        break;
                    }
                }
                if (!String.valueOf(str.charAt(j)).equals(" ")) {
                    count1 = 0;
                }
                if (!String.valueOf(str.charAt(j)).equals(" ") && !String.valueOf(str.charAt(j)).equals("'")) {
                    break;
                }

            }

            int count2 = 0;
            for (int k = b + 1; k < str.length(); k++) {
                if (String.valueOf(str.charAt(k)).equals(" ")) {
                    count2++;
                    if (count2 == 2) {
                        index.add(k);
                        break;
                    }
                }
                if (!String.valueOf(str.charAt(k)).equals(" ")) {
                    count2 = 0;
                }
                if (!String.valueOf(str.charAt(k)).equals(" ") && !String.valueOf(str.charAt(k)).equals("'")) {
                    break;
                }
            }
        }

        if (index.size() > 0) {
            for (int i = 0; i < index.size(); i++) {
                l--;
                int c = index.get(i);
                int tmp = c;
                if (i > 0) tmp--;
                str = str.substring(0, tmp) + str.substring(tmp + 1, l);
            }
        }


        StringBuffer stb = new StringBuffer();
        if (index.size() > 0) {
            for (int i = 0; i < index.size(); i++) {
                int c = index.get(i);
                for (int[] ar : arr) {
                    int a = ar[0];
                    if (a > c) {
                        ar[0] = ar[0] - 1;
                        ar[1] = ar[1] - 1;
                    }
                }
            }
        }

        for (int[] ar : arr) {
            stb.append("["+ ar[0]+","+ ar[1]+"]");
        }

        System.out.println(str);

        System.out.println(stb);
    }
}