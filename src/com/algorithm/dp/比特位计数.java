package com.algorithm.dp;

/*
* 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。

示例 1:

输入: 2
输出: [0,1,1]

* */

//解法一O(n*sizeof(integer))
// class Solution {
//     public int[] countBits(int num) {
//         int[] ans = new int[num + 1];
//         for (int i = 0; i <= num; i++) {
//             ans[i] = popcount(i);
//         }
//         return ans;
//     }
//     private int popcount(int x) {
//         int count;
//         for (count = 0; x != 0; count++) {
//             x &= x - 1;
//         }
//         return count;
//     }
// }
//解法2 记录,b代表的是差的位次
// class Solution {
//     public int[] countBits(int num) {
//         int[] ans = new int[num + 1];
//         int i = 0, b = 1;
//         while (b <= num) {
//             while (i < b && i + b <= num) {
//                 ans[i + b] = ans[i] + 1;
//                 i++;
//             }
//             i = 0;
//             b <<= 1;
//         }
//         return ans;
//     }
// }

//解法3 P(x)=P(x/2)+(xmod2)
class 比特位计数 {
    public int[] countBits(int num) {
        int[] ans = new int[num + 1];
        for (int i = 1; i <= num; i++) {
            ans[i] = ans[i >> 1] + (i & 1);
        }
        return ans;
    }
}