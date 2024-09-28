package com.dsa;

public class Arrays {

    public static void main(String[] args) {

//        int[] arr = {1,2,3,4};
//        reverse(arr, 0, arr.length-1);
//        rotate(arr, 2);
//        System.out.println("rotate: " + arr);
//        int[] arr = {5,3,20,15,8,3};
//        leaders(arr);
//        int arr[]
//            = new int[] { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };
//        System.out.println("trappingRainWater: " + trappingRainWater(arr));
//        int[] arr = {1, 5, 3, 8, 12};
//        System.out.println("stockBuyAndSell: " + stockBuyAndSell(arr));
//        int[] arr = {10,20,-5,7,9};
//        System.out.println("maxSumKConsecutive: " + maxSumKConsecutive(arr, 3));
//        int[] arr = {10,20,-5,7,9};
//        System.out.println("maxSumConsecutiveNoWindow: " + maxSumConsecutiveNoWindow(arr, 22));
//        int[] arr = {10,20,5,7,9};
//        System.out.println("getQueries: " + getQueries(arr, 1, 3));
//        int[] arr = {5,3,2,5,3};
//        System.out.println("equilibriumPoint: " + equilibriumPoint(arr));
        int[] arr = {5,10,10, 5,15};
        System.out.println("arrayDivideThree: " + arrayDivideThree(arr));

    }

    private static int maxElementInRanges(int[] l , int[] r, int n) {

        int max = r[0];
        for (int i = 0; i < n; i++) {
            max = Math.max(max, r[i]);
        }
        int[] freq = new int[max+2];

        for (int i = 0; i <n; i++) {
            freq[l[i]]++;
            freq[r[i]+1]--;
        }

        int max_freq = freq[0];
        int res = -1;
        for (int i = 1; i < freq.length ; i++) {

            freq[i] += freq[i-1];
            if(max_freq<freq[i]) {
                max_freq = freq[i];
                res = i;
            }
        }
        return res;
    }

    private static boolean arrayDivideThree(int[] arr) {

        int sum = 0;
        for (int i = 0; i <arr.length; i++) {
            sum+=arr[i];
        }

        if(sum%3!=0) {
            return false;
        }

        int s1 = sum/3;
        int s2 = 2*s1;
        boolean s1Found=false;
        boolean s2Found=false;

        int prefixSum = 0;

        for (int i = 0; i <arr.length; i++) {
            prefixSum+=arr[i];

            if(prefixSum == s1 && !s1Found) {
                s1Found=true;
            }
            if(prefixSum == s2 && !s2Found) {
                s2Found=true;
            }

            if(s1Found && s2Found) {
                return true;
            }
        }

        return false;
    }
    
    private static boolean equilibriumPoint(int[] arr) {
        int leftSum = 0;

        int prefixSum = 0;

        for (int i = 0; i <arr.length; i++) {
            prefixSum+=arr[i];
        }
        for (int i = 0; i <arr.length; i++) {
            if(leftSum == prefixSum-arr[i]) {
                return true;
            } else {
                leftSum+=arr[i];
                prefixSum-=arr[i];
            }
        }
        return false;
    }

    private static int getQueries(int[] arr, int low, int high) {

        int[] prefixSum = new int[arr.length];
        prefixSum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            prefixSum[i] = prefixSum[i-1]+arr[i];
        }

        return low == 0 ? prefixSum[high-1] : prefixSum[high-1] - prefixSum[low-1];
    }

    //will work for -ve nos as well
    private static boolean maxSumConsecutiveNoWindow(int[] arr, int sum) {

        int start = 0;
        int end = 0;
        int currSum = 0;

        while (end < arr.length) {
            while (end < arr.length && currSum < sum) {

                currSum += arr[end];
                end++;
            }
            if (currSum == sum) return true;

            while (currSum > sum && start < end) {
                currSum -= arr[start];
                start++;
            }
        }
        return false;
    }

    private static int maxSumKConsecutive(int[] arr, int k) {

        int maxSum = 0;
        int currSum = 0;
        for (int i = 0; i <k; i++) {
            currSum+=arr[i];
        }
        for (int i = k; i <arr.length ; i++) {
            currSum = currSum + (arr[i] - arr[i-k]);

            maxSum = Math.max(maxSum, currSum);

        }
        return maxSum;
    }

    private static int stockBuyAndSell(int[] arr) {

        int maxProfit = 0;
        for (int i = 1; i < arr.length; i++) {
            if(arr[i] > arr[i-1]) {
                maxProfit += (arr[i] - arr[i-1]);
            }
        }
        return maxProfit;
    }

    private static int trappingRainWaterInConstant(int[] arr) {
        int left = 0, right = arr.length - 1;
        int maxLeft = 0, maxRight = 0;
        int water = 0;

        while (left < right) {
            if (arr[left] <= arr[right]) {
                if (arr[left] >= maxLeft) {
                    maxLeft = arr[left];
                } else {
                    water += maxLeft - arr[left];
                }
                left++;
            } else {
                if (arr[right] >= maxRight) {
                    maxRight = arr[right];
                } else {
                    water += maxRight - arr[right];
                }
                right--;
            }
        }
        return water;
    }

    private static int trappingRainWater(int[] arr) {
        int[] left = new int[arr.length];
        int[] right = new int[arr.length];
        int water = 0;

        left[0] = arr[0];
        for (int i = 1; i < arr.length ; i++) {
            left[i] = Math.max(arr[i], left[i-1]);
        }
        right[arr.length -1]= arr[arr.length-1];
        for (int i = arr.length-2; i >=0 ; i--) {
            right[i] = Math.max(arr[i], right[i+1]);
        }

        for (int i = 0; i < arr.length; i++) {
            water+= Math.min(left[i], right[i]) - arr[i];
        }

        return water;
    }

    private static void leaders(int[] arr) {
        System.out.println(arr[arr.length-1]);
        int max = arr.length-1;
        for (int i = arr.length-2; i >=0 ; i--) {
            if(arr[i]> max) {
                max = arr[i];
                System.out.println(max);

            }
        }
    }

    private static void rotate(int[] arr, int d) {
        reverse(arr,0,d-1);
        reverse(arr,d,arr.length-1);
        reverse(arr,0,arr.length-1);
                for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }

    }
    private static void reverse(int[] arr, int low, int high) {
        while (low < high) {
            int temp = arr[low];
            arr[low] = arr[high];
            arr[high] = temp;
            low++;
            high--;
        }
/*        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }*/
    }
}
