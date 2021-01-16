package com.algorithm.图;

/*
* 有一个 m x n 的二元网格，其中 1 表示砖块，0 表示空白。砖块 稳定（不会掉落）的前提是：

一块砖直接连接到网格的顶部，或者
至少有一块相邻（4 个方向之一）砖块 稳定 不会掉落时
给你一个数组 hits ，这是需要依次消除砖块的位置。每当消除 hits[i] = (rowi, coli) 位置上的砖块时，对应位置的砖块（若存在）会消失，然后其他的砖块可能因为这一消除操作而掉落。一旦砖块掉落，它会立即从网格中消失（即，它不会落在其他稳定的砖块上）。

返回一个数组 result ，其中 result[i] 表示第 i 次消除操作对应掉落的砖块数目。

注意，消除可能指向是没有砖块的空白位置，如果发生这种情况，则没有砖块掉落。

 

示例 1：

输入：grid = [[1,0,0,0],[1,1,1,0]], hits = [[1,0]]
输出：[2]
解释：
网格开始为：
[[1,0,0,0]，
 [1,1,1,0]]
消除 (1,0) 处加粗的砖块，得到网格：
[[1,0,0,0]
 [0,1,1,0]]
两个加粗的砖不再稳定，因为它们不再与顶部相连，也不再与另一个稳定的砖相邻，因此它们将掉落。得到网格：
[[1,0,0,0],
 [0,0,0,0]]
因此，结果为 [2] 。
示例 2：

输入：grid = [[1,0,0,0],[1,1,0,0]], hits = [[1,1],[1,0]]
输出：[0,0]
解释：
网格开始为：
[[1,0,0,0],
 [1,1,0,0]]
消除 (1,1) 处加粗的砖块，得到网格：
[[1,0,0,0],
 [1,0,0,0]]
剩下的砖都很稳定，所以不会掉落。网格保持不变：
[[1,0,0,0],
 [1,0,0,0]]
接下来消除 (1,0) 处加粗的砖块，得到网格：
[[1,0,0,0],
 [0,0,0,0]]
剩下的砖块仍然是稳定的，所以不会有砖块掉落。
因此，结果为 [0,0] 。
 

提示：

m == grid.length
n == grid[i].length
1 <= m, n <= 200
grid[i][j] 为 0 或 1
1 <= hits.length <= 4 * 104
hits[i].length == 2
0 <= xi <= m - 1
0 <= yi <= n - 1
所有 (xi, yi) 互不相同
* */

/*
* 可以抽象出一个特殊的节点 XX：任何与网格顶部直接相邻的砖块对应的节点，都与 XX 有一条直接相邻的边。

当消除一个砖块时，相当于从图中直接移除掉对应的节点，以及所有与它相邻的边。如果在移除节点之后，某一节点 pp 不再与 XX 连通，则说明对应的砖块「掉落」。于是，任意时刻留在网格中的砖块数量，就等于图中与 XX 连通的节点数量。

第一眼看上去，读者不难想到利用并查集来维护节点的连通关系。然而，此问题的难点在于，并查集只能支持对两个连通分支的合并，而无法将一个连通分支拆成两个。

我们不妨从后向前考虑这一过程。初始时，图中具有多个独立的连通分支；每次操作会在网格中添加一个砖块，于是在图中添加一个新的节点以及对应的边。注意到这一操作可能会使得更多的节点与 XX 相连，这些多出来的节点数量，就为从前向后考虑问题时，该操作掉落的砖块数量。

作者：LeetCode-Solution
链接：https://leetcode-cn.com/problems/bricks-falling-when-hit/solution/da-zhuan-kuai-by-leetcode-solution-szrq/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
* */

