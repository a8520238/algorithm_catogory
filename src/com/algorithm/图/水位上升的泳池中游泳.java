package com.algorithm.图;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @Author Liguangzhe
 * @Date created in 2021:1:31 2021/1/31
 */

/*
* 在一个 N x N 的坐标方格 grid 中，每一个方格的值 grid[i][j] 表示在位置 (i,j) 的平台高度。

现在开始下雨了。当时间为 t 时，此时雨水导致水池中任意位置的水位为 t 。你可以从一个平台游向四周相邻的任意一个平台，但是前提是此时水位必须同时淹没这两个平台。假定你可以瞬间移动无限距离，也就是默认在方格内部游动是不耗时的。当然，在你游泳的时候你必须待在坐标方格里面。

你从坐标方格的左上平台 (0，0) 出发。最少耗时多久你才能到达坐标方格的右下平台 (N-1, N-1)？



示例 1:

输入: [[0,2],[1,3]]
输出: 3
解释:
时间为0时，你位于坐标方格的位置为 (0, 0)。
此时你不能游向任意方向，因为四个相邻方向平台的高度都大于当前时间为 0 时的水位。

等时间到达 3 时，你才可以游向平台 (1, 1). 因为此时的水位是 3，坐标方格中的平台没有比水位 3 更高的，所以你可以游向坐标方格中的任意位置


示例2:

输入: [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]
输出: 16
解释:
 0  1  2  3  4
24 23 22 21  5
12 13 14 15 16
11 17 18 19 20
10  9  8  7  6

最终的路线用加粗进行了标记。
我们必须等到时间为 16，此时才能保证平台 (0, 0) 和 (4, 4) 是连通的




提示:


	2 <= N <= 50.
	grid[i][j] 是 [0, ..., N*N - 1] 的排列。
* */

//二分

// class Solution {

//     private int N;

//     public static final int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

//     public int swimInWater(int[][] grid) {
//         N = grid.length;

//         int left = 0;
//         int right = N * N - 1;
//         while (left < right) {
//             int mid = (right - left) / 2 + left;
//             boolean[][] visited = new boolean[N][N];
//             if (grid[0][0] <= mid && dfs(grid, 0, 0, visited, mid)) {
//                 right = mid;
//             } else {
//                 left = mid + 1;
//             }
//         }
//         return left;
//     }
//     private boolean dfs(int[][] grid, int x, int y, boolean[][] visited, int threshold) {
//         visited[x][y] = true;
//         for (int[] dir: dirs) {
//             int newX = x + dir[0];
//             int newY = y + dir[1];
//             if (inArea(newX, newY) && !visited[newX][newY] && grid[newX][newY] <= threshold) {
//                 if (newX == N - 1 && newY == N - 1) {
//                     return true;
//                 }
//                 if (dfs(grid, newX, newY, visited, threshold)) {
//                     return true;
//                 }
//             }
//         }
//         return false;
//     }
//     private boolean inArea(int x, int y) {
//         return x >= 0 && x < N && y >= 0 && y < N;
//     }
// }

//并查集
// public class Solution {

//     private int N;

//     public static final int[][] DIRECTIONS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

//     public int swimInWater(int[][] grid) {
//         this.N = grid.length;

//         int len = N * N;
//         // 下标：方格的高度，值：对应在方格中的坐标
//         int[] index = new int[len];
//         for (int i = 0; i < N; i++) {
//             for (int j = 0; j < N; j++) {
//                 index[grid[i][j]] = getIndex(i, j);
//             }
//         }

//         UnionFind unionFind = new UnionFind(len);
//         for (int i = 0; i < len; i++) {
//             int x = index[i] / N;
//             int y = index[i] % N;

//             for (int[] direction : DIRECTIONS) {
//                 int newX = x + direction[0];
//                 int newY = y + direction[1];
//                 if (inArea(newX, newY) && grid[newX][newY] <= i) {
//                     unionFind.union(index[i], getIndex(newX, newY));
//                 }

//                 if (unionFind.isConnected(0, len - 1)) {
//                     return i;
//                 }
//             }
//         }
//         return -1;
//     }

//     private int getIndex(int x, int y) {
//         return x * N + y;
//     }

//     private boolean inArea(int x, int y) {
//         return x >= 0 && x < N && y >= 0 && y < N;
//     }

//     private class UnionFind {

//         private int[] parent;

//         public UnionFind(int n) {
//             this.parent = new int[n];
//             for (int i = 0; i < n; i++) {
//                 parent[i] = i;
//             }
//         }

//         public int root(int x) {
//             while (x != parent[x]) {
//                 parent[x] = parent[parent[x]];
//                 x = parent[x];
//             }
//             return x;
//         }

//         public boolean isConnected(int x, int y) {
//             return root(x) == root(y);
//         }

//         public void union(int p, int q) {
//             if (isConnected(p, q)) {
//                 return;
//             }
//             parent[root(p)] = root(q);
//         }
//     }
// }

//迪杰斯特拉
public class 水位上升的泳池中游泳 {

    // Dijkstra 算法（应用前提：没有负权边，找单源最短路径）

    public int swimInWater(int[][] grid) {
        int n = grid.length;

        Queue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(o -> grid[o[0]][o[1]]));
        minHeap.offer(new int[]{0, 0});

        boolean[][] visited = new boolean[n][n];
        // distTo[i][j] 表示：到顶点 [i, j] 须要等待的最少的时间
        int[][] distTo = new int[n][n];
        for (int[] row : distTo) {
            Arrays.fill(row, n * n);
        }
        distTo[0][0] = grid[0][0];

        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        while (!minHeap.isEmpty()) {
            // 找最短的边
            int[] front = minHeap.poll();
            int currentX = front[0];
            int currentY = front[1];
            if (visited[currentX][currentY]) {
                continue;
            }

            // 确定最短路径顶点
            visited[currentX][currentY] = true;
            if (currentX == n - 1 && currentY == n - 1) {
                return distTo[n - 1][n - 1];
            }

            // 更新
            for (int[] direction : directions) {
                int newX = currentX + direction[0];
                int newY = currentY + direction[1];
                if (inArea(newX, newY, n) && !visited[newX][newY] &&
                        Math.max(distTo[currentX][currentY], grid[newX][newY]) < distTo[newX][newY]) {
                    distTo[newX][newY] = Math.max(distTo[currentX][currentY], grid[newX][newY]);
                    minHeap.offer(new int[]{newX, newY});
                }
            }
        }
        return -1;
    }

    private boolean inArea(int x, int y, int n) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }
}
