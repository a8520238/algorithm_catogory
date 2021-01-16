package com.algorithm.图;

/*
* 给你一个字符串 s，以及该字符串中的一些「索引对」数组 pairs，其中 pairs[i] = [a, b] 表示字符串中的两个索引（编号从 0 开始）。

你可以 任意多次交换 在 pairs 中任意一对索引处的字符。

返回在经过若干次交换后，s 可以变成的按字典序最小的字符串。

 

示例 1:

输入：s = "dcab", pairs = [[0,3],[1,2]]
输出："bacd"
解释：
交换 s[0] 和 s[3], s = "bcad"
交换 s[1] 和 s[2], s = "bacd"
示例 2：

输入：s = "dcab", pairs = [[0,3],[1,2],[0,2]]
输出："abcd"
解释：
交换 s[0] 和 s[3], s = "bcad"
交换 s[0] 和 s[2], s = "acbd"
交换 s[1] 和 s[2], s = "abcd"
示例 3：

输入：s = "cba", pairs = [[0,1],[1,2]]
输出："abc"
解释：
交换 s[0] 和 s[1], s = "bca"
交换 s[1] 和 s[2], s = "bac"
交换 s[0] 和 s[1], s = "abc"
 

提示：

1 <= s.length <= 10^5
0 <= pairs.length <= 10^5
0 <= pairs[i][0], pairs[i][1] < s.length
s 中只含有小写英文字母
* */


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

class 交换字符串中的元素_并查集 {
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        if (pairs.size() == 0) {
            return s;
        }
        // 1 将任意交换的节点对输入并查集
        int len = s.length();
        UnionFind unionFind = new UnionFind(len);
        for (List<Integer> pair: pairs) {
            int index1 = pair.get(0);
            int index2 = pair.get(1);
            unionFind.union(index1, index2);
        }
        // 2 构建映射关系
        char[] charArray = s.toCharArray();
        // key：连通分量的代表元，value：同一个连通分量的字符集合（保存在一个优先队列）
        Map<Integer, PriorityQueue<Character>> hashMap = new HashMap<>(len);
        for (int i = 0; i < len; i++) {
            int root = unionFind.find(i);
            // if (hashMap.containsKey(root)) {
            //     hashMap.get(root).offer(charArray[i]);
            // } else {
            //     PriorityQueue<Character> minHeap = new PriorityQueue<>();
            //     minHeap.offer(charArray[i]);
            //     hashMap.put(root, minHeap);
            // }
            // 上面六行代码等价于下面一行代码，JDK 1.8 以及以后支持下面的写法
            hashMap.computeIfAbsent(root, key -> new PriorityQueue<>()).offer(charArray[i]);
        }
        // 3 重组字符串
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < len; i++) {
            int root = unionFind.find(i);
            builder.append(hashMap.get(root).poll());
        }
        return builder.toString();
    }
    private class UnionFind {
        private int[] parent;

        public UnionFind(int n) {
            this.parent = new int[n];
            for (int i = 0; i < n; i++) {
                this.parent[i] = i;
            }
        }
        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX == rootY) {
                return;
            } else {
                parent[rootX] = rootY;
            }
        }
        public int find(int x) {
            while (x != parent[x]) {
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
        }
    }
}

/*
class Solution {
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        // 先把所有的点和可交换关系抽象成一个图
        int n = s.length();
        List<Integer>[] adjacentList = new List[n];
        for (int i = 0; i < n; i++) {
            adjacentList[i] = new ArrayList<>();
        }
        for (List<Integer> pair: pairs) {
            int a = pair.get(0);
            int b = pair.get(1);
            adjacentList[a].add(b);
            adjacentList[b].add(a);
        }
        // 多次DFS
        boolean[] visited = new boolean[n];
        char[] arr = new char[n];
        for (int i = 0; i < n; i++) {
            if (! visited[i]) {
                List<Integer> component = new ArrayList<>();
                dfs(adjacentList, i, visited, component);
                sortComponent(arr, s, component);
            }
        }
        return new String(arr);
    }

    public void dfs(List<Integer>[] graph, int start, boolean[] visited, List<Integer> res) {
        visited[start] = true;
        res.add(start);
        for (int node: graph[start]) {
            if (! visited[node]) {
                dfs(graph, node, visited, res);
            }
        }
    }

    public void sortComponent(char[] arr, String s, List<Integer> component) {
        // 通过计数排序将连通分量重填
        component.sort(Integer::compareTo);
        char[] counter = new char[26];
        for (int index: component) {
            char ch = s.charAt(index);
            counter[ch - 'a']++;
        }
        int curChar = 0;
        for (int i: component) {
            while (counter[curChar] == 0) {
                curChar++;
            }
            arr[i] = (char) (curChar + 'a');
            counter[curChar]--;
        }
    }
}
*/