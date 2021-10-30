package com.solutions.hackerrank;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.*;

public class ShortestReachTest {
    private static int EDGE_WEIGHT = 6;

    @Test
    public void test_1() {
        // 9 2 91 84
        int nodeCount = 10;
        int edgeCount = 7;
        int startNode = 5;
        List<List<Integer>> edges = List.of(
                List.of(1, 2),
                List.of(3, 4),
                List.of(1, 3),
                List.of(1, 5),
                List.of(5, 6),
                List.of(5, 7),
                List.of(7, 8)
        );
        List<Integer> expected = List.of(6, 12, 12, 18, 6, 6, 12, -1, -1);
        List<Integer> result = search_shortest(nodeCount, edgeCount, edges, startNode);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void test_2() {
        // 9 2 91 84
        List<Integer> expected = List.of(6, -1, -1, -1, -1, -1, 12, -1, 12);
        int n = 10;
        int m = 6;
        int s = 3;
        List<List<Integer>> edges = List.of(
                List.of(3, 1),
                List.of(10, 1),
                List.of(10, 1),
                List.of(3, 1),
                List.of(1, 8),
                List.of(5, 2)
        );
        List<Integer> result = search_shortest(n, m, edges, s);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void test_3() {
        // 9 2 91 84
        List<Integer> expected = List.of(6, 18, 18, -1, -1, -1, 12, -1, 12);
        int n = 10;
        int m = 6;
        int s = 3;
        List<List<Integer>> edges = List.of(
                List.of(3, 1),
                List.of(10, 1),
                List.of(10, 1),
                List.of(3, 1),
                List.of(1, 8),
                List.of(2, 8),
                List.of(4, 10)
        );
        List<Integer> result = search_shortest(n, m, edges, s);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void test_4() {
        int n = 4;
        int m = 2;
        int s = 1;
        List<List<Integer>> edges = List.of(
                List.of(1, 2),
                List.of(1, 3)
        );
        List<Integer> expected = List.of(6, 6, -1);
        List<Integer> result = search_shortest(n, m, edges, s);
        Assertions.assertArrayEquals(expected.toArray(), result.toArray());
    }

    @Test
    public void test_5() {
        int n = 3;
        int m = 1;
        int s = 2;
        List<List<Integer>> edges = List.of(
                List.of(2, 3)
        );
        List<Integer> expected = List.of(-1, 6);
        List<Integer> result = search_shortest(n, m, edges, s);
        Assertions.assertArrayEquals(expected.toArray(), result.toArray());
    }

    public static List<Integer> search_shortest(int nodeCount, int edgeCount, List<List<Integer>> edges, int startNode) {
        List<Integer> response = new ArrayList<>();
        int[] result = new int[nodeCount];
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 1; i <= nodeCount; i++) {
            map.put(i, new HashSet<>());
        }

        for (int i = 0; i < edges.size(); i++) {
            int x = edges.get(i).get(0);
            int y = edges.get(i).get(1);
            map.get(x).add(y);
            map.get(y).add(x);
        }
        int distance = 1;
        var set = map.get(startNode);
        for (int i : set) {
            result[i - 1] = distance;
        }
        Map<Integer, Set<Integer>> resMap = new HashMap<>();
        resMap.put(distance, set);
        Set<Integer> visited = new HashSet<>();
        visited.add(startNode);
        Set<Integer> set2 = new HashSet<>();
        set2.addAll(set);

        while (set2.size() > 0) {
            set2 = new HashSet<>();
            distance++;
            for (int v : set) {
                visited.add(v);
                map.get(v).removeAll(visited);
                set2.addAll(map.get(v));
            }
            if (set2.size() > 0) {
                resMap.put(distance, set2);
                set = set2;
            }
        }
        for (int d : resMap.keySet()) {
            Set<Integer> set3 = resMap.get(d);
            for (int r : set3) {
                result[r - 1] = d;
            }
        }

        for (int i = 0; i < nodeCount; i++) {
            if (i == (startNode - 1))
                continue;
            if (result[i] == 0)
                response.add(-1);
            else
                response.add(result[i] * EDGE_WEIGHT);
        }
        return response;
    }
}
