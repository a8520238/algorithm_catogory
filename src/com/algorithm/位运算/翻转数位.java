package com.algorithm.位运算;

/*
* 给定一个32位整数 num，你可以将一个数位从0变为1。请编写一个程序，找出你能够获得的最长的一串1的长度。

示例 1：

输入: num = 1775(110111011112)
输出: 8
示例 2：

输入: num = 7(01112)
输出: 4
* */

public class 翻转数位 {
    public int reverseBits(int num) {
        int max = 0;
        int count = 0;
        int changeCount = 0;
        for (int i = 0; i < 32; i++) {
            int cur = num & 1;
            if (cur == 1) {
                count++;
                changeCount++;
            } else {
                changeCount = count + 1;
                count = 0;
            }
            max = Math.max(max, changeCount);
            num >>= 1;
        }
        return max;
    }
}
