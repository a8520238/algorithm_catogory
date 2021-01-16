package com.algorithm.树形dp;

import java.util.HashSet;
import java.util.Set;

public class 树中距离之和834 {
    private Set<Integer>[] graph;
    private int[] nodeSum;
    private int[] result;
    public int[] sumOfDistancesInTree(int N, int[][] edges) {
        graph = new Set[N];
        for (int i = 0; i < N; i++) {
            graph[i] = new HashSet<>();
        }
        for (int[] edge: edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        nodeSum = new int[N];
        result = new int[N];
        postOrder(0, -1);
        preOrder(0, -1, N);
        return result;
    }

    private void postOrder(int root, int parent) {
        nodeSum[root] = 1;
        for (int child: graph[root]) {
            if (child != parent) {
                postOrder(child, root);
                result[root] += result[child] + nodeSum[child]; //各个child的res加上增加的1*节点数
                nodeSum[root] += nodeSum[child]; //节点个数初始值为1，边界值为1，root为1+所有子节点个数。
            }
        }
    }

    private void preOrder(int root, int parent, int n) {
        for (int child: graph[root]) {
            if (child != parent) {
                result[child] = result[root] - nodeSum[child] + n - nodeSum[child];
                preOrder(child, root, n);
            }
        }
    }
    // dfs 超时
    // int[] res;
    // boolean[] visited;
    // int[][] edges;
    // public int[] sumOfDistancesInTree(int N, int[][] edges) {
    //     res = new int[N];
    //     this.edges = edges;
    //     visited = new boolean[N];
    //     for (int i = 0; i < N; i++) {
    //         dfs(i, i, 0);
    //     }
    //     return res;
    // }
    // public void dfs(int root, int cur, int height) {
    //     visited[cur] = true;
    //     res[root] += height;
    //     int from = 0, to = 0;
    //     for (int j = 0; j < edges.length; j++) {
    //         if (edges[j][0] == cur || edges[j][1] == cur) {
    //             if (edges[j][0] == cur) {
    //                 from = edges[j][0];
    //                 to =  edges[j][1];
    //             } else {
    //                 from = edges[j][1];
    //                 to = edges[j][0];
    //             }
    //             if (!visited[to] && from == cur) {
    //                 dfs(root, to, height + 1);
    //             }
    //         }
    //     }
    //     visited[cur] = false;
    //}
}
