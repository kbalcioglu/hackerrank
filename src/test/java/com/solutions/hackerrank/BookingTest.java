package com.solutions.hackerrank;

import org.junit.jupiter.api.Test;
import java.util.*;

public class BookingTest {

    @Test
    public void test_1() {
        int[][] area = generateAreaTest1();
        Set<PointB> result = this.findClosestHotels(area, 4, 2, 100, 200, 3);
    }

    private int[][] generateAreaTest1() {
        int[][] area = new int[10][10];
        area[1][1] = 100;
        area[1][4] = 150;
        area[1][2] = 1;
        area[1][3] = 1;
        area[2][2] = 1;
        area[3][2] = 1;
        area[4][3] = 1;
        area[4][4] = 1;
        area[4][5] = 1;
        area[4][6] = 1;
        area[4][7] = 1;
        area[4][8] = 300;
        area[5][3] = 1;
        area[6][3] = 1;
        area[7][3] = 1;
        area[8][3] = 200;
        area[5][3] = 1;
        area[7][4] = 1;
        area[7][5] = 1;
        area[8][5] = 50;
        return area;
    }


    public Set<PointB> findClosestHotels(int[][] area, int startX, int startY, int rangeMin, int rangeMax, int count) {
        Graph graph = new Graph(area, rangeMin, rangeMax, count, startX, startY);
        return graph.getHotels();
    }
}

enum PointType {
    OUTOFBOUND,
    NOTHING,
    ROAD,
    HOTEL
}

enum Direction {
    LEFT,
    RIGHT,
    UP,
    DOWN
}

class Graph {
    private final int[][] grid;
    private final int minRange;
    private final int maxRange;
    private final int hotelCount;
    private Set<PointB> visited;
    private Set<PointB> found;
    private Queue<PointB> queue;
    private Set<Direction> directions;

    Graph(int[][] grid, int minRange, int maxRange, int hotelCount, int startX, int startY) {
        this.grid = grid;
        this.queue = new LinkedList<>();
        queue.offer(new PointB(startX, startY));
        this.maxRange = maxRange;
        this.minRange = minRange;
        this.hotelCount = hotelCount;
        this.visited = new HashSet<>();
        this.found = new HashSet<>();
        this.directions = Set.of(
                Direction.LEFT,
                Direction.RIGHT,
                Direction.UP,
                Direction.DOWN
        );
    }

    private boolean isSearchFinished() {
        return this.queue.isEmpty();
    }

    private PointB getNextPointFromQueue() {
        PointB p = this.queue.poll();
        this.visited.add(p);
        return p;
    }

    private boolean isFinished() {
        return this.found.size() == this.hotelCount;
    }

    private PointType getPointType(PointB point) {
        if (point.col < 0 || point.row < 0 || point.col >= this.grid.length || point.row >= this.grid.length)
            return PointType.OUTOFBOUND;
        int gridVal = this.grid[point.row][point.col];
        if (gridVal == 0)
            return PointType.NOTHING;
        else if (gridVal == 1)
            return PointType.ROAD;
        else
            return PointType.HOTEL;
    }

    private void searchNext(PointB point) {
        for (Direction direction : directions) {
            PointB newPoint = point.move(direction);
            if (!this.visited.contains(newPoint)) {
                PointType pointType = this.getPointType(newPoint);
                switch (pointType) {
                    case ROAD:
                        if (!this.visited.contains(newPoint))
                            this.queue.offer(newPoint);
                        break;
                    case HOTEL:
                        int gridVal = this.grid[newPoint.row][newPoint.col];
                        if (gridVal >= this.minRange && gridVal <= this.maxRange)
                            this.found.add(newPoint.setValue(gridVal));
                        break;
                }
            }
            if (this.isFinished()) {
                this.queue = new LinkedList<>();
                break;
            }
        }
    }

    public Set<PointB> getHotels(){
        while (!this.isSearchFinished()) {
            PointB currentPoint = this.getNextPointFromQueue();
            this.searchNext(currentPoint);
        }
        return this.found;
    }
}

class PointB {
    int row;
    int col;
    int moveCount;
    int value;

    PointB(int row, int col) {
        this.col = col;
        this.row = row;
        this.moveCount = 0;
        this.value = 0;
    }

    private PointB(int row, int col, int moveCount) {
        this.col = col;
        this.row = row;
        this.moveCount = moveCount;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (!(o instanceof PointB)) {
            return false;
        }
        PointB p = (PointB) o;
        return (p.col == this.col && p.row == this.row);
    }
    public PointB setValue(int value){
        this.value = value;
        return this;
    }
    public PointB move(Direction direction) {
        switch (direction) {
            case LEFT:
                return this.moveLeft();
            case RIGHT:
                return this.moveRight();
            case UP:
                return this.moveUp();
            case DOWN:
                return this.moveDown();
        }
        return null;
    }

    private PointB moveLeft() {
        return new PointB(this.row, this.col - 1, this.moveCount + 1);
    }

    private PointB moveRight() {
        return new PointB(this.row, this.col + 1, this.moveCount + 1);
    }

    private PointB moveUp() {
        return new PointB(this.row - 1, this.col, this.moveCount + 1);
    }

    private PointB moveDown() {
        return new PointB(this.row + 1, this.col, this.moveCount + 1);
    }
}