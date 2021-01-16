package com.algorithm.树;

public class 二叉树的最近公共祖先 {
    //暴力递归超时
    // public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    //     if (root == null) {
    //         return null;
    //     }
    //     boolean left = find(root.left, p, q);
    //     boolean right = find(root.right, p, q);
    //     if (left && right || (left || right) && (root == p || root == q)) {
    //         return root;
    //     }
    //     return left? lowestCommonAncestor(root.left, p, q): lowestCommonAncestor(root.right, p, q);
    // }
    // public boolean find(TreeNode root, TreeNode p, TreeNode q) {
    //     if (root == null) {
    //         return false;
    //     }
    //     if (root == p || root == q) {
    //         return true;
    //     }
    //     return find(root.left, p, q) || find(root.right, p, q);
    // }
    //优化
    TreeNode res;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        find(root, p, q);
        return res;
    }
    public boolean find(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return false;
        }
        boolean left = find(root.left, p, q);
        boolean right = find(root.right, p, q);
        if (left && right || (left || right) && (root == p || root == q)) {
            res = root;
            return false;
        }
        if (root == p || root == q) {
            return true;
        }
        return left || right;
    }
}
