package com.solutions.hackerrank;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.*;

//https://www.hackerrank.com/challenges/castle-on-the-grid/problem
public class CastleOnGridTest {
    @Test
    public void test_1() {
        List<String> grid = List.of("...", ".X.", "...");
        int expected = 2;
        int result = minimumMoves(grid, 0, 0, 1, 2);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void test_2() {
        List<String> grid = List.of(".X.", ".X.", "...");
        int expected = 3;
        int result = minimumMoves(grid, 0, 0, 0, 2);
        Assertions.assertEquals(expected, result);
    }

    private static Point goal;
    private static Set<Point> pointVisited;
    private static Set<Point> pointOffered;
    private static Queue<Point> pointQueue;

    public static int minimumMoves(List<String> grid, int startX, int startY, int goalX, int goalY) {
        goal = new Point(goalX, goalY);
        Point start = new Point(startX, startY);
        pointVisited = new HashSet<>();
        pointOffered = new HashSet<>();
        pointQueue = new LinkedList<>();
        pointQueue.offer(start);

        while (!pointQueue.isEmpty()) {
            Point currentPoint = pointQueue.poll();
            pointVisited.add(currentPoint);
            if (isFinished(currentPoint)) {
                return currentPoint.moveCount;
            }
            move(currentPoint, grid);
        }

        return 0;
    }

    private static void move(Point p, List<String> grid) {
        moveRight(p, grid);
        moveLeft(p, grid);
        moveUp(p, grid);
        moveDown(p, grid);
    }

    private static void moveRight(Point p, List<String> grid) {
        Point temp = p;
        boolean loop = true;
        int moveCount = temp.moveCount;
        while (loop) {
            temp = temp.moveRight();
            temp.moveCount = moveCount + 1;
            if (pointVisited.contains(temp))
                return;
            else if (temp.col >= grid.size() || grid.get(temp.row).charAt(temp.col) == 'X') {
                loop = false;
            } else if (!pointOffered.contains(temp)) {
                pointQueue.offer(temp);
                pointOffered.add(temp);
            }
        }
    }

    private static void moveLeft(Point p, List<String> grid) {
        Point temp = p;
        boolean loop = true;
        int moveCount = temp.moveCount;
        while (loop) {
            temp = temp.moveLeft();
            temp.moveCount = moveCount + 1;
            if (pointVisited.contains(temp))
                return;
            else if (temp.col < 0 || grid.get(temp.row).charAt(temp.col) == 'X') {
                loop = false;
            } else if (!pointOffered.contains(temp)) {
                pointQueue.offer(temp);
                pointOffered.add(temp);
            }
        }
    }

    private static void moveUp(Point p, List<String> grid) {
        Point temp = p;
        boolean loop = true;
        int moveCount = temp.moveCount;
        while (loop) {
            temp = temp.moveUp();
            temp.moveCount = moveCount + 1;
            if (pointVisited.contains(temp))
                return;
            else if (temp.row < 0 || grid.get(temp.row).charAt(temp.col) == 'X') {
                loop = false;
            } else if (!pointOffered.contains(temp)) {
                pointQueue.offer(temp);
                pointOffered.add(temp);
            }
        }
    }

    private static void moveDown(Point p, List<String> grid) {
        Point temp = p;
        boolean loop = true;
        int moveCount = temp.moveCount;
        while (loop) {
            temp = temp.moveDown();
            temp.moveCount = moveCount + 1;
            if (pointVisited.contains(temp))
                return;
            else if (temp.row >= grid.size() || grid.get(temp.row).charAt(temp.col) == 'X') {
                loop = false;
            } else if (!pointOffered.contains(temp)) {
                pointQueue.offer(temp);
                pointOffered.add(temp);
            }
        }
    }

    private static boolean isFinished(Point p) {
        return p.row == goal.row && p.col == goal.col;
    }

}

class Point {
    int row;
    int col;
    int moveCount;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;
        Point p = (Point) o;
        return (p.col == this.col && p.row == this.row);
    }

    Point(int row, int col) {
        this.row = row;
        this.col = col;
        this.moveCount = 0;
    }

    Point(int row, int col, int moveCount) {
        this.row = row;
        this.col = col;
        this.moveCount = moveCount;
    }

    public Point moveRight() {
        return new Point(this.row, this.col + 1, this.moveCount);
    }

    public Point moveLeft() {
        return new Point(this.row, this.col - 1, this.moveCount);
    }

    public Point moveUp() {
        return new Point(this.row - 1, this.col, this.moveCount);
    }

    public Point moveDown() {
        return new Point(this.row + 1, this.col, this.moveCount);
    }

}
