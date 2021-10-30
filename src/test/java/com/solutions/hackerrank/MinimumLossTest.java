package com.solutions.hackerrank;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.*;

public class MinimumLossTest {

    @Test
    public void test_1() {
        List<Long> price = List.of(20L, 15L, 8L, 2L, 12L);
        int expected = 3;

        long start = System.currentTimeMillis();
        int result = minimumLoss(price);
        long elapsed = System.currentTimeMillis() - start;
        start = System.currentTimeMillis();
        int result2 = minimumLoss2(price);
        long elapsed2 = System.currentTimeMillis() - start;
        System.out.println("result : " + elapsed);
        System.out.println("result2 : " + elapsed2);
        Assertions.assertEquals(expected, result);
        Assertions.assertEquals(expected, result2);
    }

    @Test
    public void test_2() {
        List<Long> price = List.of(5L, 10L, 3L);
        int expected = 2;
        long start = System.currentTimeMillis();
        int result = minimumLoss(price);
        long elapsed = System.currentTimeMillis() - start;
        start = System.currentTimeMillis();
        int result2 = minimumLoss2(price);
        long elapsed2 = System.currentTimeMillis() - start;
        System.out.println("result : " + elapsed);
        System.out.println("result2 : " + elapsed2);
        Assertions.assertEquals(expected, result);
        Assertions.assertEquals(expected, result2);
    }

    @Test
    public void test_3() {
        List<Long> price = List.of(20L, 7L, 2L, 8L, 5L);
        int expected = 2;
        long start = System.currentTimeMillis();
        int result = minimumLoss(price);
        long elapsed = System.currentTimeMillis() - start;
        start = System.currentTimeMillis();
        int result2 = minimumLoss2(price);
        long elapsed2 = System.currentTimeMillis() - start;
        System.out.println("result : " + elapsed);
        System.out.println("result2 : " + elapsed2);
        Assertions.assertEquals(expected, result);
        Assertions.assertEquals(expected, result2);
    }

    // O(2n) ==> O(n)
    public static int minimumLoss(List<Long> price) {
        // Write your code here
        long min = Long.MAX_VALUE;
        Map<Long, Integer> map = new HashMap<>();
        long[] priceArr = new long[price.size()];
        for (int i = 0; i < price.size(); i++) {
            priceArr[i] = price.get(i);
            map.put(priceArr[i], i);
        }
        Arrays.sort(priceArr);
        for (int i = priceArr.length-1; i > 0; i--) {
            if (priceArr[i] - priceArr[i - 1] < min && map.get(priceArr[i]) < map.get(priceArr[i - 1])) {
                min = priceArr[i] - priceArr[i - 1];
            }
        }
        return (int) min;
    }

    // Time Limit exceeds O(n^2)
    public static int minimumLoss2(List<Long> price) {
        // Write your code here
        long min = Long.MAX_VALUE;
        for (int i = 0; i < price.size() - 1; i++) {
            long x = price.get(i);
            long val = price.subList(i + 1, price.size()).stream().filter(y -> x > y).map(y -> x - y).min(Long::compareTo).orElse(Long.MAX_VALUE);
            min = Math.min(min, val);
        }
        return (int) min;
    }

    // Time Limit exceeds O(n^2)
    public static int minimumLoss3(List<Long> price) {
        // Write your code here
        long min = Long.MAX_VALUE;
        for (int i = 0; i < price.size() - 1; i++) {
            for (int j = i + 1; j < price.size(); j++) {
                if (price.get(i) > price.get(j)) {
                    min = Math.min(min, price.get(i) - price.get(j));
                }
            }
        }
        return (int) min;
    }
}
