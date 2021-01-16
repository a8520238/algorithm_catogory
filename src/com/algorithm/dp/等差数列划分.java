package com.algorithm.dp;

/*
* 如果一个数列至少有三个元素，并且任意两个相邻元素之差相同，则称该数列为等差数列。

例如，以下数列为等差数列:

1, 3, 5, 7, 9
7, 7, 7, 7
3, -1, -5, -9
以下数列不是等差数列。

1, 1, 2, 5, 7
 

数组 A 包含 N 个数，且索引从0开始。数组 A 的一个子数组划分为数组 (P, Q)，P 与 Q 是整数且满足 0<=P<Q<N 。

如果满足以下条件，则称子数组(P, Q)为等差数组：

元素 A[P], A[p + 1], ..., A[Q - 1], A[Q] 是等差的。并且 P + 1 < Q 。

函数要返回数组 A 中所有为等差数组的子数组个数。

 

示例:

A = [1, 2, 3, 4]

返回: 3, A 中有三个子等差数组: [1, 2, 3], [2, 3, 4] 以及自身 [1, 2, 3, 4]。
* */
//暴力
// class 等差数列划分 {
//     public int numberOfArithmeticSlices(int[] A) {
//         int count = 0;
//         for (int s = 0; s < A.length - 2; s++) {
//             int d = A[s + 1] - A[s];
//             for (int e = s + 2; e < A.length; e++) {
//                 int i = 0;
//                 //这里存在冗余
//                 for (i = s + 1; i <= e; i++) {
//                     if (A[i] - A[i - 1] != d) {
//                         break;
//                     }
//                 }
//                 if (i > e) {
//                     count++;
//                 }
//             }
//         }
//         return count;
//     }
// }

//暴力优化，纯暴力是遍历任意头尾之间是否能够构成等差数列，优化只固定头，
//遍历尾能成res+1,不能成直接break
// class 等差数列划分 {
//     public int numberOfArithmeticSlices(int[] A) {
//         int count = 0;
//         for (int s = 0; s < A.length - 2; s++) {
//             int d = A[s + 1] - A[s];
//             for (int e = s + 2; e < A.length; e++) {
//                 if (A[e] - A[e - 1] == d) {
//                     count++;
//                 } else {
//                     break;
//                 }
//             }
//         }
//         return count;
//     }
// }

//递归版本
// class 等差数列划分 {
//     int sum = 0;
//     public int numberOfArithmeticSlices(int[] A) {
//         slices(A, A.length - 1);
//         return sum;
//     }
//     public int slices(int[] A, int i) {
//         if (i < 2) {
//             return 0;
//         }
//         int ap = 0;
//         if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
//             ap = 1 + slices(A, i - 1);
//             sum += ap;
//         } else {
//             slices(A, i - 1);
//         }
//         return ap;
//     }
// }

//dp
//dp的含义是以A[i]结尾的等差数列个数
// class 等差数列划分 {
//     public int numberOfArithmeticSlices(int[] A) {
//         int[] dp = new int[A.length];
//         int sum = 0;
//         for (int i = 2; i < dp.length; i++) {
//             if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
//                 dp[i] = 1 + dp[i - 1];
//                 sum += dp[i];
//             }
//         }
//         return sum;
//     }
// }

//常数dp
class 等差数列划分 {
    public int numberOfArithmeticSlices(int[] A) {
        int count = 0;
        int sum = 0;
        for (int i = 2; i < A.length; i++) {
            if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
                count++;
            } else {
                sum += (count + 1) * count / 2;
                count = 0;
            }
        }
        return sum += count * (count + 1) / 2;
    }
}