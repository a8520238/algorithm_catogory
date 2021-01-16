package com.algorithm.树;


//这个题的关键在于使用每一行的虚拟头结点，进行换行，使用next指针进行遍历
public class 填充每个节点的下一个右侧节点指针2_117 {
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        Node save = root;
        while (root != null) {
            Node temp = new Node(0);
            Node cur = temp;
            while (root != null) {
                if (root.left != null) {
                    cur.next = root.left;
                    cur = cur.next;
                }
                if (root.right != null) {
                    cur.next = root.right;
                    cur = cur.next;
                }
                root = root.next;
            }
            root = temp.next;
        }
        return save;
    }
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
}

