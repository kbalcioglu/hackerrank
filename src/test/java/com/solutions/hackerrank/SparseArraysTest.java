package com.solutions.hackerrank;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.*;

public class SparseArraysTest {
    @Test
    public void test_1() {
        List<String> strings = List.of("ab", "ab", "abc");
        List<String> queries = List.of("ab", "abc", "bc");
        List<Integer> expected = List.of(2, 1, 0);
        List<Integer> results = matchingStrings(strings, queries);
        Assertions.assertEquals(expected.size(), results.size());
        for (int i = 0; i < results.size(); i++) {
            Assertions.assertEquals(expected.get(i), results.get(i));
        }
    }
    @Test
    public void test_2() {
        List<String> strings = List.of("aba", "baba", "aba","xzxb");
        List<String> queries = List.of("aba", "xzxb", "ab");
        List<Integer> expected = List.of(2, 1, 0);
        List<Integer> results = matchingStrings(strings, queries);
        Assertions.assertEquals(expected.size(), results.size());
        for (int i = 0; i < results.size(); i++) {
            Assertions.assertEquals(expected.get(i), results.get(i));
        }
    }

    public static List<Integer> matchingStrings(List<String> strings, List<String> queries) {
        List<Integer> result = new ArrayList<>();
        // Write your code here
        for (String q : queries) {
            int count = (int)strings.stream().filter(x->x.equals(q)).count();
            result.add(count);
        }
        return result;
    }
}
