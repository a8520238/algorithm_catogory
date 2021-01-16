package com.algorithm.技巧和数学;

import java.util.ArrayList;
import java.util.List;

/*
* 给定一个包含n个数的序列，找出0-n中没有出现的那个数
* */
public class 寻找消失元素 {

    //如何找这个落单的数字呢，只要把所有的元素和索引做异或运算，成对儿的数字都会消为 0，只有这个落单的元素会剩下，也就达到了我们的目的。
    int missingNumber(int[] nums) {
        int n = nums.length;
        int res = 0;
        // 先和新补的索引异或一下
        res ^= n;
        // 和其他的元素、索引做异或
        for (int i = 0; i < n; i++)
            res ^= i ^ nums[i];

        return res;
    }

    //等差数列求和
//    int missingNumber(int[] nums) {
//        int n = nums.length;
//        // 公式：(首项 + 末项) * 项数 / 2
//        int expect = (0 + n) * (n + 1) / 2;
//
//        int sum = 0;
//        for (int x : nums)
//            sum += x;
//        return expect - sum;
}
