package com.algorithm.技巧和数学;

import java.util.Random;

/*
* 给定一个单链表，随机选择链表的一个节点，并返回相应的节点值。保证每个节点被选的概率一样。

进阶:
如果链表十分大且长度未知，如何解决这个问题？你能否使用常数级空间复杂度实现？

示例:

// 初始化一个单链表 [1,2,3].
ListNode head = new ListNode(1);
head.next = new ListNode(2);
head.next.next = new ListNode(3);
Solution solution = new Solution(head);

// getRandom()方法应随机返回1,2,3中的一个，保证每个元素被返回的概率相等。
solution.getRandom();
* */

public class 链表随机节点 {
    ListNode head;
    /** @param head The linked list's head.
    Note that the head is guaranteed to be not null, so it contains at least one node. */
    public 链表随机节点(ListNode head) {
        this.head = head;
    }

    /** Returns a random node's value. */
    public int getRandom() {
        Random r = new Random();
        int res = 0;
        int index = 0;
        ListNode cur = head;
        while (cur != null) {
            //洗牌算法，第一个数为1，每次有1/i的概率替换，r.nextInt(++index) == 0即为1/i.
            //最后总的概率，（此时 1 被保留的概率为 1 * 1/2 * 2/3 * ... * (n-1)/n
            if (r.nextInt(++index) == 0) {
                res = cur.val;
            }
            cur = cur.next;
        }
        return res;
    }
}
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}