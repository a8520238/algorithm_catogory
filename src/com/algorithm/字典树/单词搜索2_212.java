package com.algorithm.字典树;

import java.util.ArrayList;
import java.util.List;

//标准字典树
public class 单词搜索2_212 {

    private TireNode head;
    List<String> list = new ArrayList<>();
    char[][] board;
    boolean[][] f;
    StringBuilder builder = new StringBuilder();
    int[] dx = {0, 0, 1, -1};
    int[] dy = {1, -1, 0, 0};

    class TireNode {
        private boolean isLeaf;
        private TireNode[] child = new TireNode[26];
    }

    private void insert(String s) {
        char[] str = s.toCharArray();
        TireNode cur = head;
        for (char c: str) {
            if (cur.child[c-'a'] == null) {
                cur.child[c-'a'] = new TireNode();
            }
            cur = cur.child[c-'a'];
        }
        cur.isLeaf = true;
    }

    public List<String> findWords(char[][] board, String[] words) {
        this.board = board;
        head = new TireNode();
        f = new boolean[board.length][board[0].length];
        for (String s: words) {
            insert(s);
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                search(i, j, head);
            }
        }
        return list;
    }

    public void search (int i, int j, TireNode cur) {
        char c = board[i][j];
        if (cur.child[c-'a'] == null) {
            return;
        }
        builder.append(c);
        f[i][j] = true;
        cur = cur.child[c-'a'];
        if (cur.isLeaf) {
            list.add(builder.toString());
            cur.isLeaf = false;
        }
        for (int k = 0; k < 4; k++) {
            int newX = i + dx[k];
            int newY = j + dy[k];
            if (newX >= 0 && newX < board.length && newY >= 0 && newY < board[0].length && !f[newX][newY]) {
                search(newX, newY, cur);
            }
        }
        builder.deleteCharAt(builder.length() - 1);
        f[i][j] = false;
    }
}
