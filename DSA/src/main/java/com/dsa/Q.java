package com.dsa;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Q {
    static Stack<Integer> stack = new Stack<>();
    static Queue<Integer> linkedList = new LinkedList<>();
    public static void main(String[] args) {
//        Q q = new Q();
//        q.stackUsingQ_Push(2); //2
//        q.stackUsingQ_Push(3); // 3 2
//        q.stackUsingQ_Push(4); // 4 3 2
//        q.stackUsingQ_Push(5); // 5 4 3 2
//
//        System.out.println("stackUsingQ_Pop: " + stackUsingQ_Pop());
//        System.out.println("stackUsingQ_Pop: " + stackUsingQ_Pop());
//        System.out.println("stackUsingQ_Pop: " + stackUsingQ_Pop());

//        linkedList.add(1);
//        linkedList.add(2);
//        linkedList.add(3);
//        reverseQueueRec(linkedList);
        generateNumber(10);
    }

    private static int firstCircularTour(int[] petrol, int[] distance) {
        int currentCapacity = 0;
        int start = 0;
        int deficit= 0;
        for (int i = 0; i <petrol.length; i++) {
            currentCapacity= currentCapacity + (petrol[i]-distance[i]);

            if(currentCapacity<0) {
                start = i+1;
                deficit = deficit + currentCapacity;
                currentCapacity=0;
            }
        }
        return (currentCapacity + deficit >= 0) ? start : -1;
    }

    private static void generateNumber(int n) {

        Queue<String> q = new LinkedList<>();
        q.add("5");
        q.add("6");
        for (int i = 0; i < n; i++) {
            String curr = q.poll();
            System.out.println(curr);
            q.add(curr+"5");
            q.add(curr+"6");
        }

    }

    private static void reverseQueueRec(Queue<Integer> linkedList) {
        if(linkedList.size()==0) {
            return;
        }
        Integer value = linkedList.remove();
        reverseQueueRec(linkedList);
        System.out.println(value);
        linkedList.add(value);

    }
    private static void reverseQueue(Queue<Integer> linkedList) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i <linkedList.size(); i++) {
            stack.push(linkedList.remove());
        }

        while (stack.size()>0) {
            linkedList.add(stack.pop());

        }
        System.out.println(linkedList);
    }

    private static void stackUsingQ_Push( int x) {
        int size = linkedList.size();

        linkedList.add(x);
        for (int i = 0; i <size; i++) {
            linkedList.add(linkedList.remove());
        }
    }

    private static int stackUsingQ_Pop() {
        return linkedList.remove();

    }
}
