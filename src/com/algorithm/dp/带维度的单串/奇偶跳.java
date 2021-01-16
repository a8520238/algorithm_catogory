package com.algorithm.dp.带维度的单串;

import java.util.TreeMap;

/*
* 给定一个整数数组 A，你可以从某一起始索引出发，跳跃一定次数。在你跳跃的过程中，第 1、3、5... 次跳跃称为奇数跳跃，而第 2、4、6... 次跳跃称为偶数跳跃。

你可以按以下方式从索引 i 向后跳转到索引 j（其中 i < j）：

在进行奇数跳跃时（如，第 1，3，5... 次跳跃），你将会跳到索引 j，使得 A[i] <= A[j]，A[j] 是可能的最小值。如果存在多个这样的索引 j，你只能跳到满足要求的最小索引 j 上。
在进行偶数跳跃时（如，第 2，4，6... 次跳跃），你将会跳到索引 j，使得 A[i] >= A[j]，A[j] 是可能的最大值。如果存在多个这样的索引 j，你只能跳到满足要求的最小索引 j 上。
（对于某些索引 i，可能无法进行合乎要求的跳跃。）
如果从某一索引开始跳跃一定次数（可能是 0 次或多次），就可以到达数组的末尾（索引 A.length - 1），那么该索引就会被认为是好的起始索引。

返回好的起始索引的数量。
 
示例 1：

输入：[10,13,12,14,15]
输出：2
解释：
从起始索引 i = 0 出发，我们可以跳到 i = 2，（因为 A[2] 是 A[1]，A[2]，A[3]，A[4] 中大于或等于 A[0] 的最小值），然后我们就无法继续跳下去了。
从起始索引 i = 1 和 i = 2 出发，我们可以跳到 i = 3，然后我们就无法继续跳下去了。
从起始索引 i = 3 出发，我们可以跳到 i = 4，到达数组末尾。
从起始索引 i = 4 出发，我们已经到达数组末尾。
总之，我们可以从 2 个不同的起始索引（i = 3, i = 4）出发，通过一定数量的跳跃到达数组末尾。
示例 2：

输入：[2,3,1,1,4]
输出：3
解释：
从起始索引 i=0 出发，我们依次可以跳到 i = 1，i = 2，i = 3：

在我们的第一次跳跃（奇数）中，我们先跳到 i = 1，因为 A[1] 是（A[1]，A[2]，A[3]，A[4]）中大于或等于 A[0] 的最小值。

在我们的第二次跳跃（偶数）中，我们从 i = 1 跳到 i = 2，因为 A[2] 是（A[2]，A[3]，A[4]）中小于或等于 A[1] 的最大值。A[3] 也是最大的值，但 2 是一个较小的索引，所以我们只能跳到 i = 2，而不能跳到 i = 3。

在我们的第三次跳跃（奇数）中，我们从 i = 2 跳到 i = 3，因为 A[3] 是（A[3]，A[4]）中大于或等于 A[2] 的最小值。

我们不能从 i = 3 跳到 i = 4，所以起始索引 i = 0 不是好的起始索引。

类似地，我们可以推断：
从起始索引 i = 1 出发， 我们跳到 i = 4，这样我们就到达数组末尾。
从起始索引 i = 2 出发， 我们跳到 i = 3，然后我们就不能再跳了。
从起始索引 i = 3 出发， 我们跳到 i = 4，这样我们就到达数组末尾。
从起始索引 i = 4 出发，我们已经到达数组末尾。
总之，我们可以从 3 个不同的起始索引（i = 1, i = 3, i = 4）出发，通过一定数量的跳跃到达数组末尾。
* */

