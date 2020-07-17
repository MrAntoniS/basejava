package com.basejava.webapp;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainStream {

    static int[] values = {2, 1, 9, 2, 9, 1, 1};
    static Integer[] integers = {2, 7, 9, 2, 8, 1, 4};

    public static void main(String[] args) {
        System.out.println(minValue(values));
        System.out.println(oddOrEven(Arrays.asList(integers)));
    }

    static int minValue(int[] values) {
        return Arrays.stream(values).distinct().sorted().reduce(0, (a, b) -> 10 * a + b);
    }

    static List<Integer> oddOrEven(List<Integer> integers) {
        return integers.stream().mapToInt(Integer::intValue).sum() % 2 == 0 ? filter(integers, 0) : filter(integers, 1);
    }

    private static List<Integer> filter(List<Integer> integers, int i) {
        return integers.stream().filter(s -> s % 2 == i).collect(Collectors.toList());
    }
}