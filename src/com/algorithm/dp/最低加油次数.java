package com.algorithm.dp;


/*
* 汽车从起点出发驶向目的地，该目的地位于出发位置东面 target 英里处。

沿途有加油站，每个 station[i] 代表一个加油站，它位于出发位置东面 station[i][0] 英里处，并且有 station[i][1] 升汽油。

假设汽车油箱的容量是无限的，其中最初有 startFuel 升燃料。它每行驶 1 英里就会用掉 1 升汽油。

当汽车到达加油站时，它可能停下来加油，将所有汽油从加油站转移到汽车中。

为了到达目的地，汽车所必要的最低加油次数是多少？如果无法到达目的地，则返回 -1 。

注意：如果汽车到达加油站时剩余燃料为 0，它仍然可以在那里加油。如果汽车到达目的地时剩余燃料为 0，仍然认为它已经到达目的地。

 

示例 1：

输入：target = 1, startFuel = 1, stations = []
输出：0
解释：我们可以在不加油的情况下到达目的地。
示例 2：

输入：target = 100, startFuel = 1, stations = [[10,100]]
输出：-1
解释：我们无法抵达目的地，甚至无法到达第一个加油站。
* */

import java.util.Collections;
import java.util.PriorityQueue;

// class 最低加油次数 {
//     //dp的含义是，i代表加几次油(加油的次数比index多1)，dp[i]代表加第i次油能到达的最远距离，也就是说，dp[0]是原始也就是加0次油跑的最远距离。对于每一个新位置i, 最多加了i次油。那么对于新加的stations[i][1]要更新每个次数能到达的最远值。
//     public int minRefuelStops(int target, int startFuel, int[][] stations) {
//         int N = stations.length;
//         long[] dp = new long[N + 1];
//         dp[0] = startFuel;
//         for (int i = 0; i < N; i++) {
//             for (int t = i; t >= 0; t--) {
//                 if (dp[t] >= stations[i][0]) {
//                     dp[t + 1] = Math.max(dp[t + 1], dp[t] + (long)stations[i][1]);
//                 }
//             }
//         }
//         for (int i = 0; i <= N; i++) {
//             if (dp[i] >= target) {
//                 return i;
//             }
//         }
//         return -1;
//     }
// }
//使用优先队列，存储加油值的最大值，prev代表之前的路程，location代表当前的，差值就是用去的，rest是剩余的油，如果小于0，从之前的加油站里找个加油最多的，加了，如果还不行就说明到达不了。
class 最低加油次数 {
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        int ans = 0, prev = 0, rest = startFuel;
        for (int[] station: stations) {
            int location = station[0];
            int capacity = station[1];
            rest -= location - prev;
            while (!queue.isEmpty() && rest < 0) {
                rest += queue.poll();
                ans++;
            }
            if (rest < 0) {
                return -1;
            }
            queue.add(capacity);
            prev = location;
        }
        rest -= target - prev;
        while (!queue.isEmpty() && rest < 0) {
            rest += queue.poll();
            ans++;
        }
        if (rest < 0) return -1;
        return ans;
    }
}