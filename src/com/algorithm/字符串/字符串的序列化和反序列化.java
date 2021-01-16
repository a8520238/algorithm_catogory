package com.algorithm.字符串;

public class 字符串的序列化和反序列化 {
    //括号中序序列化
    public String serialize(TreeNode root) {
        if (root == null) {
            return "N";
        }
        String l = "(" + serialize(root.left) + ")";
        String r = ")" + serialize(root.right) + ")";
        return l + root.val + r;
    }

    public TreeNode deserialize(String data) {
        int[] ptr = {0};
        return parse(data, ptr);
    }

    public TreeNode parse(String data, int[] ptr) {
        if (data.charAt(ptr[0]) == 'N') {
            ++ptr[0];
            return null;
        }
        TreeNode cur = new TreeNode(0);
        cur.left = parseSubtree(data, ptr);
        cur.val = parseInt(data, ptr);
        cur.right = parseSubtree(data, ptr);
        return cur;
    }

    public TreeNode parseSubtree(String data, int[] ptr) {
        ++ptr[0];
        TreeNode subtree = parse(data, ptr);
        ++ptr[0];
        return subtree;
    }

    public int parseInt(String data, int[] ptr) {
        int x = 0, sgn = 1;
        if (!Character.isDigit(data.charAt(ptr[0]))) {
            sgn = -1;
            ++ptr[0];
        }
        while (Character.isDigit(data.charAt(ptr[0]))) {
            x = x * 10 + data.charAt(ptr[0]++) - '0';
        }
        return x * sgn;
    }
    //递归先序序列化
    // int index = 0;
    // public String serialize(TreeNode root) {
    //     if (root == null) {
    //         return "";
    //     }
    //     StringBuilder builder = new StringBuilder();
    //     preSerialize(root, builder);
    //     return builder.toString();
    // }

    // public void preSerialize(TreeNode root, StringBuilder builder) {
    //     if (root == null) {
    //         builder.append("null,");
    //     } else {
    //         builder.append(root.val + ",");
    //         preSerialize(root.left, builder);
    //         preSerialize(root.right, builder);
    //     }
    // }

    // public TreeNode deserialize(String data) {
    //     if (data == null || data.length() == 0) {
    //         return null;
    //     }
    //     String[] str = data.split(",");
    //     TreeNode root = preDeserialize(str);
    //     index = 0;
    //     return root;
    // }

    // public TreeNode preDeserialize(String[] str) {
    //     if (str[index].equals("null")) {
    //         index++;
    //         return null;
    //     }
    //     TreeNode cur = new TreeNode(Integer.parseInt(str[index]));
    //     index++;
    //     cur.left = preDeserialize(str);
    //     cur.right = preDeserialize(str);
    //     return cur;
    // }

    // 层序序列化
    // Encodes a tree to a single string.
    // public String serialize(TreeNode root) {
    //     if (root == null) {
    //         return "";
    //     }
    //     StringBuilder builder = new StringBuilder();
    //     Queue<TreeNode> queue = new LinkedList<>();
    //     queue.add(root);
    //     while (!queue.isEmpty()) {
    //         TreeNode cur = queue.poll();
    //         if (cur == null) {
    //             builder.append("null" + ",");
    //             continue;
    //         } else {
    //             builder.append(cur.val + ",");
    //         }
    //         queue.add(cur.left);
    //         queue.add(cur.right);
    //     }
    //     return builder.toString();
    // }

    // // Decodes your encoded data to tree.
    // public TreeNode deserialize(String data) {
    //     if (data == null || data.length() == 0) {
    //         return null;
    //     }
    //     String[] str = data.split(",");
    //     TreeNode root = new TreeNode(Integer.parseInt(str[0]));
    //     Queue<TreeNode> queue = new LinkedList<>();
    //     queue.add(root);
    //     int index = 1;
    //     while (!queue.isEmpty()) {
    //         TreeNode cur = queue.poll();
    //         if (!str[index].equals("null")) {
    //             cur.left = new TreeNode(Integer.parseInt(str[index]));
    //             queue.add(cur.left);
    //         }
    //         index++;
    //         if (!str[index].equals("null")) {
    //             cur.right = new TreeNode(Integer.parseInt(str[index]));
    //             queue.add(cur.right);
    //         }
    //         index++;
    //     }
    //     return root;
    // }
}
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
 }