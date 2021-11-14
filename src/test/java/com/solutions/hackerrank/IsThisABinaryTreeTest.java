package com.solutions.hackerrank;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

// https://www.hackerrank.com/challenges/is-binary-search-tree/problem
public class IsThisABinaryTreeTest {

    @Test
    public void test_1() {
        BSTNode root = null;
        boolean result = checkBST(root);
        Assertions.assertEquals(false, result);
    }

    @Test
    public void test_2() {
        BSTNode root = new BSTNode(4);
        BSTNode node1 = new BSTNode(1);
        BSTNode node2 = new BSTNode(2);
        BSTNode node3 = new BSTNode(3);
        BSTNode node5 = new BSTNode(5);
        BSTNode node6 = new BSTNode(6);
        BSTNode node7 = new BSTNode(7);
        node2.left = node1;
        node2.right = node3;
        node6.left = node5;
        node6.right = node7;
        root.left = node2;
        root.right = node6;
        boolean result = checkBST(root);
        Assertions.assertEquals(true, result);
    }

    @Test
    public void test_3() {
        BSTNode root = new BSTNode(3);
        BSTNode node1 = new BSTNode(1);
        BSTNode node4 = new BSTNode(4);
        BSTNode node5 = new BSTNode(5);
        node5.right = node4;
        node5.left = node1;
        BSTNode node2 = new BSTNode(2);
        BSTNode node6 = new BSTNode(6);
        node2.left = node6;
        root.left = node5;
        root.right = node2;
        boolean result = checkBST(root);
        Assertions.assertEquals(false, result);
    }

    @Test
    public void test_4() {
        BSTNode root = new BSTNode(3);
        BSTNode node1 = new BSTNode(1);
        BSTNode node2 = new BSTNode(2);
        BSTNode node4 = new BSTNode(4);
        BSTNode node5 = new BSTNode(5);
        BSTNode node6 = new BSTNode(6);
        BSTNode node7 = new BSTNode(7);
        node2.left = node1;
        node2.right = node4;
        node6.left = node5;
        node6.right = node7;
        root.left = node2;
        root.right = node6;
        boolean result = checkBST(root);
        Assertions.assertEquals(false, result);
    }
    @Test
    public void test_5() {
        BSTNode root = new BSTNode(4);
        BSTNode node1 = new BSTNode(1);
        BSTNode node2 = new BSTNode(2);
        BSTNode node3 = new BSTNode(2);
        BSTNode node5 = new BSTNode(5);
        BSTNode node6 = new BSTNode(6);
        BSTNode node7 = new BSTNode(7);
        node2.left = node1;
        node2.right = node3;
        node6.left = node5;
        node6.right = node7;
        root.left = node2;
        root.right = node6;
        boolean result = checkBST(root);
        Assertions.assertEquals(false, result);
    }
    boolean checkBST(BSTNode root) {
        System.out.println("started");
        if (root == null)
            return false;
        Set<OrderValue> orderSet = new HashSet<>();
        return checkRecursive(root, orderSet);
    }

    boolean checkRecursive(BSTNode node, final Set<OrderValue> orderSet) {
        boolean result = orderSet.size() == 0
                ? true
                : orderSet.stream()
                .filter(x -> (x.nodeSide == NodeSide.Left && node.data >= x.data)
                        || (x.nodeSide == NodeSide.Right && node.data <= x.data))
                .count() <= 0;

        if (result && node.left != null) {
            if (node.left.data >= node.data) {
                result = false;
            } else {
                result &= this.checkRecursive(node.left, newOrderSet(orderSet, NodeSide.Left, node.data));
            }
        }
        if (result && node.right != null) {
            if (node.right.data <= node.data) {
                result = false;
            } else {
                result &= this.checkRecursive(node.right, newOrderSet(orderSet, NodeSide.Right, node.data));
            }
        }
        return result;
    }

    private Set<OrderValue> newOrderSet(final Set<OrderValue> orderSet, NodeSide nodeSide, int data) {
        Set<OrderValue> newSet = new HashSet<>();
        newSet.addAll(orderSet);
        newSet.add(new OrderValue(data, nodeSide));
        return newSet;
    }
    enum NodeSide {
        Left,
        Right;
    }

    static class OrderValue {
        int data;
        NodeSide nodeSide;

        public OrderValue(int data, NodeSide nodeSide) {
            this.data = data;
            this.nodeSide = nodeSide;
        }
    }
}

class BSTNode {
    int data;
    BSTNode left;
    BSTNode right;

    public BSTNode(int data) {
        this.data = data;
    }
}


