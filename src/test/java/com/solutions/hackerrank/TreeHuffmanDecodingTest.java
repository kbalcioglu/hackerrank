package com.solutions.hackerrank;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.*;

public class TreeHuffmanDecodingTest {
    @Test
    public void test_1() {
        String s = "1001011";
        Node root = generateTest1();
        String expected = "ABACA";
        String result = decode(s, root);
        Assertions.assertEquals(expected, result);
    }

    private StringBuilder sb;

    public String decode(String s, Node root) {
        sb = new StringBuilder();
        List<Character> charSeq = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            charSeq.add(c);
            charSeq = execute(charSeq, root);
        }

        return sb.toString();
    }

    public List<Character> execute(List<Character> charSeq, Node root) {
        Node temp = root;
        for (char s : charSeq) {
            if (temp == null) {
                break;
            }
            if (isRight(s)) {
                temp = temp.right;
            } else {
                temp = temp.left;
            }
        }
        if (isLeaf(temp)) {
            sb.append(temp.data);
            charSeq = new ArrayList<>();
        }
        return charSeq;
    }

    private boolean isLeaf(Node node) {
        return !(node.data == 0);
    }

    private boolean isRight(char c) {
        return c == '1';
    }

    private Node generateTest1() {
        Node left = new Node();
        left.frequency = 1;
        left.data = 'B';

        Node right = new Node();
        right.frequency = 1;
        right.data = 'C';

        Node x = new Node();
        x.frequency = 2;
        x.left = left;
        x.right = right;

        Node right2 = new Node();
        right2.frequency = 3;
        right2.data = 'A';

        Node y = new Node();
        y.frequency = 5;
        y.left = x;
        y.right = right2;
        return y;
    }
}

class Node {
    public int frequency; // the frequency of this tree
    public char data;
    public Node left, right;
}


