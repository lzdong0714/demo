package com.example.base_1.stream;

import com.example.reflection.Person;
import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: Liu Zhendong
 * @Description java stream的特性演示
 *
 * +--------------------+       +------+   +------+   +---+   +-------+
 * | stream of elements +-----> |filter+-> |sorted+-> |map+-> |collect|
 * +--------------------+       +------+   +------+   +---+   +-------+
 *
 * @createTime 2019年08月21日 17:27:00
 */
public class StremDemo_1 {
    public static void main(String[] args) {
        StremDemo_1 stremDemo_1 = new StremDemo_1();
        stremDemo_1.test();
    }

    public void test(){
        List<String> nameList = Arrays.asList("abc", "dd", "llol", "", "yyy");
        List<Integer> ageList = Arrays.asList(1, 2, 3, 4);

        List<Person> personList = new LinkedList<>();

        long count = nameList.stream().filter(name -> !name.isEmpty()).count();
        System.out.println(count);
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        List<String> filtered = strings.stream()
                .filter(string -> !string.isEmpty())
//                .sorted((str1, str2) -> str1.length() - str2.length())
                .sorted(Comparator.comparing(str->str,Comparator.reverseOrder()))
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        filtered.forEach(System.out::println);

    }

}
