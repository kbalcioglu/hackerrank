package com.solutions.hackerrank;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ComponentsInGraphTest {
    @Test
    public void test_1() {
        //[[1, 6],[2, 7], [3, 8], [4,9], [2, 6]]
        List<List<Integer>> gb = List.of(
                List.of(1, 6),
                List.of(2, 7),
                List.of(3, 8),
                List.of(4, 9),
                List.of(2, 6)
        );

        List<Integer> expected = List.of(2, 4);
        List<Integer> results = componentsInGraph(gb);
        Assertions.assertEquals(expected.size(), results.size());
        for (int i = 0; i < results.size(); i++) {
            Assertions.assertEquals(expected.get(i), results.get(i));
        }
    }

    @Test
    public void test_2() {
        //[[1, 6],[2, 7], [3, 8], [4,9], [2, 6]]
        List<List<Integer>> gb = new ArrayList<>();
        for (int i = 15001; i <= 30000; i++) {
            gb.add(List.of(1, i));
        }

        List<Integer> expected = List.of(15001, 15001);
        List<Integer> results = componentsInGraph(gb);
        Assertions.assertEquals(expected.size(), results.size());
        for (int i = 0; i < results.size(); i++) {
            Assertions.assertEquals(expected.get(i), results.get(i));
        }
    }

    public static List<Integer> componentsInGraph(List<List<Integer>> gb) {
        // Write your code here
        int n = gb.size();
        int max = 0;
        int min = Integer.MAX_VALUE;
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (List<Integer> l : gb) {
            int k = l.get(0);
            int m = l.get(1);
            if (map.containsKey(k)) {
                Set<Integer> list = new HashSet<>();
                list.addAll(map.get(k));
                list.add(m);
                map.remove(k);
                map.put(k, list);
            } else {
                map.put(k, Set.of(m));
            }
            if (map.containsKey(m)) {
                Set<Integer> list = new HashSet<>();
                list.addAll(map.get(m));
                list.add(k);
                map.remove(m);
                map.put(m, list);
            } else {
                map.put(m, Set.of(k));
            }
        }
        Set<Integer> visited = new HashSet<>();
        Map<Integer, Integer> mapp = new HashMap<>();
        for (int key : map.keySet()) {
            if (visited.contains(key))
                continue;
            int count = 0;
            count = dfs(map, visited, key, count);
            if (count > 0) {
                mapp.putIfAbsent(key, count);
            }
        }
        for (int val : mapp.values()) {
            max = Math.max(max, val);
            min = Math.min(min, val);
        }
        return List.of(min, max);
    }

    private static int dfs(Map<Integer, Set<Integer>> map, Set<Integer> visited, int i, int count) {
        if (visited.contains(i))
            return count;
        visited.add(i);
        if (!map.containsKey(i))
            return count;
        count++;
        Set<Integer> list = map.get(i);
        for (int k : list) {
            count = dfs(map, visited, k, count);
        }
        //map.remove(i);
        return count;
    }

}
class Element{
    int elementId;
    int setId;
}

class Sett{
    int setId;
    HashSet<Integer> sets;
}
