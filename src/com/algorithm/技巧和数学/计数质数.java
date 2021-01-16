package com.algorithm.技巧和数学;

/*
* 统计所有小于非负整数 n 的质数的数量。

 

示例 1：

输入：n = 10
输出：4
解释：小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
示例 2：

输入：n = 0
输出：0
* */

import java.util.Arrays;

//这道题如果对每个数进行判断会超时，方法是质数乘一个数得到的一定不是质数，通过空间换时间
public class 计数质数 {
    public int countPrimes(int n) {
        boolean[] isPrime = new boolean[n];
        Arrays.fill(isPrime, true);
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (!isPrime[i]) {
                continue;
            }
            for (int j = i * i; j < n; j += i) {
                isPrime[j] = false;
            }
            // int cur = 2;
            // while (cur * i < n) {
            //     isPrime[cur * i] = false;
            //     cur++;
            // }
        }
        int res = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime[i]) {
                res++;
            }
        }
        return res;
    }
}
