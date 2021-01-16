package com.algorithm.贪心;

/*
* 给定一个非负整数 N，找出小于或等于 N 的最大的整数，同时这个整数需要满足其各个位数上的数字是单调递增。

（当且仅当每个相邻位数上的数字 x 和 y 满足 x <= y 时，我们称这个整数是单调递增的。）

示例 1:

输入: N = 10
输出: 9
示例 2:

输入: N = 1234
输出: 1234
示例 3:

输入: N = 332
输出: 299
说明: N 是在 [0, 10^9] 范围内的一个整数。
* */

public class 单调递增的数字 {
//    public int monotoneIncreasingDigits(int N) {
//        //本题思路，从后向前，记录第一个逆序的位置m，如果m不在最后，N / 10^m * 10^m - 1为最终答案
//        //比如3548，最后的逆序位置为2，最后的值为3548 / 100 * 100 - 1 = 3499
//        if (N < 10) {
//            return N;
//        }
//        int indexRecord = 0;
//        int index = 0;
//        int num = N;
//        int cur = N % 10;
//        int pre = -1;
//
//        while (N >= 10) {
//            N = N / 10;
//            pre = N % 10;
//            index++;
//            if (pre > cur) {
//                indexRecord = index;
//                //这里pre要减一，防止前面出现相等，比如332,因为当前已经出现逆序，所以无论如何，当前值也会变小。为了避免当前值与前一个位置值相等，进入不了if，在这里减1.
//                pre--;
//            }
//            cur = pre;
//        }
//
//        int m = (int)Math.pow(10, indexRecord);
//        if (m > 1) {
//            num = num / m * m - 1;
//        }
//        return num;
//    }
    //这种思路利用的字符串数组，想法与上面的一样。
    public int monotoneIncreasingDigits(int N) {
        char[] strN = Integer.toString(N).toCharArray();
        int i = 1;
        while (i < strN.length && strN[i - 1] <= strN[i]) {
            i += 1;
        }
        if (i < strN.length) {
            while (i > 0 && strN[i - 1] > strN[i]) {
                strN[i - 1] -= 1;
                i -= 1;
            }
            i += 1;
            for (; i < strN.length; i++) {
                strN[i] = '9';
            }
        }
        return Integer.parseInt(new String(strN));
    }
}
