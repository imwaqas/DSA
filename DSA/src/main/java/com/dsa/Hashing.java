package com.dsa;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Hashing {
    public static void main(String[] args) {

//        int[] arr = {3,3,5,5,5,5,13,17};
//        System.out.println("givenSum: " + givenSum(arr, 30));
//        System.out.println("largestSubArrayWithGivenSum: " + largestSubArrayWithGivenSum(arr, 20));
//        int[] arr =  {15, -2, 2, -8, 1, 7, 10, 23 };
//        System.out.println("largestSubArrayWithZeroSum: " + largestSubArrayWithZeroSum(arr));

//        int[] arr =  {1, 1, 1, 1, 0, 1, 0, 1};
//        System.out.println("binaryArray: " + binaryArray(arr));
        int[] arr =  {5,5,7,4,7,9};
        System.out.println("findPair: " + findPair(arr, 21));
    }

    private static boolean findPair(int[] arr, int x) {

        Set<Integer> set = new HashSet<>();

        for (int i = 0; i <arr.length; i++) {
            if(set.contains(x-arr[i])) {
                return true;
            } else {
                set.add(arr[i]);
            }
        }
        return false;
    }

    private static int binaryArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                arr[i] = -1;
            }
        }
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (sum == 0) {
                res= i + 1;
            }
            if (map.containsKey(sum)) {
                res = Math.max(res, i-map.get(sum));
            } else {
                map.put(sum, i);
            }
        }
        return res;
    }

    private static int largestSubArrayWithZeroSum(int[] arr) {

        int res = 0;
        int prefixSum = 0;
        int start = 0;
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i <arr.length; i++) {
            prefixSum += arr[i];

            if(prefixSum==0) {
                res = i-start+1;
            }

            if(map.containsKey(prefixSum)) {
                res = Math.max(res, i - map.get(prefixSum));
            } else {
                map.put(prefixSum, i);
            }
        }
        return res;
    }

    private static int largestSubArrayWithGivenSum(int[] arr, int sum) {

        int res = 0;
        int prefixSum = 0;
        int start = 0;
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i <arr.length; i++) {
            prefixSum += arr[i];

            if(prefixSum == sum) {
                res = i - start + 1 ;
            }
//            In other words, the +1 is not needed in the if(map.containsKey(prefixSum-sum))
//                condition because the map stores the index where the cumulative sum equals prefixSum - sum,
//                and this index is inclusive in the subarray. Therefore, when we subtract this index from i,
//                we're already counting all elements in the subarray, including the element at the index stored in the map.
            if(map.containsKey(prefixSum-sum)) {
                res = Math.max(res, i - map.get(prefixSum-sum));
            } else {
                map.put(prefixSum, i);

            }
        }
        return res;
    }

    private static boolean zeroSum(int[] arr) {

        Set<Integer> set = new HashSet<>();
        int prefixSum = 0;
        set.add(0);
        for (int i = 0; i < arr.length; i++) {
            prefixSum += arr[i];
            if(set.contains(prefixSum)) {
                return true;
            } else {
                set.add(prefixSum);
            }
        }
        return false;
    }

    private static boolean givenSum(int[] arr, int sum) {
        int prefixSum = 0;
        Set<Integer> set = new HashSet<>();
        set.add(0);
        for (int i = 0; i < arr.length; i++) {

            prefixSum += arr[i];
            if(set.contains(prefixSum-sum)) {
                return true;
            } else {
                set.add(prefixSum);
            }
        }
        return false;
    }
}
