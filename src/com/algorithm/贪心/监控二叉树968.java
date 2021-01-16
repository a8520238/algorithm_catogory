package com.algorithm.贪心;

class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
 }
//也可以用树形dp，见题解
//const minCameraCover = (root) => {
//
//        const minCam = (root) => {
//        if (root == null) {   // base case
//        return {
//        withCam: Infinity,
//        noCamWatchByDad: 0,
//        noCamWatchBySon: 0
//        };
//        }
//        const left = minCam(root.left);   // 以左儿子为根的左子树的情况
//        const right = minCam(root.right); // 以右儿子为根的右子树的情况
//        // 下面是三个“状态转移通式”
//        const withCam = 1 + Math.min(
//        left.noCamWatchByDad + right.noCamWatchByDad,
//        left.withCam + right.noCamWatchByDad,
//        left.noCamWatchByDad + right.withCam
//        );
//
//        const noCamWatchByDad = Math.min(
//        left.withCam + right.withCam,
//        left.withCam + right.noCamWatchBySon,
//        left.noCamWatchBySon + right.withCam,
//        left.noCamWatchBySon + right.noCamWatchBySon
//        );
//
//        const noCamWatchBySon = Math.min(
//        left.withCam + right.withCam,
//        left.withCam + right.noCamWatchBySon,
//        left.noCamWatchBySon + right.withCam
//        );
//
//        return { withCam, noCamWatchByDad, noCamWatchBySon }; // 返回给父调用参考
//        };
//
//        const res = minCam(root); // 相当于 dp[root]
//        return Math.min(res.withCam, res.noCamWatchBySon); // root有相机，root没有相机，被儿子监控
//        };

public class 监控二叉树968 {
    private int res = 0;
    private TreeNode head;
    public int minCameraCover(TreeNode root) {
        head = root;
        pre(root);
        return res;
    }
    //返回0: 下面的节点需要相机，
    //返回1: 下面节点有相机
    //返回2: 下面节点无相机，但被照亮
    public int pre(TreeNode root) {
        if (root == null) {
            return 2;
        }
        int lv = pre(root.left);
        int rv = pre(root.right);
        if (lv == 0 || rv == 0) {
            res += 1;
            return 1;
        }
        if (lv == 1 || rv == 1) {
            return 2;
        }
        if (head == root) {
            res += 1;
        }
        return 0;
    }
}
