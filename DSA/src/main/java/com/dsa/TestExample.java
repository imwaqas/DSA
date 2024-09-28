package com.dsa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class TestExample {

    public static void main(String[] args) {

//        String maxprefix = getMaxprefix();
//        System.out.println("max prefix: " + maxprefix);

/*        int[] coins = {1,2,3,5};
        int i = maxCoinsCombConstantSpace(coins, 10, 4);
                System.out.println("max coins: " + i);*/
//        System.out.println("fibonacci: " + fibonacciMemo(4));
//        List<String> list = List.of("a","d","c");
//        list.stream().sorted(Comparator.reverseOrder()).forEach(k-> System.out.println(k));
//                System.out.println("longestCommonSubsequenceDP: "
//                    +longestCommonSubsequenceDP(6, 6, "ABCDGH", "AEDFHR"));
//
//        System.out.println("editDistanceDp: "
//            +editDistanceDp( "SATURDAY", "SUNDAY", 8, 6));
//        int[] arr = {3, 4, 2, 8, 10, 5, 1};
//        System.out.println("longestIncreasingSubsequence: "
//            +longestIncreasingSubsequence( arr));
//        int[] arr = {1,2,3};
//        System.out.println("maxCutsDP: "
//            +maxCutsDP(  1,2,3,5));
//                int[] arr = {25,10,5};
//        System.out.println("minCoinsDp: "
//            +minCoinsDp(  arr, 3, 50));
//        int[] arr = {3,4,2,1,2,1};
//        System.out.println("minJumpsDp: "
//            +minJumpsDp(  arr, 6 ));
//        int[] val = {10, 40, 30, 50};
//        int[] wt = {5, 4, 6, 3};
//        int W = 10;
//        int N = val.length;
//        System.out.println("valWtKnapsackDP: "+valWtKnapsackDP(val, wt, W, N));
//        int[] arr = {2, 3, 15, 7};
//        System.out.println("The optimal value is: " + optimalStrategyGameRec(arr, 0, arr.length-1));
                int[] arr = {10, 5, 2, 3,6};
        System.out.println("subSetSumDP: " + subSetSumDP(arr, 8, arr.length));
//            int[] arr={8,7,6,10};
//          System.out.println("maxSumWithNoTwoElementsConsecutiveDP: " + maxSumWithNoTwoElementsConsecutiveDP(arr, arr.length));
    }

    private static int maxSumWithNoTwoElementsConsecutive(int [] arr, int N) {
//        arr[]=8,7,6,10
//        o/p:18 (8+10)
        if(N == 0) return 0;
        if(N == 1) return arr[0];
        if(N == 2) return Math.max(arr[0], arr[1]);
        return Math.max(maxSumWithNoTwoElementsConsecutive(arr, N-1), maxSumWithNoTwoElementsConsecutive(arr, N-2) + arr[N-1]);
    }

    private static int maxSumWithNoTwoElementsConsecutiveDP(int [] arr, int N) {
        int dp[] = new int[N+1];
        dp[0] = 0;
        dp[1] = arr[0];
        dp[2] = Math.max(arr[0], arr[1]);

        for (int i = 3; i <=N ; i++) {
            dp[i] = Math.max(dp[i-1], arr[i-1]+ dp[i-2]);
        }

        return dp[N];
    }

    private static int subSetSumRec(int[] arr, int sum, int N) {
        if(sum==0) return 1;
        if(N==0) return 0;
        return subSetSumRec(arr, sum, N-1) + subSetSumRec(arr, sum-arr[N-1], N-1);
    }

    private static int subSetSumDP(int[] arr, int sum, int N) {

        int[][] dp = new int[N+1][sum+1];
        IntStream.rangeClosed(0, N).forEach(col->{
            dp[col][0] = 1;
        });

        IntStream.rangeClosed(0, sum).forEach(row->{
            dp[0][row] = 0;
        });
        dp[0][0] = 1;

        IntStream.rangeClosed(1, N).forEach(i->{
            IntStream.rangeClosed(1, sum).forEach(j->{
                if(arr[i-1]<=j) {
                    dp[i][j] = dp[i-1][j] + dp[i-1][j-arr[i-1]];
                } else{
                    dp[i][j] = dp[i-1][j];
                }
            });
        });

        return dp[N][sum];
    }

    private static int optimalStrategyGameRec(int[] arr, int i, int j) {

        if(i+1==j) return Math.max(arr[i], arr[j]);
        return Math.max(arr[i] + Math.min(optimalStrategyGameRec(arr, i+1, j-1 ), optimalStrategyGameRec(arr, i+2, j)),
            arr[j] + Math.min(optimalStrategyGameRec(arr, i+1, j-1 ), optimalStrategyGameRec(arr, i, j-2 )));
    }

    private static int valWtKnapsackRec(int[] val, int[] wt, int W, int N) {

        if(N==0) return 0;
        if(W==0) return 0;

        if(wt[N-1] > W)
            return valWtKnapsackRec(val, wt, W, N-1);
        else {
            return Math.max(valWtKnapsackRec(val, wt, W, N-1), valWtKnapsackRec(val, wt, W-wt[N-1], N-1) + val[N-1]);
        }
    }

    private static int valWtKnapsackDP(int[] val, int[] wt, int W, int N) {

        int[][] dp = new int[N+1][W+1];

        IntStream.rangeClosed(0, W).forEach(row-> {
            dp[0][row] = 0;
        });

        IntStream.rangeClosed(0, N).forEach(row-> {
            dp[row][0] = 0;
        });

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= W; j++) {
//                if (i == 0 || w == 0)
//                    K[i][w] = 0;
                if(wt[i-1]>j) {
                    dp[i][j] = dp[i-1][j];
                }
                else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-wt[i-1]] + val[i-1]);
                }
            }

        }
        return dp[N][W];
    }


    private static int minJumps(int[] arr, int N) {

        if(N==1) return 0;
        int res = Integer.MAX_VALUE;
        for (int i = 0; i <= N-2; i++) {
            if(i+arr[i] >= N-1) {
                int subRes = minJumps(arr, i + 1);
                if(subRes != Integer.MAX_VALUE) {
                    res = Math.min(res, subRes+1);
                }

            }

        }
        return res;
    }

    private static int minJumpsDp(int[] arr, int N) {
        int[] jumps = new int[N+1];
        Arrays.fill(jumps, Integer.MAX_VALUE);
        jumps[0] = 0;

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if(j+arr[j]>=i) {
                    if(jumps[j]!=Integer.MAX_VALUE) {
                        jumps[i] = Math.min(jumps[i], jumps[j]+1);
                    }
            }
        }
         }
        return jumps[N-1] >= Integer.MAX_VALUE ? -1 : jumps[N-1];
    }
    
    private static int minCoinsCut(int[] coins, int N, int val) {

        if(val==0) return 0;
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            if (coins[i] <= val) {
                 int subRes = minCoinsCut(coins, N, val - coins[i]);
                if(subRes!=-Integer.MAX_VALUE) {
                    res = Math.min(res, subRes+1);
                }
            }
        }
        return res;
    }

    private static int minCoinsDp(int[] coins, int N, int val) {
        int[] dp = new int[val+1];
        Arrays.fill(dp, val+1);
        dp[0] = 0;

        for (int i = 0; i < coins.length ; i++) {
            for (int j = 1; j <=val ; j++) {
                if(j>=coins[i]) {
                dp[j] = Math.min(dp[j], dp[j-coins[i]]+1);
                }
            }
        }

        return dp[val] > val ? -1 : dp[val];
    }

    private static int maxCutsRec(int a, int b, int c, int N) {

        if(N==0) return 0;
        if(N<0) return Integer.MIN_VALUE;
        int res = Math.max(Math.max(maxCutsRec( a, b, c, N - a), maxCutsRec( a, b, c, N - b)),
                maxCutsRec( a, b, c, N - c));
        if (res == Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        return res+1;
    }

    private static int maxCutsDP(int a, int b, int c, int N) {
        int[] dp = new int[N+1];
        dp[0] = 0;

        for (int i = 1; i <= N; i++) {
            dp[i] = -1;
            if(i-a>=0)
                dp[i] = Math.max(dp[i],dp[i-a]);
            if(i-b>=0)
                dp[i] = Math.max(dp[i],dp[i-b]);
            if(i-c>=0)
                dp[i] = Math.max(dp[i],dp[i-c]);

            if(dp[i]!=-1){
                dp[i]++;
            }
            }
        return dp[N];
    }

    private static int longestIncreasingSubsequence(int[] arr) {

        List<Integer> tail = new ArrayList<>();
        tail.add(arr[0]);

        for (int i = 1; i <arr.length; i++) {
            if (arr[i] > tail.get(tail.size()-1)) {
                tail.add(arr[i]);
            }
            else {
                int pos = ceiling(tail, 0, tail.size()-1, arr[i]);
                tail.set(pos, arr[i]);
//                int pos = Collections.binarySearch(tail, arr[i]);
//                if (pos < 0) {
//                    pos = -(pos + 1); // Get the insertion point
//                }
//                tail.set(pos, arr[i]);
            }
        }

        return tail.size();
    }

    private static int ceiling(List<Integer> tail, int l, int r, int key) {
        while(r>l) {
            int m = (l+r)/2;
            Integer i = tail.get(m);
            if(key<=i) {
                r = m;
            } else {
                l = m +1;
            }
        }
        return r;
    }

    private static int editDistanceRec(String a, String b, int N, int M) {
        if(N==0) return M;
        if(M==0) return N;

        if(a.charAt(N-1) == b.charAt(M-1))
            return editDistanceRec(a, b, N-1, M-1);
        return 1+Math.min(Math.min(editDistanceRec(a, b, N, M-1), editDistanceRec(a, b, N-1, M)), editDistanceRec(a, b, N-1, M-1));
    }

    private static int editDistanceDp(String a, String b, int N, int M) {

        int dp[][] = new int[N+1][M+1];
        for (int i = 0; i <=M; i++) {
            dp[0][i] = i;
        }
        for (int i = 0; i <=N; i++) {
            dp[i][0] = i;
        }

        for (int i = 1; i <=N ; i++) {
            for (int j = 1; j <=M; j++) {
                if(a.charAt(i-1) == b.charAt(j-1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                else {
                    dp[i][j] = 1 + Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]);
                }

            }
        }

        return dp[N][M];
    }

    private static int fibonacciMemo(int n) {

        int fib[] = new int[n+1];
        Arrays.fill(fib, -1);

        if(fib[n] == -1) {
            if (n == 1 || n == 0) {
                return 1;
            }
            int res = fibonacciMemo(n - 1) + fibonacciMemo(n - 2);
            fib[n] = res;
        }
        return fib[n];
    }

    private static int longestCommonSubsequenceRec(int m, int n, String a, String b) {
        //i/p:ABCDGH and AEDFHR
        //o/p:3 (ADH)
        if(m==0 || n== 0) return 0;

        if(a.charAt(m-1) == b.charAt(n-1)) {
            return 1+longestCommonSubsequenceRec(m-1, n-1, a, b);
        }
        else {

            return Math.max(longestCommonSubsequenceRec(m-1, n, a, b), longestCommonSubsequenceRec(m, n-1, a, b));
        }

    }

    private static int longestCommonSubsequenceDP(int m, int n, String a, String b) {
        int dp[][] = new int[m+1][n+1];

        IntStream.rangeClosed(0, m).forEach(row -> {
            IntStream.rangeClosed(0, n).forEach( col -> {
                if(row == 0 || col == 0)
                    dp[row][col] = 0;

                else if(a.charAt(row-1) == b.charAt(col-1))
                    dp[row][col] = dp[row-1][col-1] + 1;
                else
                    dp[row][col] = Math.max(dp[row-1][col], dp[row][col-1]);
            });
        });

        return dp[m][n];
    }

    public static int maxCoinsCombRec(int[] coins, int amount, int N) {
        // coins[] =1,2,3,5 ,amount=10

        if(amount==0) return 1;
        if(N==0) return 0;

        int res = maxCoinsCombRec(coins, amount, N - 1);

        if(coins[N-1] <= amount) {
            res = res + maxCoinsCombRec(coins, amount - coins[N - 1], N);
        }
        return res;
    }

    public static int maxCoinsCombDP(int[] coins, int amount, int N) {

        int[][] dp = new int[amount+1][coins.length+1];

        for (int i = 0; i <= coins.length; i++) {
            dp[0][i] = 1; //when amount is zero
        }
        for (int i = 0; i <= amount; i++) {
            dp[i][0] = 0; //when coins is zero
        }
//        IntStream.range(0, coins.length)

        for (int i = 1; i <= amount; i++) {
            for (int j = 1; j <= coins.length; j++) {
                dp[i][j] = dp[i][j-1]; //ignore current coin
                if(i>=coins[j-1]) {

                    dp[i][j] += dp[i-coins[j-1]][j];
                }
            }
        }

        IntStream.rangeClosed(0, amount).forEach(i-> {
            IntStream.rangeClosed(0, coins.length).forEach(j-> {
                System.out.println(dp[i][j]);
            });
        });

        return dp[amount][coins.length];

    }

    public static int maxCoinsCombConstantSpace(int[] coins, int amount, int N) {

        int[] dp = new int[amount+1];
        dp[0] = 1;
        for (int i = 0; i < coins.length; i++) {
            for (int j = 1; j <= amount ; j++) {
                if(coins[i]<=j) {
                    dp[j] += dp[j-coins[i]];
                }
            }
        }
        return dp[amount];
    }
        public static String getMaxprefix() {
        String[] str = {"flow","flower","flight"};
        String prefix = str[0];


        for(int i=1; i< str.length; i++) {
            while(str[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length()-1);
                if(prefix.isEmpty())
                    return prefix;
            }
        }

        return prefix;
    }
}
