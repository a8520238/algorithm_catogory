package com.algorithm.单调栈;

/*
* 给定长度分别为 m 和 n 的两个数组，其元素由 0-9 构成，表示两个自然数各位上的数字。现在从这两个数组中选出 k (k <= m + n) 个数字拼接成一个新的数，要求从同一个数组中取出的数字保持其在原数组中的相对顺序。

求满足该条件的最大数。结果返回一个表示该最大数的长度为 k 的数组。

说明: 请尽可能地优化你算法的时间和空间复杂度。

示例 1:

输入:
nums1 = [3, 4, 6, 5]
nums2 = [9, 1, 2, 5, 8, 3]
k = 5
输出:
[9, 8, 6, 5, 3]
示例 2:

输入:
nums1 = [6, 7]
nums2 = [6, 0, 4]
k = 5
输出:
[6, 7, 6, 0, 4]

* */

public class 拼接最大数 {
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int len1 = nums1.length, len2 = nums2.length;
        int left = Math.max(0, k - len2);
        int right = Math.min(k, len1);
        int[] maxArr = new int[k];
        for (int i = left; i <= right; i++) {
            int[] arr1 = pick(nums1, i);
            // System.out.println(Arrays.toString(arr1));
            int[] arr2 = pick(nums2, k - i);
            // System.out.println(Arrays.toString(arr2));
            int[] curArr = merge(arr1, arr2);
            if (compare(maxArr, curArr) < 0) {
                maxArr = curArr;
            }
            // if (compare(maxArr, 0, curArr, 0) < 0) {
            //     maxArr = curArr;
            // }
        }
        return maxArr;
    }
    public int[] pick(int[] arr, int k) {
        //利用单调栈获取当前抽取k个值能获得的最大值，定义栈顶，定义剩余数
        //如果剩余数不足，也就是remain==0，也就是说即使把top放入，数量也不够用k,那么单调栈不再仅需弹出
        //如果数量已经超过k,也不再继续添加
        //弹出和越过时，remain--，remain初始值为length - k，也就是不能弹出和越过超过这些纸，否则不够k
        int length = arr.length;
        int[] stack = new int[k];
        int top = -1;
        int remain = length - k;
        for (int i = 0; i < length; i++) {
            int num = arr[i];
            while (top >= 0 && remain > 0 && num > stack[top]) {
                remain--;
                top--;
            }
            if (top < k - 1) {
                stack[++top] = num;
            } else {
                remain--;
            }
        }
        return stack;
    }
    // public int[] merge(int[] a, int[] b) {
    //     int lenA = a.length, lenB = b.length;
    //     if (lenA == 0) {
    //         return b;
    //     } else if (lenB == 0) {
    //         return a;
    //     }
    //     int[] res = new int[lenA + lenB];
    //     int index1 = 0, index2 = 0;
    //     for (int i = 0; i < lenA + lenB; i++) {
    //         //其实就是归并merge思想，为了避免相等，要判断相等时咋办
    //         if (compare(a, index1, b, index2) > 0) {
    //             res[i] = a[index1++];
    //         } else {
    //             res[i] = b[index2++];
    //         }
    //     }
    //     return res;
    // }
    // public int compare(int[] a, int index1, int[] b, int index2) {
    //     int len1 = a.length, len2 = b.length;
    //     while (index1 < len1 && index2 < len2) {
    //         if (a[index1] < b[index2]) {
    //             return -1;
    //         } else if (a[index1] > b[index2]) {
    //             return 1;
    //         }
    //         index1++;
    //         index2++;
    //     }
    //     //比较策略，先到头的后选
    //     return len1 == index1? -1: 1;
    //     // return (len1 - index1) - (len2 - index2);
    // }
    public int[] merge(int[] a, int[] b) {
        int lenA = a.length, lenB = b.length;
        if (lenA == 0) {
            return b;
        } else if (lenB == 0) {
            return a;
        }
        int indexA = 0;
        int indexB = 0;
        int index = 0;
        int[] res = new int[lenA + lenB];
        while (indexA < lenA && indexB < lenB) {
            if (a[indexA] > b[indexB]) {
                res[index++] = a[indexA++];
            } else if (a[indexA] < b[indexB]) {
                res[index++] = b[indexB++];
            } else {
                if (compare1(a, indexA + 1, b, indexB + 1) > 0) {
                    res[index++] = a[indexA++];
                } else {
                    res[index++] = b[indexB++];
                }
            }
        }
        while (indexA < lenA) {
            res[index++] = a[indexA++];
        }
        while (indexB < lenB) {
            res[index++] = b[indexB++];
        }
        return res;
    }
    //判断两个数组谁大
    public int compare(int[] a, int[] b) {
        int len = a.length;
        for (int i = 0; i < len; i++) {
            if (a[i] < b[i]) {
                return -1;
            } else if (a[i] > b[i]) {
                return 1;
            }
        }
        return 0;
    }
    //相等的时候，向后找，谁大谁在前面，如果一直相等，短的（先到尾部的）在后面
    public int compare1(int[] a, int i, int[] b, int j) {
        int len1 = a.length, len2 = b.length;
        while (i < len1 && j < len2) {
            if (a[i] < b[j]) {
                return -1;
            } else if (a[i] > b[j]) {
                return 1;
            }
            i++;
            j++;
        }
        return i == len1? -1: 1;
    }
}
