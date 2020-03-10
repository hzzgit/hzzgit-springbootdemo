package com.hzz.lamdba;

import java.sql.Struct;
import java.util.*;
import java.util.stream.Collectors;

public class lamdba联系 {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student(122, "小猫"));
        students.add(new Student(22, "小够"));
        students.add(new Student(55, "小猪"));
        students.add(new Student(55, "小猪1"));
        students.add(new Student(55, "小猪2"));
        students.add(new Student(55, "小猪2"));
        students.add(new Student(55, "小猪4"));
        students.add(new Student(55, "小猪5"));
        students.add(new Student(122, "小猪777"));
        students.add(new Student(122, "小猪513"));
        Collections.sort(students, Comparator.comparing(Student::getSex));
        int a=-1;
        System.out.println(1);

        Map<String, Student> stringStudentMap = students.stream().collect(Collectors.toMap(Student::getName, p -> p, (p, v) -> v));
        Map<String, List<Student>> stringListMap = students.stream().collect(Collectors.groupingBy(Student::getName));
        students.sort(Comparator.comparing(Student::getSex));
        List<Student> students1 = students.stream().sorted(Comparator.comparing(Student::getSex)).collect(Collectors.toList());
        System.out.println(students1);
        System.out.println(stringStudentMap);

        List<Student> names = students.stream().filter(p -> p.getName().length() > 3).collect(Collectors.toList());
        List<Student> names2 = students.stream().filter(p -> "小猪".indexOf(p.getName())>-1 ).collect(Collectors.toList());
        List<String> names3= students.stream().map( p -> "小狗"+p.getName() ).collect(Collectors.toList());
        List<Student> names4= students.stream().map( p -> {   p.setName("1"+p.getName());return  p; } ).collect(Collectors.toList());


        List costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);

//        System.out.println(bill);
        System.out.println(names);
    }
}
