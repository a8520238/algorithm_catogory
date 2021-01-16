package com.algorithm.排序;


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

/*
* 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。

进阶：

你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
 
* */

//自顶向下归并排序
// class Solution {
//     public ListNode sortList(ListNode head) {
//         return sortList(head, null);
//     }
//     public ListNode sortList(ListNode head, ListNode tail) {
//         if (head == null) {
//             return head;
//         }
//         //很重要，在递归的底层对tail进行null处理
//         if (head.next == tail) {
//             head.next = null;
//             return head;
//         }
//         ListNode slow = head, fast = head;
//         while (fast != tail && fast.next != tail) {
//             slow = slow.next;
//             fast = fast.next.next;
//         }
//         ListNode mid = slow;
//         ListNode list1 = sortList(head, mid);
//         ListNode list2 = sortList(mid, tail);
//         ListNode sorted = merge(list1, list2);
//         return sorted;
//     }
//     public ListNode merge(ListNode head1, ListNode head2) {
//         ListNode node = new ListNode(0);
//         ListNode temp = node, temp1 = head1, temp2 = head2;
//         while (temp1 != null && temp2 != null) {
//             if (temp1.val <= temp2.val) {
//                 temp.next = temp1;
//                 temp1 = temp1.next;
//             } else {
//                 temp.next = temp2;
//                 temp2 = temp2.next;
//             }
//             temp = temp.next;
//         }
//         if (temp1 != null) {
//             temp.next = temp1;
//         } else if (temp2 != null) {
//             temp.next = temp2;
//         }
//         return node.next;
//     }
// }
//自底向上归并排序
class 排序链表 {
    private class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
    public ListNode sortList(ListNode head) {
        if (head == null) {
            return head;
        }
        int length = 0;
        ListNode node = head;
        while (node != null) {
            length++;
            node = node.next;
        }
        ListNode dummyHead  = new ListNode(0, head);
        for (int subLength = 1; subLength < length; subLength <<= 1) {
            ListNode prev = dummyHead, curr = dummyHead.next;
            while (curr != null) {
                ListNode head1 = curr;
                for (int i = 1; i < subLength && curr.next != null; i++) {
                    curr = curr.next;
                }
                ListNode head2 = curr.next;
                curr.next = null;
                curr = head2;
                for (int i = 1; i < subLength && curr != null && curr.next != null; i++) {
                    curr = curr.next;
                }
                ListNode next = null;
                if (curr != null) {
                    next = curr.next;
                    curr.next = null;
                }
                ListNode merge = merge(head1, head2);
                prev.next = merge;
                while (prev.next != null) {
                    prev = prev.next;
                }
                curr = next;
            }
        }
        return dummyHead.next;
    }
    ListNode merge(ListNode head1, ListNode head2) {
        ListNode dummyHead = new ListNode(0);
        ListNode temp = dummyHead, temp1 = head1, temp2 = head2;
        while (temp1 != null && temp2 != null) {
            if (temp1.val <= temp2.val) {
                temp.next = temp1;
                temp1 = temp1.next;
            } else {
                temp.next = temp2;
                temp2 = temp2.next;
            }
            temp = temp.next;
        }
        if (temp1 != null) {
            temp.next = temp1;
        } else if (temp2 != null) {
            temp.next = temp2;
        }
        return dummyHead.next;
    }
}
//快速排序 超时
// class Solution {
//     //node结构 start是当前node的起始值，end是终点值，f是当前段内start-end被选定的part值，
//     //tail是划分后的分界线，tail前都小于tail,tail后都大于tail
//     //beforeCur在cur之前tail
//      //可以看做tail是前一段的尾，f是后一段的头
//     class node {
//         ListNode start;
//         ListNode end;
//         ListNode tail;
//         ListNode f;
//         node (ListNode start, ListNode end, ListNode tail, ListNode f) {
//             this.start = start;
//             this.end = end;
//             this.tail = tail;
//             this.f = f;
//         }
//     }
//     public ListNode sortList(ListNode head) {
//         if (head == null || head.next == null) {
//             return head;
//         }
//         ListNode tail = head;
//         while (tail.next != null) {
//             tail = tail.next;
//         }
//         node s = quickSort(head, tail);
//         return s.start;
//     }
//     private node quickSort(ListNode head, ListNode tail) {
//         if (head == tail) {
//             return new node(head, head, null, head);
//         }
//         node p = flag(head, tail);
//         node front;
//         node back;
//         //如果分割点前方没有值
//         if (p.tail == p.f) {
//             front = null;
//         } else {
//             p.tail.next = null;
//             front = quickSort(p.start, p.tail);
//         }
//         //如果分割点后方没有值
//         if (p.f.next == null) {
//             back = null;
//         } else {
//             back = quickSort(p.f.next, p.end);
//         }
//         if (front != null) {
//             front.end.next = p.f;
//             p.start = front.start;
//         } else {
//             p.start = p.f;
//         }
//         if (back != null) {
//             p.f.next = back.start;
//             p.end = back.end;
//         } else {
//             p.end = p.f;
//         }
//         return p;
//     }
//     private node flag(ListNode head, ListNode tail) {
//         ListNode part = tail;
//         ListNode start = head;
//         ListNode end = tail;
//         ListNode cur = head;
//         ListNode beforeCur = head;
//            //这里part也就是上次的tail为基准进行快排分割
//         while (cur != tail) {
//             if (cur.val > part.val) {
//                 if (cur == beforeCur) {
//                     ListNode save = cur;
//                     cur = cur.next;
//                     beforeCur = beforeCur.next;
//                     start = cur;
//                     save.next = part.next;
//                     if (part.next == null) {
//                         end = save;
//                     }
//                     part.next = save;
//                 } else {
//                     beforeCur.next = cur.next;
//                     ListNode save = cur;
//                     cur = cur.next;
//                     save.next = part.next;
//                     if (part.next == null) {
//                         end = save;
//                     }
//                     part.next = save;
//                 }
//             } else {
//                 if (cur == beforeCur) {
//                     cur = cur.next;
//                 } else {
//                     cur = cur.next;
//                     beforeCur = beforeCur.next;
//                 }
//             }
//         }
//         return new node(start, end, beforeCur, cur);
//     }
// }
