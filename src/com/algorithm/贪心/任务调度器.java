package com.algorithm.贪心;

import java.util.Arrays;

/*
* 给你一个用字符数组 tasks 表示的 CPU 需要执行的任务列表。其中每个字母表示一种不同种类的任务。任务可以以任意顺序执行，并且每个任务都可以在 1 个单位时间内执行完。在任何一个单位时间，CPU 可以完成一个任务，或者处于待命状态。

然而，两个 相同种类 的任务之间必须有长度为整数 n 的冷却时间，因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。

你需要计算完成所有任务所需要的 最短时间 。

 

示例 1：

输入：tasks = ["A","A","A","B","B","B"], n = 2
输出：8
解释：A -> B -> (待命) -> A -> B -> (待命) -> A -> B
     在本示例中，两个相同类型任务之间必须间隔长度为 n = 2 的冷却时间，而执行一个任务只需要一个单位时间，所以中间出现了（待命）状态。
*/

public class 任务调度器 {
    public int leastInterval(char[] tasks, int n) {
        if (tasks.length <= 1 || n < 1) {
            return tasks.length;
        }
        int[] counts = new int[26];
        for (int i = 0; i < tasks.length; i++) {
            counts[tasks[i] - 'A']++;
        }
        Arrays.sort(counts);
        //如果想对来说n比较大，那么最后返回一定形如AXX-AXX-AXX-AB
        int maxCount = counts[25];
        //maxCount- 1为空格数，* n + 1 再加上1，为当前总数
        int res = (maxCount - 1) * (n + 1) + 1;
        int i = 24;
        //如果相等了，就如同在后面加B
        while (i >= 0 && counts[i] == maxCount) {
            res++;
            i--;
        }
        //如果空位都插满之后还有任务，那就随便在这些间隔里面插入就可以，因为间隔长度肯定会大于n，在这种情况下就是任务的总数是最小所需时间
        return Math.max(res, tasks.length);
    }
    //暴力解法，先查排序，再插空
//    public int leastInterval(char[] tasks, int n) {
//        if (n == 0) {
//            return tasks.length;
//        }
//        Integer[] count = new Integer[26];
//        Arrays.fill(count, 0);
//        int total = 0;
//        for (int i = 0; i < tasks.length; i++) {
//            if (count[tasks[i] - 'A'] == 0) {
//                total++;
//            }
//            count[tasks[i] - 'A']++;
//        }
//        int res = 0;
//        while (total > 0) {
//            Arrays.sort(count, Collections.reverseOrder());
//            if (count[0] > 1) {
//                res += n + 1;
//                int tmp = total;
//                for (int i = 0; i < Math.min(total, n + 1); i++) {
//                    if (--count[i] == 0) {
//                        tmp--;
//                    }
//                }
//                total = tmp;
//                // if (total <= n + 1) {
//                //     res += n + 1;
//                //     int tmp = total;
//                //     for (int i = 0; i < total; i++) {
//                //         if (--count[i] == 0) {
//                //             tmp--;
//                //         }
//                //     }
//                //     total = tmp;
//                // } else {
//                //     res += n + 1;
//                //     for (int i = 0; i < n + 1; i++) {
//                //         if (--count[i] == 0) {
//                //             total--;
//                //         }
//                //     }
//                // }
//            } else {
//                res += total;
//                break;
//            }
//        }
//        return res;
//    }

    /*
    * 因此我们可以用二元组 (\textit{nextValid}_i, \textit{rest}_i)(nextValid
i
​
 ,rest
i
​
 ) 表示第 ii 个任务，其中 \textit{nextValid}_inextValid
i
​
  表示其因冷却限制，最早可以执行的时间；\textit{rest}_irest
i
​
  表示其剩余执行次数。初始时，所有的 \textit{nextValid}_inextValid
i
​
  均为 11，而 \textit{rest}_irest
i
​
  即为任务 ii 在数组 \textit{tasks}tasks 中出现的次数。

我们用 \textit{time}time 记录当前的时间。根据我们的策略，我们需要选择不在冷却中并且剩余执行次数最多的那个任务，也就是说，我们需要找到满足 \textit{nextValid}_i \leq \textit{time}nextValid
i
​
 ≤time 的并且 \textit{rest}_irest
i
​
  最大的索引 ii。因此我们只需要遍历所有的二元组，即可找到 ii。在这之后，我们将 (\textit{nextValid}_i, \textit{rest}_i)(nextValid
i
​
 ,rest
i
​
 ) 更新为 (\textit{time}+n+1, \textit{rest}_i-1)(time+n+1,rest
i
​
 −1)，记录任务 ii 下一次冷却结束的时间以及剩余执行次数。如果更新后的 \textit{rest}_i=0rest
i
​
 =0，那么任务 ii 全部做完，我们在遍历二元组时也就可以忽略它了。

而对于 \textit{time}time 的更新，我们可以选择将其不断增加 11，模拟每一个时间片。但这会导致我们在 CPU 处于待命状态时，对二元组进行不必要的遍历。为了减少时间复杂度，我们可以在每一次遍历之前，将 \textit{time}time 更新为所有 \textit{nextValid}_inextValid
i
​
  中的最小值，直接「跳过」待命状态，保证每一次对二元组的遍历都是有效的。需要注意的是，只有当这个最小值大于 \textit{time}time 时，才需要这样快速更新。
    * */

//    public int leastInterval(char[] tasks, int n) {
//        Map<Character, Integer> freq = new HashMap<Character, Integer>();
//        for (char ch : tasks) {
//            freq.put(ch, freq.getOrDefault(ch, 0) + 1);
//        }
//
//        // 任务总数
//        int m = freq.size();
//        List<Integer> nextValid = new ArrayList<Integer>();
//        List<Integer> rest = new ArrayList<Integer>();
//        Set<Map.Entry<Character, Integer>> entrySet = freq.entrySet();
//        for (Map.Entry<Character, Integer> entry : entrySet) {
//            int value = entry.getValue();
//            nextValid.add(1);
//            rest.add(value);
//        }
//
//        int time = 0;
//        for (int i = 0; i < tasks.length; ++i) {
//            ++time;
//            int minNextValid = Integer.MAX_VALUE;
//            for (int j = 0; j < m; ++j) {
//                if (rest.get(j) != 0) {
//                    minNextValid = Math.min(minNextValid, nextValid.get(j));
//                }
//            }
//            time = Math.max(time, minNextValid);
//            int best = -1;
//            for (int j = 0; j < m; ++j) {
//                if (rest.get(j) != 0 && nextValid.get(j) <= time) {
//                    if (best == -1 || rest.get(j) > rest.get(best)) {
//                        best = j;
//                    }
//                }
//            }
//            nextValid.set(best, time + n + 1);
//            rest.set(best, rest.get(best) - 1);
//        }
//
//        return time;
//    }

}
