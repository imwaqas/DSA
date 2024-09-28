package com.dsa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Heap {

    public static void main(String[] args) {

        HeapImpl heap = new HeapImpl(5);
        int[] arr = new int[8];
        insert(8, heap, arr);
        print(arr);
    }

    private static List<Integer> mergeKSorted(ArrayList<ArrayList<Integer>> list)  {

        List<Integer> res = new ArrayList<>();

        PriorityQueue<Triplet> pq = new PriorityQueue<>((a,b)-> Integer.compare(a.getVal(), b.getVal()));

        for (int i = 0; i <list.size(); i++) {
            pq.add(new Triplet(list.get(i).get(0), i, 0));
        }

        while (!pq.isEmpty()) {
            Triplet poll = pq.poll();
            res.add(poll.val);
            int ap = poll.getAp();
            int vp = poll.getVp();

            if(vp+1 < list.get(ap).size()) {
                pq.add(new Triplet(list.get(ap).get(vp+1), ap, vp+1));
            }
        }
        return res;
    }

    private static void kClosest(int[] arr, int k, int x) {

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i <arr.length; i++) {
            int abs = Math.abs(x - arr[i]);
            pq.add(abs);

            if(pq.size()>k) {
                pq.poll();
            }

        }

        while (!pq.isEmpty()) {
            System.out.println(pq.peek());
            pq.poll();
        }

    }

    //nlogk & k
    private static void kLargest(int[] arr, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i <arr.length; i++) {

            pq.add(arr[i]);

            if(pq.size()>k) {
                pq.poll();
            }
        }

        while (!pq.isEmpty()) {
            System.out.println(pq.peek());
            pq.poll();
        }
    }

    //needs fix
    private static int maxItem(int[] cost, int sum) {
        int res = 0;

        buildHeap(cost, new HeapImpl(cost.length));

        for (int i = 0; i <cost.length; i++) {
            if(cost[i]<=sum) {
                sum=-cost[i];
                res+=1;
            }
        }
        return res;
    }

    private static void kSortedArray(int[] arr, int k) {

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i <=k; i++) {
            pq.add(arr[i]);
        }

        int index = 0;
        for (int i = k+1; i <arr.length; i++) {
            arr[index++] = pq.poll();
            pq.add(arr[i]);
        }

        while(!pq.isEmpty()) {
            arr[index++] = pq.poll();
        }
    }

    private static void priorityQueue() {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        PriorityQueue<Integer> reverse = new PriorityQueue<>(Collections.reverseOrder());
        pq.add(10); //logn and n
        pq.add(30);
        pq.poll(); //logn and n
        pq.peek();
    }

    public static void sort(HeapImpl heap, int[] arr) {
        int n = arr.length;
        for (int i = (n/2)-1; i >=0; i--) {
            maxheapify(heap, arr, i);
        }

        for (int i = n-1; i >=0; i--) {
            swap(arr, i, 0);
            maxheapify(heap, arr, 0);
        }
    }

    private static void maxheapify(HeapImpl heap, int[] arr, int i) {
        int largest = i;
        int left = 2*i + 1;
        int right = 2*i + 2;

        if(left < heap.size && arr[left] > arr[i]) {
            largest = left;
        }
        if(right < heap.size && arr[right] > arr[i]) {
            largest = right;
        }

        if(largest!=i) {
            swap(arr, largest, i);
            maxheapify(heap, arr, largest);
        }
    }

    //O(n)
    private static void buildHeap(int[] arr, HeapImpl heap) {
        heap.size = arr.length;

        for (int i = (heap.size/2)-1; i >=0; i--) {
            heapify(heap, arr, i);
        }
    }

    public static void delete(HeapImpl heap, int[] arr, int index) {
        decreaseKey(heap, arr, index, Integer.MIN_VALUE);
        extractMin(heap, arr);
    }

    private static void decreaseKey(HeapImpl heap, int[] arr, int index, int val) {
        arr[index] = val;
        int i = index;
        while (i>0 && arr[heap.parent(i)]>arr[i]) {
            swap(arr, i, heap.parent(i));
            i = heap.parent(i);
        }

    }

    private static int extractMin(HeapImpl heap, int[] arr) {
        if(heap.size==0) {
            return -1;
        }
        if(heap.size==1) {
            return arr[0];
        }
        swap(arr, 0, heap.size-1);
        heap.size--;
        heapify(heap, arr, 0);
        return arr[heap.size];
    }

    private static void heapify(HeapImpl heap, int[] arr, int i) {

        int smallest = i;
        int left = heap.left(i);
        int right = heap.right(i);
        if(left < heap.size && arr[left] < arr[i]) {
            smallest = left;
        }
        if (right < heap.size && arr[right] < arr[i]) {
            smallest = right;
        }

        if(smallest!=i) {
            swap(arr, i , smallest);
            heapify(heap, arr, smallest);
        }
    }

    private static void insert(int x, HeapImpl heap, int[] arr) {
        if(heap.size!= heap.cap) {
            heap.size++;
            arr[heap.size -1] = x;
            int i = heap.size;
            while (i>0 && arr[heap.parent(i)] > arr[i]){
                swap(arr, i, heap.parent(i));
                i = heap.parent(i);
            }
        }
    }

    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    private static void print(int[] arr) {
        for (int i = 0; i <arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}

class HeapImpl {

    int[] arr;
    int cap;
    int size;

    HeapImpl(int cap) {
        this.arr = new int[cap];
        this.size=0;
        this.cap=cap;
    }

    public int left(int i) {
        return 2*i +1;
    }

    public int right(int i) {
        return 2*i + 2;
    }

    public int parent(int i) {
        return i - 1/2;
    }
    }

    class Triplet {
    int val;
    int ap;
    int vp;

    Triplet(int val, int ap, int vp) {
        this.val = val;
        this.ap = ap;
        this.vp = vp;
    }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public int getAp() {
            return ap;
        }

        public void setAp(int ap) {
            this.ap = ap;
        }

        public int getVp() {
            return vp;
        }

        public void setVp(int vp) {
            this.vp = vp;
        }

    }
