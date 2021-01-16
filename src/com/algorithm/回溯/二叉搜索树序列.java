package com.algorithm.回溯;


import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/*
* 从左向右遍历一个数组，通过不断将其中的元素插入树中可以逐步地生成一棵二叉搜索树。给定一个由不同节点组成的二叉搜索树，输出所有可能生成此树的数组。

 

示例：
给定如下二叉树

        2
       / \
      1   3
返回：

[
   [2,1,3],
   [2,3,1]
]
* */
public class 二叉搜索树序列 {
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> list = new ArrayList<>();
    public List<List<Integer>> BSTSequences(TreeNode root) {
        //dq是候选队列
        Deque<TreeNode> dq = new LinkedList<>();
        if (root != null) {
            dq.offer(root);
        }
        dfs(dq);
        return res;
    }
    public void dfs(Deque<TreeNode> dq) {
        //如果候选队列为空，证明已经全部完成
        if (dq.isEmpty()) {
            res.add(new ArrayList<>(list));
            return;
        }
        int size = dq.size();
        while (size > 0) {
            //出去的时候从前面出去，进来的时候从后面进来，避免重复
            TreeNode cur = dq.pollFirst();
            list.add(cur.val);
            int children = 0;
            if (cur.left != null) {
                children++;
                dq.offerLast(cur.left);
            }
            if (cur.right != null) {
                children++;
                dq.offerLast(cur.right);
            }
            dfs(dq);

            //回溯候选队列
            while (children > 0) {
                dq.pollLast();
                children--;
            }
            dq.offerLast(cur);
            list.remove(list.size() - 1);
            size--;
        }
    }
}
class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
 }

 //每一个传一个hashset，存下一次的值，这样思路清晰，但效率低
//class Solution {
//    List<List<Integer>> reses = new LinkedList<>();
//    LinkedList<Integer> res = new LinkedList<>();
//    public List<List<Integer>> BSTSequences(TreeNode root) {
//        if (root == null) {
//            reses.add(res);
//            return reses;
//        }
//        HashSet<TreeNode> curLevel = new HashSet<>();
//        curLevel.add(root);
//        dfs(curLevel);
//        return reses;
//    }
//    public void dfs(HashSet<TreeNode> curLevel) {
//        //当前集合没有需要遍历的元素，说明遍历到底
//        if (curLevel.size() == 0) {
//            reses.add(new LinkedList<>(res));
//            return;
//        }
//        HashSet<TreeNode> nextLevel = new HashSet<>(curLevel);
//        for (TreeNode t: curLevel) {
//            res.add(t.val);
//            nextLevel.remove(t);//出了当前节点，其余节点下一层都可以遍历
//            if (t.left != null) {
//                nextLevel.add(t.left);
//            }
//            if (t.right != null) {
//                nextLevel.add(t.right);
//            }
//            dfs(nextLevel);
//            if (t.left != null) {
//                nextLevel.remove(t.left);
//            }
//            if (t.right != null) {
//                nextLevel.remove(t.right);
//            }
//            nextLevel.add(t);
//            res.removeLast();
//        }
//    }
//}