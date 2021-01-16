package com.algorithm.优先队列;

import java.util.PriorityQueue;
/*
* 如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。

例如，

[2,3,4] 的中位数是 3

[2,3] 的中位数是 (2 + 3) / 2 = 2.5

设计一个支持以下两种操作的数据结构：

void addNum(int num) - 从数据流中添加一个整数到数据结构中。
double findMedian() - 返回目前所有元素的中位数。
*/
public class 数据流中的中位数 {
    PriorityQueue<Integer> q1;
    PriorityQueue<Integer> q2;
    /** initialize your data structure here. */
    public 数据流中的中位数() {
        q1 = new PriorityQueue<>(); //小顶堆保存较大的一半
        q2 = new PriorityQueue<>((a, b) -> b - a); //大顶堆保存较小的一半
    }
    public void addNum(int num) {
        int lenSmall = q1.size();
        int lenBig = q2.size();
        if (lenSmall == lenBig) {
            q2.add(num);
            q1.add(q2.poll());
        } else {
            q1.add(num);
            q2.add(q1.poll());
        }
    }

    public double findMedian() {
        return q1.size() == q2.size()? (q1.peek() + q2.peek()) / 2.0: q1.peek();
    }
}
