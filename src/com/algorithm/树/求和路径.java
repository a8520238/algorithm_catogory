package com.algorithm.树;

/*
* 给定一棵二叉树，其中每个节点都含有一个整数数值(该值或正或负)。设计一个算法，打印节点数值总和等于某个给定值的所有路径的数量。注意，路径不一定非得从二叉树的根节点或叶节点开始或结束，但是其方向必须向下(只能从父节点指向子节点方向)。

示例:
给定如下二叉树，以及目标和 sum = 22，

              5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1
返回:

3
解释：和为 22 的路径有：[5,4,11,2], [5,8,4,5], [4,11,7]
提示：

节点总数 <= 10000
* */

//  class 求和路径 {
//     int res = 0;
//     public int pathSum(TreeNode root, int sum) {
//         if (root == null) {
//             return 0;
//         }
//         return dfs(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
//     }
//     public int dfs(TreeNode root, int sum) {
//         if (root == null) {
//             return 0;
//         }
//         sum -= root.val;
//         return (sum == 0? 1: 0) + dfs(root.left, sum) + dfs(root.right, sum);
//     }
// }
//前缀和版本
//这个前缀和有回溯的思想

import java.util.HashMap;
import java.util.Map;

//要注意map只能存储当前这一路径上的值， 所以在返回时要删除，不可互相影响
class 求和路径 {
    public int pathSum(TreeNode root, int sum) {
        Map<Integer, Integer> prefixSumCount = new HashMap<>();
        prefixSumCount.put(0, 1);
        return recursionPathSum(root, prefixSumCount, sum, 0);
    }
    public int recursionPathSum(TreeNode node, Map<Integer, Integer> prefixSumCount, int target, int currSum) {
        if (node == null) {
            return 0;
        }
        int res = 0;
        currSum += node.val;

        res += prefixSumCount.getOrDefault(currSum - target, 0);
        prefixSumCount.put(currSum, prefixSumCount.getOrDefault(currSum, 0) + 1);

        res += recursionPathSum(node.left, prefixSumCount, target, currSum);
        res += recursionPathSum(node.right, prefixSumCount, target, currSum);

        prefixSumCount.put(currSum, prefixSumCount.get(currSum) - 1);
        return res;
    }
}
