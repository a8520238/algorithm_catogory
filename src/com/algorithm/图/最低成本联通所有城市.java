package com.algorithm.图;

/*
* 想象一下你是个城市基建规划者，地图上有 N 座城市，它们按以 1 到 N 的次序编号。

给你一些可连接的选项 conections，其中每个选项 conections[i] = [city1, city2, cost] 表示将城市 city1 和城市 city2 连接所要的成本。（连接是双向的，也就是说城市 city1 和城市 city2 相连也同样意味着城市 city2 和城市 city1 相连）。

返回使得每对城市间都存在将它们连接在一起的连通路径（可能长度为 1 的）最小成本。该最小成本应该是所用全部连接代价的综合。如果根据已知条件无法完成该项任务，则请你返回 -1。

 */

//这道题可以用Prim也可以用迪杰斯特拉加并查集
//搞混了prim和迪杰斯特拉失败
// class 最低成本联通所有城市 {
//     public int minimumCost(int N, int[][] connections) {
//         List<List<Integer>> edges = new ArrayList<>();
//         Map<Integer, Integer> map = new HashMap<>();
//         for (int[] connection: connections) {
//             int from = connection[0];
//             int to = connection[1];
//             if (!map.containsKey(from)) {
//                 edges.add(new ArrayList<>());
//                 map.put(from, edges.size() - 1);
//             }
//             if (!map.containsKey(to)) {
//                 edges.add(new ArrayList<>());
//                 map.put(to, edges.size() - 1);
//             }
//             edges.get(map.get(from)).add(to);
//             edges.get(map.get(to)).add(from);
//         }
//         boolean[] visited = new boolean[edges.size()];
//         Queue<Integer> queue1 = new LinkedList<>();
//         queue1.add(1);
//         while (!queue1.isEmpty()) {
//             int cur = queue1.poll();
//             List<Integer> curList = edges.get(map.get(cur));
//             visited[cur - 1] = true;
//             for (int n: curList) {
//                 if (!visited[n - 1]) {
//                     queue1.add(n);
//                 }
//             }
//         }
//         for (boolean f: visited) {
//             if (!f) {
//                 return -1;
//             }
//         }
//         PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> (a[2] - b[2]));
//         // int max = 0;
//         for (int[] connection: connections) {
//             queue.add(connection);
//             // max = Math.max(Math.max(connection[0], connection[1]), max);
//         }
//         // boolean[] visited = new boolean[max];
//         visited = new boolean[edges.size()];
//         System.out.println(queue);
//         int res = 0;
//         while (!queue.isEmpty()) {
//             int[] cur = queue.poll();
//             if (visited[cur[0] - 1] && visited[cur[1] - 1]) {
//                 continue;
//             }
//             visited[cur[0] - 1] = true;
//             visited[cur[1] - 1] = true;
//             res += cur[2];
//         }
//         return res;
//     }
// }
//迪杰斯特拉要和并查集共用
// class 最低成本联通所有城市 {
//     public int minimumCost(int N, int[][] connections) {
//         Arrays.sort(connections, (a, b) -> a[2] - b[2]);
//         int[] parent = new int[N + 1];
//         for (int i = 1; i <= N; i++) {
//             parent[i] = i;
//         }
//         int cost = 0;
//         for (int[] edge: connections) {
//             if (union(edge[0], edge[1], parent)) {
//                 cost += edge[2];
//             }
//         }
//         int p = -1;
//         for (int i = 1; i <= N; i++) {
//             int root = findRoot(i, parent);
//             if (p == -1) {
//                 p = root;
//             } else if (p != root) {
//                 return -1;
//             }
//         }
//         return cost;
//     }
//     public int findRoot(int x, int[] parent) {
//         //判断当前节点是否为头结点
//         //如果不是，它的父节点应该是父节点的父节点
//         //当前节点等于父节点循环
//         while (x != parent[x]) {
//             parent[x] = parent[parent[x]];
//             x = parent[x];
//         }
//         return x;
//     }
//     public boolean union(int a, int b, int[] parent) {
//         a = findRoot(a, parent);
//         b = findRoot(b, parent);
//         if (a == b) {
//             return false;
//         }
//         parent[a] = b;
//         return true;
//     }
// }

import java.util.*;

//prim算法
class 最低成本联通所有城市 {
    public int minimumCost(int N, int[][] connections) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] edge: connections) {
            int u = edge[0], v = edge[1], w = edge[2];
            List<int[]> list1 = graph.get(u);
            if (list1 == null) {
                list1 = new ArrayList<>();
                graph.put(u, list1);
            }
            list1.add(new int[]{v, w});

            List<int[]> list2 = graph.get(v);
            if (list2 == null) {
                list2 = new LinkedList<>();
                graph.put(v, list2);
            }
            list2.add(new int[] {u, w});
        }

        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        boolean[] added = new boolean[N + 1];

        heap.offer(new int[] {1, 0});
        int cost = 0;
        for (int k = 0; k < N; k++) {
            int[] min = findMin(heap, added);
            if (min == null) {
                return -1;
            }
            int node = min[0];
            int dist = min[1];
            cost += dist;
            added[node] = true;
            List<int[]> list = graph.get(node);
            if (list != null) {
                for (int[] e: list) {
                    heap.add(e);
                }
            }
        }
        return cost;
    }
    public int[] findMin(PriorityQueue<int[]> heap, boolean[] added) {
        while (heap.size() > 0) {
            int[] n = heap.poll();
            int node = n[0];
            if (!added[node]) {
                return n;
            }
        }
        return null;
    }
}