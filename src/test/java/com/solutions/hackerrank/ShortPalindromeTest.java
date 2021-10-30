package com.solutions.hackerrank;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.*;

public class ShortPalindromeTest {

    /*
        https://www.hackerrank.com/challenges/short-palindrome/problem

        Consider a string, s, of n lowercase English letters where each character, s[i] (0<=i<n), denotes the letter at index  in .
        We define an (a,b,c,d) palindromic tuple of s to be a sequence of indices in s satisfying the following criteria:
        s[a] == s[d], meaning the characters located at indices  and  are the same.
        s[b] == s[c], meaning the characters located at indices  and  are the same.
        0<=a<b<c<d<n , meaning that a, b, c, and d are ascending in value and are valid indices within string s.
        Given s, find and print the number of (a,b,c,d) tuples satisfying the above conditions.
        As this value can be quite large, print it mod (10^9 + 7).
    */
    @Test
    public void test_1() {
        String s = "kkkkkkz";
        int expected = 15;
        int result = shortPalindrome(s);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void test_2() {
        String s = "ghhggh";
        int expected = 4;
        int result = shortPalindrome(s);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void test_3() {
        String s = "abbaab";
        int expected = 4;
        int result = shortPalindrome(s);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void test_4() {
        String s = "akakak";
        int expected = 2;
        int result = shortPalindrome(s);
        Assertions.assertEquals(expected, result);
    }

    public static int shortPalindrome(String s) {
        // palindrom length 4
        // p[0] == p[3] && p[1] == p[2]
        // mod (10^9 + 7)
        // Write your code here

        long mod = (long)Math.pow(10,9) + 7;
        long total = 0;
        int n = s.length();
        if(n<=3)
            return 0;
        if(n==4){
            if(s.charAt(0) == s.charAt(3) && s.charAt(1) == s.charAt(2))
                return 1;
            return 0;
        }
        // ? dynamic programming


        return (int)(total % mod);
    }
    private boolean isPalindrome(String k){
        if(k.length()==4){
            if(k.charAt(0) == k.charAt(3) && k.charAt(1) == k.charAt(2))
                return true;
        }
        return false;
    }
}
