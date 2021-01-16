package com.algorithm.位运算;

/*
*下一个数。给定一个正整数，找出与其二进制表达式中1的个数相同且大小最接近的那两个数（一个略大，一个略小）。

示例1:

 输入：num = 2（或者0b10）
 输出：[4, 1] 或者（[0b100, 0b1]）
示例2:

 输入：num = 1
 输出：[2, -1]
提示:

num的范围在[1, 2147483647]之间；
如果找不到前一个或者后一个满足条件的正数，那么输出 -1。
*
* */
class 下一个数 {
    //位运算，碰到01就能获取最近大的值，碰到10就可以获取最近小的值
    public int[] findClosedNumbers(int num) {
        int[] target = {-1, -1};
        boolean minFlag = false;//标志位，用于标志最近小的数有没有计算过
        boolean maxFlag = false;//标志位，用于标志最近大的数有没有计算过
        int sum = 0;//1的个数
        int ans = 0;//num前n位二进制转换的值
        if ((num & 1) == 1) {
            sum++;
            ans = 1;
        }
        for (int i = 1; i < 32; i++) {
            //如果最近小的数和最近大的数都计算过了，返回所得到的最近小和最近大的数
            if (minFlag && maxFlag) {
                return target;
            }
            int first = 1 << (i - 1);//用于计算num二进制第i-1位是0还是1
            int second = 1 << i;//用于计算num二进制第i位是0还是1
            if ((num & second) == second) {//第i位是1
                sum++;
                ans += second;
                if (!minFlag && (num & first) == 0) {//判断第i位和第i-1位是不是10 是的话获取最近小的数
                    //清除前i位的1
                    target[1] = num - ans;

                    // int temp = second >> 1;
                    //n-1 到 n-1-sum位补1;
                    int temp = first;
                    int tempSum = 0;
                    for (int j = 0; j < sum; j++) {
                        tempSum += temp;
                        temp = temp >> 1;
                    }

                    target[1] += tempSum;
                    minFlag = true;
                }
            } else {//第i位是0
                if (!maxFlag && (num & first) == first) {//判断第i位和第i-1位是不是01 是的话获取最近大的数
                    //清除前n位的1
                    target[0] = num - ans;
                    //给第i位补1，此时还有sum-1个1，给第0位到sum-1补1
                    int tempSum = second;
                    for (int j = 0; j < sum - 1; j++) {
                        tempSum += 1 << j;
                    }
                    target[0] += tempSum;
                    maxFlag = true;
                }
            }
        }
        return target;
    }
}

// class 下一个数 {
//     //暴力法 通过加一减一然后判断1的总数的方法进行判断
//     public int[] findClosedNumbers(int num) {
//         int fi, i;
//         int[] arr = new int[2];
//         arr[0] = arr[1] = -1;
//         if (num == Integer.MAX_VALUE) {
//             return arr;
//         }
//         fi = oneFigure(num);
//         for (i = num + 1; i <= Integer.MAX_VALUE; i++) {
//             int f = oneFigure(i);
//             if (fi == f) {
//                 arr[0] = i;
//                 break;
//             }
//         }
//         for (i = num - 1; i >= 1; i--) {
//             int f = oneFigure(i);
//             if (fi == f) {
//                 arr[1] = i;
//                 break;
//             }
//         }
//         return arr;
//     }
//     public int oneFigure(int num) {
//         int fi = 0;
//         while (num != 0) {
//             if ((num & 1) == 1) {
//                 fi++;
//             }
//             num >>= 1;
//         }
//         return fi;
//     }
// }