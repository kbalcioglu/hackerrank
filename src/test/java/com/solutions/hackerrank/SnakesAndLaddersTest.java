package com.solutions.hackerrank;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.util.*;

public class SnakesAndLaddersTest {
    /*
        https://www.hackerrank.com/challenges/the-quickest-way-up/problem
        Markov takes out his Snakes and Ladders game, stares at the board and wonders:
        "If I can always roll the die to whatever number I want, what would be the least number of rolls to reach the destination?"

        Rules The game is played with a cubic dice of 6 faces numbered 1 to 6.
        Starting from square 1, land on square 100 with the exact roll of the die.
        If moving the number rolled would place the player beyond square 100, no move is made.
        If a player lands at the base of a ladder, the player must climb the ladder.
        Ladders go up only.

        If a player lands at the mouth of a snake, the player must go down the snake and come out through the tail.
        Snakes go down only.
    */
    @Test
    public void test_1() {
        List<List<Integer>> ladders = List.of(
                List.of(32, 62),
                List.of(42, 68),
                List.of(12, 98)
        );
        List<List<Integer>> snakes = List.of(
                List.of(95, 13),
                List.of(97, 25),
                List.of(93, 37),
                List.of(79, 27),
                List.of(75, 19),
                List.of(49, 47),
                List.of(67, 17)
        );
        long expected = 3;
        long result = quickestWayUp(ladders, snakes);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void test_2() {
        List<List<Integer>> ladders = List.of(
                List.of(8, 52),
                List.of(6, 80),
                List.of(26, 42),
                List.of(2, 72)
        );
        List<List<Integer>> snakes = List.of(
                List.of(51, 19),
                List.of(39, 11),
                List.of(37, 29),
                List.of(81, 3),
                List.of(59, 5),
                List.of(79, 23),
                List.of(43, 33),
                List.of(77, 21)
        );
        long expected = 5;
        long result = quickestWayUp(ladders, snakes);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void test_3() {
        List<List<Integer>> ladders = List.of(
                List.of(3, 54),
                List.of(37, 100)
        );
        List<List<Integer>> snakes = List.of(
                List.of(56, 33)
        );
        long expected = 3;
        long result = quickestWayUp(ladders, snakes);
        Assertions.assertEquals(expected, result);
    }

    // BFS or DFS or DP
    public static int quickestWayUp(List<List<Integer>> ladders, List<List<Integer>> snakes) {
        // Write your code here
        int[] dp = new int[101];
        dp[100] = -1;
        for (int i = 0; i < ladders.size(); i++) {
            dp[ladders.get(i).get(0)] = (-1) * ladders.get(i).get(1);
        }
        for (int i = 0; i < snakes.size(); i++) {
            dp[snakes.get(i).get(0)] = (-1) * snakes.get(i).get(1);
        }

        Set<Integer> visited = new HashSet<>();
        Set<Integer> offered = new HashSet<>();
        Queue<Integer> q = new LinkedList<>();
        for (int i = 2; i < 8; i++) {
            if (dp[i] == 0) {
                dp[i] = 1;
                q.offer(i);
                offered.add(i);
            } else {
                dp[Math.abs(dp[i])] = 1;
                q.offer(Math.abs(dp[i]));
                offered.add(i);
                offered.add(Math.abs(dp[i]));
            }
        }
        boolean loop = true;
        while (!q.isEmpty() && loop) {
            int v = q.poll();
            int dpVal = dp[v];
            visited.add(v);

            for (int i = 1; i <= 6; i++) {
                int next = v + i;

                if(offered.contains(next))
                    continue;
                if (next >= 100) {
                    dp[100] = dpVal + 1;
                    loop = false;
                    break;
                } else if (dp[next] < 0 && !visited.contains(Math.abs(dp[next]))) {
                    dp[Math.abs(dp[next])] = dpVal + 1;
                    if(Math.abs(dp[next]) == 100){
                        loop = false;
                        break;
                    }
                    offered.add(next);
                    offered.add(Math.abs(dp[next]));
                    q.offer(Math.abs(dp[next]));
                } else {
                    dp[next] = dpVal + 1;
                    q.offer(next);
                    offered.add(next);
                }
            }
        }
        return dp[100];
    }

}
