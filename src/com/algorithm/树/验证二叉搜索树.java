package com.algorithm.树;

public class 验证二叉搜索树 {
    //这种思想比中序遍历精妙一些
    boolean isValidBST(TreeNode root) {
        return isValidBST(root, null, null);
    }

    boolean isValidBST(TreeNode root, TreeNode min, TreeNode max) {
        if (root == null) return true;
        if (min != null && root.val <= min.val) return false;
        if (max != null && root.val >= max.val) return false;
        return isValidBST(root.left, min, root)
                && isValidBST(root.right, root, max);
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }
}
