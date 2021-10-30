package com.solutions.hackerrank;
/*
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class GenaPlayingHanoiTest {
    @Test
    public void test_1() {
        List<Integer> posts = List.of(4, 3, 2, 1);
        int expected = 3;
        int result = this.hanoi(posts);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void test_2() {
        List<Integer> posts = List.of(4, 3, 2, 1, 1, 1, 2);
        int expected = 3;
        int result = this.hanoi(posts);
        Assertions.assertEquals(expected, result);
    }

    static Stack<Integer>[] rods;
    static int discCount;

    public static int hanoi(List<Integer> posts) {
        int minMoveCount = 0;
        discCount = posts.size();
        rods = new Stack[4];
        for (int i = 0; i < 4; i++) {
            rods[i] = new Stack<>();
        }
        int rodNumberMax = posts.get(posts.size() - 1);
        for (int i = posts.size() - 1; i >= 0; i--) {
            int val = posts.get(i);
            rods[val].push(i);
        }
        Queue<Integer> queue = new LinkedList<>();
        while (!queue.isEmpty() || !isFinished()) {

        }
        // Write your code here
        return minMoveCount;
    }

    private static boolean isFinished() {
        return rods[0].size() == discCount;
    }
}
*/