package com.algorithm.树;

public class 二叉树深度_两种写法 {
    //自底向上
    // public int maxDepth(TreeNode root) {
    //     if (root == null) {
    //         return 0;
    //     }
    //     int res = Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    //     return res;
    // }
    //自顶向下
    int depth = 0;
    public int maxDepth(TreeNode root) {
        traver(root, 0);
        return depth;
    }
    public void traver(TreeNode cur, int depth) {
        if (cur == null) {
            this.depth = Math.max(depth, this.depth);
            return;
        }
        traver(cur.left, depth + 1);
        traver(cur.right, depth + 1);
    }
}
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}