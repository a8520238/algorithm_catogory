package com.algorithm.图;

import java.util.Arrays;

public class K站中转内最便宜的航班 {
    //Bellman Ford
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        int[][]  dp = new int[2][n];
        int MAX_SUM = 10001 * n;
        Arrays.fill(dp[0], MAX_SUM);
        Arrays.fill(dp[1], MAX_SUM);
        dp[0][src] = 0;
        dp[1][src] = 0;
        for (int i = 0; i <= K; i++) {
            for (int[] flight: flights) {
                int flightSrc = flight[0];
                int flightDst = flight[1];
                int cost = flight[2];
                int cur = i & 1;
                int pre = ~i & 1;
                dp[cur][flightDst] = Math.min(dp[cur][flightDst], dp[pre][flightSrc] + cost);
            }
        }
        return dp[K & 1][dst] >= MAX_SUM? -1: dp[K & 1][dst];
    }
    //bfs思想 也可以看成Dijkstra-优先队列
    // public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
    //     //邻接矩阵
    //     int[][] g = new int[n][n];
    //     for (int[] f: flights) {
    //         g[f[0]][f[1]] = f[2];
    //     }

    //     PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
    //     //集合的参数是一个comparator的lambda表达式，默认升序
    //     heap.offer(new int[] {0, src, K + 1});
    //     //想集合添加一个记录费用、起点和中转站+1的数组
    //     //K + 1是还可以走过站点的个数

    //     while (!heap.isEmpty()) {
    //         int[] cur = heap.poll();//得到集合当中添加的数组
    //         int price = cur[0], place = cur[1], remainStops = cur[2];

    //         if (place == dst) {//起点等于v中点
    //             return price;//返回0费用0
    //         }

    //         if (remainStops > 0) {//中转次数>0（至少执行一次，因为remain====k+1）
    //             for (int i = 0; i < n; i++) {//小于城市数量
    //                 if (g[place][i] > 0) {//表示起点----终点的中转路线是否存在
    //                     heap.offer(new int[] {price + g[place][i], i, remainStops - 1});
    //                     //如果存在 计算路费、起点的值、中转站-1
    //                 }
    //             }
    //         }
    //     }
    //     return -1;
    // }
}
