package com.algorithm.排序;

/*
* 给定一个数组 nums ，如果 i < j 且 nums[i] > 2*nums[j] 我们就将 (i, j) 称作一个重要翻转对。

你需要返回给定数组中的重要翻转对的数量。

示例 1:

输入: [1,3,2,3,1]
输出: 2
示例 2:

输入: [2,4,3,5,1]
输出: 3
注意:

给定数组的长度不会超过50000。
输入数组中的所有数字都在32位整数的表示范围内。
* */

//On2暴力超时
// class Solution {
//     public int reversePairs(int[] nums) {
//         if (nums == null || nums.length < 2) {
//             return 0;
//         }
//         int res = 0;
//         for (int i = 1; i < nums.length; i++) {
//             for (int j = 0; j < i; j++) {
//                 if ((long)nums[j] > 2 * (long)nums[i]) {
//                     res++;
//                 }
//             }
//         }
//         return res;
//     }
// }
// class 翻转对_归并树状数组 {
//     public int reversePairs(int[] nums) {
//         if (nums == null || nums.length < 2) {
//             return 0;
//         }
//         return reverseCount(nums, 0, nums.length - 1);
//     }
//     public int reverseCount(int[] nums, int left, int right) {
//         if (left == right) {
//             return 0;
//         }
//         int mid = (right - left) / 2 + left;
//         int leftP = reverseCount(nums, left, mid);
//         int rightP = reverseCount(nums, mid + 1, right);

//         int ret = leftP + rightP;
//         int i = left, j = mid + 1;
//         //计算这一段是核心
//         //左半部分有序，右半部分有序，如果某一位置，(long)nums[i] > 2 * (long)nums[j]，则从i往后都会大于，所以ret一直增加，On取值即可
//         while (i <= mid) {
//             while (j <= right && (long)nums[i] > 2 * (long)nums[j]) {
//                 j++;
//             }
//             ret += j - mid - 1;
//             i++;
//         }
//         mergeCount(nums, left, mid, right);
//         return ret;
//     }
//     public void mergeCount(int[] nums, int left, int mid, int right) {
//         int[] tmp = new int[right - left + 1];
//         int i = left, j = mid + 1, k = 0;
//         while (i <= mid && j <= right) {
//             if (nums[i] <= nums[j]) {
//                 tmp[k++] = nums[i++];
//             } else {
//                 tmp[k++] = nums[j++];
//             }
//         }
//         while (i <= mid) {
//             tmp[k++] = nums[i++];
//         }
//         while (j <= right) {
//             tmp[k++] = nums[j++];
//         }
//         for (int l = 0; l < tmp.length; l++) {
//             nums[l + left] = tmp[l];
//         }
//     }
// }

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

class 翻转对_归并树状数组 {
    public int reversePairs(int[] nums) {
        //通过treeSET保证有序
        Set<Long> allNumbers = new TreeSet<Long>();
        for (int x : nums) {
            allNumbers.add((long) x);
            allNumbers.add((long) x * 2);
        }
        // 利用哈希表进行离散化
        Map<Long, Integer> values = new HashMap<Long, Integer>();
        int idx = 0;
        for (long x : allNumbers) {
            values.put(x, idx);
            idx++;
        }

        int ret = 0;
        BIT bit = new BIT(values.size());
        for (int i = 0; i < nums.length; i++) {
            //由于是顺序遍历，那么保证了i的nums中的index大于当前树状数组中的index，查找当前树状数组中比nums[i] * 2大的个数。而树状数组的下标代表的实际值是有序的。
            int left = values.get((long) nums[i] * 2), right = values.size() - 1;
            ret += bit.query(right + 1) - bit.query(left + 1);
            bit.update(values.get((long) nums[i]) + 1, 1);
        }
        return ret;
    }
}

class BIT {
    int[] tree;
    int n;

    public BIT(int n) {
        this.n = n;
        this.tree = new int[n + 1];
    }

    public static int lowbit(int x) {
        return x & (-x);
    }

    public void update(int x, int d) {
        while (x <= n) {
            tree[x] += d;
            x += lowbit(x);
        }
    }

    public int query(int x) {
        int ans = 0;
        while (x != 0) {
            ans += tree[x];
            x -= lowbit(x);
        }
        return ans;
    }
}