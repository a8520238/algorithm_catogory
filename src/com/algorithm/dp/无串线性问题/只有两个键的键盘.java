package com.algorithm.dp.无串线性问题;

/*
* 最初在一个记事本上只有一个字符 'A'。你每次可以对这个记事本进行两种操作：

Copy All (复制全部) : 你可以复制这个记事本中的所有字符(部分的复制是不允许的)。
Paste (粘贴) : 你可以粘贴你上一次复制的字符。
给定一个数字 n 。你需要使用最少的操作次数，在记事本中打印出恰好 n 个 'A'。输出能够打印出 n 个 'A' 的最少操作次数。

示例 1:

输入: 3
输出: 3
解释:
最初, 我们只有一个字符 'A'。
第 1 步, 我们使用 Copy All 操作。
第 2 步, 我们使用 Paste 操作来获得 'AA'。
第 3 步, 我们使用 Paste 操作来获得 'AAA'。
说明:

n 的取值范围是 [1, 1000] 。
* */

// class Solution {
//     //思路：当n是质数时，只能通过复制1次，黏贴n-1次形成。刚好n次。
// //当n是和数时，先将其质因数分解。质因数的和就是最少操作次数。
// //最终的结果形如CPPCPPPPCP 可以分为 [CPP][CPPPP][CP] 三组。如果n是质数，则只能一个个复制，如果能分解，分解后的质因数的和即为最小。
//     public int minSteps(int n) {
//         //从最小的质因数开始
//         int ans = 0, d = 2;
//         while (n > 1) {
//             //如果能分解，继续划分剩余的
//             while (n % d == 0) {
//                 ans += d;
//                 n /= d;
//             }
//             //不能的话，质因数增加，如果一直不能最后本身%d会为0
//             d++;
//         }
//         return ans;
//     }
// }
class 只有两个键的键盘 {
    public int minSteps(int n) {
        if (n <= 1) {
            return 0;
        }
        if (n <= 3) {
            return n;
        }
        //dp[i到达当前位置的最小次数，这里要理解好复制的那一次去哪了，如果是15的话，先生成3，复制一次，粘贴4次，所以复制那一次算进了粘贴中，形成了5.
        int[] dp = new int[n + 1];
        dp[1] = 0;
        for (int i = 2; i <= n; i++) {
            dp[i] = i;
        }
        for (int i = 2; i <= n; i++) {
            for (int mult = 2; mult * i <= n; mult++) {
                //每个格子都尝试去标记之后的自己可以到达的格子。对多种可能取最小。
                //这个mult就已经包括了复制的一次
                dp[mult * i] = Math.min(dp[mult * i], dp[i] + mult);
            }
        }
        return dp[n];
    }
    //另外一种dp思路，找最大的因子
    // public int minSteps(int n) {
    //     int[] dp = new int[1001];
    //     dp[1] = 0;
    //     for (int i = 2; i <= n; i++) {
    //         dp[i] = i;
    //         for (int j = i / 2; j >= (int) Math.sqrt(i); j--) {
    //             if (i % j == 0) {
    //                 // dp[i] = dp[j] + dp[i / j];
    //                 dp[i] = dp[j] + i / j;
    //                 break;
    //             }
    //         }
    //     }
    //     return dp[n];
    // }
}