package com.algorithm.位运算;
/*
* 一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。
* 请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。
* */
public class 数组中数字出现的次数 {
    class Solution {
        /*
        先对所有数字进行一次异或，得到两个出现一次的数字的异或值。

        在异或结果中找到任意为 11 的位。

        根据这一位对所有的数字进行分组。

        在每个组内进行异或操作，得到两个数字。

         */
        public int[] singleNumbers(int[] nums) {
            int ret = 0;
            for (int n : nums) {
                ret ^= n;
            }
            int div = 1;
            while ((div & ret) == 0) {
                div <<= 1;
            }
            int a = 0, b = 0;
            for (int n: nums) {
                if ((div & n) != 0) {
                    a ^= n;
                } else {
                    b ^= n;
                }
            }
            return new int[] {a, b};
        }
    }
}
