package com.algorithm.图;

/*
* 给你一个变量对数组 equations 和一个实数值数组 values 作为已知条件，其中 equations[i] = [Ai, Bi] 和 values[i] 共同表示等式 Ai / Bi = values[i] 。每个 Ai 或 Bi 是一个表示单个变量的字符串。

另有一些以数组 queries 表示的问题，其中 queries[j] = [Cj, Dj] 表示第 j 个问题，请你根据已知条件找出 Cj / Dj = ? 的结果作为答案。

返回 所有问题的答案 。如果存在某个无法确定的答案，则用 -1.0 替代这个答案。

 

注意：输入总是有效的。你可以假设除法运算中不会出现除数为 0 的情况，且不存在任何矛盾的结果。

 

示例 1：

输入：equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
输出：[6.00000,0.50000,-1.00000,1.00000,-1.00000]
解释：
条件：a / b = 2.0, b / c = 3.0
问题：a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
结果：[6.0, 0.5, -1.0, 1.0, -1.0 ]
示例 2：

输入：equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
输出：[3.75000,0.40000,5.00000,0.20000]
示例 3：

输入：equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
输出：[0.50000,2.00000,-1.00000,-1.00000]
 

提示：

1 <= equations.length <= 20
equations[i].length == 2
1 <= Ai.length, Bi.length <= 5
values.length == equations.length
0.0 < values[i] <= 20.0
1 <= queries.length <= 20
queries[i].length == 2
1 <= Cj.length, Dj.length <= 5
Ai, Bi, Cj, Dj 由小写英文字母与数字组成
* */


import java.util.*;

/*
广度优先搜索算法
时间复杂度：O(ML+Q⋅(L+M))，其中 M 为边的数量，Q为询问的数量，L 为字符串的平均长度。构建图时，需要处理 MM 条边，每条边都涉及到 O(L)O(L) 的字符串比较；处理查询时，每次查询首先要进行一次 O(L)O(L) 的比较，然后至多遍历 O(M)O(M) 条边。
*/
 class 除法求值 {
     public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
         int nvars = 0;
         Map<String, Integer> variables = new HashMap<>();
         int n = equations.size();
         for (int i = 0; i < n; i++) {
             if (!variables.containsKey(equations.get(i).get(0))) {
                 variables.put(equations.get(i).get(0), nvars++);
             }
             if (!variables.containsKey(equations.get(i).get(1))) {
                 variables.put(equations.get(i).get(1), nvars++);
             }
         }
         //对于每个点，存储其直接连接到的所有点及对应的权值
         List<Pair>[] edges = new List[nvars];
         for (int i = 0; i < nvars; i++) {
             edges[i] = new ArrayList<Pair>();
         }
         for (int i = 0; i < n; i++) {
             int va = variables.get(equations.get(i).get(0));
             int vb = variables.get(equations.get(i).get(1));
             edges[va].add(new Pair(vb, values[i]));
             edges[vb].add(new Pair(va, 1.0 / values[i]));
         }
         int queriesCount = queries.size();
         double[] ret = new double[queriesCount];
         for (int i = 0; i < queriesCount; i++) {
             List<String> query = queries.get(i);
             double result = -1.0;
             if (variables.containsKey(query.get(0)) && variables.containsKey(query.get(1))) {
                 int ia = variables.get(query.get(0));
                 int ib = variables.get(query.get(1));
                 if (ia == ib) {
                     result = 1.0;
                 } else {
                     Queue<Integer> points = new LinkedList<Integer>();
                     points.add(ia);
                     double[] ratios = new double[nvars];
                     Arrays.fill(ratios, -1.0);
                     ratios[ia] = 1.0;
                     while (!points.isEmpty() && ratios[ib] < 0) {
                         int x = points.poll();
                         for (Pair pair: edges[x]) {
                             int y = pair.index;
                             double val = pair.value;
                             if (ratios[y] < 0) {
                                 ratios[y] = ratios[x] * val;
                                 points.add(y);
                             }
                         }
                     }
                     result = ratios[ib];
                 }
             }
             ret[i] = result;
         }
         return ret;
     }

     class Pair {
         int index;
         double value;

         Pair(int index, double value) {
             this.index = index;
             this.value = value;
         }
     }
 }

