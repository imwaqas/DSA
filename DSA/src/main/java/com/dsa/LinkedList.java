package com.dsa;

public class LinkedList {

    static LinkedNode head;

    public static void main(String[] args) {

//        head = new LinkedNode(5);
//        insertBegin(10);
//        insertBegin(11);
//        insertEnd(10);
//        insertEnd(11);
//        print();
//        head = new LinkedNode(5);
//        head.next = new LinkedNode(10);
//        head.next.next = new LinkedNode(6);
//        head.next.next.next = new LinkedNode(5);
//        head.next.next.next.next = new LinkedNode(5);
//        System.out.println("middle: " + middle(head));
//        System.out.println("NthNodeFromEnd: " + NthNodeFromEnd(head, 1));
//        LinkedNode reverse = reverse(head);
//        print(reverse);
//        head = new LinkedNode(5);
//        head.next = new LinkedNode(10);
//        head.next.next = head;
//        System.out.println("detectLoop: " + detectLoop(head));
//        System.out.println("detectAndRemove: " + detectAndRemove(head));
//        detectAndRemove(head);
//        print(head);
        head = new LinkedNode(10);
        head.next = new LinkedNode(7);
        head.next.next = new LinkedNode(7);
        head.next.next.next = new LinkedNode(10);
//        LinkedNode linkedNode = segregateEvenOdd(head);
//        print(linkedNode);
//        System.out.println("palindrom: "+ palindrom(head));
        LinkedNode linkedNode = mergeSorted(head, head);
        print(linkedNode);
    }

    private static LinkedNode mergeSorted(LinkedNode head1, LinkedNode head2) {
        if(head1==null) return head2;
        if(head2==null) return head1;
        if(head1.data< head.data) {
            head1.next = mergeSorted(head1.next, head2);
            return head1;
        } else {
            head2.next = mergeSorted(head1, head2.next);
            return head2;
        }
    }

    private static boolean palindrom(LinkedNode head) {

        LinkedNode slow = head;
        LinkedNode fast = head;
        LinkedNode curr = head;

        while (fast!=null && fast.next!=null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        LinkedNode reverse = reverse(slow);
        while(reverse!=null) {
            if(reverse.data!=curr.data) {
                return false;
            }
            reverse = reverse.next;
            curr = curr.next;
        }

        return true;

    }

    private static LinkedNode intersection(LinkedNode head1, LinkedNode head2) {
        int len1 = 0;
        int len2 = 0;

        LinkedNode curr1 = head1;
        LinkedNode curr2 = head2;

        // Calculate lengths of both linked lists
        while (curr1 != null) {
            len1++;
            curr1 = curr1.next;
        }
        while (curr2 != null) {
            len2++;
            curr2 = curr2.next;
        }

        curr1 = head1;
        curr2 = head2;

        // If list1 is longer, move its pointer 'd' steps ahead
        if (len1 > len2) {
            for (int i = 0; i < len1 - len2; i++) {
                curr1 = curr1.next;
            }
        }
        // If list2 is longer, move its pointer 'd' steps ahead
        else {
            for (int i = 0; i < len2 - len1; i++) {
                curr2 = curr2.next;
            }
        }

        // Now, traverse both lists, starting from the same point
        while (curr1 != null && curr2 != null) {
            if (curr1 == curr2) {
                return curr1;
            }
            curr1 = curr1.next;
            curr2 = curr2.next;
        }

        return null; // If no intersection point was found
    }

    private static LinkedNode segregateEvenOdd(LinkedNode head) {
        LinkedNode evenStart = null;
        LinkedNode evenEnd = null;
        LinkedNode oddStart = null;
        LinkedNode oddEnd = null;

        for (LinkedNode curr = head; curr != null; curr = curr.next) {
            if (curr.data % 2 == 0) {

                if (evenStart == null) {
                    evenStart = curr;
                    evenEnd = evenStart;
                } else {
                    evenEnd.next = curr;
                    evenEnd = evenEnd.next;

                }
            } else {
                if (oddStart == null) {
                    oddStart = curr;
                    oddEnd = oddStart;
                } else {
                    oddEnd.next = curr;
                    oddEnd = oddEnd.next;
                }
            }
        }
        if (oddStart == null) {
            return evenStart;
        }
        else if(evenStart == null) {
            return oddStart;
        } else {
            evenEnd.next = oddStart;
            oddEnd.next = null;
            return evenStart;
        }
    }

    private static void deleteRandom(LinkedNode randomNode) {
        randomNode.data = randomNode.next.data;
        randomNode.next = randomNode.next.next;
    }

    private static void detectAndRemove(LinkedNode head) {
        LinkedNode slow = head;
        LinkedNode fast = head;
        while (fast!=null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if(fast == slow) {
                slow = head;
                while (slow!=fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                while (fast.next != slow) {
                    fast = fast.next;
                }
                fast.next = null;
            }
        }
    }

    private static boolean detectLoop(LinkedNode head) {
        LinkedNode slow = head;
        LinkedNode fast = head;
        while (fast!=null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if(fast == slow) {
                return true;
            }
        }
        return false;
    }

    private static LinkedNode reverse(LinkedNode head) {
        LinkedNode prev = null;
        LinkedNode next;
        LinkedNode curr = head;
        while (curr!=null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;

    }

    private static void print(LinkedNode node) {
        LinkedNode curr = node;
        while (curr!=null) {
            System.out.println(curr.data);
            curr = curr.next;
        }
    }

    private static int NthNodeFromEnd(LinkedNode head, int n) {
        LinkedNode first = head;
        LinkedNode second = head;

        for (int i = 0; i <n; i++) {
            first = first.next;
        }
        while (first!=null && first.next!=null) {
            second = second.next;
            first = first.next;
        }
        return second.data;
    }

    private static int middle(LinkedNode head) {
        LinkedNode first = head;
        LinkedNode second = head;

        while(second!=null && second.next!=null) {
            first = first.next;
            second = second.next.next;
        }
        return first.data;
    }

    private static void insertEnd(int data) {
        LinkedNode curr = null;
        LinkedNode prev = null;
        curr = head;
        while (curr!=null) {
            prev = curr;
            curr = curr.next;
        }
        prev.next = new LinkedNode(data);

    }

    private static void insertBegin(int data) {
        LinkedNode temp = new LinkedNode(data);
        temp.next = head;
        head = temp;
    }

    private static void print(){
        LinkedNode curr = null;
        curr = head;
        while (curr!=null) {
            System.out.println(curr.data);
            curr = curr.next;
        }
    }
}

class LinkedNode {
    int data;
    LinkedNode next;

    LinkedNode(int data) {
        this.data = data;
        this.next = null;
    }
}
