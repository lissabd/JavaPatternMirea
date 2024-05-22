package org.example;

import java.util.Arrays;
import java.util.function.Function;

public class MinValue implements Function<int [], String> {
    @Override
    public String apply(int[] numbers) {
        int[] uniqueNumbers = Arrays.stream(numbers).distinct().toArray();
        Arrays.sort(uniqueNumbers);
        StringBuilder result = new StringBuilder();
        for (int number : uniqueNumbers) {
            result.append(number);
        }
        return result.toString();
    }
    public static void main(String[] args) {
        MinValue minValue = new MinValue();
        int[] numbers = {1, 3, 1};
        System.out.println(minValue.apply(numbers));

        int[] numbers2 = {5, 3, 3, 1, 3, 6, 4};
        System.out.println(minValue.apply(numbers2));
    }
}
