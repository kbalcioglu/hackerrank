package com.solutions.hackerrank;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class SwapNodesAlgoTest {

    @Test
    public void test_1() {
        List<List<Integer>> indexes = List.of(
                List.of(2, 3),   // 1
                List.of(4, -1),  // 2
                List.of(5, -1),  // 3
                List.of(6, -1),  // 4
                List.of(7, 8),   // 5
                List.of(-1, 9),  // 6
                List.of(-1, -1), // 7
                List.of(10, 11), // 8
                List.of(-1, -1), // 9
                List.of(-1, -1), // 10
                List.of(-1, -1)  // 11
        );
        List<Integer> queries = List.of(2, 4);
        List<List<Integer>> expected = List.of(
                List.of(2, 9, 6, 4, 1, 3, 7, 5, 11, 8, 10),
                List.of(2, 6, 9, 4, 1, 3, 7, 5, 10, 8, 11)
        );
        List<List<Integer>> result = swapNodes(indexes, queries);
        Assertions.assertArrayEquals(expected.toArray(),result.toArray());
    }

    static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
        }
    }

    public static List<List<Integer>> swapNodes(List<List<Integer>> indexes, List<Integer> queries) {
        List<List<Integer>> result = new ArrayList<>();
        Node root = ConstructTree(indexes);
        for (int k : queries) {
            List<Integer> list = new ArrayList<>();
            swapLevels(root, k);
            list = readOrdered(root, list);
            result.add(list);
        }
        return result;
    }

    public static Node ConstructTree(List<List<Integer>> tree) {
        Node root = new Node(1);
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        for (int i = 0; i < tree.size(); i++) {
            Node left, right;

            if (tree.get(i).get(0) != -1)
                left = new Node(tree.get(i).get(0));
            else
                left = null;
            if (tree.get(i).get(1) != -1)
                right = new Node(tree.get(i).get(1));
            else
                right = null;

            Node temp = q.remove();
            temp.left = left;
            temp.right = right;

            if (left != null)
                q.add(left);
            if (right != null)
                q.add(right);
        }
        return root;
    }

    public static void swapLevels(Node root, int k) {
        Queue<Node> currentlevel = new LinkedList<>();
        Queue<Node> nextlevel = new LinkedList<>();
        int level = 1;
        currentlevel.add(root);
        while (!currentlevel.isEmpty()) {
            Node temp = currentlevel.poll();
            if (temp.left != null)
                nextlevel.add(temp.left);
            if (temp.right != null)
                nextlevel.add(temp.right);
            if (level % k == 0) {
                Node n = temp.left;
                temp.left = temp.right;
                temp.right = n;
            }
            if (currentlevel.isEmpty()) {
                Queue<Node> t = currentlevel;
                currentlevel = nextlevel;
                nextlevel = t;
                level++;
            }
        }
    }

    public static List<Integer> readOrdered(Node root, List<Integer> list) {
        if (root == null)
            return list;
        list = readOrdered(root.left, list);
        list.add(root.data);
        list = readOrdered(root.right, list);
        return list;
    }
}
