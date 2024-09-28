package com.dsa;

public class Backtracking {

    static boolean[][] maze = new boolean[3][3];
    static boolean[][] soln = new boolean[3][3];
    static int N = maze.length;

    public static void main(String[] args) {
        maze = new boolean[][]{{true, false, true},
            {false, false, false},
            {false, false, false}};
//        System.out.println(solveMaze());

//        String str = "ABC";
//        permute(str, 0, str.length()-1);
//        System.out.println(solveQueen(N));
        System.out.println(solveGrid());

    }

    private static boolean solveGrid() {

        int n =3;
        int i,j=0;
        int grid[][] = new int[n][n];
        for ( i = 0; i <n ; i++) {
            for ( j = 0; j <n; j++) {
                if(grid[i][j]==0){
                    break;
                }
            }
        }
        if(i==N && j==N){
            return true;
        }
        return false;
    }

    private static boolean grid(int row, int col, int n, int[][] grid) {
        for (int i = 1; i <=n ; i++) {
            if(isGridSafe(i,row,col,3, grid)){
                grid[row][col] = i;
                if(solveGrid()) return true;
                else {
                    grid[row][col] = 0;
                }
            }
        }
        return false;
    }

    private static boolean isGridSafe(int n, int row, int col, int N, int[][] grid){
        for (int k = 0; k <N; k++) {
            if(grid[row][k]==n || grid[col][k]==n) {
                return false;
            }
        }

        int sqrt = (int) Math.sqrt(N);
        int rs = row-row%sqrt;
        int cs = col-col%sqrt;

        for (int i = 0; i <sqrt ; i++) {
            for (int j = 0; j <sqrt ; j++) {
                if(grid[i+rs][j+cs]==n) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean solveQueen(int n) {
        if(queen(0) == false) {
            return false;
        } else {
            print(soln);
            return true;
        }
    }

    private static boolean queen(int col) {
        if(col==N){
            return true;
        }
        for (int i = 0; i < N ; i++) {
            if(isQueenSafe(i, col)) {
                soln[i][col] = true;
                if(queen(col+1))
                    return true;
                soln[i][col] = false;
            }
        }
        return false;
    }

    private static boolean isQueenSafe(int row, int col) {
        for (int i = 0; i < col; i++) {
            if(soln[row][i]) {
                return false;
            }
        }
        for (int i = row, j=col; i >=0 && j>=0 ; i--,j--) {

            if(soln[i][j]){
                return false;
            }
        }
        for (int i = row,j=col; j >=0 && i<N ; i++,j--) {
            if(soln[i][j]){
                return false;
            }
        }
        return true;
    }

    private static void permute(String str, int low, int high) {
        if(low==high) {
            System.out.println(str + " ");
        }
        else {
            for (int i = low; i <= high; i++) {
                if (isSafePermute(str, low, i, high)) {
                    str = swap(str, low, i);
                    permute(str, low + 1, high);
                    str = swap(str, low, i);
                }
            }
        }
    }

    private static String swap(String str, int i, int j) {
        char[] charArray = str.toCharArray();
        char temp = charArray[i];
        charArray[i] = charArray[j];
        charArray[j] = temp;
        return String.valueOf(charArray);
    }

    private static boolean isSafePermute(String str, int low, int i, int high) {

        if(low!=0 && str.charAt(low-1) == 'A' && str.charAt(i) == 'B') {
            return false;
        }
        if (high == low + 1 && str.charAt(i) == 'A'
            && str.charAt(low) == 'B'
            || high == low + 1 && low == i
            && str.charAt(high) == 'B'
            && str.charAt(low) == 'A')
            return false;
        return true;
    }

    private static boolean solveMaze() {
        if(maze(0,0)) {
//            print(soln);
            return true;
        } else {
            return false;
        }
    }

    private static boolean maze(int i, int j) {
        if(i==N-1 && j == N-1){
            soln[i][j] = true;
            return true;
        }
        if(isSafeMaze(i,j)) {
            soln[i][j]=true;
            if(maze(i+1,j)==true) return true;
            else if (maze(i,j+1)== true) return true;
            soln[i][j] = false;
        }
        return false;
    }

    private static boolean isSafeMaze(int i, int j) {
        return i<N && j<N && maze[i][j]==true;
    }

    private static void print(boolean[][] print) {
        for (int i = 0; i <N; i++) {
            for (int j = 0; j <N; j++) {
                System.out.println(print[i][j]);
            }

        }
    }
}
