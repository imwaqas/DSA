package com.dsa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Stacks {

    public static void main(String[] args) {

//        System.out.println("balanceParenthesis: " + balanceParenthesis("{[]}"));
//        int[] arr = {15, 13, 12, 14, 16, 8, 6, 4, 10, 30};
//        stockSpan(arr);
//        System.out.println("stockSpan: " + stockSpan(arr);
//        int[] arr={15, 10, 18, 12, 4, 6, 2, 8};
//        nextGreater(arr);
        int arr[] = { 6, 2, 5, 4, 5, 1, 6 };
        System.out.println("largestRectangularArea: "+ largestRectangularArea(arr));
    }

    private static int largestRectangularArea(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        int max_area = 0;
        int i =0;
        int top;
        int area;
        while(i<arr.length) {
            if (stack.isEmpty() || arr[stack.peek()] <= arr[i]) {
                stack.push(i);
                i++;
            }
            else {
                 top = stack.pop();
                 area = arr[top]*(stack.isEmpty() ? i : i - stack.peek() - 1);
                if (area > max_area) {
                    max_area = area;
                }
            }
        }

        while (!stack.isEmpty()) {
            top = stack.pop();
            area = arr[top]*(stack.isEmpty() ? i : i - stack.peek() - 1);
            if (area > max_area) {
                max_area = area;
            }
        }
        return max_area;
    }

    private static void previousGreater(int[] arr) {
//        i/p:15 10 18 12 4  6  2 8
//            -1 15 -1 18 12 12 6 12

        Stack<Integer> stack = new Stack<>();
        System.out.println(-1);
        stack.push(arr[0]);

        for (int i = 1; i <arr.length; i++) {

            while (!stack.isEmpty() && stack.peek()< arr[i]) {
                stack.pop();
            }
            int previousGreater = stack.isEmpty() ? -1 : stack.peek();
            System.out.println(previousGreater);
            stack.push(arr[i]);
        }
    }

    private static void nextGreater(int[] arr) {
//        i/p:15 10 18 12 4  6  2 8
//          [18, 18, -1, -1, 6, 8, 8, 8]

        Stack<Integer> stack = new Stack<>();
        int n = arr.length;
//        System.out.println(-1);
        stack.push(arr[n-1]);
        List<Integer> list = new ArrayList<>();
//        list.add(arr[n-1]);

        for (int i = n-1; i >=0; i--) {

            while (!stack.isEmpty() && stack.peek()< arr[i]) {
                stack.pop();
            }
            int nextGreater = stack.isEmpty() ? -1 : stack.peek();
            list.add(nextGreater);
//            System.out.println(nextGreater);
            stack.push(arr[i]);
        }
        Collections.reverse(list);
        System.out.println(list);
    }

    //O(n)
    private static void stockSpan(int[] arr) {
//        int[] arr = {15, 13, 12, 14, 16, 8, 6, 4, 10, 30};
        Stack<Integer> stack = new Stack<>();
        stack.push(0);

        for (int i = 1; i <arr.length ; i++) {

            while(!stack.isEmpty() && arr[stack.peek()] <= arr[i]) {
                stack.pop();
            }
            int span = stack.isEmpty() ? i+1 : i-stack.peek();
            System.out.println("span: " + span);
            stack.push(i);
        }
    }

    private static boolean balanceParenthesis(String str) {

        Stack<Character> stack = new Stack<>();

        char[] charArray = str.toCharArray();

        for (int i = 0; i <charArray.length; i++) {
            if(charArray[i] == '[' || charArray[i] == '{' || charArray[i] == '(') {
                stack.push(charArray[i]);
            } else {
                if(stack.isEmpty() || !getMatched(charArray[i], stack.pop())) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    private static boolean getMatched(char b, char a) {
        return (a=='[' && b == ']') || (a=='{' && b == '}') || (a=='(' && b == ')');
    }
}
