package com.algorithm.bfs;

import java.util.*;
//思路双向/单向bfs,先建图，再bfs
public class 单词接龙 {
    // dfs超时
// class Solution {
//     // int num;
//     // public int ladderLength(String beginWord, String endWord, List<String> wordList) {
//     //     if (wordList == null || wordList.size() == 0) {
//     //         return 0;
//     //     }
//     //     Set<String> set = new HashSet<>();
//     //     num = Integer.MAX_VALUE;
//     //     for (String s: wordList) {
//     //         set.add(s);
//     //     }
//     //     if (!set.contains(endWord)) {
//     //         return 0;
//     //     }
//     //     Iterator it = set.iterator();
//     //     backward(beginWord, endWord, set, it, 0);
//     //     return num == Integer.MAX_VALUE? 0: num;
//     // }
//     // public void backward(String beginWord, String endWord, Set<String> set, Iterator it, int count) {

//     //     if (beginWord.equals(endWord)) {
//     //         num = Math.min(num, count);
//     //         return;
//     //     }
//     //     while (it.hasNext()) {
//     //         String s = (String)it.next();
//     //         int diff = 0;
//     //         for (int i = 0; i < beginWord.length(); i++) {
//     //             if (beginWord.charAt(i) != s.charAt(i)) {
//     //                 diff++;
//     //             }
//     //         }
//     //         if (diff == 1) {
//     //             it.remove();
//     //             backward(s, endWord, set, it, count + 1);
//     //             set.add(s);
//     //         }
//     //     }
//     // }
//     // Map<String, Integer> map = new HashMap<>();
//     public int ladderLength(String beginWord, String endWord, List<String> wordList) {
//         if (wordList == null || wordList.size() == 0) {
//             return 0;
//         }
//         boolean f = false;
//         for (String s: wordList) {
//             if (endWord.equals(s)) {
//                 f = true;
//             }
//         }
//         if (!f) {
//             return 0;
//         }
//         int res = backward(beginWord, endWord, wordList);
//         return res == Integer.MAX_VALUE? 0: res;
//     }

//     public int backward(String beginWord, String endWord, List<String> list) {
//         if (beginWord.equals(endWord)) {
//             return 1;
//         }
//         int res = Integer.MAX_VALUE;
//         for (int i = 0; i < list.size(); i++) {
//             int diff = 0;
//             String s = list.get(i);
//             for (int j = 0; j < beginWord.length(); j++) {
//                 if (beginWord.charAt(j) != s.charAt(j)) {
//                     diff++;
//                 }
//             }
//             if (diff == 1) {
//                 list.remove(i);
//                 int cur = backward(s, endWord, list);
//                 if (cur != Integer.MAX_VALUE) {
//                     res = Math.min(res, cur + 1);
//                 }
//                 list.add(i, s);
//             }
//         }
//         return res;
//     }
// }

//双端深度搜索(其实也是双向bfs，只不过优化的好)
// class Solution {
//     public int ladderLength(String beginWord, String endWord, List<String> wordList) {
//         if (wordList == null || !wordList.contains(endWord)) {
//             return 0;
//         }

//         HashSet<String> wordSet = new HashSet<>();
//         wordSet.addAll(wordList);

//         HashSet<String> headSet = new HashSet<>();
//         headSet.add(beginWord);

//         HashSet<String> tailSet = new HashSet<>();
//         tailSet.add(endWord);

//         return deDfs(wordSet, headSet, tailSet, 2);
//     }

//     /**
//      * 双端 深搜
//      * @param wordSet 还未满足“接龙序列” 的 部分连续的原单词set
//      * @param headSet “符合要求的” 前缀接龙序列
//      * @param tailSet “符合要求的” 后缀接龙序列
//      * @param depth  “符合要求的” 接龙序列 长度
//      * @return 接龙序列长度
//      */
//     private int deDfs(HashSet<String> wordSet, HashSet<String> headSet, HashSet<String> tailSet, int depth) {
//         /*
//             双端深搜 的 精髓：
//                 headSet长度 > tailSet长度，从尾部 反向搜索，
//                 减少搜索次数，加快运行效率
//          */
//         if (headSet.size() > tailSet.size()) {
//             return deDfs(wordSet, tailSet, headSet, depth);
//         }

//         if (headSet.isEmpty()) {    // 若 当前前缀序列 为空，则表示 原单词序列 不满足 “接龙”要求
//             return 0;
//         }

//         wordSet.removeAll(headSet); // 将 前缀(逻辑前缀，反向时 是 后缀) 在 原单词序列 中 删除

//         /*
//             遍历 前缀单词set：
//                 1、改变每一个单词的每一个字母；
//                 2、判断 新生成的单词 在 不包含前缀序列的原单词set 中 是否存在：
//                     1) 若存在：
//                         ① 前缀、后缀 set 中 均存在，则 表示两个序列可以连接，返回 当前depth
//                         ② 仅在 当前前缀set(逻辑前缀) 中存在，则 表示两个序列还不能连接，将新单词 记录在 一个新的set中，以便后续步骤的处理
//                     2) 若不存在，不做任何操作，继续之后的循环
//          */
//         HashSet<String> nextSet = new HashSet<>();  // 用于保存 下一个允许出现的单词(通过 当前前缀单词 生成)
//         for (String headWord : headSet) {
//             char[] wordChars = headWord.toCharArray();
//             for (int i = 0; i < wordChars.length; i++) {
//                 char curChar = wordChars[i];
//                 for (char j = 'a'; j <= 'z'; j++) {
//                     wordChars[i] = j;
//                     String newWord = new String(wordChars);
//                     if (wordSet.contains(newWord)) {
//                         if (tailSet.contains(newWord)) {    // 当前 “新单词” 在两个set都出现了，即：两个set可以拼接在一起了
//                             return depth;
//                         }
//                         nextSet.add(newWord);
//                     }
//                 }
//                 wordChars[i] = curChar; // 将 当前单词 恢复原状
//             }
//         }

//         return deDfs(wordSet, nextSet, tailSet, depth + 1);
//     }
// }

//单向bfs
// class Solution {
//     Map<String, Integer> wordId = new HashMap<>();
//     List<List<Integer>> edge = new ArrayList<>();
//     int nodeNum = 0;
//     public int ladderLength(String beginWord, String endWord, List<String> wordList) {
//         for (String s: wordList) {
//             addEdge(s);
//         }
//         addEdge(beginWord);
//         if (!wordId.containsKey(endWord)) {
//             return 0;
//         }
//         // int[] dis = new int[nodeNum];
//         // Arrays.fill(dis, Integer.MAX_VALUE);
//         // int beginId = wordId.get(beginWord), endId = wordId.get(endWord);
//         // dis[beginId] = 0;

