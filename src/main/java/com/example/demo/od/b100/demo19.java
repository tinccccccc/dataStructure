package com.example.demo.od.b100;

import java.util.*;
import java.util.stream.Collectors;

//比赛的冠亚季军(自写通过)
public class demo19 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] arr = sc.nextLine().split(" ");
        List<long[]> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            list.add(new long[]{i,Long.parseLong(arr[i])});
        }

        System.out.println(getResult(list));
    }

    public static String getResult(List<long[]> list) {

        List<long[]> win = new ArrayList<>();
        List<long[]> lost = new ArrayList<>();
        for (int i = 0; i < list.size(); i+=2) {
            if (i == list.size() -1){
                win.add(list.get(i));
                break;
            }
            if (list.get(i)[1] >= list.get(i + 1)[1]){
                win.add(list.get(i));
                lost.add(list.get(i + 1));
            }else {
                win.add(list.get(i + 1));
                lost.add(list.get(i));
            }
        }

        if (win.size() == 2){
            StringJoiner joiner = new StringJoiner(" ");
            List<long[]> two = win.stream().sorted(((Comparator<long[]>) (o1, o2) -> {
                if (o2[1] - o1[1]>0){
                    return 1;
                }
                if (o2[1] - o1[1]<0){
                    return -1;
                }
                return 0;
            }).thenComparing(o -> o[0])).collect(Collectors.toList());
            joiner.add(two.get(0)[0] +"");
            joiner.add(two.get(1)[0] +"");
            long[] three = lost.stream().sorted(((Comparator<long[]>) (o1, o2) -> {
                if (o2[1] - o1[1]>0){
                    return 1;
                }
                if (o2[1] - o1[1]<0){
                    return -1;
                }
                return 0;
            }).thenComparing(o -> o[0])).collect(Collectors.toList()).get(0);
            joiner.add(three[0]+"");
            return joiner.toString();
        }

        return getResult(win);
    }
}
