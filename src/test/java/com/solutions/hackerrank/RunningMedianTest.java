package com.solutions.hackerrank;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class RunningMedianTest {

    @Test
    public void test_1() {
        List<Integer> a = List.of(12, 4, 5, 3, 8, 7);
        List<Double> expected = List.of(12.0, 8.0, 5.0, 4.5, 5.0, 6.0);
        List<Double> result = this.runningMedian(a);
        Assertions.assertArrayEquals(expected.toArray(),result.toArray());
    }

    @Test
    public void test_2() {
        List<Integer> a = List.of(12, 4, 28, 16, 15, 7, 6, 2, 8, 5, 18);
        List<Double> expected = List.of(12.0, 8.0, 12.0, 14.0, 15.0, 13.5, 12.0, 9.5, 8.0, 7.5, 8.0);
        List<Double> result = this.runningMedian(a);
        Assertions.assertArrayEquals(expected.toArray(),result.toArray());
    }

    @Test
    public void test_sorted_1() {
        List<Integer> input = List.of(12, 4, 5, 3, 8, 7);
        List<Integer> expected = List.of(3, 4, 5, 7, 8, 12);
        List<Integer> result = addOrderedAll(input);
        Assertions.assertArrayEquals(expected.toArray(),result.toArray());
    }

    @Test
    public void test_sorted_2() {
        List<Integer> input = List.of(12, 4, 28, 16, 15, 7, 6, 2, 8, 5, 18);
        List<Integer> expected = List.of(2, 4, 5, 6, 7, 8, 12, 15, 18, 28);
        List<Integer> result = addOrderedAll(input);
        Assertions.assertArrayEquals(expected.toArray(),result.toArray());

    }

    public static List<Double> runningMedian(List<Integer> a) {
        // Write your code here
        List<Double> resultList = new ArrayList<>();
        List<Integer> sortedList = new ArrayList<>();

        for (int i = 0; i < a.size(); i++) {
            sortedList = addOrdered(sortedList, a.get(i));
            int x = sortedList.size();
            if (x % 2 == 0) {
                int k = ((x + 1) / 2);
                int m = k - 1;
                resultList.add(((Double.valueOf(sortedList.get(k)) + Double.valueOf(sortedList.get(m))) / 2));
            } else {
                int k = (((x + 2) / 2) - 1);
                resultList.add(Double.valueOf(sortedList.get(k)));
            }
        }
        return resultList;
    }

    private static List<Integer> addOrderedAll(List<Integer> list) {
        List<Integer> sortedList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            sortedList = addOrdered(sortedList, list.get(i));
        }
        return sortedList;
    }

    private static List<Integer> addOrdered(List<Integer> sortedList, int k) {
        if (sortedList == null) {
            sortedList = new ArrayList<>();
        }
        if (sortedList.size() == 0) {
            sortedList.add(k);
        } else if (sortedList.get(0) >= k) {
            sortedList.add(0, k);
        } else if (sortedList.get(sortedList.size() - 1) <= k) {
            sortedList.add(k);
        } else {
            int start = 0;
            int end = sortedList.size() - 1;
            while (true) {
                int x = end + start;
                if (x % 2 == 1) {
                    x = (x + 1) / 2;
                } else {
                    x = x / 2;
                }
                int p = sortedList.get(x);
                if (p == k) {
                    sortedList.add(x, k);
                    break;
                } else if (p > k && sortedList.get(x - 1) <= k) {
                    sortedList.add(x, k);
                    break;
                } else if (p < k && sortedList.get(x + 1) >= k) {
                    sortedList.add((x + 1), k);
                    break;
                } else if (p > k) {
                    end = x;
                } else {
                    start = x;
                }
            }

        }
        return sortedList;
    }
}
