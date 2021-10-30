package com.solutions.hackerrank;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.util.*;

public class StringSimilarityTest {

    @Test
    public void test_1() {
        String s = "ababaa";
        long expected = 11;
        long result = stringSimilarity(s);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void test_2() {
        String s = "aa";
        long expected = 3;
        long result = stringSimilarity(s);
        Assertions.assertEquals(expected, result);
    }

    public static long stringSimilarity2(String s) {
        StringBuilder sbS = new StringBuilder(s);
        long ans = s.length();
        while (sbS.length() > 0) {
            sbS.deleteCharAt(0);
            int i;
            for (i = 0; i < sbS.length(); i++) {
                if (s.charAt(i) != sbS.charAt(i))
                    break;
            }
            ans += i;
        }
        return ans;
    }

    public static long stringSimilarity3(String s) {
        long ans = 0;
        StringBuilder sb = new StringBuilder();
        Set<String> suffixSet = new HashSet<>();
        for (int i = s.length() - 1; i >= 0; i--) {
            sb.insert(0, s.charAt(i));
            suffixSet.add(sb.toString());
        }
        sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            sb.append(s.charAt(i));
            if (suffixSet.contains(sb.toString())) {
                ans += sb.length();
            }
        }
        return ans;
    }

    public static long stringSimilarity(String str) {
        // Write your code here
        long c = str.length();
        int L = 0, R = 0, n = str.length();
        char[] s = str.toCharArray();
        int[] z = new int[n];

        for (int i = 1; i < n; i++) {
            if (i > R) {
                L = R = i;
                while (R < n && s[R - L] == s[R]) R++;
                z[i] = R - L;
                R--;
                c += z[i];
            } else {
                int k = i - L;
                if (z[k] < R - i + 1) {
                    z[i] = z[k];
                    c += z[i];
                } else {
                    L = i;
                    while (R < n && s[R - L] == s[R]) R++;
                    z[i] = R - L;
                    c += z[i];
                    R--;
                }
            }
        }
        return c;
    }
}
