package com.solutions.hackerrank;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.*;

public class JourneyToTheMoonTest {
    /*
        https://www.hackerrank.com/challenges/journey-to-the-moon/problem

        he member states of the UN are planning to send 2 people to the moon.
        They want them to be from different countries. You will be given a list of pairs of astronaut ID's.
        Each pair is made of astronauts from the same country.
        Determine how many pairs of astronauts from different countries they can choose from.
        - n => total astronaut count (not size of 2D list)
        - given list is the pairs of same country
        EXAMPLE
            n = 4 ==> this means there are 4 astronauts -> 0,1,2,3
            [[2,3],[1,2]] ==> 1-2,2-3 same country so 1,2,3 are same country. 0 not exists means anouther country
            ==> [0] | [1,2,3] there are 3 possible pairs to send ==> 0-1,0-2,0-3
            return 3
    */
    @Test
    public void test_1() {
        // 3 3 2 1
        int expected = 6;
        int n = 5;
        List<List<Integer>> astronaut = List.of(
                List.of(0, 1),
                List.of(0, 4),
                List.of(2, 3)
        );
        long result = journeyToMoon(n, astronaut);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void test_2() {
        // 3 3 2 1
        int expected = 5;
        int n = 4;
        List<List<Integer>> astronaut = List.of(
                List.of(0, 2)
        );
        long result = journeyToMoon(n, astronaut);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void test_3() {
        // 3 3 2 1
        int expected = 23;
        int n = 10;
        List<List<Integer>> astronaut = List.of(
                List.of(0, 2),
                List.of(1, 8),
                List.of(1, 4),
                List.of(2, 8),
                List.of(2, 6),
                List.of(3, 5),
                List.of(6, 7)
        );
        long result = journeyToMoon(n, astronaut);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void test_4() {
        long expected = 4999949998L;
        int n = 100000;
        List<List<Integer>> astronaut = List.of(
                List.of(1, 2),
                List.of(3, 4)
        );
        // 0,5,6,7,8,9

        long start = System.currentTimeMillis();
        long result = journeyToMoon(n, astronaut);
        long end = System.currentTimeMillis();
        System.out.println("time : " + (end - start));
        Assertions.assertEquals(expected, result);
    }

    public static long journeyToMoon(int n, List<List<Integer>> astronaut) {
        // Write your code here

        // create same country map
        // map.size < 2 => return 0
        // map.size == 2 => return map[1].size x map[2].size
        // map.size > 2 -- discuss

        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(i, new ArrayList<>());
        }
        for (int i = 0; i < astronaut.size(); i++) {
            int city1 = astronaut.get(i).get(0);
            int city2 = astronaut.get(i).get(1);
            if (map.containsKey(city1)) {
                map.get(city1).add(city2);
            }
            if (map.containsKey(city2)) {
                map.get(city2).add(city1);
            }
        }

        List<Integer> comps = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if (map.get(i).size() == 0) {
                comps.add(1);
            } else if (map.get(i).size() > 0 && !visited.contains(i)) {
                comps.add(dfs(map, i, visited));
            }
        }
        Collections.sort(comps, Collections.reverseOrder());
        int c = (int) comps.stream().filter(x -> x > 1).count();
        int r = comps.size() - c;
        long r1 = 0;
        long com = 0;
        if (r > 1) {
            com = combination(r, 2);
        }
        for (int i = 0; i < c; i++) {
            int val = comps.get(i);
            r1 += r * val;
        }
        long r2 = 0;
        for (int i = 0; i < c - 1; i++) {
            int val = comps.get(i);
            for (int j = i + 1; j < c; j++) {
                long c1 = combination(comps.get(i), 1);
                long c2 = combination(comps.get(j), 1);
                r2 += (c1 * c2);

            }
        }
        long count = r1 + r2 + com;

        return count;
    }

    private static int dfs(Map<Integer, List<Integer>> map, int src, Set<Integer> visited) {
        visited.add(src);
        int ans = 1;
        for (int i = 0; i < map.get(src).size(); i++) {
            int val = map.get(src).get(i);
            if (!visited.contains(val)) {
                ans += dfs(map, val, visited);
            }
        }
        return ans;
    }

    private static long combination(int n, int r) {
        if (r == 1 || n == 1) {
            return n;
        }
        if (n == r) {
            return 1;
        }
        int k = n - r; // n-r
        int max = Math.max(k, r);
        int min = Math.min(k, r);
        long ans = 1;
        for (int i = n; i > max; i--) {
            ans = ans * i;
        }
        ans = ans / min;
        return ans;
    }
}
