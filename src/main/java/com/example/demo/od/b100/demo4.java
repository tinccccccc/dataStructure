package com.example.demo.od.b100;

import java.util.*;
import java.util.stream.Collectors;

//AI识别面板
public class demo4 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());

        List<Light> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] s = sc.nextLine().split(" ");
            list.add(new Light(Integer.parseInt(s[0])
                    ,(Integer.parseInt(s[1])+Integer.parseInt(s[3])) / 2
                    ,(Integer.parseInt(s[2])+Integer.parseInt(s[4])) / 2,
                    (Integer.parseInt(s[3])-Integer.parseInt(s[1])) / 2));
        }
        System.out.println(getResult(list));
    }


    public static String getResult(List<Light> list) {
        StringJoiner sj = new StringJoiner(" ");
        List<Light> collect = list.stream().sorted(Comparator.comparingInt(o -> o.y)).collect(Collectors.toList());

        List<Light> sameLine = new ArrayList<>();
        Light base = collect.get(0);
        sameLine.add(base);
        for (int i = 1; i < list.size(); i++) {
            Light light = list.get(i);
            if (light.y - base.y <= light.r){
                //同一行
                sameLine.add(light);
            }else {
                //不同一行，由于是按灯高进行排序的，当前不为同一行，那后面更不可能同一行了
                List<Light> asc = sameLine.stream().sorted(Comparator.comparingInt(o -> o.x)).collect(Collectors.toList());
                asc.forEach(a -> sj.add(a.id + ""));

                sameLine = new ArrayList<>();
                base = light;
                sameLine.add(base);
            }
        }

        if (sameLine.size()>0){
            List<Light> asc = sameLine.stream().sorted(Comparator.comparingInt(o -> o.x)).collect(Collectors.toList());
            asc.forEach(a -> sj.add(a.id + ""));
        }

        return sj.toString();
    }
}

//灯芯
class Light{
    int id;
    //灯芯横坐标
    int x;
    //灯芯纵坐标
    int y;
    //灯半径
    int r;

    public Light(int id, int x, int y, int r) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.r = r;
    }
}