class 打砖块_并查集 {
    public int[] hitBricks(int[][] grid, int[][] hits) {
        int h = grid.length, w = grid[0].length;
        UnionFind uf = new UnionFind(h * w + 1);
        int[][] status = new int[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                status[i][j] = grid[i][j];
            }
        }
        for (int i = 0; i < hits.length; i++) {
            status[hits[i][0]][hits[i][1]] = 0;
        }
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (status[i][j] == 1) {
                    if (i == 0) {
                        uf.merge(h * w, i * w + j);
                    }
                    if (i > 0 && status[i - 1][j] == 1) {
                        uf.merge(i * w + j, (i - 1) * w + j);
                    }
                    if (j > 0 && status[i][j - 1] == 1) {
                        uf.merge(i * w + j, i * w + j - 1);
                    }
                }
            }
        }
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int[] ret = new int[hits.length];
        for (int i = hits.length - 1; i >= 0; i--) {
            int r = hits[i][0], c = hits[i][1];
            if (grid[r][c] == 0) {
                continue;
            }
            int prev = uf.size(h * w);
            if (r == 0) {
                uf.merge(c, h * w);
            }
            for (int[] direction: directions) {
                int dr = direction[0], dc = direction[1];
                int nr = r + dr, nc = c + dc;

                if (nr >= 0 && nr < h && nc >= 0 && nc < w) {
                    if (status[nr][nc] == 1) {
                        uf.merge(r * w + c, nr * w + nc);
                    }
                }
            }
            int size = uf.size(h * w);
            ret[i] = Math.max(0, size - prev - 1);
            status[r][c] = 1;
        }
        return ret;
    }
}

class UnionFind {
    int[] f;
    int[] sz;

    public UnionFind(int n) {
        f = new int[n];
        sz = new int[n];
        for (int i = 0; i < n; i++) {
            f[i] = i;
            sz[i] = 1;
        }
    }

    public int find(int x) {
        if (f[x] == x) {
            return x;
        }
        int newf = find(f[x]);
        f[x] = newf;
        return f[x];
    }

    public void merge(int x, int y) {
        int fx = find(x);
        int fy = find(y);
        if (fx == fy) {
            return;
        }
        f[fx] = fy;
        sz[fy] += sz[fx];
    }

    public int size(int x) {
        return sz[find(x)];
    }
}
//暴力dfs超时
// class Solution {
//     int count = 0;
//     int[] dx = new int[] {0, 0, 1, -1};
//     int[] dy = new int[] {1, -1, 0, 0};
//     boolean[][] visited;
//     public int[] hitBricks(int[][] grid, int[][] hits) {
//         int[] res = new int[hits.length];
//         visited = new boolean[grid.length][grid[0].length];
//         for (int i = 0; i < hits.length; i++) {
//             int[] hit = hits[i];
//             int m = hit[0];
//             int n = hit[1];
//             if (grid[m][n] == 0) {
//                 continue;
//             }
//             visited[m][n] = true;
//             for (int k = 0; k < 4; k++) {
//                 int newM = m + dx[k];
//                 int newN = n + dy[k];
//                 if (newM >= 0 && newM < grid.length && newN >= 0 && newN < grid[0].length) {
//                     if (!check(newM, newN, grid)) {
//                         change(newM, newN, grid);
//                     }
//                 }
//             }
//             grid[m][n] = 0;
//             visited[m][n] = false;
//             res[i] = count;
//             count = 0;
//         }
//         return res;
//     }
//     public boolean check(int i, int j, int[][] grid) {
//         if (grid[i][j] == 0) {
//             return false;
//         }
//         if (i == 0) {
//             return true;
//         }
//         visited[i][j] = true;
//         boolean f = false;
//         for (int k = 0; k < 4; k++) {
//             int newI = i + dx[k];
//             int newJ = j + dy[k];
//             if (newI >= 0 && newI < grid.length && newJ >= 0 && newJ < grid[0].length && !visited[newI][newJ]) {
//                 if (check(newI, newJ, grid)) {
//                     f = true;
//                 }
//             }
//         }
//         visited[i][j] = false;
//         if (f) {
//             return true;
//         }
//         return false;
//     }
//     public void change(int i, int j, int[][] grid) {
//         if (grid[i][j] == 0) {
//             return;
//         }
//         visited[i][j] = true;
//         for (int k = 0; k < 4; k++) {
//             int newI = i + dx[k];
//             int newJ = j + dy[k];
//             if (newI >= 0 && newI < grid.length && newJ >= 0 && newJ < grid[0].length && !visited[newI][newJ]) {
//                 change(newI, newJ, grid);
//             }
//         }
//         visited[i][j] = false;
//         grid[i][j] = 0;
//         count++;
//     }
// }