package com.dsa;

import java.util.LinkedList;

public class Tree {
    Node root = new Node(5);
    Tree() {
        Node root = new Node(5);
        this.root.left = new Node(10);
        this.root.right = new Node(20);
        this.root.left.left = new Node(25);
    }
    public static void main(String[] args) {
        Node root = new Node(10);
        root.left = new Node(5);
        root.left.left = new Node(5);
        root.left.left.left = new Node(5);
        root.right = new Node(5);
//        System.out.println("printLeftView: ");
//        printLeftView(tree.root);
        System.out.println("isBalanced: " + isBalanced(root));
    }

    private static boolean isBalanced(Node root) {

        if(root==null) return true;

        return Math.abs(height(root.left)-height(root.right))<=1 && isBalanced(root.left) && isBalanced(root.right);
    }

    private static boolean childrenSumProperty(Node root) {
        if (root == null) return true;
        if(root.left == null && root.right == null) return true;
        int sum = 0;
        if(root.left != null) {
            sum += root.left.data;
        }
        if(root.right != null) {
            sum += root.right.data;
        }
        return (root.data == sum) && childrenSumProperty(root.left) && childrenSumProperty(root.right);
    }

    private static void printNodeAtKDistance(Node root, int k) {
        if(root == null) return;
        if(k == 0) System.out.println(root.data);
            printNodeAtKDistance(root.left, k-1);
            printNodeAtKDistance(root.right, k-1);

    }

    private static void printLeftView(Node root) {
        LinkedList<Node> q = new LinkedList<>();
        q.add(root);
        System.out.println(root.data);

        while (!q.isEmpty()) {
            Node node = q.poll();
            if(node.left!=null) {
                System.out.println(node.left.data);
                q.add(node.left);
            }
            if(node.right!=null) {
                q.add(node.right);
            }
        }
    }

    private static int getMax(Node root) {

        if(root == null) return 0;

        return Math.max(root.data, Math.max(getMax(root.left), getMax(root.right)));

    }

    private static int height(Node root) {

        if(root == null) return 0;
        return Math.max(height(root.left),height(root.right)) + 1;
    }


    private static int sizeOfBinaryTree(Node root) {

        if(root == null) return 0;
        return 1 + sizeOfBinaryTree(root.left) + sizeOfBinaryTree(root.right);
    }

    private static int sizeOfBinaryTreeDp(Node root) {

        LinkedList<Node> q = new LinkedList<>();
        q.add(root);

        int res = 1;

        while(!q.isEmpty()) {
            Node node = q.poll();
            if(node.left!=null){
                q.add(node.left);
                res++;
            }
            if(node.right!=null){
                q.add(node.right);
                res++;
            }
        }
        return res;
    }

    private static void inorder(Node root) {

       if(root!=null) {
            inorder(root.left);
           System.out.println(root.data);
           inorder(root.right);
        }
    }

    private static void levelOrderLineByLine(Node root) {
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);

        while(queue.size()>1) {
            Node node = queue.poll();
            if(node==null) {
                System.out.println();
                queue.add(null);
            }
            else {
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                System.out.print(node.data+ " ");
            }

        }
    }

    private static void levelOrder(Node root) {
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()) {
            Node node = queue.poll();
            if(node!=null) {
                if(node.left!=null) {
                    queue.add(node.left);
                }
                if(node.right!=null) {
                    queue.add(node.right);
                }
                System.out.println(node.data);
            }
        }
    }


    private static void preOrder(Node root) {

        if(root!=null) {
            System.out.println(root.data);
            inorder(root.left);
            inorder(root.right);
        }
    }

    private static void postOrder(Node root) {

        if(root!=null) {
            inorder(root.left);
            inorder(root.right);
            System.out.println(root.data);
        }
    }

}
class Node {
    Node left;
    Node right;
    int data;
    Node(int data) {
        this.data=data;
    }
}

class NodeLCount {
    NodeLCount left;
    NodeLCount right;
    int data, lcount;
    NodeLCount(int data) {
        this.data=data;
        this.lcount = 0;
    }

}
