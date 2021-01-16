package com.algorithm.dp.打家劫舍;

public class 打家劫舍3_337 {
    //dp
    public int rob(TreeNode root) {
        int[] res = dfs(root);
        return Math.max(res[0], res[1]);
    }
    public int[] dfs(TreeNode root) {
        if (root == null) {
            return new int[] {0, 0};
        }
        int[] left = dfs(root.left);
        int[] right = dfs(root.right);
        int selected = left[1] + right[1] + root.val;
        int unselected = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        return new int[] {selected, unselected};
    }
    //记忆法
    // Map<TreeNode, Integer> map = new HashMap<>();
    // public int rob(TreeNode root) {
    //     if (root == null) {
    //         return 0;
    //     }
    //     if (map.containsKey(root)) {
    //         return map.get(root);
    //     }
    //     int doi = root.val +
    //     (root.left == null? 0: rob(root.left.left) + rob(root.left.right)) +
    //     (root.right == null? 0: rob(root.right.left) + rob(root.right.right));
    //     int undo = rob(root.left) + rob(root.right);
    //     int res = Math.max(doi, undo);
    //     map.put(root, res);
    //     return res;
    // }
     public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
  }
}
