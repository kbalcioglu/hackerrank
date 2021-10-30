package com.solutions.hackerrank;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

public class DijkstraShortestPathTest {
    static class Possible {
        final int node;
        final int weight;

        Possible(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }

    static class NextNode {
        int node;
        int weight;

        NextNode(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }

    static class ProcessMap {
        private final int nodeCount;
        private final Set<Integer> visitedVertexes;
        private final Set<Integer> unvisitedVertexes;
        private final int[] previousVertex;
        private final int[] weightVertex;
        private final Map<Integer, List<Possible>> possibilities;
        private final List<NextNode> orderedNodeList;

        ProcessMap(int nodeCount, List<Integer> from, List<Integer> to, List<Integer> weight) {
            this.nodeCount = nodeCount;
            this.visitedVertexes = new HashSet<>();
            this.unvisitedVertexes = new HashSet<>();
            this.previousVertex = new int[nodeCount + 1];
            this.weightVertex = new int[nodeCount + 1];
            this.possibilities = new HashMap<>();
            this.orderedNodeList = new ArrayList<>();

            this.unvisitedVertexes.add(1);
            this.previousVertex[1] = -1;
            this.weightVertex[1] = 0;
            this.possibilities.putIfAbsent(1, new ArrayList<>());
            for (int i = 2; i <= nodeCount; i++) {
                this.unvisitedVertexes.add(i);
                this.previousVertex[i] = -1;
                this.weightVertex[i] = Integer.MAX_VALUE;
                this.possibilities.putIfAbsent(i, new ArrayList<>());
            }
            this.setPossibilities(from, to, weight);
        }

        private void setPossibilities(List<Integer> from, List<Integer> to, List<Integer> weight) {
            for (int i = 0; i < from.size(); i++) {
                int f = from.get(i);
                int t = to.get(i);
                int w = weight.get(i);
                this.possibilities.get(f).add(new Possible(t, w));
                this.possibilities.get(t).add(new Possible(f, w));
            }
        }

        boolean processNotFinished() {
            return this.visitedVertexes.size() < this.nodeCount;
        }

        List<Possible> getPossibilities(int node) {
            return this.possibilities.get(node).stream()
                    .filter(x -> !this.visitedVertexes.contains(x.node))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        }

        int getWeightOfNode(int node) {
            return this.weightVertex[node];
        }

        void addOrdered(int node, int weight) {
            if (this.orderedNodeList.size() == 0) {
                this.orderedNodeList.add(new NextNode(node, weight));
            } else if (this.orderedNodeList.get(0).weight >= weight) {
                this.orderedNodeList.add(0, new NextNode(node, weight));
            } else if (this.orderedNodeList.get(this.orderedNodeList.size() - 1).weight <= weight) {
                this.orderedNodeList.add(new NextNode(node, weight));
            } else {
                int start = 0;
                int end = this.orderedNodeList.size() - 1;
                while (true) {
                    int x = end + start;
                    if (x % 2 == 1) {
                        x = (x + 1) / 2;
                    } else {
                        x = x / 2;
                    }
                    NextNode p = this.orderedNodeList.get(x);
                    if (p.weight == weight) {
                        this.orderedNodeList.add(x, new NextNode(node, weight));
                        break;
                    } else if (p.weight > weight && this.orderedNodeList.get(x - 1).weight <= weight) {
                        this.orderedNodeList.add(x, new NextNode(node, weight));
                        break;
                    } else if (p.weight < weight && this.orderedNodeList.get(x + 1).weight >= weight) {
                        this.orderedNodeList.add((x + 1), new NextNode(node, weight));
                        break;
                    } else if (p.weight > weight) {
                        end = x;
                    } else {
                        start = x;
                    }
                }

            }
        }

        void updateVertexStatus(int node, int weight, int previous) {
            this.weightVertex[node] = weight;
            this.previousVertex[node] = previous;
        }

        NextNode getNextNode() {
            int minWeight = Integer.MAX_VALUE;
            int node = -1;
            for (int i = 1; i < this.weightVertex.length; i++) {
                if (!this.visitedVertexes.contains(i)) {
                    int w = this.weightVertex[i];
                    if (w < minWeight) {
                        minWeight = w;
                        node = i;
                    }
                }
            }

            this.visitedVertexes.add(node);
            this.unvisitedVertexes.remove(node);
            return new NextNode(node, minWeight);
        }
    }

    @Test
    public void test_1() {
        List<Integer> gFrom = List.of(1, 1, 2, 2, 3, 3, 4, 4, 5, 5);
        List<Integer> gTo = List.of(2, 4, 3, 6, 6, 7, 6, 5, 6, 7);
        List<Integer> gWeight = List.of(3, 7, 2, 8, 2, 1, 1, 4, 9, 4);
        int gNodes = 7;
        this.shortestDistance(gNodes, gFrom, gTo, gWeight);
    }

    @Test
    public void test_2() {
        List<Integer> gFrom = List.of(1, 2, 1, 3);
        List<Integer> gTo = List.of(2, 4, 3, 4);
        List<Integer> gWeight = List.of(20, 30, 5, 40);
        int gNodes = 4;
        String expected = "30";
        String result = this.getCost(gNodes, gFrom, gTo, gWeight);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void test_3() {
        List<Integer> gFrom = List.of(1, 3);
        List<Integer> gTo = List.of(2, 4);
        List<Integer> gWeight = List.of(10, 20);
        int gNodes = 4;
        String expected = "NO PATH EXISTS";
        String result = this.getCost(gNodes, gFrom, gTo, gWeight);
        Assertions.assertEquals(expected, result);
    }

    // general Dijkstra's algorithm
    public static void shortestDistance(int gNodes, List<Integer> gFrom, List<Integer> gTo, List<Integer> gWeight) {
        ProcessMap processMap = new ProcessMap(gNodes, gFrom, gTo, gWeight);
        while (processMap.processNotFinished()) {
            NextNode nextNode = processMap.getNextNode();

            List<Possible> possibleList = processMap.getPossibilities(nextNode.node);
            for (Possible p : possibleList) {
                int k = processMap.getWeightOfNode(p.node);
                int m = processMap.getWeightOfNode(nextNode.node);
                int w = m + p.weight;
                if (k > w) {
                    processMap.updateVertexStatus(p.node, w, nextNode.node);
                }
            }
        }
    }

    // https://www.hackerrank.com/challenges/jack-goes-to-rapture/problem
    public static String getCost(int gNodes, List<Integer> gFrom, List<Integer> gTo, List<Integer> gWeight) {
        ProcessMap processMap = new ProcessMap(gNodes, gFrom, gTo, gWeight);
        while (processMap.processNotFinished()) {
            NextNode nextNode = processMap.getNextNode();
            if (nextNode.node == -1)
                break;
            List<Possible> possibleList = processMap.getPossibilities(nextNode.node);
            for (Possible p : possibleList) {
                int k = processMap.getWeightOfNode(p.node);
                int w = p.weight - nextNode.weight;
                if (w < 0)
                    w = 0;
                w += nextNode.weight;
                if (k > w) {
                    processMap.updateVertexStatus(p.node, w, nextNode.node);
                }
            }
        }
        String result = "NO PATH EXISTS";
        int weight = processMap.getWeightOfNode(gNodes);
        if (weight < Integer.MAX_VALUE)
            result = String.valueOf(weight);
        System.out.println(result);
        return result;
    }
}
