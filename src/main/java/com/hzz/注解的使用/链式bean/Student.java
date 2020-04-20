package com.hzz.注解的使用.链式bean;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Map;

/**
 * @author hzz
 * @version 1.0
 * @date 2020/4/17 14:44
 */
@Accessors(chain = true)
@Setter
@Getter
@RequiredArgsConstructor(staticName = "of")
public class Student {
    @NonNull
    private String name111;
    @NonNull
    private int age;

    public static void main(String[] args) {
        Student student = Student.of("1",2);
        System.out.println(1);
        Map<Object, Object> objectObjectMap = Collections.emptyMap();
    }
}
