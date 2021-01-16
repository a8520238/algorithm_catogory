package com.algorithm.排序;

import java.util.Arrays;

public class BubbleSort {
    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }
    //量大的时候改进会有效，改进的是如果某一次一次交换都不发生，直接break,不需要进入后续轮次
//    public static void bubbleSort(int[] arr) {
//        int doSwitch = 0, first = 1, last = arr.length;
//        for (int i = first - 1; i < last; i++) {
//            if (doSwitch == 1) {
//                break;
//            }
//            for (int j = first - 1; j < last - i - 1; j++) {
//                doSwitch = 1;
//                if (arr[j] > arr[j + 1]) {
//                    doSwitch = 0;
//                    swap(arr, j, j + 1);
//                }
//            }
//        }
//    }
    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
    public static void main(String[] args) {
        long t1 = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) { //改进1390 原始1281
            int[] a = {454, 4343,54,22, 43, 43, 2, 5, 6, 1, 3, 8, 7, 9, 234,23,32,43,545,1, 22,3};
            BubbleSort.bubbleSort(a);
            System.out.println(Arrays.toString(a));
        }
        long t2 = System.currentTimeMillis();
        System.out.println(t2 - t1);
    }
}
