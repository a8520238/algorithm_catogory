package com.algorithm.图;


import java.util.ArrayList;

/*
* 给定从 0 到 n-1 标号的 n 个结点，和一个无向边列表（每条边以结点对来表示），请编写一个函数用来判断这些边是否能够形成一个合法有效的树结构。

示例 1：

输入: n = 5, 边列表 edges = [[0,1], [0,2], [0,3], [1,4]]
输出: true
示例 2:

输入: n = 5, 边列表 edges = [[0,1], [1,2], [2,3], [1,3], [1,4]]
输出: false
注意：你可以假定边列表 edges 中不会出现重复的边。由于所有的边是无向边，边 [0,1] 和边 [1,0] 是相同的，因此不会同时出现在边列表 edges 中。

* */

//自己写的dfs
 class 以树判图 {
     //检测是否出现环结构
     int count = 0;
     public boolean validTree(int n, int[][] edges) {
         if (edges == null || edges.length == 0) {
             return n == 1;
         }
         boolean[] visited = new boolean[n];
         ArrayList<Integer>[] map = new ArrayList[n];
         for (int[] edge: edges) {
             if (map[edge[0]] == null) {
                 map[edge[0]] = new ArrayList<>();
             }
             if (map[edge[1]] == null) {
                 map[edge[1]] = new ArrayList<>();
             }
             map[edge[0]].add(edge[1]);
             map[edge[1]].add(edge[0]);
         }
         int i = 0;
         for (; i < map.length; i++) {
             if (map[i] != null) {
                 break;
             }
         }
         return dfs(visited, map, i, -1) && count == n;
     }
     public boolean dfs(boolean[] visited, ArrayList<Integer>[] map, int begin, int from) {
         if (visited[begin]) {
             return false;
         }
         visited[begin] = true;
         count++;
         ArrayList<Integer> list = map[begin];
         for (int i = 0; i < list.size(); i++) {
             if (list.get(i) == from) {
                 continue;
             }
             if (!dfs(visited, map, list.get(i), begin)) {
                 return false;
             }
         }
         return true;
     }
 }

//并查集
// class Solution {
//     public boolean validTree(int n, int[][] edges) {
//         DisjointSet set = new DisjointSet(n);
//         for (int[] edge: edges) {
//             if (!set.union(edge[0], edge[1])) {
//                 return false;
//             }
//         }
//         return set.count == 1;
//     }
//     class DisjointSet {
//         int n;
//         int[] parent;
//         int[] rank;
//         int count;
//         DisjointSet(int n) {
//             this.n = n;
//             this.parent = new int[n];
//             Arrays.fill(parent, -1);
//             this.rank = new int[n];
//             this.count = n;
//         }
//         private int findRoot(int x) {
//             int root = x;
//             while (parent[root] != -1) {
//                 root = parent[root];
//             }
//             return root;
//         }
//         public boolean union(int x, int y) {
//             int xRoot = findRoot(x);
//             int yRoot = findRoot(y);
//             if (xRoot == yRoot) {
//                 return false;
//             }
//             if (rank[xRoot] > rank[yRoot]) {
//                 parent[yRoot] = xRoot;
//             } else if (rank[xRoot] < rank[yRoot]) {
//                 parent[xRoot] = yRoot;
//             } else if (rank[xRoot] == rank[yRoot]) {
//                 parent[xRoot] = yRoot;
//                 rank[yRoot]++;
//             }
//             count--;
//             return true;
//         }
//     }
// }

//BFS
// class Solution {
//     public boolean validTree(int n, int[][] edges) {
//         int[][] graph = new int[n][n];
//         for (int[] edge: edges) {
//             graph[edge[0]][edge[1]] = 1;
//             graph[edge[1]][edge[0]] = 1;
//         }
//         Queue<Integer> queue = new LinkedList<>();
//         queue.add(0);
//         boolean[] visited = new boolean[n];
//         while (!queue.isEmpty()) {
//             Integer cur = queue.poll();
//             visited[cur] = true;
//             for (int i = 0; i < n; i++) {
//                 if (graph[cur][i] == 1) {
//                     if (visited[i]) {
//                         return false;
//                     }
//                     visited[i] = true;
//                     graph[cur][i] = 0;
//                     graph[i][cur] = 0;
//                     queue.add(i);
//                 }
//             }
//         }
//         for (int i = 0; i < n; i++) {
//             if (!visited[i]) {
//                 return false;
//             }
//         }
//         return true;
//     }
// }

// class Solution {
//     public boolean validTree(int n, int[][] edges) {
//         int[][] graph = new int[n][n];
//         for (int[] edge: edges) {
//             graph[edge[0]][edge[1]] = 1;
//             graph[edge[1]][edge[0]] = 1;
//         }
//         Stack<Integer> stack = new Stack<>();
//         stack.add(0);
//         boolean[] visited = new boolean[n];
//         while (!stack.isEmpty()) {
//             Integer cur = stack.pop();
//             visited[cur] = true;
//             for (int i = 0; i < n; i++) {
//                 if (graph[cur][i] == 1) {
//                     if (visited[i]) {
//                         return false;
//                     }
//                     visited[i] = true;
//                     graph[cur][i] = 0;
//                     graph[i][cur] = 0;
//                     stack.add(i);
//                 }
//             }
//         }
//         for (int i = 0; i < n; i++) {
//             if (!visited[i]) {
//                 return false;
//             }
//         }
//         return true;
//     }
// }

//另一种检测成环的思路
// class Solution {
//     /*
//     举例
//     0 - 4
//     [0, 1][0, 2][0, 3]
// 	0 - 1
//  	| \
// 	2   3         4

//     */
//     public boolean validTree(int n, int[][] edges) {
//         //判断成环的
//         if(n != edges.length + 1)       return false;

//         ArrayList<Integer>[] adj = new ArrayList[n];
//         for(int i = 0; i < n; i++)
//             adj[i] = new ArrayList<>();

//         for(int[] edge : edges)
//         {
//             adj[edge[0]].add(edge[1]);
//             adj[edge[1]].add(edge[0]);
//         }

//         Deque<Integer> queue = new ArrayDeque<>();
//         HashSet<Integer> visited = new HashSet<>();
//         queue.offer(0);
//         visited.add(0);

//         while(!queue.isEmpty())
//         {
//             int node = queue.poll();
//             for(Integer i : adj[node])
//             {
//                 if(!visited.contains(i))
//                 {
//                     queue.add(i);
//                     visited.add(i);
//                 }
//             }
//         }
//         return visited.size() == n;
//     }
// }
