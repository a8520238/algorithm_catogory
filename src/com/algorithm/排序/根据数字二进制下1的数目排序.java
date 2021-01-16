package com.algorithm.排序;

import java.util.Arrays;

// class Solution {
//     public int[] sortByBits(int[] arr) {
//         Node[] arrNode = new Node[arr.length];
//         for (int i = 0; i < arr.length; i++) {
//             int count = countOne(arr[i]);
//             arrNode[i] = new Node(arr[i], count);
//         }
//         Arrays.sort(arrNode, (a, b) -> a.one == b.one? a.val - b.val: a.one - b.one);
//         for (int i = 0; i < arr.length; i++) {
//             arr[i] = arrNode[i].val;
//         }
//         return arr;
//     }
//     public int countOne(int n) {
//         int sum = 0;
//         while (n != 0) {
//             sum += (n & 1);
//             n >>= 1;
//         }
//         return sum;
//     }
//     class Node {
//         int val;
//         int one;
//         Node (int val, int one) {
//             this.val = val;
//             this.one = one;
//         }
//         Node() {}
//     }
// }

//效率不如上面高
// class Solution {
//     public int[] sortByBits(int[] arr) {
//         int[] bit = new int[10001];
//         List<Integer> list = new ArrayList<Integer>();
//         for (int x : arr) {
//             list.add(x);
//             bit[x] = get(x);
//         }
//         Collections.sort(list, new Comparator<Integer>() {
//             public int compare(Integer x, Integer y) {
//                 if (bit[x] != bit[y]) {
//                     return bit[x] - bit[y];
//                 } else {
//                     return x - y;
//                 }
//             }
//         });
//         for (int i = 0; i < arr.length; ++i) {
//             arr[i] = list.get(i);
//         }
//         return arr;
//     }

//     public int get(int x) {
//         int res = 0;
//         while (x != 0) {
//             res += x % 2;
//             x /= 2;
//         }
//         return res;
//     }
// }
//效率也一般
// class Solution {
//     public int[] sortByBits(int[] arr) {
//         List<Integer> list = new ArrayList<Integer>();
//         for (int x : arr) {
//             list.add(x);
//         }
//         int[] bit = new int[10001];
//         for (int i = 1; i <= 10000; ++i) {
//             bit[i] = bit[i >> 1] + (i & 1);
//         }
//         Collections.sort(list, new Comparator<Integer>() {
//             public int compare(Integer x, Integer y) {
//                 if (bit[x] != bit[y]) {
//                     return bit[x] - bit[y];
//                 } else {
//                     return x - y;
//                 }
//             }
//         });
//         for (int i = 0; i < arr.length; ++i) {
//             arr[i] = list.get(i);
//         }
//         return arr;
//     }
// }
//效率最高
public class 根据数字二进制下1的数目排序 {
    public int[] sortByBits(int[] arr) {
        int length = arr.length;

        /*
            根据 1的个数 和 当前数值，存储 每一个数字：
                此处是本题解的精髓：1的个数权值最大，其次是本身的值，方便之后的 还原和排序
         */
        for (int i = 0; i < length; i++) {
            //这个效率高
            arr[i] = Integer.bitCount(arr[i]) * 100_000 + arr[i];
            //  arr[i] = countOne(arr[i]) * 100_000 + arr[i];
        }

        /*
            将 存储的数字，还原成最初始的数字，并根据 1的个数 和 当前数值 排序

         */
        Arrays.sort(arr);
        for (int i = 0; i < length; i++) {
            arr[i] %= 100_000;
        }
        return arr;
    }
    // public int countOne(int n) {
    //     int sum = 0;
    //     while (n != 0) {
    //         sum += (n & 1);
    //         n >>= 1;
    //     }
    //     return sum;
    // }
}