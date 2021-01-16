package com.algorithm.图;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/*
* 在一组 N 个人（编号为 0, 1, 2, ..., N-1）中，每个人都有不同数目的钱，以及不同程度的安静（quietness）。

为了方便起见，我们将编号为 x 的人简称为 "person x "。

如果能够肯定 person x 比 person y 更有钱的话，我们会说 richer[i] = [x, y] 。注意 richer 可能只是有效观察的一个子集。

另外，如果 person x 的安静程度为 q ，我们会说 quiet[x] = q 。

现在，返回答案 answer ，其中 answer[x] = y 的前提是，在所有拥有的钱不少于 person x 的人中，person y 是最安静的人（也就是安静值 quiet[y] 最小的人）。

示例：

输入：richer = [[1,0],[2,1],[3,1],[3,7],[4,3],[5,3],[6,3]], quiet = [3,2,5,4,6,1,7,0]
输出：[5,5,2,5,4,5,6,7]
解释：
answer[0] = 5，
person 5 比 person 3 有更多的钱，person 3 比 person 1 有更多的钱，person 1 比 person 0 有更多的钱。
唯一较为安静（有较低的安静值 quiet[x]）的人是 person 7，
但是目前还不清楚他是否比 person 0 更有钱。

answer[7] = 7，
在所有拥有的钱肯定不少于 person 7 的人中(这可能包括 person 3，4，5，6 以及 7)，
最安静(有较低安静值 quiet[x])的人是 person 7。

其他的答案也可以用类似的推理来解释。
提示：

1 <= quiet.length = N <= 500
0 <= quiet[i] < N，所有 quiet[i] 都不相同。
0 <= richer.length <= N * (N-1) / 2
0 <= richer[i][j] < N
richer[i][0] != richer[i][1]
richer[i] 都是不同的。
对 richer 的观察在逻辑上是一致的。

* */
//dfs
// class 喧闹和富有 {
//     public int[] loudAndRich(int[][] richer, int[] quiet) {
//         int len = quiet.length;
//         List<Integer>[] edges = new List[len];
//         // Map<Integer, Integer> map = new HashMap<>();
//         // for (int i = 0; i < quiet.length; i++) {
//         //     map.put(quiet[i], i);
//         // }
//         for (int[] arr: richer) {
//             int big = arr[0];
//             int small = arr[1];
//             if (edges[small] == null) {
//                 edges[small] = new ArrayList<>();
//             }
//             edges[small].add(big);
//         }
//         int[] res = new int[len];
//         Arrays.fill(res, -1);
//         for (int i = 0; i < len; i++) {
//             // res[i] = map.get(dfs(edges, i, quiet));
//             // res[i] = dfs(edges, i, quiet);
//             dfs(edges, i, quiet, res);
//         }
//         return res;
//     }
//     //这题要注意如果要使用dfs,必须使用缓存res,减少重复计算。如果res被计算过，不能再次计算
//     public int dfs(List<Integer>[] edges, int i, int[] quiet, int[] res) {
//         if (edges[i] == null) {
//             res[i] = i;
//         }
//         // int min = i;
//         if (res[i] == -1) {
//             res[i] = i;
//             for (int n: edges[i]) {
//                 int index = dfs(edges, n, quiet, res);
//                 if (quiet[index] < quiet[res[i]]) {
//                     res[i] = index;
//                 }
//                 // min = Math.min(min, dfs(edges, n, quiet));
//             }
//         }
//         return res[i];
//     }
// }
// 拓扑排序
class 喧闹和富有 {
    public int[] loudAndRich(int[][] richer, int[] quiet) {
        int n = quiet.length;
        int[] in = new int[n];
        List<Integer>[] list = new List[n];
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            list[i] = new ArrayList<>();
            ans[i] = i;
        }
        for (int i = 0; i < richer.length; i++) {
            int u = richer[i][0];
            int v = richer[i][1];
            in[v]++;
            list[u].add(v);
        }
        //拓扑排序
        Queue<Integer> Q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (in[i] == 0) {
                Q.add(i);
            }
        }
        //u相当于富有的人
        while (!Q.isEmpty()) {
            int u = Q.poll();
            for (int v: list[u]) {
                //如果富有的人比你安静，则替换
                if (quiet[ans[u]] < quiet[ans[v]]) {
                    ans[v] = ans[u];
                }
                in[v]--;
                if(in[v] == 0) {
                    Q.add(v);
                }
            }
        }
        return ans;
    }
}