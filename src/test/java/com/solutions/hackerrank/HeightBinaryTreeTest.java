package com.solutions.hackerrank;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class HeightBinaryTreeTest {
    static class Node {
        int data;
        Node left;
        Node right;
        int level;

        Node(int data) {
            this.data = data;
        }
    }

    @Test
    public void test_1() {
        List<Integer> list = List.of(3, 2, 5, 1, 4, 6, 7);
        Node root = null;
        for (int i : list) {
            root = insert(root, i);
        }
        int expected = 3;
        int result = height(root);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void test_2() {
        List<Integer> list = List.of(4, 2, 6, 1, 3, 5, 7);
        Node root = null;
        for (int i : list) {
            root = insert(root, i);
        }
        int expected = 2;
        int result = height(root);
        Assertions.assertEquals(expected, result);
    }

    public static int height(Node root) {
        int max_count = 0;

        Queue<Node> queue = new LinkedList<>();
        root.level = 1;
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node.left == null && node.right == null) {
                max_count = Math.max(max_count, node.level);
            } else {
                if (node.left != null) {
                    node.left.level = node.level + 1;
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    node.right.level = node.level + 1;
                    queue.offer(node.right);
                }
            }

        }

        return max_count - 1;
    }


    public static Node insert(Node root, int data) {
        if (root == null) {
            return new Node(data);
        } else {
            Node cur;
            if (data <= root.data) {
                cur = insert(root.left, data);
                root.left = cur;
            } else {
                cur = insert(root.right, data);
                root.right = cur;
            }
            return root;
        }
    }

}
