package com.dsa;

public class Trie {

    static TrieNode root;
    static boolean[][] mat;

    public static void main(String[] args) {
        root = new TrieNode();
//        insert("waqas");
//        System.out.println("search: " + search("waqas"));
        mat = new boolean[][] {{true,true,false},{true,false,true},{true,false,false}};
        System.out.println("insertMatrix:" + insertMatrix(3, root));
    }

    private static int insertMatrix(int M, TrieNode root) {
        int res = 0;
        for (int row = 0; row <M; row++) {
            if(matrix(row, root)) {
                res++;
            }
        }
        return res;
    }
    private static boolean matrix(int row, TrieNode root) {
        TrieNode curr =root;
        boolean flag = false;
        for (int i = 0; i <3; i++) {
            int index = mat[row][i] ? 1 : 0;
            if(curr.child[index] == null) {
                curr.child[index] = new TrieNode();
                flag=true;
            }
            curr = curr.child[index];
        }
        return flag;
    }

    private static TrieNode delete(TrieNode root, String key, int i) {

        if(root==null) return null;
        if(i==key.length()) {
            root.isEnd = false;
            if(isEmpty(root) == true) {
                root = null;
            }
            return root;
        }

        int index = key.charAt(i)-'a';
        root.child[index] = delete(root.child[index], key, i + 1);
        if(isEmpty(root) && root.isEnd==false) {
            root = null;
        }
        return root;
    }

    private static boolean isEmpty(TrieNode root) {
        for (int i = 0; i < 26; i++) {
            if(root.child[i]!=null){
                return false;
            }
        }
        return true;
    }

    private static void insert(String key) {
        TrieNode curr = root;
        for (int i = 0; i <key.length(); i++) {
            int index = key.charAt(i) - 'a';
            if(curr.child[index] == null) {
                curr.child[index] = new TrieNode();
                curr = curr.child[index];
            }
        }
         curr.isEnd=true;
    }

    private static boolean search(String key) {
        TrieNode curr = root;

        for (int i = 0; i <key.length(); i++) {
            int index = key.charAt(i) - 'a';
            if(curr.child[index]==null) {
                return false;
            }
            curr =curr.child[index];
        }
        return curr.isEnd;
    }

}

class TrieNode{
    TrieNode[] child = new TrieNode[26];
    boolean isEnd;

}
