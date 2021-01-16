package com.algorithm.位运算;

/*
 * 在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。
 * */
public class 数组中数字出现的次数2 {
    //状态机解法
    // public int singleNumber(int[] nums) {
    //     int two = 0, one = 0;
    //     for (int n: nums) {
    //         // one = one ^ n & ~two;
    //         // two = two ^ n & ~one;
    //         one = (n ^ one) & ~two;
    //         two = (n ^ two) & ~one;
    //     }
    //     return one;
    // }
    //遍历解法
    public int singleNumber(int[] nums) {
        int[] count = new int[32];
        for (int n : nums) {
            for (int j = 0; j < 32; j++) {
                count[j] += n & 1;
                n >>>= 1;
            }
        }
        int res = 0, mod = 3;
        for (int i = 0; i < 32; i++) {
            res <<= 1;
            res |= count[31 - i] % mod;
        }
        return res;
    }
}
