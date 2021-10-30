package com.solutions.hackerrank;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.*;

public class IsBalancedBraceletsTest {
    @Test
    public void test_1() {
        String s = "{[()]}";
        String expected = "YES";
        String result = isBalanced(s);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void test_2() {
        String s = "{[(])} ";
        String expected = "NO";
        String result = isBalanced(s);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void test_3() {
        String s = "{{[[(())]]}} ";
        String expected = "YES";
        String result = isBalanced(s);
        Assertions.assertEquals(expected, result);
    }

    public String isBalanced(String s) {
        // Write your code here
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                case '{':
                case '(':
                case '[':
                    stack.push(s.charAt(i));
                    break;
                case '}':
                    if(checkStack(stack,'{'))
                        stack.pop();
                    else
                        return "NO";
                    break;
                case ')':
                    if(checkStack(stack,'('))
                        stack.pop();
                    else
                        return "NO";
                    break;
                case ']':
                    if(checkStack(stack,'['))
                        stack.pop();
                    else
                        return "NO";
                    break;
            }
        }
        if (stack.size() == 0)
            return "YES";
        else
            return "NO";
    }

    private boolean checkStack(Stack<Character> stack,char c) {
        if (stack.size() == 0)
            return false;
        int maxIndex = stack.size() - 1;
        if (stack.elementAt(maxIndex) != c) {
            return false;
        }
        return true;
    }
}
