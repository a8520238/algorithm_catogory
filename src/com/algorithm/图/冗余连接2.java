package com.algorithm.图;
/*
* 在本问题中，有根树指满足以下条件的有向图。该树只有一个根节点，所有其他节点都是该根节点的后继。每一个节点只有一个父节点，除了根节点没有父节点。

输入一个有向图，该图由一个有着N个节点 (节点值不重复1, 2, ..., N) 的树及一条附加的边构成。附加的边的两个顶点包含在1到N中间，这条附加的边不属于树中已存在的边。

结果图是一个以边组成的二维数组。 每一个边 的元素是一对 [u, v]，用以表示有向图中连接顶点 u 和顶点 v 的边，其中 u 是 v 的一个父节点。

返回一条能删除的边，使得剩下的图是有N个节点的有根树。若有多个答案，返回最后出现在给定二维数组的答案。

* 输入: [[1,2], [1,3], [2,3]]
输出: [2,3]
解释: 给定的有向图如下:
  1
 / \
v   v
2-->3
示例 2:

输入: [[1,2], [2,3], [3,4], [4,1], [1,5]]
输出: [4,1]
解释: 给定的有向图如下:
5 <- 1 -> 2
     ^    |
     |    v
     4 <- 3

*/
public class 冗余连接2 {
    private int[] parent;

    private int find(int a) {
        while (a != parent[a]) {
            parent[a] = parent[parent[a]];
            a = parent[a];
        }
        return a;
    }
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int[] backedge = new int[2];
        int[] pending = new int[2];
        parent = new int[edges.length + 1];
        //要么有入度为2的点（非单项环）如[[2,1],[3,1],[4,2],[1,4]]，要么有单项环。
        //先把入度为2的边记录
        for (int[] edge: edges) {
            if (parent[edge[1]] == 0) {
                parent[edge[1]] = edge[0];
            } else {
                pending = new int[] {edge[0], edge[1]}; // 最后出现的边（可能是入度为2）
                //记录可能存在环边的边 如果不理解看样例[[2,1],[3,1],[4,2],[1,4]]
                backedge = new int[] {parent[edge[1]], edge[1]};
                //把最后出现的边的入节点删除
                edge[1] = 0;
            }
        }

        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
        //再找环
        for (int[] edge: edges) {
            if (edge[1] == 0) {
                continue;
            }

            if (find(edge[0]) == edge[1]) {
                //如果没有入度为2的点，此时backedge还是初始化的0，翻回这条最后的edge边
                //如果backage这条边不为0，且找到了环，说明删了pending也会有环，所以删backedge
                return backedge[0] == 0? edge: backedge;
            }
            parent[edge[1]] = edge[0];
        }
        //删了pending,无环，删pending
        return pending;
    }
}
