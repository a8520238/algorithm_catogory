package com.algorithm.排序;

public class HeapSort {
    public void Sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            HeapInsert(arr, i);
        }
        swap(arr, 0, --len);
        while (len > 0) {
            Heapify(arr, 0, len);
            swap(arr, 0, --len);
        }
    }

    public void Heapify(int[] arr, int index, int len) {
        int left = 2 * index + 1;
        while (left < len) {
            int Largest = left + 1 < len && arr[left + 1] > arr[left] ? left + 1 : left;
            if (arr[index] > arr[Largest]) {
                break;
            } else {
                swap(arr, index, Largest);
                index = Largest;
                left = 2 * index + 1;
            }
        }
    }

    public void HeapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    private void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
