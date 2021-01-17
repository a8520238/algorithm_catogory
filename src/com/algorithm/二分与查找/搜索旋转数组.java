package com.algorithm.二分与查找;

/*
* 搜索旋转数组。给定一个排序后的数组，包含n个整数，但这个数组已被旋转过很多次了，次数不详。请编写代码找出数组中的某个元素，假设数组元素原先是按升序排列的。若有多个相同元素，返回索引值最小的一个。

示例1:

 输入: arr = [15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14], target = 5
 输出: 8（元素5在该数组中的索引）
示例2:

 输入：arr = [15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14], target = 11
 输出：-1 （没有找到）
提示:

arr 长度范围在[1, 1000000]之间

* */

public class 搜索旋转数组 {
    public int search(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        //过滤相等的值
        while (arr[left] == arr[right]) {
            right--;
        }
        while (left < right) {
            int mid = (right - left) / 2 + left;
            if (arr[mid] >= arr[left] && arr[mid] > arr[right]) {
                if (target >= arr[left] && target <= arr[mid]) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            } else {
                if (target <= arr[right] && arr[mid] < target) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
        }

        return arr[left] == target? left: -1;
    }
}
