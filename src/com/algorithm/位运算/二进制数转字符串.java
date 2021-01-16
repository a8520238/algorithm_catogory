package com.algorithm.位运算;

/*
*
* 二进制数转字符串。给定一个介于0和1之间的实数（如0.72），类型为double，打印它的二进制表达式。如果该数字无法精确地用32位以内的二进制表示，则打印“ERROR”。

示例1:

 输入：0.625
 输出："0.101"
示例2:

 输入：0.1
 输出："ERROR"
 提示：0.1无法被二进制准确表示
提示：

32位包括输出中的"0."这两位。

* */

public class 二进制数转字符串 {
    //乘2取1法
    /*
    那结合题意，我们就这样不断地将这个实数乘以2然后取出小数点前的那一位（非0即1），然后让剩下的数往复执行上述步骤，直到该数变为0，我们的转换任务就完成了。但是我们知道用二进制表示小数是存在精度丢失的，有的数它表示不了，按照上面的做法，碰到一些数可能会需要很长的、甚至是无限循环的二进制表示
    */
    public String printBin(double num) {
        StringBuilder builder = new StringBuilder();
        builder.append("0.");
        while (num != 0) {
            if (builder.length() >= 32) {
                return "ERROR";
            }
            num = num * 2;
            builder.append(((int)num) & 1);
            if (num >= 1) {
                num -= 1;
            }
        }
        return builder.toString();
    }
}
