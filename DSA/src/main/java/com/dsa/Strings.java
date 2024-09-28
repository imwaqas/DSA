package com.dsa;

public class Strings {

    public static void main(String[] args) {


//        String s1 = "geeksforgeeks";
//        String s2 = "for";
//        System.out.println("first index of: " +s1.indexOf(s2));

//        String s1 = "silent";
//        String s2 = "listenn";
//        String s3 = "i am best";
//        System.out.println("reverseSentence: " +reverseSentence(s3));
//        String s1 = "BACDGABCDA";
//        String s2 = "ABCD";
//        anagramSearch(s1, s2);
//        System.out.println("anagramSearch: " +);
//        System.out.println("lexicographicRankOptimised:" + lexicographicRankOptimised("STRING"));
          System.out.println("longestSubstringWithAllDistinctChar: " + longestSubstringWithAllDistinctChar("waqas"));

    }

//    private static boolean patternSearchDistinctChar(String text, String pattern) {
//
//    }

    private static int longestSubstringWithAllDistinctChar(String s) {

        int[] lastIndex = new int[256];
        int start = 0;
        int res = 0;
        for (int i = 0; i < 256; i++) {
            lastIndex[i] = -1;
        }
        for (int i = 0; i < s.length(); i++) {

            start = Math.max(start, lastIndex[s.charAt(i)] + 1);

            res = Math.max(res, i-start+1);

            lastIndex[s.charAt(i)] = i;
        }
        return res;
    }

    private static int lexicographicRankOptimised(String s) {


        int rank = 1;
        int mul = fact(s.length());

        int[] count = new int[256];
        populateAndIncreaseCount(count, s);

        for (int i = 0; i <s.length(); i++) {

            mul = mul/(s.length()-i);

            rank = rank + count[s.charAt(i)-1] * mul;
            updateCount(s.charAt(i), count);
        }
        return rank;
    }
    private static int lexicographicRank(String s) {

        int rank = 1;
        int n = s.length();
        int countRight = 0;
        int mul = fact(n);

        for (int i = 0; i <s.length(); i++) {

            mul = mul/(n-i);
            countRight = countsmallerInRight(s, i);

            rank += countRight * mul;
        }

        return rank;

    }

    private static void populateAndIncreaseCount(int[] count, String s) {

        for (int i = 0; i <s.length(); i++) {
            count[s.charAt(i)]++;
        }

        for (int i = 1; i < 256 ; i++) {
            count[i] += count[i-1];
        }
    }

    private static void updateCount(char c, int[] count) {
        for (int i = c; i < 256 ; i++) {
            count[i]--;
        }
    }

    private static int fact(int n) {
        int res =1;
        for (int i = 2; i <= n ; i++) {
            res = res *i;
        }
        return res;
    }

    private static int countsmallerInRight(String s, int low) {

        int count = 0;
        for (int i = low +1; i < s.length() ; i++) {
            if(s.charAt(i) < s.charAt(low)) {
                count++;
            }
        }
        return count;
    }
    private static void anagramSearch(String text, String patten) {

        int[] t = new int[256];
        int[] p = new int[256];

        for (int i = 0; i <patten.length(); i++) {
            t[text.charAt(i)]++;
            p[patten.charAt(i)]++;
        }
        for (int i = patten.length(); i < text.length() ; i++) {
            if(compare(t, p)) {
                System.out.println("found match at: " + (i - patten.length()));
            }
            t[text.charAt(i-patten.length())]--;
            t[text.charAt(i)]++;
        }
    }

    private static boolean compare(int[] text, int[] pattern) {
        for (int i = 0; i < 256; i++) {
            if(text[i]!=pattern[i]) {
                return false;
            }
        }
        return true;
    }

    private static boolean patternSearch(String text, String pattern) {
        for (int i = 0; i <= text.length()-pattern.length(); i++) {
            if(text.substring(i, i+pattern.length()).equals(pattern)) {
                return true;
            }
        }
        return false;
        /*for (int i = 0; i < text.length(); i++) {
            if(i+pattern.length() > text.length()) {
                return false;
            }
            for (int j = 0; j < pattern.length(); j++) {
                if(text.charAt(i+j) != pattern.charAt(j)) {
                  break;
                } else {
                    if(j==pattern.length()-1) {
                        return true;
                    }
                }
            }
        }
        return false;*/
    }

    private static String reverseSentence(String s) {

        char[] charArray = s.toCharArray();
        int n = charArray.length;
        int start=0;
        for (int i = 0; i <n; i++) {

            if(charArray[i] == ' ') {
//                end = i-1;
                swap(charArray, start, i-1);
                start = i+1;
            }
        }

        swap(charArray, start, n-1);
        swap(charArray, 0, n-1);

        return new String(charArray);
    }

    private static void swap(char[] s, int start, int end) {

        while (start<end) {
            char temp = s[start];
            s[start] = s[end];
            s[end] = temp;
            start++;
            end--;
        }
    }

    private static int leftMostRep(String s) {
        boolean[] bool = new boolean[256];
        int res = -1;
        for (int i = s.length()-1; i >=0 ; i--) {
            if(bool[s.charAt(i)]) {
                res = i;
            } else {
                bool[s.charAt(i)]=true;
            }
        }
        return res;
    }

    private static int leftMostNonRep(String s) {

        boolean[] bool = new boolean[256];
        int res =-1;
        for (int i = s.length()-1; i >=0; i--) {
            if(!bool[s.charAt(i)]) {
                bool[s.charAt(i)]=true;
                res = i;
            }
        }
        return res;
    }

    private static boolean anagram(String s1, String s2) {
        if(s1.length()!=s2.length()) return false;
        char[] a = new char[256];

        for (int i = 0; i < s1.length(); i++) {
            a[s1.charAt(i)]++;
            a[s2.charAt(i)]--;
        }

        for (int i = 0; i < a.length; i++) {
            if(a[i]!=0) {
                return false;
            }
        }

        return true;
    }
}
