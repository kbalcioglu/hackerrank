package com.solutions.hackerrank;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class GameOfTwoStacksTest {

    @Test
    public void test_1() {
        List<Integer> a = List.of(4, 2, 4, 6, 1);
        List<Integer> b = List.of(2, 1, 8, 5);
        int maxSum = 10;
        int expected = 4;
        int result = this.twoStacks(maxSum, a, b);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void test_2() {
        List<Integer> a = List.of(7, 15, 12, 0, 5, 18, 17, 2, 10, 15, 4, 2, 9, 15, 13, 12, 16);
        List<Integer> b = List.of(12, 16, 6, 8, 16, 15, 18, 3, 11, 0, 17, 7, 6, 11, 14, 13, 15, 6, 18, 6, 16, 12, 16, 11, 16, 11);
        int maxSum = 62;
        int expected = 6;
        int result = this.twoStacks(maxSum, a, b);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void test_3() {
        List<Integer> a = List.of(4, 11, 16, 0, 18, 17, 9, 13, 7, 12, 16, 19, 2, 15, 5, 13, 1, 10, 0, 8, 0, 6, 16, 12, 15, 7, 1, 6, 19, 16, 2);
        List<Integer> b = List.of(15, 8, 11, 16, 6, 0, 5, 11, 7, 9, 8, 6, 3, 3, 4, 8, 17, 14, 9, 5, 15, 15, 1, 19, 10, 0, 12, 8, 11, 9, 11, 18, 17, 14);
        int maxSum = 5;
        int expected = 1;
        int result = this.twoStacks(maxSum, a, b);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void test_4() {
        List<Integer> a = List.of(11, 6, 1, 13, 14, 7, 8, 10, 3, 17, 7, 18, 6, 4, 5, 13, 17, 4, 16, 9, 17, 16, 12, 6, 7);
        List<Integer> b = List.of(10, 15, 13, 17, 10, 7, 0, 16, 8, 13, 11, 8, 14, 13);
        int maxSum = 55;
        int expected = 6;
        int result = this.twoStacks(maxSum, a, b);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void test_5() {
        List<Integer> a = List.of(4, 2, 3, 3, 17, 1, 16, 8, 0, 12, 13, 14, 18);
        List<Integer> b = List.of(14, 7, 13, 14, 15, 0, 8, 10, 9, 1, 2, 19, 12, 17, 17, 17, 15, 0, 1, 10, 3, 17, 7, 16, 13, 7, 17, 17, 0, 3, 16, 14, 10, 1, 0, 6, 13, 8, 16, 2, 10, 10, 14, 2, 7, 3, 19, 2, 3);
        int maxSum = 91;
        int expected = 12;
        int result = this.twoStacks(maxSum, a, b);
        Assertions.assertEquals(expected, result);
    }

    static int twoStacks4(int maxSum, List<Integer> a, List<Integer> b) {
        int lengthB = 0;
        int sum = 0;
        while (lengthB < b.size() && sum + b.get(lengthB) <= maxSum) {
            sum += b.get(lengthB);
            lengthB++;
        }

        int maxScore = lengthB;
        for (int lengthA = 1; lengthA <= a.size(); lengthA++) {
            sum += a.get(lengthA - 1);

            while (sum > maxSum && lengthB > 0) {
                lengthB--;
                sum -= b.get(lengthB);
            }

            if (sum > maxSum) {
                break;
            }

            maxScore = Math.max(maxScore, lengthA + lengthB);
        }
        return maxScore;
    }

    static int twoStacks2(int maxSum, List<Integer> a, List<Integer> b) {

        List<Integer> aa = new ArrayList<>();
        List<Integer> bb = new ArrayList<>();
        int a_index = 0;
        int b_index = 0;
        int count = 0;
        int sum = 0;
        // move b_index to the position where if only take elements from B, last element it can take
        while (b_index < b.size() && sum + b.get(b_index) <= maxSum) {
            sum += b.get(b_index);
            b_index++;
        }
        // loop exits only when b_index reaches end or sum > x; in both case b_index should decrease
        b_index--;
        count = b_index + 1;

        while (a_index < a.size() && b_index < b.size()) {
            sum += a.get(a_index);
            if (sum > maxSum) {
                while (b_index >= 0) {
                    sum -= b.get(b_index);
                    b_index--;
                    if (sum <= maxSum)
                        break;
                }
                // if even no elements taken from B, but still sum greater than x, then a[a_index] should not be chosen
                // and loop terminates
                if (sum > maxSum && b_index < 0) {
                    a_index--;
                    break;
                }
            }
            count = Math.max(a_index + b_index + 2, count);
            a_index++;
        }

        return count;
    }

    public static int twoStacks3(int maxSum, List<Integer> a, List<Integer> b) {
        int n = a.size();
        int m = b.size();
        List<Integer> resultList = new ArrayList<>();
        int total = 0;
        int iA = 0;
        int iB = 0;
        while (true) {
            int p = a.get(iA);
            int k = b.get(iB);
            int min = Math.min(p, k);
            if (total + min > maxSum) {
                break;
            }
            total += min;
            if (min == p) {
                resultList.add(p);
                iA++;
            } else {
                resultList.add(k);
                iB++;
            }
            if (iA >= n || iB >= m)
                break;
        }
        for (int i = iA; i < n; i++) {
            int p = a.get(i);
            if (total + p > maxSum) {
                break;
            }
            total += p;
            resultList.add(p);
        }

        for (int i = iB; i < m; i++) {
            int p = b.get(i);
            if (total + p > maxSum) {
                break;
            }
            total += p;
            resultList.add(p);
        }
        return resultList.size();
    }

    public static int twoStacks(int maxSum, List<Integer> a, List<Integer> b) {
        Map<String, Integer> memo = new HashMap<>();
        int maxCount = 0;
        int count = 0;
        int indexA = 0;
        int indexB = 0;
        maxCount = rec(maxSum, a, b, indexA, indexB, count, maxCount, memo);
        return maxCount;
    }

    private static int rec(int sum, List<Integer> a, List<Integer> b, int indexA, int indexB, int count, int maxCount, Map<String, Integer> memo) {
        String key = "";
        if(memo.containsKey(key)){
            return memo.get(key);
        }
        if (sum > 0) {
            if (indexA < a.size() && indexB < b.size()) {
                int valA = a.get(indexA);
                int valB = b.get(indexB);
                if (sum >= valA) {
                    maxCount = rec(sum - valA, a, b, indexA + 1, indexB, count + 1, maxCount, memo);
                }
                if (sum >= valB) {
                    maxCount = rec(sum - valB, a, b, indexA, indexB + 1, count + 1, maxCount, memo);
                }

            } else if (indexA < a.size() && indexB >= b.size()) {
                int valA = a.get(indexA);
                if (sum >= valA) {
                    maxCount = rec(sum - valA, a, b, indexA + 1, indexB, count + 1, maxCount, memo);
                }
            } else if (indexA >= a.size() && indexB < b.size()) {
                int valB = b.get(indexB);
                if (sum >= valB) {
                    maxCount = rec(sum - valB, a, b, indexA, indexB + 1, count + 1, maxCount, memo);
                }
            }
        }
        return Math.max(maxCount, count);
    }
}
