package com.algorithm.树;

import java.util.*;

public class 序列化和反序列化N叉树428 {
    public String serialize(Node root) {
        if (root == null) {
            return "";
        }
        Queue<Node> queue = new LinkedList<>();
        StringBuilder builder = new StringBuilder();
        queue.add(root);
        builder.append(root.val);
        builder.append('!');
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            for (Node node: cur.children) {
                queue.add(node);
                builder.append(node.val + ",");
            }
            builder.append('!');
        }
        return builder.toString();
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }
        Queue<Node> queue = new LinkedList<>();
        Node root = new Node(-1);
        queue.add(root);
        String[] s = data.split("!");
        System.out.println(Arrays.toString(s));
        System.out.println(s.length);
        int i = 0;
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            cur.children = new ArrayList<>();
            String snow = s[i++];
            String[] n = snow.split(",");
            for (String ss: n) {
                if (ss.length() == 0) {
                    continue;
                }
                int val = Integer.parseInt(ss);
                Node next = new Node(val);
                cur.children.add(next);
                queue.add(next);
            }
        }
        return root.children.get(0);
    }
    public static void main(String[] args) {
        String s = "1,!3,2,4,!5,6,!!!!!";
        序列化和反序列化N叉树428 test  = new 序列化和反序列化N叉树428();
        test.deserialize(s);
    }
}

class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
}

// dfs 解法 用到了迭代器
// 还有一种思路是使用了"["标志栈空间
//class Codec {
//    // Encodes a tree to a single string.
//    public String serialize(Node root) {
//        if (root == null) {
//            return "";
//        }
//        StringBuilder builder = new StringBuilder();
//        dfs(root, builder);
//        return builder.toString();
//    }
//
//    private void dfs(Node root, StringBuilder builder) {
//        int val = root.val;
//        int len = root.children.size();
//        builder.append(val + "," + len + ",");
//        for (Node node: root.children) {
//            dfs(node, builder);
//        }
//    }
//
//    // Decodes your encoded data to tree.
//    public Node deserialize(String data) {
//        if (data.length() == 0) {
//            return null;
//        }
//        String[] s = data.split(",");
//        Iterator<String> iterator = Arrays.stream(s).iterator();
//        return rdfs(iterator);
//    }
//
//    private Node rdfs(Iterator<String> iterator) {
//        int val = Integer.parseInt(iterator.next());
//        int num = Integer.parseInt(iterator.next());
//        Node root = new Node(val);
//        root.children = new ArrayList<>();
//        for (int i = 0; i < num; i++) {
//            root.children.add(rdfs(iterator));
//        }
//        return root;
//    }
//}