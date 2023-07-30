package com.example.demo.od.b100;

import java.util.*;
import java.util.stream.Collectors;
//i2i1r1a0h0i0j0o0v0
//i2i1a0h0i0j0o0r0v0
//字符串摘要(自写通过)
public class demo20 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        getResult(str.toLowerCase());
    }

    public static void getResult(String str) {
        //统计所有字母出现点的次数
        int[] count = new int[128];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c >= 'a' && c <= 'z') {
                count[c]++;
                sb.append(c);
            }
        }
        str = sb.toString();
        List<String[]> list = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {

            String[] cur = new String[2];

            if (i == str.length() - 1 && count[str.charAt(i)] != 0){
                cur[0] = str.charAt(i)+"";
                cur[1] = 0+"";
                list.add(cur);
                continue;
            }

            char c = str.charAt(i);
            int num = 0;
            char next = str.charAt(i + 1);
            while (c == next){
                num ++;
                i++;
                if (i == str.length()-1) break;
                next = str.charAt(i+1);
            }
            if (num != 0){
                cur[0] = c+"";
                cur[1] = num+1+"";
                count[c] -= num+1;
                list.add(cur);
                continue;
            }
            cur[0] = c+"";
            cur[1] = --count[c]+"";
            list.add(cur);
        }

        list = list.stream().sorted(((Comparator<String[]>) (o1, o2) -> Integer.parseInt(o2[1]) - Integer.parseInt(o1[1])).thenComparing(o1 -> o1[0])).collect(Collectors.toList());
        StringBuilder builder = new StringBuilder();
        for (String[] item : list) {
            builder.append(item[0]);
            builder.append(item[1]);
        }
        System.out.println(builder);
    }
}
