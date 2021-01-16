package com.algorithm.图;

/*
* 有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。

省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。

给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而 isConnected[i][j] = 0 表示二者不直接相连。

返回矩阵中 省份 的数量。

 

示例 1：


输入：isConnected = [[1,1,0],[1,1,0],[0,0,1]]
输出：2
示例 2：


输入：isConnected = [[1,0,0],[0,1,0],[0,0,1]]
输出：3
 

提示：

1 <= n <= 200
n == isConnected.length
n == isConnected[i].length
isConnected[i][j] 为 1 或 0
isConnected[i][i] == 1
isConnected[i][j] == isConnected[j][i]

* */

//深度优先搜索
class 省份数量 {
    public int findCircleNum(int[][] isConnected) {
        int provinces = isConnected.length;
        boolean[] visited = new boolean[provinces];
        int circles = 0;
        for (int i = 0; i < provinces; i++) {
            if (!visited[i]) {
                dfs(isConnected, visited, provinces, i);
                circles++;
            }
        }
        return circles;
    }
    public void dfs(int[][] isConnected, boolean[] visited, int provinces, int i) {
        for (int j = 0; j < provinces; j++) {
            if (j == i) {
                continue;
            }
            if (isConnected[i][j] == 1 && !visited[j]) {
                visited[j] = true;
                dfs(isConnected, visited, provinces, j);
            }
        }
    }
}



//广度优先搜索
// class Solution {
//     public int findCircleNum(int[][] isConnected) {
//         int provinces = isConnected.length;
//         boolean[] visited = new boolean[provinces];
//         int circles = 0;
//         Queue<Integer> queue = new LinkedList<>();
//         for (int i = 0; i < provinces; i++) {
//             if (!visited[i]) {
//                 queue.add(i);
//                 while (!queue.isEmpty()) {
//                     int j = queue.poll();
//                     visited[j] = true;
//                     for (int k = 0; k < provinces; k++) {
//                         if (isConnected[j][k] == 1 && !visited[k]) {
//                             queue.add(k);
//                         }
//                     }
//                 }
//                 circles++;
//             }
//         }
//         return circles;
//     }
// }

//并查集
// class Solution {
//     public int findCircleNum(int[][] isConnected) {
//         int n = isConnected.length;
//         if (n < 2) {
//             return n;
//         }
//         unionSet set = new unionSet(n);
//         for (int i = n - 2; i >= 0; i--) {
//             for (int j = i + 1; j < n; j++) {
//                 if (isConnected[i][j] == 1) {
//                     set.union(i, j);
//                 }
//             }
//         }
//         return set.count;
//     }
//     class unionSet {
//         int count;
//         int[] parent;
//         unionSet(int n) {
//             parent = new int[n];
//             count = n;
//             for (int i = 0; i < n; i++) {
//                 parent[i] = i;
//             }
//         }
//         public int find(int i) {
//             while (parent[i] != i) {
//                 parent[i] = parent[parent[i]];
//                 i = parent[i];
//             }
//             return i;
//         }
//         public void union(int a, int b) {
//             int pa = find(a);
//             int pb = find(b);
//             if (pa != pb) {
//                 parent[pa] = pb;
//                 count--;
//             }
//         }
//     }
// }