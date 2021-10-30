package com.solutions.hackerrank;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.*;

public class ConnectedCellsInGridTest {
    private int e2;

    @Test
    public void test_1() {
        int expected = 5;
        List<List<Integer>> matrix = List.of(
                List.of(1, 1, 0, 0),
                List.of(0, 1, 1, 0),
                List.of(0, 0, 1, 0),
                List.of(1, 0, 0, 0)
        );
        int result = connectedCell(matrix);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void test_2() {
        int expected = 8;
        List<List<Integer>> matrix = List.of(
                List.of(0, 0, 1, 1),
                List.of(0, 0, 1, 0),
                List.of(0, 1, 1, 0),
                List.of(0, 1, 0, 0),
                List.of(1, 1, 0, 0)
        );
        int result = connectedCell(matrix);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void test_3() {
        int expected = 15;
        List<List<Integer>> matrix = List.of(
                List.of(0, 1, 1, 1, 1),
                List.of(1, 0, 0, 0, 1),
                List.of(1, 1, 0, 1, 0),
                List.of(0, 1, 0, 1, 1),
                List.of(0, 1, 1, 1, 0)
        );
        int result = connectedCell(matrix);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void test_4() {
        int expected = 10;
        List<List<Integer>> matrix = List.of(
                List.of(0, 1, 1, 1, 1),
                List.of(0, 0, 0, 0, 1),
                List.of(1, 1, 0, 1, 0),
                List.of(1, 0, 0, 1, 1),
                List.of(1, 0, 1, 1, 0)
        );
        int result = connectedCell(matrix);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void test_5() {
        int expected = 29;
        List<List<Integer>> matrix = List.of(
                List.of(0, e2, 0, 0, 0, 0, 1, 1, 0),
                List.of(1, 1, 0, 0, 1, 0, 0, 0, 1),
                List.of(0, 0, 0, 0, 1, 0, 1, 0, 0),
                List.of(0, 1, 1, 1, 0, 1, 0, 1, 1),
                List.of(0, 1, 1, 1, 0, 0, 1, 1, 0),
                List.of(0, 1, 0, 1, 1, 0, 1, 1, 0),
                List.of(0, 1, 0, 0, 1, 1, 0, 1, 1),
                List.of(1, 0, 1, 1, 1, 1, 0, 0, 0)
        );
        int result = connectedCell(matrix);
        Assertions.assertEquals(expected, result);
    }

    public static int connectedCell(List<List<Integer>> matrix) {
        int maxConnectedCells = 0;
        int[][] arr = new int[matrix.size()][matrix.get(0).size()];
        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.get(i).size(); j++) {
                arr[i][j] = matrix.get(i).get(j);
            }
        }
        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.get(i).size(); j++) {
                if (arr[i][j] == 1) {
                    maxConnectedCells = dfs(arr, i, j, 0, maxConnectedCells);
                }
            }
        }
        return maxConnectedCells;
    }

    private static int dfs(int[][] matrix, int i, int j, int connectedCells, int maxConnectedCells) {
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[i].length || matrix[i][j] == 0) {
            return maxConnectedCells;
        }
        matrix[i][j] = 0;
        connectedCells++;
        maxConnectedCells = Math.max(maxConnectedCells, connectedCells);
        maxConnectedCells = dfs(matrix, i, j - 1, connectedCells, maxConnectedCells); // left
        maxConnectedCells = dfs(matrix, i, j + 1, connectedCells, maxConnectedCells); // right
        maxConnectedCells = dfs(matrix, i - 1, j, connectedCells, maxConnectedCells); // up
        maxConnectedCells = dfs(matrix, i + 1, j, connectedCells, maxConnectedCells); // down
        maxConnectedCells = dfs(matrix, i - 1, j - 1, connectedCells, maxConnectedCells); // left - down
        maxConnectedCells = dfs(matrix, i - 1, j + 1, connectedCells, maxConnectedCells); // right - up
        maxConnectedCells = dfs(matrix, i + 1, j - 1, connectedCells, maxConnectedCells); // left - down
        maxConnectedCells = dfs(matrix, i + 1, j + 1, connectedCells, maxConnectedCells); // right - down
        matrix[i][j] = 1;
        return maxConnectedCells;
    }

    public static int connectedCell2(List<List<Integer>> matrix) {
        int largest = 0;
        int n = matrix.size();
        int m = matrix.get(0).size();
        int[][] dp = new int[n + 2][m + 2];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                int val = matrix.get(i - 1).get(j - 1);
                if (val == 1) {
                    dp[i][j] = getVal(dp, i, j);
                    largest = Math.max(largest, dp[i][j]);
                }
            }
        }
        return largest;
    }

    private static int getVal(int[][] dp, int i, int j) {
        int up = dp[i - 1][j];
        int left = dp[i][j - 1];
        int leftUp = dp[i - 1][j - 1];
        int rightUp = dp[i - 1][j + 1];
        int right = dp[i][j + 1];
        int down = dp[i - 1][j];
        int leftDown = dp[i + 1][j - 1];
        int rightDown = dp[i + 1][j + 1];
        return 1 + Math.max(Math.max(Math.max(up, down), Math.max(left, right)), Math.max(Math.max(leftUp, leftDown), Math.max(rightUp, rightDown)));
    }
}
