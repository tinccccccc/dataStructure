package com.example.demo.od.b200;

import java.util.*;
import java.util.stream.Collectors;

//字符串化繁为简()happy(xyz)new(wxy)year(t) ||  ()abcdefgAC(a)(Ab)(C)
public class demo2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        System.out.println(getResult(str));
    }
    public static String getResult(String str){
        //存放等效字符集
        Set<String> container = new HashSet<>();
        //存放一个括号内的等效字符集
        Set<String> brace = new HashSet<>();
        //记录每个括号内的字符数量
        int count = 0;
        //判断当前字符是在（）内
        boolean open = false;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '(') {
                open = true;
                continue;
            }
            if (c == ')') {
                open = false;
                if (count > 1){
                    container.addAll(brace);
                }
                count = 0;
                brace = new HashSet<>();
                continue;
            }
            if (open){
                count ++;
                brace.add(String.valueOf(c));
            }
        }
        List<String> list = container.stream().sorted().collect(Collectors.toList());
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '(') {
                open = true;
                continue;
            }
            if (c == ')') {
                open = false;
                continue;
            }
            if (!open){
                if (list.contains(String.valueOf(c))){
                    builder.append(list.get(0));
                    continue;
                }
                builder.append(c);
            }
        }
        return builder.toString().equals("")?"0":builder.toString();
    }
}
