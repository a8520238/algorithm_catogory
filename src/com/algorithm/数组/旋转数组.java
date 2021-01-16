package com.algorithm.数组;

/*
*给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。

示例 1:

输入: [1,2,3,4,5,6,7] 和 k = 3
输出: [5,6,7,1,2,3,4]
解释:
向右旋转 1 步: [7,1,2,3,4,5,6]
向右旋转 2 步: [6,7,1,2,3,4,5]
向右旋转 3 步: [5,6,7,1,2,3,4]
示例 2:

输入: [-1,-100,3,99] 和 k = 2
输出: [3,99,-1,-100]
解释:
向右旋转 1 步: [99,-1,-100,3]
向右旋转 2 步: [3,99,-1,-100]
说明:

尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
要求使用空间复杂度为 O(1) 的 原地 算法。

* */
// class Solution {
//     //暴力解法  如果可以使用数组，可以利用空间复杂度Ok
//     public void rotate(int[] nums, int k) {
//         if (nums.length == 0) {
//             return;
//         }
//         for (int j = 0; j < k; j++) {
//             int pre = nums[0];
//             for (int i = 1; i < nums.length; i++) {
//                 int tmp = nums[i];
//                 nums[i] = pre;
//                 pre = tmp;
//             }
//             nums[0] = pre;
//         }
//     }
// }

//反转法
// class Solution {
//     public void rotate(int[] nums, int k) {
//         k %= nums.length;
//         reverse(nums, 0, nums.length - 1);
//         reverse(nums, 0, k - 1);
//         reverse(nums, k, nums.length - 1);
//     }
//     public void reverse(int[] nums, int i, int j) {
//         while (i < j) {
//             int tmp = nums[i];
//             nums[i] = nums[j];
//             nums[j] = tmp;
//             i++;
//             j--;
//         }
//     }
// }

//循环依赖法
class 旋转数组 {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        int count = gcd(k, n);
        for (int start = 0; start < count; start++) {
            int current = start;
            int prev = nums[start];
            do {
                int next = (current + k) % n;
                int tmp = nums[next];
                nums[next] = prev;
                prev = tmp;
                current = next;
            } while (start != current);
        }
    }
    public int gcd(int x, int y) {
        return y > 0? gcd(y, x % y): x;
    }
}