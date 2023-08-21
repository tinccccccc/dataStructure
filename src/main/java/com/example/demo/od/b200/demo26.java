package com.example.demo.od.b200;

import java.util.*;

//MELON的难题
public class demo26 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = Integer.parseInt(sc.nextLine());
        int[] arr = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        getResult(num,arr);
    }

    public static void getResult(int num, int[] arr){
        //记录剩余摇摇车数量
        int remain = num;
        //记录正在完的小朋友编号
        LinkedList<Integer> play = new LinkedList<>();
        //记录正在等待的小朋友
        LinkedList<Integer> wait = new LinkedList<>();
        //记录不开心小朋友数量
        int sad = 0;
        for (int child : arr) {
            //当前小朋友开心的离开
            if (play.contains(child)){
                remain ++;
                play.remove(play.indexOf(child));
                continue;
            }

            if (wait.contains(child)){
                //伤心的离开了
                if (remain == 0){
                    sad ++;
                }
                wait.remove(wait.indexOf(child));
                continue;
            }

            //当前小朋友刚来来
            if (remain > 0){

                //不需要排队直接完上了
                if (wait.size() == 0){
                    play.add(child);
                }else {
                    //需要排队
                    wait.add(child);
                    wait.removeFirst();
                }
                remain --;
                //需要排队
                continue;
            }
            //刚来，但是没有没有空的摇摇车
            wait.add(child);
        }

        System.out.println(sad);
    }
}
