package com;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zhangf
 * @description:
 * @date: 2018/12/6
 */
public class TestClass {

    public static void main(String[] args){
        List<String> arr1 = new ArrayList<String>();
        arr1.add("123");
        List<String> arr2 = new ArrayList<String>();
        arr2.addAll(arr1);
        System.out.println("first:"+arr2.toString());
        arr1.add("456");
        System.out.println("secand:"+arr2.toString());
        System.out.println("arr1:"+arr1.toString());

        System.out.println("---------这是个分割线----------");
        arr1.forEach(str->{
            System.out.println(str);
        });
    }
}
