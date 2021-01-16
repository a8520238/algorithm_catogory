package com.algorithm.dp;

/*
* 假设你有一个特殊的键盘包含下面的按键：

Key 1: (A)：在屏幕上打印一个 'A'。

Key 2: (Ctrl-A)：选中整个屏幕。

Key 3: (Ctrl-C)：复制选中区域到缓冲区。

Key 4: (Ctrl-V)：将缓冲区内容输出到上次输入的结束位置，并显示在屏幕上。

现在，你只可以按键 N 次（使用上述四种按键），请问屏幕上最多可以显示几个 'A'呢？

样例 1:

输入: N = 3
输出: 3
解释:
我们最多可以在屏幕上显示三个'A'通过如下顺序按键：
A, A, A

* */

public class 四键键盘651 {
    public int maxA(int N) {
        //进行N次操作当前的A的最大数量
        int[] dp = new int[N + 1];
        dp[0] = 0;
        for (int i = 1; i <= N; i++) {
            dp[i] = dp[i-1] + 1;
            for (int j = 2; j < i; j++) {
                dp[i] = Math.max(dp[i], dp[j - 2] * (i - j + 1));
            }
        }
        return dp[N];
    }
}
