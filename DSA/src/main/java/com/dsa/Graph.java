package com.dsa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.stream.IntStream;

public class Graph {

    static ArrayList<ArrayList<Integer>> adjacencyList;

    public static void main(String[] args) {
        graphRepresentation(3);
        addEdge(adjacencyList, 0, 1);
        print(adjacencyList);

    }

    private static void topologicalSortingDFS(ArrayList<ArrayList<Integer>> adjacencyList) {

//        Integer i1 = Stream.of(1, 2, 3).max(Comparator.comparing(Integer::valueOf)).get();
        int[] arr = {1,2,3};
        Arrays.sort(arr);
        List<Integer> list = List.of(1,2,3);
        int sum = list.stream().mapToInt(i -> i).sum();


        int V = adjacencyList.size();
        boolean[] visited = new boolean[V];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                dfs(adjacencyList, i, visited, stack);
            }
        }

        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }

    private static void dfs(ArrayList<ArrayList<Integer>> adjacencyList, int node, boolean[] visited, Stack<Integer> stack) {
        visited[node] = true;

        for (int u : adjacencyList.get(node)) {
            if (!visited[u]) {
                dfs(adjacencyList, u, visited, stack);
            }
        }

        stack.push(node);
    }

    private static void topologicalSortingBFS(ArrayList<ArrayList<Integer>> adjacencyList) {
        int V = adjacencyList.size();

        int[] inDegree = new int[V];
        for (int i = 0; i < V; i++) {
            adjacencyList.get(i).forEach(u-> {
                inDegree[u]++;
            });
        }
        ArrayList<Integer> topologicalOrder = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < V; i++) {
            if(inDegree[i]==0) {
                queue.add(i);
            }
        }

        int visitedNodes = 0;
        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            topologicalOrder.add(poll);

            for (int u : adjacencyList.get(poll)) {
                inDegree[u]--;
                if(inDegree[u] == 0) {
                    queue.add(u);
                }
            }
            visitedNodes++;
        }

        if(visitedNodes!=V){
            System.out.println("not possible");
        }

    }

    private static boolean detectCycleDirected(ArrayList<ArrayList<Integer>> adjacencyList, int source, int V) {
        boolean[] visited = new boolean[V];
        boolean[] recst = new boolean[V];

        for (int i = 0; i <V; i++) {
            if(detectCycleDirectedRec(adjacencyList, visited, recst,i)) return true;
        }
        return false;
    }

    private static boolean detectCycleDirectedRec(ArrayList<ArrayList<Integer>> adjacencyList, boolean[] visited, boolean[] recst, int source) {
        visited[source] = true;
        recst[source] = true;
        for (int u : adjacencyList.get(source)) {
            if(!visited[u] && detectCycleDirectedRec(adjacencyList, visited, recst, u)) {
                return true;
            } else if (recst[u]==true) {
                return true;
            }
        }
        recst[source] = false;
        return false;
    }

    private static boolean detectCycleUndirected(ArrayList<ArrayList<Integer>> adjacencyList, int source, int V) {

        boolean[] visited = new boolean[V];

        for (int i = 0; i < V; i++) {
            if(!visited[i]) {
                if(detectCycleUndirectedRec(adjacencyList, i, visited, -1)) return true;
            }
        }
        return false;
    }

    private static boolean detectCycleUndirectedRec(ArrayList<ArrayList<Integer>> adjacencyList, int source, boolean[] visisted, int parent) {

        visisted[source] = true;
        for (int u : adjacencyList.get(source)) {
            if(!visisted[u]) {
                if(detectCycleUndirectedRec(adjacencyList, u, visisted, source)) return true;
                    return true;
                }
            else if (parent!=u) {
                return true;
            }
        }
        return false;
    }

    private static int[] shortestPathUnWeighted(ArrayList<ArrayList<Integer>> adjacencyList, int source, int V) {
        int[] dis = new int[V];
        boolean[] visited = new boolean[V];
        Arrays.fill(dis, Integer.MAX_VALUE);

        Arrays.fill(visited, false);
        dis[source] = 0;
        visited[source] = true;
        Queue<Integer> queue = new LinkedList<>();

        queue.add(source);

        while (!queue.isEmpty()) {
            Integer poll = queue.poll();

            for (int u : adjacencyList.get(poll)) {
                if(!visited[u]) {
                    dis[u] = dis[poll] + 1;
                    visited[u] = true;
                    queue.add(u);
                }
            }
        }
        return dis;
    }

    private static void dfs(ArrayList<ArrayList<Integer>> adjacencyList, int source, boolean[] visited) {
        visited[source] = true;
        System.out.println(source);
        for (int u : adjacencyList.get(source)) {
            if (!visited[u]) {
                dfs(adjacencyList, u, visited );
            }
        }
    }

    private static void dfsDisconnected(ArrayList<ArrayList<Integer>> adjacencyList, int V) {
        boolean[] visited = new boolean[V];
        for (int i = 0; i < V; i++) {
            if(!visited[i]) {
                dfs(adjacencyList, i, visited);
            }
        }
    }

    private static void bfs(ArrayList<ArrayList<Integer>> adjacencyList, int V, int source, boolean[] visited) {

        Queue<Integer> queue = new LinkedList<>();
        visited[source] = true;
        queue.add(source);

        while (queue.size()!=0) {
            Integer poll = queue.poll();
            System.out.println(poll);
            ArrayList<Integer> neighbours = adjacencyList.get(poll);
            for (int i = 0; i <neighbours.size(); i++) {
                if(visited[neighbours.get(i)] == false) {
                    visited[neighbours.get(i)] = true;
                    queue.add(neighbours.get(i));
                }
            }
        }
    }

    private static void bfsDisconnected(ArrayList<ArrayList<Integer>> adjacencyList, int V) {
        boolean[] visited = new boolean[V+1];
        for (int i = 0; i < V; i++) {
            if(!visited[i]) {
                bfs(adjacencyList, V, i, visited);
            }
        }
    }

    private static int countBfsDisconnected(ArrayList<ArrayList<Integer>> adjacencyList, int V) {
        boolean[] visited = new boolean[V+1];
        int count = 0;
        for (int i = 0; i < V; i++) {
            if(!visited[i]) {
                bfs(adjacencyList, V, i, visited);
                count++;
            }
        }
        return count;
    }

    private static void graphRepresentation(int V) {

        adjacencyList = new ArrayList<>(V);

        for (int i = 0; i < V; i++) {
            adjacencyList.add(new ArrayList<>());
        }
    }

    private static void addEdge(ArrayList<ArrayList<Integer>> adjacencyList, int u, int v) {
        adjacencyList.get(u).add(v);
        adjacencyList.get(v).add(u);
    }

    private static void print(ArrayList<ArrayList<Integer>> adjacencyList) {
        IntStream.range(0, adjacencyList.size()).forEach(i->{
            ArrayList<Integer> innerList = adjacencyList.get(i);
            innerList.forEach(System.out::println);
            System.out.println();
        });
    }
}
