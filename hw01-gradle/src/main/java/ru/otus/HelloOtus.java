package ru.otus;

import java.util.List;
import com.google.common.collect.Lists;

public class HelloOtus {
    public static void main(String[] args) {
        List<String> fruits = Lists.newArrayList("orange", "banana", "kiwi", "mandarin", "date", "quince");
        fruits.forEach(System.out::println);
    }
}