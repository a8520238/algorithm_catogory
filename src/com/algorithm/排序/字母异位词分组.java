package com.algorithm.排序;

import java.util.*;

/*
* 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。

示例:

输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
输出:
[
  ["ate","eat","tea"],
  ["nat","tan"],
  ["bat"]
]
说明：

所有输入均为小写字母。
不考虑答案输出的顺序。
* */

//本题思路，构建排序后的字符串为键值
public class 字母异位词分组 {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            String cur = strs[i];
            char[] s = cur.toCharArray();
            Arrays.sort(s);
            String newCur = String.valueOf(s);
            if (map.containsKey(newCur)) {
                map.get(newCur).add(cur);
            } else {
                List<String> list = new ArrayList<>();
                list.add(cur);
                map.put(newCur, list);
                res.add(list);
            }
        }
        return res;
    }
}
