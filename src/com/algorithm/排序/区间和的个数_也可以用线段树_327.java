package com.algorithm.排序;

//官方题解5种解法todo
//前缀和加归并排序
class 区间和的个数_也可以用线段树_327 {
    public int countRangeSum(int[] nums, int lower, int upper) {
        long s = 0;
        long[] sum = new long[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            s += nums[i];
            sum[i + 1] = s;
        }
        return countRangeSumRecursive(sum, lower, upper, 0, sum.length - 1);
    }

    public int countRangeSumRecursive(long[] sum, int lower, int upper, int left, int right) {
        if (left == right) {
            return 0;
        } else {
            int mid = (left + right) / 2;
            int n1 = countRangeSumRecursive(sum, lower, upper, left, mid);
            int n2 = countRangeSumRecursive(sum, lower, upper, mid + 1, right);
            int ret = n1 + n2;

            //统计下标对的数量
            int i = left;
            int l = mid + 1;
            int r = mid + 1;
            while (i <= mid) {
                while (l <= right && sum[l] - sum[i] < lower) {
                    l++;
                }
                while (r <= right && sum[r] - sum[i] <= upper) {
                    r++;
                }
                ret += r - l;
                i++;
            }
            //随后合并两个排序数组
            int[] sorted = new int[right - left + 1];
            int p1 = left, p2 = mid + 1;
            int p = 0;
            while (p1 <= mid && p2 <= right) {
                if (sum[p1] < sum[p2]) {
                    sorted[p++] = (int)sum[p1++];
                } else {
                    sorted[p++] = (int)sum[p2++];
                }
            }
            while (p1 <= mid) {
                sorted[p++] = (int)sum[p1++];
            }
            while (p2 <= right) {
                sorted[p++] = (int)sum[p2++];
            }

            for (int j = 0; j < sorted.length; j++) {
                sum[left + j] = sorted[j];
            }
            return ret;
        }
    }
}
//线段树
// class 区间和的个数_也可以用线段树_327 {
//     public int countRangeSum(int[] nums, int lower, int upper) {
//         long sum = 0;
//         long[] preSum = new long[nums.length + 1];
//         for (int i = 0; i < nums.length; i++) {
//             sum += nums[i];
//             preSum[i + 1] = sum;
//         }
//         //注意treeSet进行了排序
//         Set<Long> allNumbers = new TreeSet<Long>();
//         for (long x: preSum) {
//             allNumbers.add(x);
//             allNumbers.add(x - lower);
//             allNumbers.add(x - upper);
//         }

//         Map<Long, Integer> values = new HashMap<>();
//         int idx = 0;
//         for (long x: allNumbers) {
//             values.put(x, idx++);
//         }
//         SegNode root = build(0, values.size() - 1);
//         int ret = 0;
//         for (long x: preSum) {
//             int left = values.get(x - upper), right = values.get(x - lower);
//             ret += count(root, left, right);
//             insert(root, values.get(x));
//         }
//         return ret;
//     }

//     public SegNode build(int left, int right) {
//         SegNode node = new SegNode(left, right);
//         if (left == right) {
//             return node;
//         }
//         int mid = (left + right) / 2;
//         node.lchild = build(left, mid);
//         node.rchild = build(mid + 1, right);
//         return node;
//     }

//     public int count(SegNode root, int left, int right) {
//         if (left > root.hi || right < root.lo) {
//             return 0;
//         }
//         if (left <= root.lo && root.hi <= right) {
//             return root.add;
//         }
//         return count(root.lchild, left, right) + count(root.rchild, left, right);
//     }

//     public void insert(SegNode root, int val) {
//         root.add++;
//         if (root.lo == root.hi) {
//             return;
//         }
//         int mid = (root.lo + root.hi) / 2;
//         if (val <= mid) {
//             insert(root.lchild, val);
//         } else {
//             insert(root.rchild, val);
//         }
//     }

//     class SegNode {
//         int lo, hi, add;
//         SegNode lchild, rchild;

//         public SegNode(int left, int right) {
//             lo = left;
//             hi = right;
//             add =0;
//             lchild = null;
//             rchild = null;
//         }
//     }
// }
//On2 前缀和
// class 区间和的个数_也可以用线段树_327 {
//     public int countRangeSum(int[] nums, int lower, int upper) {
//         long[] sum = new long[nums.length + 1];
//         long s = 0;
//         for (int i = 0; i < nums.length; i++) {
//             s += nums[i];
//             sum[i + 1] = s;
//         }
//         int res = 0;
//         for (int i = 0; i < sum.length; i++) {
//             for (int j = i + 1; j < sum.length; j++) {
//                 if (sum[j] - sum[i] >= lower && sum[j] - sum[i] <= upper) {
//                     res++;
//                 }
//             }
//         }
//         return res;
//     }
// }
//On2
// class 区间和的个数_也可以用线段树_327 {
//     public int countRangeSum(int[] nums, int lower, int upper) {
//         int res = 0;
//         for (int i = 0; i < nums.length; i++) {
//             long cur = 0;
//             for (int j = i; j < nums.length; j++) {
//                 cur += nums[j];
//                 if (cur >= lower && cur <= upper) {
//                     res++;
//                 }
//             }
//         }
//         return res;
//     }
// }

