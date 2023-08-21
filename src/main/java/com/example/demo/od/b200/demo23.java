package com.example.demo.od.b200;

import java.util.LinkedList;
import java.util.Scanner;

public class demo23 {

    static class ZipStr{
        int num;
        String str;

        public ZipStr(int num, String str) {
            this.num = num;
            this.str = str;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getStr() {
            return str;
        }

        public void setStr(String str) {
            this.str = str;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str1 = sc.nextLine();
        String str2 = sc.nextLine();

        getResult(str1,str2);
    }


    public static void getResult(String str1, String str2){
        LinkedList<ZipStr> linkList1 = getLinkList(str1);
        LinkedList<ZipStr> linkList2 = getLinkList(str2);
        int diff = 0;
        int same = 0;
        while (linkList1.size() > 0){
            ZipStr pop1 = linkList1.pop();
            ZipStr pop2 = linkList2.pop();

            int compareNum = Math.min(pop1.getNum(),pop2.getNum());
            if (!pop1.getStr().equals(pop2.getStr())){
                diff += compareNum;
            }else {
                same += compareNum;
            }

            if (pop1.getNum() > pop2.getNum()){
                pop1.setNum(pop1.num-=compareNum);
                linkList1.addFirst(pop1);
                continue;
            }

            if (pop1.getNum() < pop2.getNum()){
                pop2.setNum(pop2.num-=compareNum);
                linkList2.addFirst(pop2);
            }
        }
        System.out.println(diff+ "/"+(same+diff));
    }


    public static LinkedList<ZipStr> getLinkList(String str){
        LinkedList<ZipStr> linkedList = new LinkedList<>();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c >= '0' && c <= '9'){
                builder.append(c);
            }else {
                linkedList.add(new ZipStr(Integer.parseInt(builder.toString()),c+""));
                builder = new StringBuilder();
            }
        }
        return linkedList;
    }
}
