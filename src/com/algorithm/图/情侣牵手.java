package com.algorithm.图;

/**
 * @Author Liguangzhe
 * @Date created in  2021/2/14
 */

/*
* N 对情侣坐在连续排列的 2N 个座位上，想要牵到对方的手。 计算最少交换座位的次数，以便每对情侣可以并肩坐在一起。 一次交换可选择任意两人，让他们站起来交换座位。

人和座位用 0 到 2N-1 的整数表示，情侣们按顺序编号，第一对是 (0, 1)，第二对是 (2, 3)，以此类推，最后一对是 (2N-2, 2N-1)。

这些情侣的初始座位  row[i] 是由最初始坐在第 i 个座位上的人决定的。

示例 1:

输入: row = [0, 2, 1, 3]
输出: 1
解释: 我们只需要交换row[1]和row[2]的位置即可。


示例 2:

输入: row = [3, 2, 0, 1]
输出: 0
解释: 无需交换座位，所有的情侣都已经可以手牵手了。


说明:


	len(row) 是偶数且数值在 [4, 60]范围内。
	可以保证row 是序列 0...len(row)-1 的一个全排列
* */
// class Solution {
//     public int minSwapsCouples(int[] row) {
//         int n = row.length;
//         int tot = n / 2;
//         int[] f = new int[tot];
//         for (int i = 0; i < tot; i++) {
//             f[i] = i;
//         }
//         for (int i = 0; i < n; i += 2) {
//             int l = row[i] / 2;
//             int r = row[i + 1] / 2;
//             add(f, l, r);
//         }
//         Map<Integer, Integer> map = new HashMap<>();
//         for (int i = 0; i < tot; i++) {
//             int fx = getf(f, i);
//             map.put(fx, map.getOrDefault(fx, 0) + 1);
//         }
//         int ret = 0;
//         for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
//             ret += entry.getValue() - 1;
//         }
//         return ret;
//     }

//     public int getf(int[] f, int x) {
//         if (f[x] == x) {
//             return x;
//         }
//         int newf = getf(f, f[x]);
//         f[x] = newf;
//         return newf;
//     }
//     public void add(int[] f, int x, int y) {
//         int fx = getf(f, x);
//         int fy = getf(f, y);
//         f[fx] = fy;
//     }
// }


public class 情侣牵手 {

    public int minSwapsCouples(int[] row) {
        int len = row.length;
        int N = len / 2;
        UnionFind unionFind = new UnionFind(N);
        for (int i = 0; i < len; i += 2) {
            unionFind.union(row[i] / 2, row[i + 1] / 2);
        }
        return N - unionFind.getCount();
    }

    private class UnionFind {

        private int[] parent;

        private int count;

        public int getCount() {
            return count;
        }

        public UnionFind(int n) {
            this.count = n;
            this.parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            while (x != parent[x]) {
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return;
            }

            parent[rootX] = rootY;
            count--;
        }
    }
}

