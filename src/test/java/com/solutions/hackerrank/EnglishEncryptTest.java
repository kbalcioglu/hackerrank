package com.solutions.hackerrank;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.*;

public class EnglishEncryptTest {

    @Test
    public void test_1() {
        String s = "if man was meant to stay on the ground god would have given us roots";
        String expected = "imtgdvs fearwer mayoogo anouuio ntnnlvt wttddes aohghn sseoau";
        String result = encryption(s);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void test_2() {
        String s = "have a nice day";
        String expected = "hae and via ecy";
        String result = encryption(s);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void test_3() {
        String s = "feed the dog";
        String expected = "fto ehg ee dd";
        String result = encryption(s);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void test_4() {
        String s = "chill out";
        String expected = "clu hlt io";
        String result = encryption(s);
        Assertions.assertEquals(expected, result);
    }

    public String encryption(String s) {
        // Write your code here
        s = s.replaceAll(" ", "");
        StringBuilder sb = new StringBuilder();
        int n = s.length();
        double sqr = Math.sqrt(n);
        int x = (int) Math.floor(sqr);
        int y = (int) Math.ceil(sqr);
        while ((x * y) < n) {
            x++;
        }
        char[][] grid = new char[x][y];
        int index = 0;
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                index = (i * y) + j;
                if (index < s.length()) {
                    grid[i][j] = s.charAt(index);
                } else {
                    grid[i][j] = ' ';
                }
            }
        }
        for (int j = 0; j < y; j++) {
            for (int i = 0; i < x; i++) {
                if (grid[i][j] != ' ') {
                    sb.append(grid[i][j]);
                }
            }
            sb.append(" ");
        }

        return sb.toString().trim();
    }

}
