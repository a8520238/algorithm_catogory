package com.algorithm.排序;

/*
* 插入排序算法：

插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。
每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。
重复直到所有输入数据插入完为止。
 

示例 1：

输入: 4->2->1->3
输出: 1->2->3->4

* */

class ListNode {
     int val;
     ListNode next;
     ListNode(int x) { val = x; }
}

// class 对链表进行插入排序 {
//     public ListNode insertionSortList(ListNode head) {
//         if (head == null || head.next == null) {
//             return head;
//         }
//         ListNode preNode = new ListNode(0);
//         preNode.next = head;
//         ListNode cur = head;
//         while (cur.next != null) {
//             ListNode pre = preNode;
//             while (pre.next.val < cur.next.val) {
//                 pre = pre.next;
//             }
//             if (pre.next == cur.next) {
//                 cur = cur.next;
//             } else {
//                 ListNode tmp = cur.next.next;
//                 cur.next.next = pre.next;
//                 pre.next = cur.next;
//                 cur.next = tmp;
//             }
//         }
//         return preNode.next;
//     }
// }
//这个思路很巧妙，没有每一次都去比较cur.next，而是存了LastSorted,如果LastSorted与cur有序，则前面一定有序。
class 对链表进行插入排序 {
    public ListNode insertionSortList(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode lastSorted = head, curr = head.next;
        while (curr != null) {
            if (lastSorted.val <= curr.val) {
                lastSorted = lastSorted.next;
            } else {
                ListNode prev = dummyHead;
                while (prev.next.val <= curr.val) {
                    prev = prev.next;
                }
                lastSorted.next = curr.next;
                curr.next = prev.next;
                prev.next = curr;
            }
            curr = lastSorted.next;
        }
        return dummyHead.next;
    }
}