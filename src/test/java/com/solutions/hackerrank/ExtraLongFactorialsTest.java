package com.solutions.hackerrank;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.*;

public class ExtraLongFactorialsTest {
    @Test
    public void test_1() {
        int n = 30;
        BigInteger expected = new BigInteger("265252859812191058636308480000000");
        BigInteger result = this.extraLongFactorials(n);
        Assertions.assertEquals(expected, result);
    }

    public static BigInteger extraLongFactorials(int n) {
        // Write your code here
        BigInteger result = new BigInteger("1");
        for (int i = 1; i <= n; i++) {
            result = result.multiply(new BigInteger(String.valueOf(i)));
        }
        System.out.println(result);
        return result;
    }
}