/*
Floyd 算法
时间复杂度：O(ML+N^3+QL)O(ML+N
3
 +QL)。构建图需要 O(ML)O(ML) 的时间；\text{Floyd}Floyd 算法需要 O(N^3)O(N
3
 ) 的时间；处理查询时，单次查询只需要 O(L)O(L) 的字符串比较以及常数时间的额外操作。

*/
// class Solution {
//     public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
//         int nvars = 0;
//         Map<String, Integer> variables = new HashMap<>();
//         int n = equations.size();
//         for (int i = 0; i < n; i++) {
//             if (!variables.containsKey(equations.get(i).get(0))) {
//                 variables.put(equations.get(i).get(0), nvars++);
//             }
//             if (!variables.containsKey(equations.get(i).get(1))) {
//                 variables.put(equations.get(i).get(1), nvars++);
//             }
//         }
//         double[][] graph = new double[nvars][nvars];
//         for (int i = 0; i < nvars; i++) {
//             Arrays.fill(graph[i], -1);
//         }
//         for (int i = 0; i < n; i++) {
//             int va = variables.get(equations.get(i).get(0));
//             int vb = variables.get(equations.get(i).get(1));
//             graph[va][vb] = values[i];
//             graph[vb][va] = 1.0 / values[i];
//         }
//         for (int k = 0; k < nvars; k++) {
//             for (int i = 0; i < nvars; i++) {
//                 for (int j = 0; j < nvars; j++) {
//                     if (graph[i][k] > 0 && graph[k][j] > 0) {
//                         graph[i][j] = graph[i][k] * graph[k][j];
//                     }
//                 }
//             }
//         }
//         int queriesCount = queries.size();
//         double[] ret = new double[queriesCount];
//         for (int i = 0; i < queriesCount; i++) {
//             List<String> query = queries.get(i);
//             double result = -1.0;
//             if (variables.containsKey(query.get(0)) && variables.containsKey(query.get(1))) {
//                 int ia = variables.get(query.get(0));
//                 int ib = variables.get(query.get(1));
//                 if (graph[ia][ib] > 0) {
//                     result = graph[ia][ib];
//                 }
//             }
//             ret[i] = result;
//         }
//         return ret;
//     }
// }


//并查集 秒啊
/*
我们还可以考虑以并查集的方式存储节点之间的关系。设节点 xx 的值（即对应变量的取值）为 v[x]v[x]。对于任意两点 x, yx,y，假设它们在并查集中具有共同的父亲 ff，且 v[x]/v[f] = a, v[y]/v[f]=bv[x]/v[f]=a,v[y]/v[f]=b，则 v[x]/v[y]=a/bv[x]/v[y]=a/b。

在观察到这一点后，就不难利用并查集的思想解决此题。对于每个节点 xx 而言，除了维护其父亲 f[x]f[x] 之外，还要维护其权值 ww，其中「权值」定义为节点 xx 的取值与父亲 f[x]f[x] 的取值之间的比值。

时间复杂度：O(ML+N+M\log N+Q\cdot(L+\log N))O(ML+N+MlogN+Q⋅(L+logN))。构建图需要 O(ML)O(ML) 的时间；初始化并查集需要 O(N)O(N) 的初始化时间；构建并查集的单次操作复杂度为 O(\log N)O(logN)，共需 O(M\log N)O(MlogN) 的时间；每个查询需要 O(L)O(L) 的字符串比较以及 O(\log N)O(logN) 的查询。

*/
//class Solution {
////    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
////        int nvars = 0;
////        Map<String, Integer> variables = new HashMap<>();
////        int n = equations.size();
////        for (int i = 0; i < n; i++) {
////            if (!variables.containsKey(equations.get(i).get(0))) {
////                variables.put(equations.get(i).get(0), nvars++);
////            }
////            if (!variables.containsKey(equations.get(i).get(1))) {
////                variables.put(equations.get(i).get(1), nvars++);
////            }
////        }
////        int[] f = new int[nvars];
////        double[] w = new double[nvars];
////        Arrays.fill(w, 1.0);
////        for (int i = 0; i < nvars; i++) {
////            f[i] = i;
////        }
////        for (int i = 0; i < n; i++) {
////            int va = variables.get(equations.get(i).get(0));
////            int vb = variables.get(equations.get(i).get(1));
////            merge(f, w, va, vb, values[i]);
////        }
////        int queriesCount = queries.size();
////        double[] ret = new double[queriesCount];
////        for (int i = 0; i < queriesCount; i++) {
////            List<String> query = queries.get(i);
////            double result = -1.0;
////            if (variables.containsKey(query.get(0)) && variables.containsKey(query.get(1))) {
////                int ia = variables.get(query.get(0));
////                int ib = variables.get(query.get(1));
////                int fa = findf(f, w, ia), fb = findf(f, w, ib);
////                if (fa == fb) {
////                    result = w[ia] / w[ib];
////                }
////            }
////            ret[i] = result;
////        }
////        return ret;
////    }
////
////    public void merge(int[] f, double[] w, int x, int y, double val) {
////        int fx = findf(f, w, x);
////        int fy = findf(f, w, y);
////        f[fx] = fy;
////        w[fx] = val * w[y] / w[x];
////    }
////
////    public int findf(int[] f, double[] w, int x) {
////        if (f[x] != x) {
////            int father = findf(f, w, f[x]);
////            w[x] = w[x] * w[f[x]];
////            f[x] = father;
////        }
////        return f[x];
////    }
////}