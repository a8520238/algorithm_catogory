package com.algorithm.树;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class 后序遍历 {
    public List<Integer> postorderTraversal(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<>();
        List<Integer> list = new ArrayList<>();
        TreeNode cur = root;
        //pre是上一次最后访问，成功及加入list的
        TreeNode pre = null;
        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.peek();
            //注意判断是不是从右子树返回时，要判断右子树是否为null
            if (cur.right == pre || cur.right == null) {
                stack.pop();
                list.add(cur.val);
                pre = cur;
                cur = null;
            } else {
                cur = cur.right;
            }
        }
        return list;
    }
}
