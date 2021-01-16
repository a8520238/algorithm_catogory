package com.algorithm.二进制运算;

public class 不用加减乘除做加法 {
    public int add(int a, int b) {
        while (b != 0) {
            int c = (a & b) << 1;
            a ^= b;
            b = c;
        }
        return a;
    }
    // public int add(int a, int b) {
    //     while (b != 0) {
    //         int tempSum = a ^ b;
    //         int carrySum = (a & b) << 1;
    //         a = tempSum;
    //         b = carrySum;
    //     }
    //     return a;
    // }
}