//超时解法
// class Solution {
//     public int oddEvenJumps(int[] A) {
//         if (A == null || A.length == 0) {
//             return 0;
//         }
//         int res = 0;
//         int len = A.length;
//         boolean[] dp1 = new boolean[len];
//         boolean[] dp2 = new boolean[len];
//         dp1[len - 1] = true;
//         dp2[len - 1] = true;
//         for (int i = len - 2; i >= 0; i--) {
//             int min = -1;
//             for (int j = i + 1; j < len; j++) {
//                 if (A[j] >= A[i]) {
//                     if (min == -1) {
//                         min = j;
//                     } else {
//                         if (A[j] < A[min]) {
//                             min = j;
//                         }
//                     }
//                 }
//             }
//             dp1[i] = min == -1? false: dp2[min];
//             int max = -1;
//             for (int j = i + 1; j < len; j++) {
//                 if (A[j] <= A[i]) {
//                     if (max == -1) {
//                         max = j;
//                     } else {
//                         if (A[j] > A[max]) {
//                             max = j;
//                         }
//                     }
//                 }
//             }
//             dp2[i] = max == -1? false: dp1[max];
//         }
//         for (int i = 0; i < dp1.length; i++) {
//             if (dp1[i]) {
//                 res++;
//             }
//         }
//         return res;
//     }
// }
//自己搞的单调栈，错误
// class Solution {
//     public int oddEvenJumps(int[] A) {
//         if (A == null || A.length < 2) {
//             return A.length;
//         }
//         int n = A.length;
//         boolean[] dp1 = new boolean[n];
//         boolean[] dp2 = new boolean[n];
//         Deque<Integer> deque1 = new LinkedList<>();
//         Deque<Integer> deque2 = new LinkedList<>();
//         dp1[n - 1] = true;
//         dp2[n - 1] = true;
//         deque1.push(n - 1);
//         deque2.push(n - 1);
//         for (int i = n - 2; i >= 0; i--) {
//             while (!deque1.isEmpty() && A[deque1.peek()] < A[i]) {
//                 deque1.pop();
//             }
//             dp1[i] = deque1.isEmpty()? false: dp2[deque1.peek()];
//             deque1.push(i);
//             while (!deque2.isEmpty() && A[deque2.peek()] > A[i]) {
//                 deque2.pop();
//             }
//             dp2[i] = deque2.isEmpty()? false: dp1[deque2.peek()];
//             deque2.push(i);
//         }
//         System.out.println(Arrays.toString(dp1));
//         System.out.println(Arrays.toString(dp2));
//         int res = 0;
//         for (int i = 0; i < dp1.length; i++) {
//             if (dp1[i]) {
//                 res++;
//             }
//         }
//         return res;
//     }
// }
// class Solution {
//     public int oddEvenJumps(int[] A) {
//         int n = A.length;
//         Integer[] a = new Integer[n];
//         int[] oddnext = new int[n];
//         int[] evennext = new int[n];
//         //对下标排序，按照值的大小
//         for (int i = 0; i < n; i++) {
//             a[i] = i;
//         }
//         Arrays.sort(a, (aa, bb) -> A[aa] == A[bb]? aa - bb: A[aa] - A[bb]);
//         //oddnext找到比A[i]大的最小值的下标
//         oddnext = make(a);

//         Arrays.sort(a, (aa, bb) -> A[aa] == A[bb]? aa - bb: A[bb] - A[aa]);
//         //evennext找到比A[i]小的最大值的下标
//         evennext = make(a);
//         boolean[] odd = new boolean[n];
//         boolean[] even = new boolean[n];
//         odd[n - 1] = even[n - 1] = true;
//         for (int i = n - 2; i >= 0; i--) {
//             if (oddnext[i] != -1) {
//                 odd[i] = even[oddnext[i]];
//             }
//             if (evennext[i] != -1) {
//                 even[i] = odd[evennext[i]];
//             }
//         }
//         int ans = 0;
//         for (int i = 0; i < n; i++) {
//             if (odd[i]) {
//                 ans++;
//             }
//         }
//         return ans;
//     }
//     private int[] make(Integer[] arr) {
//         int n = arr.length;
//         int[] ans = new int[n];
//         Deque<Integer> stack = new LinkedList<>();
//         Arrays.fill(ans, -1);
//         //此单调栈，栈顶的值小，栈底的值大，如果遇到大的值，证明该index是遇到的第一个比栈内index(比当前index小)所对应的值大的最小值。
//         //不管所对应的A的值是升序还是降序，此刻index是栈顶小，栈底大，出栈时，当前arr[i]一定是最符合的下标
//         for (int i = 0; i < n; i++) {
//             while (!stack.isEmpty() && stack.peek() < arr[i]) {
//                 ans[stack.pop()] = arr[i];
//             }
//             stack.push(arr[i]);
//         }
//         return ans;
//     }
// }

//treeMap解法

class 奇偶跳 {
    public int oddEvenJumps(int[] A) {
        int N = A.length;
        if (N <= 1) {
            return N;
        }
        //当前位置是奇数跳
        boolean[] odd = new boolean[N];
        //当前位置是偶数跳
        boolean[] even = new boolean[N];
        odd[N - 1] = even[N - 1] = true;
        TreeMap<Integer, Integer> vals = new TreeMap<>();
        vals.put(A[N - 1], N - 1);
        for (int i = N - 2; i >= 0; i--) {
            int v = A[i];
            //第一个恰好大或者恰好小一定是相等的值
            if (vals.containsKey(v)) {
                odd[i] = even[vals.get(v)];
                even[i] = odd[vals.get(v)];
            } else {
                Integer lower = vals.lowerKey(v);
                Integer higher = vals.higherKey(v);
                if (lower != null) {
                    even[i] = odd[vals.get(lower)];
                }
                if (higher != null) {
                    odd[i] = even[vals.get(higher)];
                }
            }
            vals.put(v, i);
        }
        int ans = 0;
        for (boolean b: odd) {
            if (b) {
                ans++;
            }
        }
        return ans;
    }
}