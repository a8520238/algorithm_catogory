package com.algorithm.图;

/*
* n 块石头放置在二维平面中的一些整数坐标点上。每个坐标点上最多只能有一块石头。

如果一块石头的 同行或者同列 上有其他石头存在，那么就可以移除这块石头。

给你一个长度为 n 的数组 stones ，其中 stones[i] = [xi, yi] 表示第 i 块石头的位置，返回 可以移除的石子 的最大数量。

 

示例 1：

输入：stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
输出：5
解释：一种移除 5 块石头的方法如下所示：
1. 移除石头 [2,2] ，因为它和 [2,1] 同行。
2. 移除石头 [2,1] ，因为它和 [0,1] 同列。
3. 移除石头 [1,2] ，因为它和 [1,0] 同行。
4. 移除石头 [1,0] ，因为它和 [0,0] 同列。
5. 移除石头 [0,1] ，因为它和 [0,0] 同行。
石头 [0,0] 不能移除，因为它没有与另一块石头同行/列。
示例 2：

输入：stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
输出：3
解释：一种移除 3 块石头的方法如下所示：
1. 移除石头 [2,2] ，因为它和 [2,0] 同行。
2. 移除石头 [2,0] ，因为它和 [0,0] 同列。
3. 移除石头 [0,2] ，因为它和 [0,0] 同行。
石头 [0,0] 和 [1,1] 不能移除，因为它们没有与另一块石头同行/列。
示例 3：

输入：stones = [[0,0]]
输出：0
解释：[0,0] 是平面上唯一一块石头，所以不可以移除它。
 

提示：

1 <= stones.length <= 1000
0 <= xi, yi <= 104
不会有两块石头放在同一个坐标点上
* */

import java.util.HashMap;
import java.util.Map;

//并查集
class Solution {
    public int removeStones(int[][] stones) {
        UnionFind unionFind = new UnionFind();
        for (int[] stone: stones) {
            unionFind.union(stone[0] + 10001, stone[1]);
        }
        return stones.length - unionFind.getCount();
    }
    private class UnionFind {
        private Map<Integer, Integer> parent;
        private int count;

        public UnionFind() {
            this.parent = new HashMap<>();
            this.count = 0;
        }
        public int getCount() {
            return count;
        }
        public int find(int x) {
            if (!parent.containsKey(x)) {
                parent.put(x, x);
                count++;
            }
            if (x != parent.get(x)) {
                parent.put(x, find(parent.get(x)));
            }
            return parent.get(x);
        }
        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return;
            }
            parent.put(rootX, rootY);
            count--;
        }
    }
}

//官方dfs,反而没有我的快
// class Solution {
//     public int removeStones(int[][] stones) {
//         int n = stones.length;
//         List<List<Integer>> edge = new ArrayList<>();
//         for (int i = 0; i < n; i++) {
//             edge.add(new ArrayList<>());
//             for (int j = 0; j < n; j++) {
//                 if (stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1]) {
//                     edge.get(i).add(j);
//                 }
//             }
//         }
//         boolean[] vis = new boolean[n];
//         int num = 0;
//         for (int i = 0; i < n; i++) {
//             if (!vis[i]) {
//                 num++;
//                 dfs(i, edge, vis);
//             }
//         }
//         return n - num;
//     }
//     public void dfs(int x, List<List<Integer>> edge, boolean[] vis) {
//         vis[x] = true;
//         for (int y: edge.get(x)) {
//             if (!vis[y]) {
//                 dfs(y, edge, vis);
//             }
//         }
//     }
// }

//自己写的dfs
// class Solution {
//     Map<int[], Integer> map = new HashMap<>();
//     Map<Integer, int[]> map1 = new HashMap<>();
//     Map<Integer, List<Integer>> rows = new HashMap<>();
//     Map<Integer, List<Integer>> cols = new HashMap<>();
//     int res = 0;
//     public int removeStones(int[][] stones) {
//         int id = 0;
//         for (int[] stone: stones) {
//             map.put(stone, id);
//             map1.put(id++, stone);
//         }
//         for (int[] stone: stones) {
//             int row = stone[0];
//             int col = stone[1];
//             if (!rows.containsKey(row)) {
//                 rows.put(row, new ArrayList<>());
//             }
//             if (!cols.containsKey(col)) {
//                 cols.put(col, new ArrayList<>());
//             }
//             int arrId = map.get(stone);
//             rows.get(row).add(arrId);
//             cols.get(col).add(arrId);
//         }
//         boolean[] visited = new boolean[id];
//         for (int[] stone: stones) {
//             int curId = map.get(stone);
//             if (visited[curId]) {
//                 continue;
//             }
//             dfs(visited, stone);
//         }
//         return res;
//     }
//     public void dfs(boolean[] visited, int[] stone) {
//         int curId = map.get(stone);
//         visited[curId] = true;
//         int row = stone[0];
//         int col = stone[1];
//         List<Integer> edgeRow = rows.get(row);
//         List<Integer> edgeCol = cols.get(col);
//         for (int rId: edgeRow) {
//             if (!visited[rId]) {
//                 dfs(visited, map1.get(rId));
//                 res++;
//             }
//         }
//         for (int lId: edgeCol) {
//             if (!visited[lId]) {
//                 dfs(visited, map1.get(lId));
//                 res++;
//             }
//         }
//     }
// }
