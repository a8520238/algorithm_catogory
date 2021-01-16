package com.algorithm.排序;

public class MergeSort {
    public int[] MergeSort(int[] arr) {
        sort(arr, 0, arr.length - 1);
        return arr;
    }
    public void sort(int[] arr, int left, int right) {
        if (left == right) {
            return;
        }
        int mid = (right - left) / 2 + left;
        sort(arr, left, mid);
        sort(arr, mid + 1, right);
        Merge(arr, left, mid, right);
    }
    public void Merge(int[] arr, int left, int mid, int right) {
        int[] help = new int[right - left + 1];
        int i = left, j = mid + 1, index = 0;
        while (i <= mid && j <= right) {
            if (arr[i] < arr[j]) {
                help[index++] = arr[i++];
            } else {
                help[index++] = arr[j++];
            }
        }
        while (i <= mid) {
            help[index++] = arr[i++];
        }
        while (j <= right) {
            help[index++] = arr[j++];
        }
        for (int l = 0; l < help.length; l++) {
            arr[l + i] = help[l];
        }
    }
}
