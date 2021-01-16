package com.algorithm.dp;

/*
*你将会获得一系列视频片段，这些片段来自于一项持续时长为 T 秒的体育赛事。这些片段可能有所重叠，也可能长度不一。

视频片段 clips[i] 都用区间进行表示：开始于 clips[i][0] 并于 clips[i][1] 结束。我们甚至可以对这些片段自由地再剪辑，例如片段 [0, 7] 可以剪切成 [0, 1] + [1, 3] + [3, 7] 三部分。

我们需要将这些片段进行再剪辑，并将剪辑后的内容拼接成覆盖整个运动过程的片段（[0, T]）。返回所需片段的最小数目，如果无法完成该任务，则返回 -1 。

输入：clips = [[0,2],[4,6],[8,10],[1,9],[1,5],[5,9]], T = 10
输出：3
解释：
我们选中 [0,2], [8,10], [1,9] 这三个片段。
然后，按下面的方案重制比赛片段：
将 [1,9] 再剪辑为 [1,2] + [2,8] + [8,9] 。
现在我们手上有 [0,2] + [2,8] + [8,10]，而这些涵盖了整场比赛 [0, 10]。
*  */

import java.util.Arrays;

public class 视频拼接1024_子区间问题 {

    //方法1贪心，先排序，再用记录最大值和当前初始值做子循环，更新最大结束值
    public int videoStitching(int[][] clips, int T) {
        Arrays.sort(clips, (a, b) -> (a[0] == b[0])? a[1] - b[1]: a[0] - b[0]);
        int res = 0;
        int len = clips.length;
        int end = 0;
        int index = 0;
        while (index < len) {
            int curEnd = end;
            if (clips[index][0] > end) {
                return -1;
            }
            while (index < len && clips[index][0] <= end) {
                curEnd = Math.max(curEnd, clips[index][1]);
                index++;
            }
            res ++;
            end = curEnd;
            if (index == len || end >= T) {
                break;
            }
        }
        return end >= T? res: -1;
    }

    //方法2 dp思路 dp[i] = min{dp[aj]} + 1 (aj < i < bj) O(T * N)
//    public int videoStitching(int[][] clips, int T) {
//        int[] dp = new int[T + 1];
//        Arrays.fill(dp, Integer.MAX_VALUE - 1);
//        dp[0] = 0;
//        for (int i = 1; i <= T; i++) {
//            for (int[] arr: clips) {
//                if (i > arr[0] && i <= arr[1]) {
//                    dp[i] = Math.min(dp[i], dp[arr[0]] + 1);
//                }
//            }
//        }
//        return dp[T] == Integer.MAX_VALUE - 1? -1: dp[T];
//    }

    //官方的贪心思想 记录每一个位置最远能到的结束位置
//    public int videoStitching(int[][] clips, int T) {
//        int[] maxn = new int[T];
//        int last = 0, res = 0, pre = 0;
//        for (int[] clip: clips) {
//            if (clip[0] < T) {
//                maxn[clip[0]] = Math.max(maxn[clip[0]], clip[1]);
//            }
//        }
//        for (int i = 0; i < T; i++) {
//            last = Math.max(last, maxn[i]);
//            if (i == last) {
//                return -1;
//            }
//            if (i == pre) {
//                res++;
//                pre = last;
//            }
//        }
//        return res;
//    }
}
