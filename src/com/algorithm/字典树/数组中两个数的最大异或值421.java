package com.algorithm.字典树;


//数字0、1字典树
class 数组中两个数的最大异或值421 {

    private TireNode head = new TireNode();

    class TireNode {
        private TireNode[] child;
        TireNode() {
            child = new TireNode[2];
        }
    }

    public void insert(int n) {
        TireNode cur = head;
        for (int i = 31; i >= 0; i--) {
            int remain = (n >> i) & 1;
            if (cur.child[remain] == null) {
                cur.child[remain] = new TireNode();
            }
            cur = cur.child[remain];
        }
    }

    public int findMaximumXOR(int[] nums) {
        int res = 0;
        for (int n: nums) {
            insert(n);
        }
        for (int n: nums) {
            TireNode cur = head;
            int sum = 0;
            for (int i = 31; i >= 0; i--) {
                int remain = (n >> i) & 1;
                if (remain == 1) {
                    if (cur.child[0] != null) {
                        cur = cur.child[0];
                        sum += (1 << i);
                    } else {
                        cur = cur.child[1];
                    }
                } else {
                    if (cur.child[1] != null) {
                        cur = cur.child[1];
                        sum += (1 << i);
                    } else {
                        cur = cur.child[0];
                    }
                }
            }
            res = Math.max(res, sum);
        }
        return res;
    }
}
