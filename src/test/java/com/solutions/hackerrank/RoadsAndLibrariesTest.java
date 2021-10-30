package com.solutions.hackerrank;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.*;

public class RoadsAndLibrariesTest {
    /*
        https://www.hackerrank.com/challenges/torque-and-development/problem

        Determine the minimum cost to provide library access to all citizens of HackerLand.
        There are n cities numbered from 1 to n. Currently there are no libraries and the cities are not connected.
        Bidirectional roads may be built between any city pair listed in cities.
        A citizen has access to a library if:
            - Their city contains a library.
            - They can travel by road from their city to a city containing a library.
    */
    @Test
    public void test_1() {
        // 3 3 2 1
        long expected = 4;
        List<List<Integer>> possibleRoads = List.of(
                List.of(1, 2),
                List.of(3, 1),
                List.of(2, 3)
        );
        long result = roadsAndLibraries(3, 2, 1, possibleRoads);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void test_2() {
        // 6 6 2 5
        long expected = 12;
        List<List<Integer>> possibleRoads = List.of(
                List.of(1, 3),
                List.of(3, 4),
                List.of(2, 4),
                List.of(1, 2),
                List.of(2, 3),
                List.of(5, 6)
        );
        long result = roadsAndLibraries(6, 2, 5, possibleRoads);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void test_3() {
        // 5 3 6 1
        long expected = 15;
        List<List<Integer>> possibleRoads = List.of(
                List.of(1, 2),
                List.of(1, 3),
                List.of(1, 4)
        );
        long result = roadsAndLibraries(5, 6, 1, possibleRoads);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void test_4() {
        // 9 2 91 84
        long expected = 805;
        List<List<Integer>> possibleRoads = List.of(
                List.of(8, 2),
                List.of(2, 9)
        );
        long result = roadsAndLibraries(9, 91, 84, possibleRoads);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void test_5() {
        // 5 9 92 23
        long expected = 184;
        List<List<Integer>> possibleRoads = List.of(
                List.of(2, 1),
                List.of(5, 3),
                List.of(5, 1),
                List.of(3, 4),
                List.of(3, 1),
                List.of(5, 4),
                List.of(4, 1),
                List.of(5, 2),
                List.of(4, 2)
        );
        long result = roadsAndLibraries(5, 92, 23, possibleRoads);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void test_6() {
        // 5 9 92 23
        long expected = 35;
        List<List<Integer>> possibleRoads = List.of(
                List.of(1, 2),
                List.of(13, 14),
                List.of(3, 4),
                List.of(10, 11),
                List.of(1, 3),
                List.of(6, 7),
                List.of(9, 10),
                List.of(12, 10),
                List.of(5, 3),
                List.of(3, 13)
        );
        long result = roadsAndLibraries(15, 3, 2, possibleRoads);
        Assertions.assertEquals(expected, result);
    }
    public static long roadsAndLibraries(int n, int c_lib, int c_road, List<List<Integer>> possibleRoads) {
        // 1-  convert into graph
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            map.put(i, new ArrayList<>());
        }
        for (int i = 0; i < possibleRoads.size(); i++) {
            int city1 = possibleRoads.get(i).get(0);
            int city2 = possibleRoads.get(i).get(1);
            if (map.containsKey(city1)) {
                map.get(city1).add(city2);
            }
            if (map.containsKey(city2)) {
                map.get(city2).add(city1);
            }
        }

        List<Integer> comps = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();

        for (int i = 1; i <= n; i++) {
            if (map.get(i).size() == 0) {
                comps.add(1);
            } else if (map.get(i).size() > 0 && !visited.contains(i)) {
                comps.add(dfs(map, i, visited));
            }
        }
        long ans = 0;
        for (int i = 0; i < comps.size(); i++) {

            ans += Math.min((comps.get(i) - 1) * c_road + c_lib, comps.get(i) * c_lib);
        }
        return ans;
    }

    private static int dfs(Map<Integer, List<Integer>> map, int src, Set<Integer> visited) {
        visited.add(src);
        int ans = 1;
        for (int i = 0; i < map.get(src).size(); i++) {
            int val = map.get(src).get(i);
            if (val > 0 && !visited.contains(val)) {
                ans += dfs(map, val, visited);
            }
        }
        return ans;
    }
}
