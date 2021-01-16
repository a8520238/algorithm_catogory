package com.algorithm.贪心;

public class 分割数组为连续子序列 {
    //统计数字 谭新思想
     public boolean isPossible(int[] nums) {
         int len = nums.length;
         if (len < 3) {
             return false;
         }
         int min = nums[0];
         int max = nums[len - 1];
         int[] count = new int[max - min + 1];
         for (int i = 0; i < nums.length; i++) {
             count[nums[i] - min]++;
         }
         int begin = 0, end = 0;
         int lenCount = count.length;
         while (begin < lenCount) {
             //后面的次数如果小于当前的，那么必不可能处于同一个子序列
             while (end < lenCount - 1 && count[end] <= count[end + 1]) {
                 end++;
             }
             //当前子序列小于3，false
             if (end - begin + 1 < 3) {
                 return false;
             }
             int tmp = begin;
             //每个选中的count减去1
             for (int i = begin; i <= end; i++) {
                 count[i]--;
                 if (count[i] == 0) {
                     tmp++;
                 }
             }
             //防止中间有断层，除0
             begin = tmp;
             while (begin < lenCount && count[begin] == 0) {
                 begin++;
             }
             end =  begin;
         }
         return true;
     }
    //堆
    //原始思路：当 xx 在数组中时，如果存在多个子序列以 x-1x−1 结尾，应该将 xx 加入其中的哪一个子序列？由于题目要求每个子序列的长度至少为 33，显然应该让最短的子序列尽可能长，因此应该将 xx 加入其中最短的子序列。
    // public boolean isPossible(int[] nums) {
    //     Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();
    //     //如果哈希表中存在以 x-1x−1 结尾的子序列，则取出以 x-1x−1 结尾的最小的子序列长度，将子序列长度加 11 之后作为以 xx 结尾的子序列长度。此时，以 x-1x−1 结尾的子序列减少了一个，以 xx 结尾的子序列增加了一个。
    //     //如果哈希表中不存在以 x-1x−1 结尾的子序列，则新建一个长度为 11 的以 xx 结尾的子序列。
    //     for (int x: nums) {
    //         if (!map.containsKey(x)) {
    //             map.put(x, new PriorityQueue<Integer>());
    //         }
    //         if (map.containsKey(x - 1)) {
    //             int prevLength = map.get(x - 1).poll();
    //             if (map.get(x - 1).isEmpty()) {
    //                 map.remove(x - 1);
    //             }
    //             map.get(x).offer(prevLength + 1);
    //         } else {
    //             map.get(x).offer(1);
    //         }
    //     }
    //     Set<Map.Entry<Integer, PriorityQueue<Integer>>> entrySet = map.entrySet();
    //     for (Map.Entry<Integer, PriorityQueue<Integer>> entry: entrySet) {
    //         PriorityQueue<Integer> queue = entry.getValue();
    //         if (queue.peek() < 3) {
    //             return false;
    //         }
    //     }
    //     return true;
    // }
    //贪心检测能否成为子序列
//    public boolean isPossible(int[] nums) {
//        //用一个哈希表统计每个元素出现的次数
//        Map<Integer, Integer> countNum = new HashMap<Integer, Integer>();
//        for (int num : nums) {
//            countNum.put(num, countNum.getOrDefault(num, 0) + 1);
//        }
//        //定义一个哈希表记录最长的子序列
//        Map<Integer, Integer> tail = new HashMap<Integer, Integer>();
//        for (int num : nums) {
//            int count = countNum.getOrDefault(num, 0);
//            if (count <= 0) {//当前元素已经用完，直接跳过
//                continue;
//            } else if (tail.getOrDefault(num - 1, 0) > 0) {//前面还有数字，可以构成以num结尾的子序列
//                countNum.put(num, count - 1);
//                tail.put(num - 1, tail.get(num - 1) - 1);//覆盖当前最长的子序列
//                tail.put(num, tail.getOrDefault(num, 0) + 1);//当前以num结尾的子序列+1
//            } else if (countNum.getOrDefault(num + 1, 0) > 0 && countNum.getOrDefault(num + 2, 0) > 0) {//前面无数字构成子序列后，判断能不能跟后面的构成子序列
//                countNum.put(num, count - 1);
//                countNum.put(num + 1, countNum.get(num + 1) - 1);
//                countNum.put(num + 2, countNum.get(num + 2) - 1);
//                tail.put(num + 2, tail.getOrDefault(num + 2, 0) + 1);//当前以num+2结尾的子序列+1
//            } else
//                return false;//前后不能构成子序列直接返回false
//        }
//        return true;
//    }
}
