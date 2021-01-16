package com.algorithm.单调栈;

import java.util.Stack;

/*
Leetcode 84
* 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。

求在该柱状图中，能够勾勒出来的矩形的最大面积。
* */

public class 柱状图中最大矩形 {
    //单调栈解法
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int max = 0;
        for (int i = 0; i < heights.length; i++) {
            //如果当前值小，开始弹出
            while (stack.peek() != -1 && heights[i] <= heights[stack.peek()]) {
                //如果为-1，左侧都比其大
                max = Math.max(max, heights[stack.pop()] * (i - stack.peek() - 1));
            }
            stack.push(i);
        }
        while (stack.peek() != -1) {
            //右侧都比其大, 仍要考虑左边可达-1的情况， 故先pop，后peek
            max = Math.max(max, heights[stack.pop()] * (heights.length - stack.peek() - 1));
        }
        return max;
    }
    //分治思想 Onlgn
    // public int largestRectangleArea(int[] heights) {
    //     if (heights == null || heights.length == 0) {
    //         return 0;
    //     }
    //     return process(heights, 0, heights.length-1);
    // }
    // public int process(int[] heights, int start, int end) {
    //     if (start > end) {
    //         return 0;
    //     }
    //     if (start == end) {
    //         return heights[start];
    //     }
    //     int index = start;
    //     for (int i = start + 1; i <= end; i++) {
    //         if (heights[i] < heights[index]) {
    //             index = i;
    //         }
    //     }
    //     int max = heights[index] * (end - start + 1);
    //     return Math.max(process(heights, index + 1, end), Math.max(max, process(heights, start, index - 1)));
    // }
}
