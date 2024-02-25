package com.example.demo.od.c132;

import java.util.Scanner;
import java.util.StringJoiner;

/**
 * 考勤信息
 */
public class Demo025 {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        String[][] strs = new String[n][];
        for (int i = 0; i < n; i++) {
            String[] str = sc.nextLine().split(" ");
            strs[i] = str;
        }
        System.out.println(getResult(strs));
    }

    public static String getResult(String[][] strs){
        StringJoiner joiner = new StringJoiner(" ");
        for (String[] one : strs) {
            int l = 0, r = 0;
            int n = one.length;
            //缺勤不能超过一次
            int cnt = 0;
            //连续七次中，不能出现三次缺勤/迟到/早退
            int three = 0;
            boolean flag = true;
            while (r < n){
                if (r - l + 1< 7){
                    if (one[r].equals("absent") || one[r].equals("late") || one[r].equals("leaveearly")){
                        //缺勤不能超过一次
                        if (one[r].equals("absent")){
                            cnt ++;
                            if (cnt > 1){
                                joiner.add("false");
                                flag = false;
                                break;
                            }
                        }

                        //不能出现连续的迟到或早退
                        if ((r > 0) && (one[r].equals("late") || one[r].equals("leaveearly"))){
                            if (one[r-1].equals("late") || one[r-1].equals("leaveearly")){
                                joiner.add("false");
                                flag = false;
                                break;
                            }
                        }

                        three ++;
                        //连续七天，不能出现三次缺勤/迟到/早退
                        if (three > 3) {
                            joiner.add("false");
                            flag = false;
                            break;
                        }
                    }
                    r ++;
                    continue;
                }

                if (one[l].equals("absent") || one[l].equals("late") || one[l].equals("leaveearly")){
                    three --;
                    if (one[l].equals("absent")){
                        cnt --;
                    }
                }
                l ++;
            }
            if (flag){
                joiner.add("true");
            }
        }
        return joiner.toString();
    }
}
