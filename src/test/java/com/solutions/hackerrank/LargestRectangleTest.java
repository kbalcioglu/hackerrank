package com.solutions.hackerrank;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.*;

public class LargestRectangleTest {
    @Test
    public void test_1() {
        List<Integer> h = new ArrayList<>();
        h.add(1);
        h.add(2);
        h.add(3);
        h.add(4);
        h.add(5);
        long expected = 9;
        long result = largestRectangle(h);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void test_2() {
        List<Integer> h = new ArrayList<>();
        h.add(1);
        h.add(3);
        h.add(5);
        h.add(9);
        h.add(11);
        long expected = 18;
        long result = largestRectangle(h);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void test_3() {
        List<Integer> h = new ArrayList<>();
        h.add(8979);
        h.add(4570);
        h.add(6436);
        h.add(5083);
        h.add(7780);
        h.add(3269);
        h.add(5400);
        h.add(7579);
        h.add(2324);
        h.add(2116);
        long expected = 26152;
        long result = largestRectangle(h);
        Assertions.assertEquals(expected, result);
    }

    public static long largestRectangle2(List<Integer> h) {
        // Write your code here
        //Collections.sort(h);
        //h.add(0);
        //Stack<Integer> stack = new Stack<>();
        long maxArea = 0;
        for (int i = 0; i < h.size(); i++) {
            int maxHeight = h.get(i);
            int left = expandToLeft(i, h, maxHeight);
            int right = expandToRight(i, h, maxHeight);

            int len = right - left + 1;
            maxArea = Math.max(maxArea, len * maxHeight);
        }

        return maxArea;
    }

    private static int expandToLeft(int i, List<Integer> h, int maxHeight) {
        if (i <= 0) {
            return i;
        }
        i--;
        int val = h.get(i);
        if (maxHeight > val) {
            i++;
        } else {
            i = expandToLeft(i, h, maxHeight);
        }
        return i;
    }

    private static int expandToRight(int i, List<Integer> h, int maxHeight) {
        if (i >= h.size()-1) {
            return i;
        }
        i++;
        int val = h.get(i);
        if (maxHeight > val) {
            i--;
        } else {
            i = expandToRight(i, h, maxHeight);
        }
        return i;
    }


    static int largestRectangle(List<Integer> h)
    {
        Stack<Integer> s = new Stack<>();

        int max_area = 0;
        int tp;
        int area_with_top;

        int i = 0;
        while (i < h.size())
        {
            if (s.empty() || h.get(s.peek()) <= h.get(i))
                s.push(i++);
            else
            {
                tp = s.peek();
                s.pop();
                area_with_top = h.get(tp) * (s.empty() ? i : i - s.peek() - 1);

                if (max_area < area_with_top)
                    max_area = area_with_top;
            }
        }
        while (s.empty() == false)
        {
            tp = s.peek();
            s.pop();
            area_with_top = h.get(tp) * (s.empty() ? i : i - s.peek() - 1);

            if (max_area < area_with_top)
                max_area = area_with_top;
        }

        return max_area;

    }
}
