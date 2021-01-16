package com.algorithm.树;

/*
* 给出一个完全二叉树，求出该树的节点个数。

说明：

完全二叉树的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第 h 层，则该层包含 1~ 2h 个节点。

示例:

输入:
    1
   / \
  2   3
 / \  /
4  5 6

输出: 6
* */


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//直接dfs
// class Solution {
//     public int countNodes(TreeNode root) {
//         if (root == null) {
//             return 0;
//         }
//         int left = countNodes(root.left);
//         int right = countNodes(root.right);
//         return left + right + 1;
//     }
// }
//二分查找 + 位运算 复杂度Ologn2
//设根节点为第0层，当最底层包含1个节点时，共有2^h
//当最底层包含全部节点时，共有2^(h + 1) - 1
//故节点个数在[2^h, 2^(h + 1) - 1]之间，利用二分，同时节点序号如12, 1100，最高为代表最后一层，后三位100代表从根节点出发，先走右节点，再走左节点，做左节点。
// class Solution {
//     public int countNodes(TreeNode root) {
//         if (root == null) {
//             return 0;
//         }
//         int level = 0;
//         TreeNode node = root;
//         while (node.left != null) {
//             level++;
//             node = node.left;
//         }
//         int low = 1 << level, high = (1 << (level + 1)) - 1;
//         while (low < high) {
//             int mid = (high - low + 1) / 2 + low;
//             if (exists(root, level, mid)) {
//                 low = mid;
//             } else {
//                 high = mid - 1;
//             }
//         }
//         return low;
//     }
//     public boolean exists(TreeNode root, int level, int k) {
//         int bits = 1 << (level - 1);
//         TreeNode node = root;
//         while (node != null && bits > 0) {
//             if ((bits & k) == 0) {
//                 node = node.left;
//             } else {
//                 node = node.right;
//             }
//             bits >>= 1;
//         }
//         return node != null;
//     }
// }

//巧妙思路，先判断左右子树的层数，如果相等，左子树是完全二叉树，如果不等，右子树是完全二叉树
//T(n) = T(n / 2) + logn 使用主定理的特殊情况，可以求得时间复杂度是O(logn * logn)
class 完全二叉树的节点个数 {
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = countLevel(root.left);
        int right = countLevel(root.right);
        if (left == right) {
            return countNodes(root.right) + (1 << left);
        } else {
            return countNodes(root.left) + (1 << right);
        }
    }
    private int countLevel(TreeNode root) {
        int level = 0;
        while (root != null) {
            level++;
            root = root.left;
        }
        return level;
    }
}