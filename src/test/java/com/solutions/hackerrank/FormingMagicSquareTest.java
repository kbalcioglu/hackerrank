package com.solutions.hackerrank;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.*;

public class FormingMagicSquareTest {
    @Test
    public void test_1() {
        int[][] s = {{5, 3, 4}, {1, 5, 8}, {6, 4, 2}};
        int expected = 7;
        int result = this.formingMagicSquare(s);
        assertion(expected, result);
    }

    @Test
    public void test_2() {
        int[][] s = {{4, 9, 2}, {3, 5, 7}, {8, 1, 5}};
        int expected = 1;
        int result = this.formingMagicSquare(s);
        assertion(expected, result);
    }

    @Test
    public void test_3() {
        int[][] s = {{4, 8, 2}, {4, 5, 7}, {6, 1, 6}};
        int expected = 4;
        int result = this.formingMagicSquare(s);
        assertion(expected, result);
    }

    @Test
    public void test_4() {
        int[][] s = {{4, 9, 2}, {3, 5, 7}, {8, 1, 6}};
        int expected = 0;
        int result = this.formingMagicSquare(s);
        assertion(expected, result);
    }

    private void assertion(int expected, int result) {
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void test_isMagical_True() {
        int[][] s = {{4, 9, 2}, {3, 5, 7}, {8, 1, 6}};
        boolean result = isMagicalSquare(s, 15);
        Assertions.assertEquals(true, result);
    }

    @Test
    public void test_isMagical_False() {
        int[][] s = {{4, 9, 2}, {3, 5, 7}, {8, 1, 5}};
        boolean result = isMagicalSquare(s, 15);
        Assertions.assertEquals(false, result);
    }

    private static int minCost;

    public int formingMagicSquare(int[][] s) {
        minCost = Integer.MAX_VALUE;
        // Write your code here
        int[] digits = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[][] target = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        int currentIndex = 0;
        int expectedSum = 15;
        if (isMagicalSquare(s, expectedSum))
            return 0;
        generatePossibleSquares(target, s, currentIndex, digits, expectedSum);
        return minCost;
    }

    private void generatePossibleSquares(int[][] target, int[][] s, int currentIndex, int[] digits, int expectedSum) {
        if (currentIndex > digits.length)
            return;
        if (currentIndex == digits.length) {
            int index = 0;
            int currentCost = 0;
            for (int i = 0; i < s.length; i++) {
                for (int j = 0; j < s.length; j++) {
                    target[i][j] = digits[index++];
                    currentCost += Math.abs(target[i][j] - s[i][j]);
                }
            }
            if (isMagicalSquare(target, expectedSum)) {
                minCost = Math.min(minCost, currentCost);
            }
        }
        for (int i = currentIndex; i < digits.length; i++) {
            int temp = digits[i];
            digits[i] = digits[currentIndex];
            digits[currentIndex] = temp;

            generatePossibleSquares(target, s, currentIndex + 1, digits, expectedSum);

            temp = digits[i];
            digits[i] = digits[currentIndex];
            digits[currentIndex] = temp;
        }
    }
    //

    private boolean isMagicalSquare(int[][] s, int expectedSum) {
        for (int i = 0; i < s.length; i++) {
            int rowSum = 0;
            int colSum = 0;
            for (int j = 0; j < s.length; j++) {
                rowSum += s[i][j];
                colSum += s[j][i];
            }
            if (rowSum != expectedSum)
                return false;
            if (colSum != expectedSum)
                return false;
        }
        int leftDiagonalSum = 0;
        int rightDiagonalSum = 0;
        int i = 0, j = 0, k = s.length - 1;
        while (i < s.length && j < s.length && k >= 0) {
            leftDiagonalSum += s[i][j];
            rightDiagonalSum += s[i][k];
            i++;
            j++;
            k--;
        }
        if (leftDiagonalSum != expectedSum)
            return false;
        if (rightDiagonalSum != expectedSum)
            return false;

        return true;
    }

    // dfs generate all possible squares
    // isMagic
    // if(isMagic) -> calculate min cost;
    /*
    private int calcCost(int a, int b) {
        return Math.abs(a - b);
    }

    Set<Integer> mSet = new HashSet<>();
    Map<Integer, Integer> fMap = new HashMap<>();
    Map<Integer, Integer> colTotalMap = new HashMap<>();
    Map<Integer, Integer> rowTotalMap = new HashMap<>();

    private void analyzeSquare(List<List<Integer>> s) {
        int colTotal0 = 0;
        int colTotal1 = 0;
        int colTotal2 = 0;
        for (int i = 0; i < s.size(); i++) {
            List<Integer> row = s.get(i);
            int rowTotal = 0;
            for (int j = 0; j < s.size(); j++) {
                int key = row.get(j);
                switch (j) {
                    case 0:
                        colTotal0 += key;
                        break;
                    case 1:
                        colTotal1 += key;
                        break;
                    case 2:
                        colTotal2 += key;
                        break;
                }
                rowTotal += key;
                int v = 0;
                if (fMap.containsKey(key)) {
                    v = fMap.get(key);
                    fMap.remove(key);
                }
                v++;
                fMap.put(row.get(j), v);
            }
            rowTotalMap.put(i, rowTotal);
        }
        colTotalMap.put(0, colTotal0);
        colTotalMap.put(1, colTotal1);
        colTotalMap.put(2, colTotal2);
        for (int d : digits) {
            if (fMap.containsKey(d))
                continue;
            mSet.add(d);
        }
    }

    private int getCountRow(int index) {
        return rowTotalMap.get(index);
    }

    private int getCountCol(int index) {
        return colTotalMap.get(index);
    }

     */
}
