package com.solutions.hackerrank;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.*;

public class EvenTreeDecomposeTest {
    /*
        https://www.hackerrank.com/challenges/even-tree/problem
        You are given a tree (a simple connected graph with no cycles).
        Find the maximum number of edges you can remove from the tree to get a forest
        such that each connected component of the forest contains an even number of nodes.

        https://www.geeksforgeeks.org/convert-tree-forest-even-nodes/
        Given a tree of n even nodes.
        The task is to find the maximum number of edges to be removed from the given tree
        to obtain forest of trees having even number of nodes.
        This problem is always solvable as given graph has even nodes

        t_from[i] - t_to[i] an edge node connection
        t_from.size() length == t_to.size() ==> n   ==> 2<=n<=100 && n%2 = 0

        DFS -> maximum edges to have max amount of even tree
    */

    @Test
    public void test_1() {
        int t_nodes = 10;
        int t_edges = 9;
        List<Integer> t_from = List.of(2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> t_to = List.of(1, 1, 3, 2, 1, 2, 6, 8, 8);
        long expected = 2;
        long result = evenForest(t_nodes, t_edges, t_from, t_to);
        Assertions.assertEquals(expected, result);
    }

    private static long ans;

    public static long evenForest(int t_nodes, int t_edges, List<Integer> t_from, List<Integer> t_to) {
        ans = 0;
        Set<Integer> visited = new HashSet<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 1; i <= t_nodes; i++) {
            map.put(i, new ArrayList<>());
        }
        for (int i = 0; i < t_edges; i++) {
            int f = t_from.get(i);
            int t = t_to.get(i);
            if (map.containsKey(f)) {
                map.get(f).add(t);
            }
            if (map.containsKey(t)) {
                map.get(t).add(f);
            }
        }

        dfs(visited, 1, map);
        if (ans > t_edges)
            return t_edges;
        return ans;
    }

    // recursive dfs method
    private static int dfs(Set<Integer> visited, int node, Map<Integer, List<Integer>> map) {
        int num = 0, temp = 0;
        if (!map.containsKey(node))
            return 0;
        visited.add(node);
        List<Integer> children = map.get(node);
        for (int i = 0; i < children.size(); i++) {
            int c = children.get(i);
            if (!visited.contains(c)) {
                temp = dfs(visited, c, map);
                if (temp % 2 != 0)
                    num += temp;
                else
                    ans++;

            }
        }
        return num+1;
    }
}
