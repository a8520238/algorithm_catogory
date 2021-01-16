package com.algorithm.排序;

public class QuickSort {
    public int[] sort(int[] arr) {
        return quickSort(arr, 0, arr.length - 1);
    }
    private int[] quickSort(int[] arr, int left, int right) {
        if (left >= right) {
            return arr;
        }
        int index = partition(arr, left, right);
        quickSort(arr, left, index - 1);
        quickSort(arr, index + 1, right);
        return arr;
    }
    private int partition(int[] arr, int left, int right) {
        int select = left;
        int index = left + 1;
        //index的含义是个边界，代表index之前全小于select
        for (int i = index; i <= right; i++) {
            if (arr[i] < arr[select]) {
                swap(arr, i, index);
                index++;
            }
        }
        //由于index之前全部小于select，所以index- 1必定也小于， 交换位置
        swap(arr, select, index - 1);
        return index - 1;
    }
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
