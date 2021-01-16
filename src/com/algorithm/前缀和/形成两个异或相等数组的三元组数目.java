package com.algorithm.前缀和;

/*
* 给你一个整数数组 arr 。

现需要从数组中取三个下标 i、j 和 k ，其中 (0 <= i < j <= k < arr.length) 。

a 和 b 定义如下：

a = arr[i] ^ arr[i + 1] ^ ... ^ arr[j - 1]
b = arr[j] ^ arr[j + 1] ^ ... ^ arr[k]
注意：^ 表示 按位异或 操作。

请返回能够令 a == b 成立的三元组 (i, j , k) 的数目。

 

示例 1：

输入：arr = [2,3,1,6,7]
输出：4
解释：满足题意的三元组分别是 (0,1,2), (0,2,2), (2,3,4) 以及 (2,4,4)
示例 2：

输入：arr = [1,1,1,1,1]
输出：10
示例 3：

输入：arr = [2,3]
输出：0
示例 4：

输入：arr = [1,3,5,7,9]
输出：3
示例 5：

输入：arr = [7,11,12,9,5,2,7,17,22]
输出：8
 

提示：

1 <= arr.length <= 300
1 <= arr[i] <= 10^8
* */

public class 形成两个异或相等数组的三元组数目 {
    //这种方法是错的,原因是每次以i遍历的最后节点，在当前轮数会变
    // public int countTriplets(int[] arr) {
    //     int[] preffix = new int[arr.length + 1];
    //     for (int i = 1; i <= arr.length; i++) {
    //         preffix[i] = preffix[i - 1] ^ arr[i - 1];
    //     }
    //     System.out.println(Arrays.toString(preffix));
    //     Map<Integer, int[]> map = new HashMap<>();
    //     int res = 0;
    //     //i 和 j 此时代表下标
    //     for (int i = 0; i < arr.length; i++) {
    //         for (int j = i + 1; j < arr.length; j++) {
    //             //此cur 代表从i到j - 1的异或和
    //             int cur = preffix[j] ^ preffix[i];
    //             int second = preffix[j + 1] ^ preffix[i];
    //             System.out.println(second);
    //             if (map.containsKey(second) && map.get(second)[0] == i - 1) {
    //                 int count = map.get(second)[1];
    //                 res += count;
    //                 map.put(second, new int[] {j, count + 1});
    //             } else {
    //                 //map里存的是尾的异或和
    //                 map.put(cur, new int[] {j - 1, 1});
    //             }
    //         }
    //     }
    //     return res;
    // }
    /*
    如果a == b，那么a ^ b == 0。反之也成立。
    异或具有结合率。(a1 ^ a2) ^ a3 =a1 ^ (a2 ^ a3) 。
    区间[i, k]，一共有k - i + 1个元素。
    举个例子将1，2，3，4分成左右两部分，每个部分至少一个元素，有3种分法。那么对于k - i + 1个元素，就有k - i种分法。
    所以这里就可以找到k - i组三组元(i , j, k)
    */
    //On2
    public int countTriplets(int[] arr) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            int temp = arr[i];
            for (int k = i + 1; k < arr.length; k++) {
                temp ^= arr[k];
                if (temp == 0) {
                    count += k - i;
                }
            }
        }
        return count;
    }
    //On
    // public int countTriplets(int[] arr) {
    //     int result = 0;
    //     int curXor = 0;
    //     Map<Integer, int[]> map = new HashMap<>();
    //     // int[0]代表次数， int[1]代表多个索引i + 1累加和
    //     map.put(0, new int[] {1, 0});
    //     for (int i = 0; i < arr.length; i++) {
    //         curXor ^= arr[i];
    //         int[] countAndSum = map.getOrDefault(curXor, new int[2]);
    //         //原理，如果以i为结束，与以j为结束的异或和相等，长度i - j 的异或和必为0，根据On2算法，共有i - j + 1种。
    //         if (map.containsKey(curXor)) {
    //             result += countAndSum[0] * i - countAndSum[1];
    //             ++countAndSum[0];
    //             countAndSum[1] += i + 1;
    //         } else {
    //             countAndSum[0] = 1;
    //             countAndSum[1] = i + 1;
    //         }
    //         map.put(curXor, countAndSum);
    //     }
    //     return result;
    // }
    //
    // public int countTriplets(int[] arr) {
    //     int len = arr.length;
    //     int ans = 0;
    //     int[] dp = new int[len];
    //     Map<Integer, Map<Integer, Integer>> countMap = new HashMap<>();
    //     dp[0] = arr[0];
    //     //countMap中存的是以i为起始的异或值map
    //     for (int i = 1; i < len; i++) {
    //         dp[i] = dp[i - 1] ^ arr[i];
    //         Map<Integer, Integer> map = new HashMap<>();
    //         int xor = 0;
    //         for (int j = i; j < len; j++) {
    //             xor ^= arr[j];
    //             map.put(xor, map.getOrDefault(xor, 0) + 1);
    //         }
    //         countMap.put(i, map);
    //     }
    //     for (int i = 0; i < len - 1; i++) {
    //         for (int j = i + 1; j < len; j++) {
    //             int a = dp[j - 1] ^ (i == 0? 0: dp[i - 1]);
    //             ans += countMap.get(j).getOrDefault(a, 0);
    //         }
    //     }
    //     return ans;
    // }
}
