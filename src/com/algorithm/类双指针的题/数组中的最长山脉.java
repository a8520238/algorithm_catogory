package com.algorithm.类双指针的题;

public class 数组中的最长山脉 {
    //数组空间换时间
    // public int longestMountain(int[] A) {
    //     int len = A.length;
    //     if (len < 3) {
    //         return 0;
    //     }
    //     int[] left = new int[len];
    //     int[] right = new int[len];
    //     for (int i = 1; i < len; i++) {
    //         if (A[i] > A[i - 1]) {
    //             left[i] = left[i - 1] + 1;
    //         }
    //     }
    //     for (int i = len - 2; i >= 0; i--) {
    //         if (A[i] > A[i + 1]) {
    //             right[i] = right[i + 1] + 1;
    //         }
    //     }
    //     int max = 0;
    //     for (int i = 1; i < len - 1; i++) {
    //         if (left[i] > 0 && right[i] > 0) {
    //             max = Math.max(max, left[i] + right[i] + 1);
    //         }
    //     }
    //     return max;
    // }
    // 双指针思想
    public int longestMountain(int[] A) {
        int n = A.length;
        int ans = 0;
        int left = 0;
        while (left + 2 < n) {
            int right = left + 1;
            if (A[left] < A[left + 1]) {
                while (right + 1 < n && A[right] < A[right + 1]) {
                    ++right;
                }
                if (right < n - 1 && A[right] > A[right + 1]) {
                    while (right + 1 < n && A[right] > A[right + 1]) {
                        ++right;
                    }
                    ans = Math.max(ans, right - left + 1);
                } else {
                    ++right;
                }
            }
            left = right;
        }
        return ans;
    }
}
