package com.algorithm.图;

public class 判断二分图785 {
    //并查集思路
    public boolean isBipartite(int[][] graph) {
        UnionFind uf = new UnionFind(graph.length);
        for (int i = 0; i < graph.length; i++) {
            int[] next = graph[i];
            for (int w: next) {
                if (uf.isConnected(w, i)) {
                    return false;
                }
                uf.union(w, next[0]);
            }
        }
        return true;
    }

    class UnionFind {
        int[] roots;
        public UnionFind(int n) {
            roots = new int[n];
            for (int i = 0; i < n; i++) {
                roots[i] = i;
            }
        }

        public int find (int i) {
            if (roots[i] == i) {
                return i;
            }
            return roots[i] = find(roots[i]);
        }

        public boolean isConnected(int p, int q) {
            return find(p) == find(q);
        }

        public void union(int q, int p) {
            roots[find(q)] = find(p);
        }
    }

    //dfs 与bfs思路差不多
    // public boolean isBipartite(int[][] graph) {
    //     int[] visited = new int[graph.length];
    //     for (int i = 0; i < graph.length; i++) {
    //         if (visited[i] == 0 && !dfs(graph, i, 1, visited)) {
    //             return false;
    //         }
    //     }
    //     return true;
    // }

    // private boolean dfs(int[][] graph, int v, int color, int[] visited) {
    //     if (visited[v] != 0) {
    //         return visited[v] == color;
    //     }
    //     visited[v] = color;
    //     for (int next: graph[v]) {
    //         if (!dfs(graph, next, -color, visited)) {
    //             return false;
    //         }
    //     }
    //     return true;
    // }

    //bfs
    // public boolean isBipartite(int[][] graph) {
    //     int n = graph.length;
    //     int[] f = new int[n];
    //     // 定义 visited 数组  -1未访问 1和0两种颜色
    //     Arrays.fill(f, -1);
    //     for (int m = 0; m < graph.length; m++) {
    //         // 因为图中可能含有多个连通域，所以我们需要判断是否存在顶点未被访问，若存在则从它开始再进行一轮 bfs 染色。
    //         //若不存在，直接跳过
    //         if (f[m] == -1) {
    //             Queue<Integer> queue = new LinkedList<>();
    //             queue.add(m);
    //             f[m] = 1;
    //             while(!queue.isEmpty()) {
    //                 int cur = queue.poll();
    //                 int[] next = graph[cur];
    //                 for (int i = 0; i < next.length; i++) {
    //                     if (f[next[i]] == -1) {
    //                         queue.add(next[i]);
    //                         f[next[i]] = 1 - f[cur];
    //                     } else if (f[next[i]] == f[cur]) {
    //                         return false;
    //                     }
    //                 }
    //             }
    //         }
    //     }
    //     return true;
    // }
}
