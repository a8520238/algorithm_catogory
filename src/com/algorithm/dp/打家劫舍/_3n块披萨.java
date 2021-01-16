package com.algorithm.dp.打家劫舍;

//dp的含义前i个数，选择j个不相邻的数的最大和

// class _3n块披萨 {
//     public int maxSizeSlices(int[] slices) {
//         if (slices.length == 0) {
//             return 0;
//         }
//         return Math.max(process(slices, 0, slices.length - 1), process(slices, 1, slices.length));
//     }
//     public int process(int[] slices, int start, int end) {
//         int n = slices.length;
//         // 这个choose是关键，不管你怎么选，choose为n / 3就一定是合法值，避免了考虑对选完的值进行处理
//         int choose = n / 3;
//         int[][] dp = new int[n + 1][choose + 1];
//         for (int i = start + 1; i <= end; i++) {
//             for (int j = 1; j <= choose; j++) {
//                 dp[i][j] = Math.max(dp[i - 1][j], (i- 2 >= 0? dp[i - 2][j - 1]: 0) + slices[i - 1]);
//             }
//         }
//         return dp[end][choose];
//     }
// }

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

//class _3n块披萨 {
//    public int maxSizeSlices(int[] slices) {
//        int[] slices1 = new int[slices.length - 1];
//        System.arraycopy(slices, 1, slices1, 0, slices1.length);
//        int[] slices2 = new int[slices.length - 1];
//        System.arraycopy(slices, 0, slices2, 0, slices2.length);
//        return Math.max(process(slices1), process(slices2));
//    }
//
//    int process(int[] slices) {
//        int n = slices.length;
//        int choose = (n + 1) / 3;
//        int[][] dp = new int[n + 1][choose + 1];
//        for (int i = 1; i <= n; i++) {
//            for (int j = 1; j <= choose; j++) {
//                dp[i][j] = Math.max(dp[i - 1][j], (i - 2 >= 0? dp[i - 2][j - 1]: 0) + slices[i - 1]);
//            }
//        }
//        return dp[n][choose];
//    }
//}

/*
* 我们在序列 \cdots, x, y, z, \cdots⋯,x,y,z,⋯ 中选取了 yy，那么我们在删去 xx 和 zz 的同时，将 yy 的值改为 y' = x + z - yy
′
 =x+z−y。这样以来，我们就可以进行反悔操作了：如果我们选取了这个新的 y'y
′
  值，那么与之前选取的 yy 值相加，即为：

y + y' = y + (x + z - y) = x + z
y+y
′
 =y+(x+z−y)=x+z

恰好就是其相邻的两个数之和。对于序列


[8, 9, 8, 6, 1, 1]
我们在选择 9 之后，删除两个相邻的 8 并将 9 修改为 8 + 8 - 9 = 7，得到新的序列


[7, 6, 1, 1]
第二次就会选择 7 而不是 6 了，这样就得到了正确的答案。
* */
class _3n块披萨 {
    public int maxSizeSlices(int[] slices) {
        int n = slices.length;
        //使用数组模拟双向链表
        int[] linkL = new int[n];
        int[] linkR = new int[n];
        for (int i = 0; i < n; i++) {
            linkL[i] = i == 0? n - 1: i - 1;
            linkR[i] = i == n - 1? 0: i + 1;
        }
        boolean[] valid = new boolean[n];
        Arrays.fill(valid, true);
        PriorityQueue<int[]> queue = new PriorityQueue<int[]>(new Comparator<int[]>() {
            public int compare(int[] array1, int[] array2) {
                if (array1[0] != array2[0]) {
                    return array2[0] - array1[0];
                } else {
                    return array2[1] - array1[1];
                }
            }
        });
        for (int i = 0; i < n; i++) {
            queue.offer(new int[] {slices[i], i});
        }
        int ans = 0;
        for (int i = 0; i < n / 3; i++) {
            // 从优先队列中取出元素时要判断其是否已被删除
            while (!valid[queue.peek()[1]]) {
                queue.poll();
            }
            int pos = queue.poll()[1];
            ans += slices[pos];
            // 更新当前位置的值
            slices[pos] = slices[linkL[pos]] + slices[linkR[pos]] - slices[pos];
            queue.offer(new int[] {slices[pos], pos});
            // 删去左右两侧的值
            valid[linkL[pos]] = valid[linkR[pos]] = false;
            linkR[linkL[linkL[pos]]] = pos;
            linkL[linkR[linkR[pos]]] = pos;
            linkL[pos] = linkL[linkL[pos]];
            linkR[pos] = linkR[linkR[pos]];
        }
        return ans;
    }
}