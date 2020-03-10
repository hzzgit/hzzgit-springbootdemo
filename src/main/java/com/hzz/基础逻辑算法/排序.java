package com.hzz.基础逻辑算法;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class 排序 {

    public void 冒泡排序() {
        Integer[] integers = new Integer[]{22, 33, 1, 222, 333, 34, 45, 3, 44, 555, 6, 7, 8, 1, 2, 3};
        for (int i = 0; i < integers.length; i++) {
            for (int i1 = 0; i1 < integers.length; i1++) {
                if (integers[i] < integers[i1]) {
                    Integer a = integers[i1];
                    Integer b = integers[i];
                    integers[i] = a;
                    integers[i1] = b;
                }
            }
        }
        System.out.println(integers);
    }

    //
    public static void 桶排序(int[] ints, Boolean isAsc) {
        int[] basket = new int[101]; //定义足够大的数组桶
        for (int i : ints) {
            basket[i]++; //对应元素的桶下标自增
        }
        if (isAsc) {
            for (int i = 0; i < basket.length - 1; i++) {
                if (basket[i] > 0) {
                    for (int j = 1; j <= basket[i]; j++) {
                        System.out.print(i + " "); //循环输出桶元素不为0的下标值
                    }
                }
            }
        } else {
            for (int i = basket.length - 1; i > 0; i--) {
                if (basket[i] > 0) {
                    for (int j = 1; j <= basket[i]; j++) {
                        System.out.print(i + " "); //循环输出桶元素不为0的下标值
                    }
                }
            }
        }

    }

    public static void 快速排序(int[] arr,int low,int high){
        int i,j,temp,t;
        if(low>high){
            return;
        }
        i=low;
        j=high;
        //temp就是基准位
        temp = arr[low];

        while (i<j) {
            //先看右边，依次往左递减
            while (temp<=arr[j]&&i<j) {
                j--;
            }
            //再看左边，依次往右递增
            while (temp>=arr[i]&&i<j) {
                i++;
            }
            //如果满足条件则交换
            if (i<j) {
                t = arr[j];
                arr[j] = arr[i];
                arr[i] = t;
            }

        }
        //最后将基准为与i和j相等位置的数字交换
        arr[low] = arr[i];
        arr[i] = temp;
        //递归调用左半数组
        快速排序(arr, low, j-1);
        //递归调用右半数组
        快速排序(arr, j+1, high);
    }



    public static void main(String[] args) {
        List<String> nas = new ArrayList<>();
        nas.add("a");
        nas.add("c");
        nas.add("b");
        Collections.sort(nas, (s1, s2) -> s2.compareTo(s1));
        System.out.println(nas);
    }
}
