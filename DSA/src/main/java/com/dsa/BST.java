package com.dsa;

import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class BST {

//    Node root;
        static Node prev, first, middle, second;

    BST() {
    }

    public static void main(String[] args) {
        Node root = new Node(10);
        root.left = new Node(5);
//        root.left.left = new Node(5);
//        root.left.right = new Node(5);
        root.right = new Node(15);
//        Node deleteNode = deleteNode(root, 15);
//        System.out.println("deleteNode: " + deleteNode);
//        System.out.println("verticalSum: " + verticalSum(root, 0, new HashMap<Integer, Integer>()));
//        HashMap<Integer, Integer> map = new HashMap<>();
//        verticalSum(root, 0, map);
//        printMap(map);
        TreeMap<Integer, LinkedList<Integer>> map = new TreeMap<>();
        verticalTraversal(root, 0, map);
        printVerticalTraversal(map);

    }

    private static void verticalSum(Node root, int hd, Map<Integer, Integer> map) {

        if(root == null) return;
        verticalSum(root.left, hd-1, map);
        int sum = map.get(hd) == null ? 0 : map.get(hd);
        map.put(hd, sum+root.data);
        verticalSum(root.right, hd+1, map);
    }

    private static void verticalTraversal(Node root, int hd, TreeMap<Integer, LinkedList<Integer>> map) {
        if(root==null) {
            return;
        }
        verticalTraversal(root.left, hd-1, map);
        if(map.containsKey(hd)) {
            LinkedList<Integer> integers = map.get(hd);
            integers.add(root.data);
        } else {
            LinkedList<Integer> list = new LinkedList<>();
            list.add(root.data);
            map.put(hd, list);
        }
        verticalTraversal(root.right, hd+1, map);
    }

    private static void printVerticalTraversal(TreeMap<Integer, LinkedList<Integer>> map) {
        map.forEach((k, v)-> {
            System.out.println("HD: " + k + "value: " + v);
        });
    }

    private static void printMap(Map<Integer, Integer> map) {
        map.forEach((k, v) -> {
            System.out.println("HD: " + k + "value: " + v);
        });
    }

    private static boolean pair(Node root, int sum, Set<Integer> set) {
        if(root == null) {
            return false;
        }
        if(pair(root.left, sum, set)==true) {
            return true;
        }
        if(set.contains(sum-root.data)) {
            return true;
        }
        else {
            set.add(root.data);
        }
            return pair(root.right, sum, set);

    }

    private static void correctBSTWithSwapping(Node root) {

        if(root!=null) {
            correctBSTWithSwapping(root.left);
            if(prev!=null && root.data< prev.data) {
                if(first==null) {
                    first=prev;
                    middle=root;
                } else {
                    second=root;
                }
            }
            prev=root;
            correctBSTWithSwapping(root.right);
        }

        if( first != null && second != null )
        {
            int temp = first.data;
            first.data = second.data;
            second.data = temp;
        }
        // Adjacent nodes swapped
        else if( first != null && middle !=
            null )
        {
            int temp = first.data;
            first.data = middle.data;
            middle.data = temp;
        }
    }

    private static boolean isBST(Node root) {
        if(root!=null) {
            if (!isBST(root.left)) {
                return false;
            }
            if(prev!=null && root.data<prev.data) {
                return false;
            } else
                prev=root;
                isBST(root.right);
        }
        return true;
    }

    private static int kthSmallest(NodeLCount root, int k) {

        if(root==null) return -1;
        int lcount = root.lcount;
        if(lcount+1 == k) {
            return root.data;
        } else if (lcount>k) {
            return kthSmallest(root.left, k);
        } else return kthSmallest(root.right, k-lcount);
    }

    private static NodeLCount insert(NodeLCount root, int data) {
        if(root == null) return new NodeLCount(data);
        if(data<root.data){
            root.left = insert(root.left, data);
            root.lcount++;
        } else {
            root.right = insert(root.right, data);
        }
        return root;
    }

    private static int ceil(Node root, int key) {
        int ceil = -1;
        while ((root!=null)) {
            if (root.data == key) {
                return key;
            }
            else if(root.data < key) {
                root = root.right;
            } else {
                ceil = root.data;
                root = root.left;
            }
        }
        return ceil;
    }

    private static int floor(Node root, int key) {
      int floor = -1;
        while (root!=null) {
            if(root.data == key) {
                return key;
            }
            else if(root.data > key) {
                root = root.left;
            } else {
                floor = root.data;
                root = root.right;
            }
        }
            return floor;
    }

    private static Node deleteNode(Node root, int key) {
            if(root == null)
                return root;
            else if (key < root.data) {
                root.left = deleteNode(root.left, key);
            }
            else if (key > root.data) {
                root.right = deleteNode(root.right, key);
            } else {
                if (root.left == null) {
                    return root.right;
                } else if (root.right == null) {
                    return root.left;
                }
                Node succ = getSucc(root);
                root.data = succ.data;
                root.right = deleteNode(root.right, succ.data);
            }

                return root;
    }

    private static Node del(Node root) {
        if(root.left == null) return root.right;
        else if (root.right == null) return root.left;
        else return getSucc(root);
    }

    private static Node getSucc(Node root) {
        Node curr = root.right;
        while (curr!=null) {
            curr = curr.left;
        }
        return curr;
    }
    private static Node insert(Node root, int key) {
        if(root == null) return new Node(key);
        if(key < root.data) {
            root.left = insert(root.left, key);
        } else {
            root.right = insert(root.right, key);
        } return root;
    }

    private static Node insertIt(Node root, int key) {
        Node curr = root;
        Node parent = null;
        while(curr!=null) {
            parent = curr;
            if (key < curr.data) {
                curr = curr.left;
            } else curr = curr.right;
        }
        updateParent(parent, key);
        return root;

    }

    private static void updateParent(Node parent, int key) {
        if(parent == null) {
            parent = new Node(key);
        }
        else if (key < parent.data) {
            parent.left = new Node(key);
        } else parent.right = new Node(key);
    }

    private static boolean searchRec(Node root, int key) {
        if (root == null) return false;
        if (root.data == key) {
            return true;
        } else if (root.data > key) {
            return searchRec(root.left, key);
        } else return searchRec(root.right, key);
    }

    private static boolean searchIter(Node root, int key) {
        while (root != null) {
            if (root.data == key) return true;
            else if (key < root.data) {
                root = root.left;
            } else root = root.right;
        }
        return false;
    }
}
