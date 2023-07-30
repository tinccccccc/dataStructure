package com.example.demo.od.b100;
//报文重排序
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class demo15 {
    static class Word {
        int id;
        String content;

        public Word(int id, String content) {
            this.id = id;
            this.content = content;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        String[] arr = sc.nextLine().split(" ");

        System.out.println(getResult(n, arr));
    }

    public static String getResult(int n, String[] arr) {
        ArrayList<Word> ans = new ArrayList<>();

        Pattern pattern = Pattern.compile("([a-zA-Z]+)(\\d+)");

        for (String s : arr) {
            Matcher matcher = pattern.matcher(s);
            if (matcher.find()) {
                String content = matcher.group(1);
                int i = Integer.parseInt(matcher.group(2)) - 1;
                ans.add(new Word(i, content));
            }
        }

        ans.sort((a, b) -> a.id - b.id);

        StringJoiner sj = new StringJoiner(" ");
        for (Word an : ans) {
            sj.add(an.content);
        }
        return sj.toString();
    }
}
