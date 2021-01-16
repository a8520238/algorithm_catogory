package com.algorithm.设计问题;

/*
* 堆盘子。设想有一堆盘子，堆太高可能会倒下来。因此，在现实生活中，盘子堆到一定高度时，我们就会另外堆一堆盘子。请实现数据结构SetOfStacks，模拟这种行为。SetOfStacks应该由多个栈组成，并且在前一个栈填满时新建一个栈。此外，SetOfStacks.push()和SetOfStacks.pop()应该与普通栈的操作方法相同（也就是说，pop()返回的值，应该跟只有一个栈时的情况一样）。 进阶：实现一个popAt(int index)方法，根据指定的子栈，执行pop操作。

当某个栈为空时，应当删除该栈。当栈中没有元素或不存在该栈时，pop，popAt 应返回 -1.

示例1:

 输入：
["StackOfPlates", "push", "push", "popAt", "pop", "pop"]
[[1], [1], [2], [1], [], []]
 输出：
[null, null, null, 2, 1, -1]
示例2:

 输入：
["StackOfPlates", "push", "push", "push", "popAt", "popAt", "popAt"]
[[2], [1], [2], [3], [0], [0], [0]]
 输出：
[null, null, null, null, 2, 1, 3]
* */


// 一种链表思想，像极了LFU
public class 堆盘子 {
    int length;
    node head;
    node curNode;
    public 堆盘子(int cap) {
        length = cap;
        head = new node(cap);
        curNode = head;
    }

    public void push(int val) {
        if (length == 0) {
            return;
        }
        if (curNode == head || curNode.index == length) {
            node newNode = new node(length);
            curNode.next = newNode;
            newNode.pre = curNode;
            curNode = newNode;
        }
        curNode.arr[curNode.index++] = val;
    }

    public int pop() {
        if (curNode == head || length == 0) {
            return -1;
        }
        int res = curNode.arr[curNode.index - 1];
        if (--curNode.index == 0) {
            deleteNode(curNode);
        }
        return res;
    }

    public int popAt(int index) {
        node curNode = head.next;
        while (index > 0 && curNode != null) {
            curNode = curNode.next;
            index--;
        }
        if (curNode == null || length == 0) {
            return -1;
        }
        int res = curNode.arr[curNode.index - 1];
        if (--curNode.index == 0) {
            deleteNode(curNode);
        }
        return res;
    }

    public void deleteNode(node dNode) {
        node pre = dNode.pre;
        node next = dNode.next;
        pre.next = next;
        if (next != null) {
            next.pre = pre;
            curNode = next;
        } else {
            curNode = pre;
        }
    }

    class node {
        int[] arr;
        int index;
        node next;
        node pre;
        node (int k) {
            arr = new int[k];
            index = 0;
        }
    }
}
