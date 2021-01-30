package com.algorithm.图;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Author Liguangzhe
 * @Date created in 2021:1:30 2021/1/30
 */

/*
* 你准备参加一场远足活动。给你一个二维 rows x columns 的地图 heights ，其中 heights[row][col] 表示格子 (row, col) 的高度。一开始你在最左上角的格子 (0, 0) ，且你希望去最右下角的格子 (rows-1, columns-1) （注意下标从 0 开始编号）。你每次可以往 上，下，左，右 四个方向之一移动，你想要找到耗费 体力 最小的一条路径。

一条路径耗费的 体力值 是路径上相邻格子之间 高度差绝对值 的 最大值 决定的。

请你返回从左上角走到右下角的最小 体力消耗值 。



示例 1：



输入：heights = [[1,2,2],[3,8,2],[5,3,5]]
输出：2
解释：路径 [1,3,5,3,5] 连续格子的差值绝对值最大为 2 。
这条路径比路径 [1,2,2,2,5] 更优，因为另一条路径差值最大值为 3 。


示例 2：



输入：heights = [[1,2,3],[3,8,4],[5,3,5]]
输出：1
解释：路径 [1,2,3,4,5] 的相邻格子差值绝对值最大为 1 ，比路径 [1,3,5,3,5] 更优。


示例 3：

输入：heights = [[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]
输出：0
解释：上图所示路径不需要消耗任何体力。




提示：


	rows == heights.length
	columns == heights[i].length
	1 <= rows, columns <= 100
	1 <= heights[i][j] <= 106
* */
// class Solution {
//     //回溯不对 不符合题意 回溯求的是和
//     int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
//     int ans = Integer.MAX_VALUE;
//     int m, n;
//     boolean[] seen;
//     public int minimumEffortPath(int[][] heights) {
//         m = heights.length;
//         n = heights[0].length;
//         seen = new boolean[m * n];
//         backward(heights, 0, 0, 0);
//         return ans;
//     }
//     public void backward(int[][] heights, int x, int y, int cur) {
//         if (x == heights.length - 1 && y == heights[0].length - 1) {
//             ans = Math.min(ans, cur);
//             return;
//         }
//         for (int i = 0; i < 4; i++) {
//             int nx = x + dirs[i][0];
//             int ny = y + dirs[i][1];
//             if (nx >= 0 && nx < m && ny >= 0 && ny < n && !seen[nx * n + ny]) {
//                 seen[nx * n + ny] = true;
//                 backward(heights, nx, ny, cur + Math.abs(heights[x][y] - heights[nx][ny]));
//                 seen[nx * n + ny] = false;
//             }
//         }
//     }
// }

// class Solution {
//     //广度优先 + 二分
//     int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
//     public int minimumEffortPath(int[][] heights) {
//         int m = heights.length;
//         int n = heights[0].length;
//         int left = 0, right = 999999, ans = 0;
//         while (left <= right) {
//             int mid = (right - left) / 2 + left;
//             Queue<int[]> queue = new LinkedList<>();
//             queue.offer(new int[]{0, 0});
//             boolean[] seen = new boolean[m * n];
//             seen[0] = true;
//             while (!queue.isEmpty()) {
//                 int[] cell = queue.poll();
//                 int x = cell[0], y = cell[1];
//                 for (int i = 0; i < 4; i++) {
//                     int nx = x + dirs[i][0];
//                     int ny = y + dirs[i][1];
//                     if (nx >= 0 && nx < m && ny >= 0 && ny < n && !seen[nx * n + ny] && Math.abs(heights[x][y] - heights[nx][ny]) <= mid) {
//                         queue.offer(new int[]{nx, ny});
//                         seen[nx * n + ny] = true;
//                     }
//                 }
//             }
//             if (seen[m * n - 1]) {
//                 ans = mid;
//                 right = mid - 1;
//             } else {
//                 left = mid + 1;
//             }
//         }
//         return ans;
//     }
// }


// class Solution {
//     public int minimumEffortPath(int[][] heights) {
//         int m = heights.length;
//         int n = heights[0].length;
//         List<int[]> edges = new ArrayList<int[]>();
//         for (int i = 0; i < m; ++i) {
//             for (int j = 0; j < n; ++j) {
//                 int id = i * n + j;
//                 if (i > 0) {
//                     edges.add(new int[]{id - n, id, Math.abs(heights[i][j] - heights[i - 1][j])});
//                 }
//                 if (j > 0) {
//                     edges.add(new int[]{id - 1, id, Math.abs(heights[i][j] - heights[i][j - 1])});
//                 }
//             }
//         }
//         Collections.sort(edges, new Comparator<int[]>() {
//             public int compare(int[] edge1, int[] edge2) {
//                 return edge1[2] - edge2[2];
//             }
//         });

//         UnionFind uf = new UnionFind(m * n);
//         int ans = 0;
//         for (int[] edge : edges) {
//             int x = edge[0], y = edge[1], v = edge[2];
//             uf.unite(x, y);
//             if (uf.connected(0, m * n - 1)) {
//                 ans = v;
//                 break;
//             }
//         }
//         return ans;
//     }
// }

// // 并查集模板
// class UnionFind {
//     int[] parent;
//     int[] size;
//     int n;
//     // 当前连通分量数目
//     int setCount;

//     public UnionFind(int n) {
//         this.n = n;
//         this.setCount = n;
//         this.parent = new int[n];
//         this.size = new int[n];
//         Arrays.fill(size, 1);
//         for (int i = 0; i < n; ++i) {
//             parent[i] = i;
//         }
//     }

//     public int findset(int x) {
//         return parent[x] == x ? x : (parent[x] = findset(parent[x]));
//     }

//     public boolean unite(int x, int y) {
//         x = findset(x);
//         y = findset(y);
//         if (x == y) {
//             return false;
//         }
//         if (size[x] < size[y]) {
//             int temp = x;
//             x = y;
//             y = temp;
//         }
//         parent[y] = x;
//         size[x] += size[y];
//         --setCount;
//         return true;
//     }

//     public boolean connected(int x, int y) {
//         x = findset(x);
//         y = findset(y);
//         return x == y;
//     }
// }

//迪杰斯特拉 效率最高
class 最小体力消耗路径 {
    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int minimumEffortPath(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
            public int compare(int[] edge1, int[] edge2) {
                return edge1[2] - edge2[2];
            }
        });
        pq.offer(new int[]{0, 0, 0});

        int[] dist = new int[m * n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;
        boolean[] seen = new boolean[m * n];

        while (!pq.isEmpty()) {
            int[] edge = pq.poll();
            int x = edge[0], y = edge[1], d = edge[2];
            int id = x * n + y;
            if (seen[id]) {
                continue;
            }
            if (x == m - 1 && y == n - 1) {
                break;
            }
            seen[id] = true;
            for (int i = 0; i < 4; ++i) {
                int nx = x + dirs[i][0];
                int ny = y + dirs[i][1];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && Math.max(d, Math.abs(heights[x][y] - heights[nx][ny])) < dist[nx * n + ny]) {
                    dist[nx * n + ny] = Math.max(d, Math.abs(heights[x][y] - heights[nx][ny]));
                    pq.offer(new int[]{nx, ny, dist[nx * n + ny]});
                }
            }
        }

        return dist[m * n - 1];
    }
}