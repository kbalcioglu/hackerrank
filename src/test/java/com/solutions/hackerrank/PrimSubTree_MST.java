package com.solutions.hackerrank;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

public class PrimSubTree_MST {
    @Test
    public void test_1() {
        List<List<Integer>> edges = List.of(
                List.of(1, 2, 2),
                List.of(2, 3, 2),
                List.of(1, 3, 3)
        );
        int start = 1;
        int n = 3;
        int expected = 4;
        int result = this.prims(n, edges, start);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void test_2() {
        List<List<Integer>> edges = List.of(
                List.of(1, 2, 3),
                List.of(1, 3, 4),
                List.of(4, 2, 6),
                List.of(5, 2, 2),
                List.of(2, 3, 5),
                List.of(3, 5, 7)
        );
        int start = 1;
        int n = 5;
        int expected = 15;
        int result = this.prims(n, edges, start);
        Assertions.assertEquals(expected, result);
    }

    static class Possible {
        int node;
        int weight;

        Possible(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }

    static class PossibleList {
        private List<Possible> possibleList;

        PossibleList() {
            possibleList = new ArrayList<>();
        }

        void add(int node, int weight) {
            if (possibleList.size() == 0)
                possibleList.add(new Possible(node, weight));
            else if (possibleList.get(0).weight > weight)
                possibleList.add(0, new Possible(node, weight));
            else if (possibleList.get(possibleList.size() - 1).weight <= weight)
                possibleList.add(new Possible(node, weight));
            else {
                for (int i = 0; i < possibleList.size(); i++) {
                    if (possibleList.get(i).weight >= weight) {
                        possibleList.add(i, new Possible(node, weight));
                        break;
                    }
                }
            }
        }

        void add(PossibleList other, Set<Integer> visited) {
            for (int i = 0; i < other.possibleList.size(); i++) {
                if (!visited.contains(other.possibleList.get(i).node))
                    this.add(other.possibleList.get(i).node, other.possibleList.get(i).weight);
            }
        }

        PossibleList remove(Set<Integer> visited) {
            possibleList = possibleList.stream().filter(x-> !visited.contains(x.node)).collect(Collectors.toList());
            return this;
        }

        Possible getMinWeight(Set<Integer> visited) {
            return possibleList.stream()
                    .filter(x -> !visited.contains(x.node))
                    .findFirst()
                    .get();
        }
    }

    public static int prims(int n, List<List<Integer>> edges, int start) {
        int total = 0;
        Set<Integer> visited = new HashSet<>();
        visited.add(start);
        Map<Integer, PossibleList> possibleMap = new HashMap<>();
        for (int i = 0; i < edges.size(); i++) {
            int node1 = edges.get(i).get(0);
            int node2 = edges.get(i).get(1);
            int weight = edges.get(i).get(2);
            possibleMap.putIfAbsent(node1, new PossibleList());
            possibleMap.putIfAbsent(node2, new PossibleList());
            possibleMap.get(node1).add(node2, weight);
            possibleMap.get(node2).add(node1, weight);
        }
        Queue<PossibleList> queue = new LinkedList<>();
        queue.add(possibleMap.get(start));
        while (!queue.isEmpty()) {
            if (visited.size() == n)
                break;
            PossibleList list = queue.poll();
            Possible p = list.getMinWeight(visited);
            visited.add(p.node);
            total += p.weight;
            list.remove(visited).add(possibleMap.get(p.node),visited);
            queue.offer(list);
        }

        return total;
    }
}
