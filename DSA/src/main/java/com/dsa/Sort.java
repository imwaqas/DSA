package com.dsa;

public class Sort {

    public static void main(String[] args) {
        int[] arr = {1,0,2,1,0,1,0,2};
        sort123(arr);
        for (int i = 0; i <arr.length; i++) {
            System.out.println(arr[i]);
        }

    }

    private static void sort123(int[] arr) {
        int low = 0;
        int mid = 0;
        int high= arr.length-1;
        while (mid<high) {
            switch (arr[mid]) {
                case 1: mid++;
                        break;
                case 2: swap(arr, mid, high);
                        high --;
                        break;
                case 0: swap(arr, low, mid);
                        low++;
                        mid++;
                        break;
            }
        }
    }

    private static void swap(int[] arr, int index1, int index2) {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }
}
