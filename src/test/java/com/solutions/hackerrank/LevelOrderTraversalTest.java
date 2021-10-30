package com.solutions.hackerrank;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class LevelOrderTraversalTest {
    static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
        }
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

    public static void levelOrder(Node root) {
        List<String> list = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            list.add(String.valueOf(node.data));
            if (node.left == null && node.right == null) {
                continue;
            } else {
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        System.out.print(String.join(" ", list));
    }

    @Test
    public void test_1() {
        List<Integer> list = List.of(1, 2, 5, 3, 6, 4);
        Node root = null;
        for (int i : list) {
            root = insert(root, i);
        }
        // 1 2 5 3 6 4
        levelOrder(root);
    }
}