    //         // Queue<Integer> que = new LinkedList<Integer>();
//         // que.offer(beginId);
//         // while (!que.isEmpty()) {
//         //     int x = que.poll();
//         //     if (x == endId) {
//         //         return dis[endId] / 2 + 1;
//         //     }
//         //     for (int it : edge.get(x)) {
//         //         if (dis[it] == Integer.MAX_VALUE) {
//         //             dis[it] = dis[x] + 1;
//         //             que.offer(it);
//         //         }
//         //     }
//         // }
//         int beginId =  wordId.get(beginWord);
//         int endId = wordId.get(endWord);
//         int count = 1;
//         Queue<Integer> queue = new LinkedList<>();
//         queue.add(beginId);
//         Set<Integer> f = new HashSet<>();
//         while (!queue.isEmpty()) {
//             int len = queue.size();
//             for (int i = 0; i < len; i++) {
//                 int cur = queue.poll();
//                 if (cur == endId) {
//                     return count / 2 + 1;
//                 }
//                 for (int next: edge.get(cur)) {
//                     if (f.contains(next)) {
//                         continue;
//                     }
//                     queue.add(next);
//                     f.add(next);
//                 }
//             }
//             count++;
//         }
//         return 0;
//     }
//     public void addEdge(String word) {
//         addWord(word);
//         int id1 = wordId.get(word);
//         char[] array = word.toCharArray();
//         int len = array.length;
//         for (int i = 0; i < len; i++) {
//             char tmp = array[i];
//             array[i] = '*';
//             String newWord = new String(array);
//             addWord(newWord);
//             int id2 = wordId.get(newWord);
//             edge.get(id1).add(id2);
//             edge.get(id2).add(id1);
//             array[i] = tmp;
//         }
//     }
//     public void addWord(String word) {
//         if (!wordId.containsKey(word)) {
//             wordId.put(word, nodeNum++);
//             edge.add(new ArrayList<Integer>());
//         }
//     }
//  }
// 双向bfs
    class Solution {

