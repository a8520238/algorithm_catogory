package com.algorithm.单调栈;

import java.util.*;

//解题思路单调栈
class 去除重复字母 {
    //优化On可以采用Set空间换时间，也可以采用有序二分
    public String removeDuplicateLetters(String s) {
        Deque<Character> deque = new LinkedList<>();
        Set<Character> set = new HashSet<>();
        char[] str = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for (Character c: str) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (int i = 0; i < str.length; i++) {
            //set是判断栈中是否有当前值，如果有，越过，如果没有，一定会加入（加入之前进行出栈操作）
            if (set.contains(str[i])) {
                map.put(str[i], map.get(str[i]) - 1);
                continue;
            }
            //如果当前字母的字典序小于栈中字母，且栈中字母还有没遍历到的，弹出
            while (!deque.isEmpty() && str[i] < deque.peekLast() && map.get(deque.peekLast()) > 0) {
                set.remove(deque.peekLast());
                deque.pollLast();
            }
            deque.addLast(str[i]);
            set.add(str[i]);
            map.put(str[i], map.get(str[i]) - 1);
        }
        StringBuilder builder = new StringBuilder();
        while (!deque.isEmpty()) {
            char c = deque.pollFirst();
            builder.append(c);
        }
        return builder.toString();
    }
}
//使用数组做map
//class Solution {
//    //利用最小栈的性质，弹出的时候如果栈顶大于当前值，且后置位还有重复值，方可弹出。否则就算前面的值比当前值大，也要不动，比如bcab，最后的结果为bca，而不是cab
//    public String removeDuplicateLetters(String s) {
//        int[] map = new int[26];
//        char[] str = s.toCharArray();
//        for (char c: str) {
//            map[c - 'a']++;
//        }
//        Deque<Character> stack = new LinkedList<>();
//        Set<Character> set = new HashSet<>();
//        for (int i = 0; i < str.length; i++) {
//            if (set.contains(str[i])) {
//                map[str[i] - 'a']--;
//                continue;
//            }
//            while (!stack.isEmpty() && str[i] < stack.peek() && map[stack.peek() - 'a'] > 0) {
//                char c = stack.pop();
//                set.remove(c);
//            }
//            stack.push(str[i]);
//            map[str[i] - 'a']--;
//            set.add(str[i]);
//        }
//        StringBuilder builder = new StringBuilder();
//        while (!stack.isEmpty()) {
//            builder.append(stack.pollLast());
//        }
//        return builder.toString();
//    }
//}