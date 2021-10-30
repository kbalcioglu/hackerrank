package com.solutions.hackerrank;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.*;

public class BreadthFirstSearchShortestSearchTest {
    /*
        https://www.hackerrank.com/challenges/bfsshortreach/problem
        Consider an undirected graph where each edge weighs 6 units. Each of the nodes is labeled consecutively from 1 to n.

        You will be given a number of queries. For each query, you will be given a list of edges describing an undirected graph.
        After you create a representation of the graph,
        you must determine and report the shortest distance to each of the other nodes from a given starting position
        using the breadth-first search algorithm (BFS).
        Return an array of distances from the start node in node number order.
        If a node is unreachable, return -1 for that node.

        EXAMPLE
        n = 5
        m = 3
        edges = {[1,2],[1,3],[3,4]}
        s = 1
        All distances are from the start node 1 => [6,6,12,-1]
            * 1-2 -> 6
            * 1-3 -> 6
            * 1-4 -> 12 (1-3->3-4)
            * 1-5 -> -1 (no connection with 5)
    */


    @Test
    public void test_1() {
        // 9 2 91 84
        List<Integer> expected = List.of(6, 6, 12, -1);
        int n = 5;
        int m = 3;
        int s = 1;
        List<List<Integer>> edges = List.of(
                List.of(1, 2),
                List.of(3, 4),
                List.of(1, 3)
        );
        List<Integer> result = bfs(n, m, edges, s);
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
        List<Integer> result = bfs(n, m, edges, s);
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
        List<Integer> result = bfs(n, m, edges, s);
        Assertions.assertEquals(expected, result);
    }

    public static List<Integer> bfs(int n, int m, List<List<Integer>> edges, int s) {
        int x = 6;
        List<Integer> result = new ArrayList<>();
        Map<Integer, Set<Integer>> map = new HashMap<>();
        int[] distances = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            map.put(i, new HashSet<>());
            distances[i] = -1;
        }
        for (int i = 0; i < edges.size(); i++) {
            map.get(edges.get(i).get(0)).add(edges.get(i).get(1));
            map.get(edges.get(i).get(1)).add(edges.get(i).get(0));
        }

        Set<Integer> visited = new HashSet<>();
        Queue<Integer> q = new LinkedList<>();
        q.add(s);
        visited.add(s);
        distances[s] = 0;
        while (!q.isEmpty()) {
            int v = q.poll();
            var list = map.get(v);
            for (int i : list) {
                if (!visited.contains(i)) {
                    q.offer(i);
                    visited.add(i);
                    distances[i] = distances[v] + 6;
                }
            }
        }

        for (int i = 1; i < distances.length; i++) {
            if (i != s)
                result.add(distances[i]);
        }
        return result;
    }

}