        Map<String, Integer> wordId = new HashMap<String, Integer>();
        List<List<Integer>> edge = new ArrayList<List<Integer>>();
        int nodeNum = 0;

        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            for (String word: wordList) {
                addEdge(word);
            }
            addEdge(beginWord);
            if (!wordId.containsKey(endWord)) {
                return 0;
            }

            int[] disBegin = new int[nodeNum];
            Arrays.fill(disBegin, Integer.MAX_VALUE);
            int beginId = wordId.get(beginWord);
            disBegin[beginId] = 0;
            Queue<Integer> queBegin = new LinkedList<>();
            queBegin.offer(beginId);

            int[] disEnd = new int[nodeNum];
            Arrays.fill(disEnd, Integer.MAX_VALUE);
            int endId = wordId.get(endWord);
            disEnd[endId] = 0;
            Queue<Integer> queEnd = new LinkedList<>();
            queEnd.offer(endId);

            while (!queBegin.isEmpty() && !queEnd.isEmpty()) {
                int queBeginSize = queBegin.size();
                for (int i = 0; i < queBeginSize; i++) {
                    int nodeBegin = queBegin.poll();
                    if (disEnd[nodeBegin] != Integer.MAX_VALUE) {
                        return (disBegin[nodeBegin] + disEnd[nodeBegin]) / 2 + 1;
                    }
                    for (int it : edge.get(nodeBegin)) {
                        if (disBegin[it] == Integer.MAX_VALUE) {
                            disBegin[it] = disBegin[nodeBegin] + 1;
                            queBegin.offer(it);
                        }
                    }
                }
                int queEndSize = queEnd.size();
                for (int i = 0; i < queEndSize; ++i) {
                    int nodeEnd = queEnd.poll();
                    if (disBegin[nodeEnd] != Integer.MAX_VALUE) {
                        return (disBegin[nodeEnd] + disEnd[nodeEnd]) / 2 + 1;
                    }
                    for (int it : edge.get(nodeEnd)) {
                        if (disEnd[it] == Integer.MAX_VALUE) {
                            disEnd[it] = disEnd[nodeEnd] + 1;
                            queEnd.offer(it);
                        }
                    }
                }
            }
            return 0;
        }

        public void addEdge(String word) {
            addWord(word);
            int id1 = wordId.get(word);
            char[] array = word.toCharArray();
            int length = array.length;
            for (int i = 0; i < length; ++i) {
                char tmp = array[i];
                array[i] = '*';
                String newWord = new String(array);
                addWord(newWord);
                int id2 = wordId.get(newWord);
                edge.get(id1).add(id2);
                edge.get(id2).add(id1);
                array[i] = tmp;
            }
        }

        public void addWord(String word) {
            if (!wordId.containsKey(word)) {
                wordId.put(word, nodeNum++);
                edge.add(new ArrayList<Integer>());
            }
        }
    }
}
