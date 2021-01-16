package com.algorithm.树;

//递归方式
// public class 二叉搜索树的后序遍历序列 {
//     public boolean verifyPostorder(int[] postorder) {
//         return dfs(postorder, 0, postorder.length - 1);
//     }

import java.util.Deque;
import java.util.LinkedList;

//     public boolean dfs(int[] postorder, int left, int right) {
//         if (left >= right) {
//             return true;
//         }
//         int root = postorder[right];
//         int head1 = right;
//         for (int i = left; i < right; i++) {
//             if (postorder[i] > root) {
//                 head1 = i;
//                 break;
//             }
//         }
//         for (int i = head1; i < right; i++) {
//             if (postorder[i] < root) {
//                 return false;
//             }
//         }
//         return dfs(postorder, left, head1 - 1) && dfs(postorder, head1, right - 1);
//     }
// }
//迭代方式
//单调栈
public class 二叉搜索树的后序遍历序列 {
    public boolean verifyPostorder(int[] postorder) {
        Deque<Integer> stack = new LinkedList<>();
        int parent = Integer.MAX_VALUE;
        for (int i = postorder.length - 1; i >= 0; i--) {
            int cur = postorder[i];
            while (!stack.isEmpty() && stack.peek() > cur) {
                parent = stack.pop();
            }
            //每一次出栈都将parent的后侧节点出栈，如果后面碰到大于parent的值，也就是左节点大于了parent，所以返回false
            if (cur > parent) {
                return false;
            }
            stack.push(cur);
        }
        return true;
    }
}