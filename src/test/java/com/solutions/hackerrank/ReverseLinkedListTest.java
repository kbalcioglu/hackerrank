package com.solutions.hackerrank;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.*;

public class ReverseLinkedListTest {
    public static void main(String[] args){

        SinglyLinkedListNode root = createTestData1();
        SinglyLinkedListNode result = reverse(root);
    }

    private static SinglyLinkedListNode createTestData1(){
        SinglyLinkedListNode node = null;
        for (int i = 5; i >= 1; i--) {
            node = addNode(i,node);
        }
        return node;
    }
    private static SinglyLinkedListNode addNode(int data, SinglyLinkedListNode prevNode){
        SinglyLinkedListNode node = new SinglyLinkedListNode();
        node.data = data;
        if(prevNode == null){
            prevNode = node;
        }
        else{
            node.next = prevNode;
            prevNode = node;
        }
        return prevNode;
    }
    public static SinglyLinkedListNode reverse(SinglyLinkedListNode llist) {
        SinglyLinkedListNode result = null;
        while (llist != null){
            result = addNode(llist.data,result);
            llist = llist.next;
        }
        return result;
    }
}

class SinglyLinkedListNode {
    int data;
    SinglyLinkedListNode next;

    SinglyLinkedListNode() {
    }

    SinglyLinkedListNode(int data) {
        this.data = data;
    }

    SinglyLinkedListNode(int data, SinglyLinkedListNode next) {
        this.data = data;
        this.next = next;
    }
}
