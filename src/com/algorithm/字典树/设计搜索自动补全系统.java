package com.algorithm.字典树;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

//设计字典树系统
class 设计搜索自动补全系统 {

    private TireNode head;
    private TireNode t;
    private StringBuilder builder;

    class TireNode {
        private TireNode[] child = new TireNode[27];
        private String str;
        private int times;
    }

    class Node {
        private String str;
        private int times;
        Node () {
        }
        Node (String str, int times) {
            this.str = str;
            this.times = times;
        }
    }

    public void insert(String s, int n) {
        char[] str = s.toCharArray();
        TireNode cur = head;
        for (char c: str) {
            if (c == ' ') {
                if (cur.child[26] == null) {
                    cur.child[26] = new TireNode();
                }
                cur = cur.child[26];
                continue;
            }
            if (cur.child[c-'a'] == null) {
                cur.child[c-'a'] = new TireNode();
            }
            cur = cur.child[c-'a'];
        }
        cur.str = s;
        cur.times += n;
    }

    public 设计搜索自动补全系统(String[] sentences, int[] times) {
        head = new TireNode();
        t = head;
        builder = new StringBuilder();
        for (int i = 0; i < times.length; i++) {
            insert(sentences[i], times[i]);
        }
        t = head;
    }

    public List<String> input(char c) {
        List<String> res = new ArrayList<>();
        PriorityQueue<Node> queue = new PriorityQueue<>((a, b) -> a.times == b.times? a.str.compareTo(b.str): b.times - a.times);
        if (c == '#') {
            t.str = builder.toString();
            t.times += 1;
            builder = new StringBuilder();
            t = head;
            return res;
        }
        builder.append(c);
        if (c == ' ') {
            if (t.child[26] == null) {
                t.child[26] = new TireNode();
            }
            t = t.child[26];
        }
        else if (t.child[c-'a'] == null) {
            t.child[c-'a'] = new TireNode();
            t = t.child[c-'a'];
        } else {
            t = t.child[c-'a'];
        }
        dfs(t, queue);
        for (int i = 0; i < 3; i++) {
            if (queue.isEmpty()) {
                break;
            }
            res.add(queue.poll().str);
        }
        return res;
    }

    public void dfs(TireNode root, PriorityQueue<Node> queue) {
        if (root.str != null) {
            queue.add(new Node(root.str, root.times));
        }
        for (int i = 0; i < 27; i++) {
            if (root.child[i] != null) {
                dfs(root.child[i], queue);
            }
        }
    }

    public void updateTimes(TireNode root, String key) {
        if (root.str == null) {
            root.str = key;
            root.times = 1;
        } else {
            root.times += 1;
        }
    }

    public static void main(String[] args) {
        String[] s = new String[] {"i love you","island","iroman","i love leetcode"};
        int[] times = new int[] {5, 3, 2, 2};
        设计搜索自动补全系统 sys = new 设计搜索自动补全系统(s ,times);
        System.out.println(sys.input('i'));
        System.out.println(sys.input(' '));
        System.out.println(sys.input('a'));
        System.out.println(sys.input('#'));
    }
}