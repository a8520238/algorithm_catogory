package com.algorithm.设计问题;

/*
* 栈排序。 编写程序，对栈进行排序使最小元素位于栈顶。最多只能使用一个其他的临时栈存放数据，但不得将元素复制到别的数据结构（如数组）中。该栈支持如下操作：push、pop、peek 和 isEmpty。当栈为空时，peek 返回 -1。

示例1:

 输入：
["SortedStack", "push", "push", "peek", "pop", "peek"]
[[], [1], [2], [], [], []]
 输出：
[null,null,null,1,null,2]
示例2:

 输入：
["SortedStack", "pop", "pop", "push", "pop", "isEmpty"]
[[], [], [], [1], [], []]
 输出：
[null,null,null,null,null,true]
说明:

栈中的元素数目在[0, 5000]范围内。

* */

import java.util.Deque;
import java.util.LinkedList;

//大顶堆小顶堆思想
public class 栈排序 {

    Deque<Integer> stack;
    Deque<Integer> helpStack;

    public 栈排序() {
        stack = new LinkedList<>();
        helpStack = new LinkedList<>();
    }

    public void push(int val) {
        if (stack.isEmpty()) {
            stack.push(val);
        } else if (stack.peek() < val) {
            //主栈时小顶堆，help栈是大顶堆
            //主栈应该是从顶到底是由小到大，一旦出现大值要进入，先pop,此时help栈中存的都是小值
            while (!stack.isEmpty() && stack.peek() < val) {
                helpStack.push(stack.pop());
            }
            stack.push(val);
        } else if (helpStack.isEmpty() || helpStack.peek() <= val) {
            helpStack.push(val);
        } else {
            while (!helpStack.isEmpty() && helpStack.peek() > val) {
                stack.push(helpStack.pop());
            }
            helpStack.push(val);
        }
    }

    public void pop() {
        while (!helpStack.isEmpty()) {
            stack.push(helpStack.pop());
        }
        if (!stack.isEmpty()) {
            stack.pop();
        }
    }

    public int peek() {
        while (!helpStack.isEmpty()) {
            stack.push(helpStack.pop());
        }
        if (stack.isEmpty()) {
            return -1;
        }
        return stack.peek();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }
}
