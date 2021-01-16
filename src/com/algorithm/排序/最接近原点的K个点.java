package com.algorithm.排序;

import java.util.Arrays;
import java.util.Random;

//暴力排序解法
// class 最接近原点的K个点 {
//     public int[][] kClosest(int[][] points, int K) {
//         int[][] res = new int[K][2];
//         Arrays.sort(points, (a, b) -> (int)(Math.pow(a[0], 2) + Math.pow(a[1], 2) - Math.pow(b[0], 2) - Math.pow(b[1], 2)));
//         for (int i = 0; i < K; i++) {
//             res[i][0] = points[i][0];
//             res[i][1] = points[i][1];
//         }
//         return res;
//     }
// }
//优先队列 nlogK
// class 最接近原点的K个点 {
//     public int[][] kClosest(int[][] points, int K) {
//         PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]> () {
//             public int compare(int[] arr1, int[] arr2) {
//                 return arr2[0] - arr1[0];
//             }
//         });
//         for (int i = 0; i < K; i++) {
//             pq.add(new int[] {points[i][0] * points[i][0] + points[i][1] * points[i][1], i});
//         }
//         int n = points.length;
//         for (int i = K; i < n; i++) {
//             int dist = points[i][0] * points[i][0] + points[i][1] * points[i][1];
//             if (dist < pq.peek()[0]) {
//                 pq.poll();
//                 pq.add(new int[]{dist, i});
//             }
//         }
//         int[][] res = new int[K][2];
//         for (int i = 0; i < K; i++) {
//             res[i] = points[pq.poll()[1]];
//         }
//         return res;
//     }
// }
//快速选择（快速排序）oN
class 最接近原点的K个点 {
    Random rand = new Random();

    public int[][] kClosest(int[][] points, int K) {
        int n = points.length;
        rand_select(points, 0, n - 1, K);
        return Arrays.copyOfRange(points, 0, K);
    }
    public void rand_select(int[][] points, int left, int right, int K) {
        int pivotId = left + rand.nextInt(right - left + 1);
        int pivot = points[pivotId][0] * points[pivotId][0] + points[pivotId][1] * points[pivotId][1];
        swap(points, right, pivotId);
        int i = left - 1;
        for (int j = left; j < right; ++j) {
            int dist = points[j][0] * points[j][0] + points[j][1] * points[j][1];
            if (dist <= pivot) {
                ++i;
                swap(points, i, j);
            }
        }
        ++i;
        swap(points, i, right);
        if (K < i - left + 1) {
            rand_select(points, left, i - 1, K);
        } else if (K > i - left + 1) {
            rand_select(points, i + 1, right, K - (i - left + 1));
        }
    }
    public void swap(int[][] points, int i, int j) {
        int[] tmp = points[i];
        points[i] = points[j];
        points[j] = tmp;
    }

}
