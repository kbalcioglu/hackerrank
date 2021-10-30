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

    public static int minimumMoves(List<String> grid, int startX, int startY, int goalX, int goalY) {
        SearchGrid searchGrid = new SearchGrid(grid,startX,startY,goalX,goalY);
        return searchGrid.search();
    }

}
enum MoveDirection {
    RIGHT,
    LEFT,
    UP,
    DOWN;
}

class SearchGrid {
    private int gridRowCount;
    private int gridColCount;
    private final int[][] grid;
    private final Point start;
    private final Point goal;
    private final Set<Point> pointVisited;
    private final Set<Point> pointOffered;
    private final Queue<Point> pointQueue;
    private boolean isFinished;
    private Point finishedPoint;

    public SearchGrid(List<String> grid, int startX, int startY, int goalX, int goalY) {
        this.grid = generateGrid(grid);
        this.goal = new Point(goalX, goalY);
        this.start = new Point(startX, startY);
        this.pointVisited = new HashSet<>();
        this.pointOffered = new HashSet<>();
        this.pointQueue = new LinkedList<>();
        this.pointQueue.offer(start);
        this.pointOffered.add(start);
    }

    private int parseToInt(String s) {
        if (s.equals("."))
            return 1;
        return 0;
    }

    private boolean checkPointIsEligible(Point p) {
        return p.row < gridRowCount
                && p.col < gridColCount
                && p.row >= 0
                && p.col >= 0
                && this.grid[p.row][p.col] == 1;
    }

    private boolean isFinished(Point currentPoint) {
        return currentPoint != null && currentPoint.row == this.goal.row && currentPoint.col == this.goal.col;
    }

    private int[][] generateGrid(List<String> grid) {
        gridRowCount = grid.size();
        gridColCount = grid.get(0).length();
        int[][] result = new int[gridRowCount][gridColCount];
        for (int i = 0; i < grid.size(); i++) {
            result[i] = Arrays.stream(grid.get(i).split("")).mapToInt(this::parseToInt).toArray();
        }
        return result;
    }

    public int search() {
        while (!pointQueue.isEmpty() && !this.isFinished) {
            Point currentPoint = pointQueue.poll();
            if (isFinished(currentPoint)) {
                this.isFinished = true;
                this.finishedPoint = currentPoint;
                break;
            }
            if (this.pointVisited.contains(currentPoint))
                continue;
            this.pointVisited.add(currentPoint);
            expand(currentPoint);
        }
        if (this.finishedPoint != null)
            return this.finishedPoint.moveCount;
        return 0;
    }

    private Point makeMove(Point p, MoveDirection moveDirection) {
        switch (moveDirection) {
            case DOWN:
                return p.moveDown();
            case RIGHT:
                return p.moveRight();
            case LEFT:
                return p.moveLeft();
            case UP:
                return p.moveUp();
        }
        return null;
    }

    private void checkForMove(Point p, MoveDirection moveDirection) {
        Point newPoint = makeMove(p, moveDirection);
        if (newPoint == null)
            return;
        Point newOfferPoint = newPoint.incrementMoveCount();
        if (!checkPointIsEligible(newOfferPoint)) {
            return;
        }
        if (this.isFinished(newOfferPoint)) {
            this.isFinished = true;
            this.finishedPoint = newOfferPoint;
            return;
        }
        this.offerNewPoint(newOfferPoint);
        this.checkForMove(newPoint, moveDirection);
    }

    private void expand(Point p) {
        for (MoveDirection moveDirection : MoveDirection.values()) {
            checkForMove(p, moveDirection);
        }
    }

    private void offerNewPoint(Point p) {
        if (pointVisited.contains(p))
            return;
        if (pointOffered.contains(p))
            return;
        pointQueue.offer(p);
        pointOffered.add(p);
    }
}

class Point {
    int row;
    int col;
    int moveCount;

    @Override
    public int hashCode() {
        int result = row;
        result = 31 * result + col;
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null)
            return false;
        if (!(other instanceof Point))
            return false;
        Point p = (Point) other;
        //return p.hashCode() == this.hashCode();
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

    public Point incrementMoveCount() {
        return new Point(this.row, this.col, this.moveCount + 1);
    }

}
