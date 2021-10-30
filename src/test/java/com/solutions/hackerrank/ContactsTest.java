package com.solutions.hackerrank;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.*;

public class ContactsTest {
    @Test
    public void test_1() {
        List<List<String>> queries = List.of(
                List.of("add", "ed"),
                List.of("add", "eddie"),
                List.of("add", "edward"),
                List.of("find", "ed"),
                List.of("add", "edwina"),
                List.of("find", "edw"),
                List.of("find", "a")
        );

        List<Integer> expected = List.of(3, 2, 0);
        List<Integer> results = contacts(queries);
        Assertions.assertEquals(expected.size(), results.size());
        for (int i = 0; i < results.size(); i++) {
            Assertions.assertEquals(expected.get(i), results.get(i));
        }
    }

    public static List<Integer> contacts(List<List<String>> queries) {
        List<Integer> results = new ArrayList<>();
        TrieNode contacts = new TrieNode();
        for (List<String> ops : queries) {
            switch (ops.get(0)) {
                case "add":
                    add(contacts,ops.get(1));
                    break;
                case "find":
                    results.add(find(contacts,ops.get(1)));
                    break;
            }
        }
        return results;
    }

    public static void add(TrieNode contacts, String contact) {

        TrieNode temp = contacts;
        temp.count++;

        for (char c : contact.toCharArray()) {
            // like mode 26 gets the index between 0-25
            int index = c - 'a';
            if (temp.trieNode[index] != null) {
                temp = temp.trieNode[index];
            } else {
                temp.trieNode[index] = new TrieNode();
                temp = temp.trieNode[index];
            }
            temp.count++;
        }

    }

    public static int find(TrieNode contacts, String contact) {

        TrieNode temp = contacts;

        for (char c : contact.toCharArray()) {

            int index = c - 'a';
            if (temp.trieNode[index] != null) {
                temp = temp.trieNode[index];
            } else {
                return 0;
            }

        }
        return temp.count;
    }
}

class TrieNode {
    int count = 0;
    // 26 english chars lower case
    TrieNode[] trieNode = new TrieNode[26];
}