package com.algorithm.二进制运算;
/*
* 输入一个整数 n ，求1～n这n个整数的十进制表示中1出现的次数。

例如，输入12，1～12这些整数中包含1 的数字有1、10、11和12，1一共出现了5次。
*/
// class 整数中1出现的次数 {
//     //按位计算1的个数
//     public int countDigitOne(int n) {
//         int digit = 1, res = 0;
//         int high = n / 10, cur = n % 10, low = 0;
//         while (high != 0 || cur != 0) {
//             if (cur == 0) {
//                 res += digit * high;
//             } else if (cur == 1) {
//                 res += high * digit + low + 1;
//             } else {
//                 res += (high + 1) * digit;
//             }
//             low += cur * digit;
//             cur = high % 10;
//             high /= 10;
//             digit *= 10;
//         }
//         return res;
//     }
// }
//递归解法
class 整数中1出现的次数 {
    public int countDigitOne(int n) {
        return f(n);
    }
    public int f(int n) {
        if (n <= 0) {
            return 0;
        }
        String s = String.valueOf(n);
        int high = s.charAt(0) - '0';
        int pow = (int)Math.pow(10, s.length() - 1);
        int last = n - pow * high;
        if (high == 1) {
            return f(last) + f(pow - 1) + last + 1;
        } else {
            return pow + high * f(pow - 1) + f(last);
        }
    }
}
