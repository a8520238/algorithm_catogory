package com.algorithm.字典树;

import java.util.ArrayList;
import java.util.List;


//这道题的关键在于要有回溯思想，同时利用前缀，判断当前已经存在的String所构成的前缀在words中
//是否存在，这里利用字典树每个节点保存以当前为前缀的所有words的index。然后回溯得到所有解。
public class 单词方块425 {
    List<List<String>> res = new ArrayList<>();
    private TireNode head = new TireNode();
    String[] words;
    int n;

    public List<List<String>> wordSquares(String[] words) {
        this.words = words;
        this.n = words[0].length();
        for (int i = 0; i < words.length; i++) {
            String s = words[i];
            TireNode cur = head;
            char[] str = s.toCharArray();
            for (int j = 0; j < str.length; j++) {
                if (cur.child[str[j] - 'a'] == null) {
                    cur.child[str[j] - 'a'] = new TireNode();
                }
                cur = cur.child[str[j] - 'a'];
                cur.list.add(i);
            }
        }
        for (String s: words) {
            List<String> ws = new ArrayList<>();
            ws.add(s);
            backtracking(1, ws);
        }
        return res;
    }

    public void backtracking(int index, List<String> ws) {
        if (index == n) {
            res.add(new ArrayList<String> (ws));
            return;
        }
        StringBuilder prefix = new StringBuilder();
        for (String s: ws) {
            prefix.append(s.charAt(index));
        }
        for (int nIndex: findWithPrefix(prefix.toString())) {
            ws.add(this.words[nIndex]);
            backtracking(index + 1, ws);
            ws.remove(ws.size() - 1);
        }
    }

    public List<Integer> findWithPrefix(String s) {
        char[] str = s.toCharArray();
        TireNode cur = head;
        for (int i = 0; i < str.length; i++) {
            if (cur.child[str[i] - 'a'] == null) {
                return new ArrayList<>();
            }
            cur = cur.child[str[i] - 'a'];
        }
        return cur.list;
    }

    class TireNode {
        TireNode[] child;
        List<Integer> list;
        TireNode() {
            this.child = new TireNode[26];
            this.list = new ArrayList<>();
        }
    }
}
