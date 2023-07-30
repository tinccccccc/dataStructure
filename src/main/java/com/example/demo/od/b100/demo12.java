package com.example.demo.od.b100;

import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//选修课
public class demo12 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //科目一
        String one = sc.nextLine();
        String[] o = one.split(";");
        List<String[]> subjectOne = new ArrayList<>();
        for (String s : o) {
            String[] strArr = s.split(",");
            subjectOne.add(strArr);
        }

        //科目二
        String two = sc.nextLine();
        String[] t = two.split(";");
        List<String[]> subjectTwo = new ArrayList<>();
        for (String s : t) {
            String[] strArr = s.split(",");
            subjectTwo.add(strArr);
        }

        getResult(subjectOne,subjectTwo);
    }

    //该方法太过繁琐，建议分装成Student对象，然后进行操作。
    public static void getResult(List<String[]> subjectOne,List<String[]> subjectTwo){
        //1.根据学号分组，每个分组里数据数量为2，则是选了了两门课的学生
        Map<String, List<String[]>> groupById = Stream.concat(subjectOne.stream(), subjectTwo.stream())
                .collect(Collectors.groupingBy(val -> val[0].substring(0, 5)));

        //2.将满足条件的学生根据班级进行分组，并根据班级升序
        List<String[]> students = new ArrayList<>();
        for (String key : groupById.keySet()) {
            if (groupById.get(key).size() == 2){
                students.addAll(groupById.get(key));
            }
        }
        if (CollectionUtils.isEmpty(students)){
            System.out.println("NULL");
            return;
        }
        Map<String, List<String[]>> groupByClass = students.stream().collect(Collectors.groupingBy(val -> val[0].substring(0, 5)));
        HashMap<String, List<String[]>> sortByClass = groupByClass.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, HashMap::new));

        //3.遍历排好序的分组，输出班级号
        for (String key : sortByClass.keySet()) {
            System.out.println(key+"\n");

            //4.将当前分组（班级）中的学生根据学号进行分组，并更具两门课成绩和降序，相同则按学号升序
//            Map<String, List<String[]>> groupById = sortByClass.get(key).stream().collect(Collectors.groupingBy(val -> val[0]));


            //5.遍历输出编号

        }
    }

    // todo
    public static void getResult2(List<String[]> subjectOne,List<String[]> subjectTwo) {

    }
}
