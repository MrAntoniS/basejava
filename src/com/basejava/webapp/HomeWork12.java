package com.basejava.webapp;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class HomeWork12 {

    static int[] values = {2, 1, 9, 2, 9, 1, 1};
    static Integer[] integers = {2, 7, 9, 2, 8, 1, 4, 3};

    public static void main(String[] args) {
        System.out.println(minValue(values));
        System.out.println(oddOrEven(Arrays.asList(integers)));
    }


    static int minValue(int[] values) {
        int[] sortedValues = Arrays.stream(values).distinct().sorted().toArray();
        int value = 0;
        for (int i = 0; i < sortedValues.length; i++) {
            int number = sortedValues[i] * (int) Math.pow(10, sortedValues.length - i - 1);
            value = value + number;
        }
        return value;
    }

    static List<Integer> oddOrEven(List<Integer> integers) {
        if (integers.stream().mapToInt(Integer::intValue).sum() % 2 == 0) {
            return integers.stream().filter(s -> s % 2 == 0).collect(Collectors.toList());
        } else {
            return integers.stream().filter(s -> s % 2 != 0).collect(Collectors.toList());
        }
    }
}