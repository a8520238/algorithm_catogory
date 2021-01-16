package com.algorithm.设计问题;

import java.util.HashMap;
import java.util.Map;

public class LFU460 {

    Map<Integer, Node> cache;
    MyLinkedList minList;
    MyLinkedList maxList;
    int size;
    int capacity;

    public LFU460(int capacity) {
        cache = new HashMap<>(capacity);
        minList = new MyLinkedList();
        maxList = new MyLinkedList();
        this.capacity = capacity;
        maxList.pre = minList;
        minList.next = maxList;
    }

    public int get(int key) {
        Node curNode = cache.get(key);
        if (curNode == null) {
            return -1;
        }
        addFre(curNode);
        return curNode.value;
    }

    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }
        Node curNode = cache.get(key);
        if (curNode != null) {
            curNode.value = value;
            addFre(curNode);
        } else {
            if (size == capacity) {
                cache.remove(minList.next.end.pre.key);
                minList.next.removeNode(minList.next.end.pre);
                if (minList.next.first.next == minList.next.end) {
                    removeLinkedList(minList.next);
                }
                size--;
            }
            curNode = new Node(key, value);
            cache.put(key, curNode);
            if (minList.next.fre != 1) {
                MyLinkedList curList = new MyLinkedList(1);
                curList.addNode(curNode);
                addLinkedList(curList, minList.next);
            } else {
                MyLinkedList curList = minList.next;
                curList.addNode(curNode);
            }
            size++;
        }
    }

    public void addFre(Node curNode) {
        MyLinkedList curList = curNode.list;
        MyLinkedList nextList = curList.next;
        curList.removeNode(curNode);
        if (curList.first.next == curList.end) {
            removeLinkedList(curList);
        }
        curNode.fre++;
        if (curNode.fre != nextList.fre) {
            MyLinkedList newList = new MyLinkedList(curNode.fre);
            newList.addNode(curNode);
            addLinkedList(newList, nextList);
        } else {
            nextList.addNode(curNode);
        }
    }

    public void addLinkedList(MyLinkedList curList, MyLinkedList nextList) {
        curList.pre = nextList.pre;
        curList.pre.next = curList;
        curList.next = nextList;
        nextList.pre = curList;
    }

    public void removeLinkedList(MyLinkedList curList) {
        MyLinkedList preList = curList.pre;
        MyLinkedList nextList = curList.next;
        preList.next = nextList;
        nextList.pre = preList;
    }

    class Node {
        int fre;
        int value;
        int key;
        MyLinkedList list;
        Node pre;
        Node next;
        Node () {

        }
        Node (int key, int value) {
            fre = 1;
            this.value = value;
            this.key = key;
        }
    }

    class MyLinkedList {
        Node first;
        Node end;
        MyLinkedList pre;
        MyLinkedList next;
        int fre;

        MyLinkedList(int fre) {
            first = new Node();
            end = new Node();
            first.next = end;
            end.pre = first;
            this.fre = fre;
        }

        MyLinkedList() {
            first = new Node();
            end = new Node();
            first.next = end;
            end.pre = first;
        }
        public void removeNode(Node curNode) {
            curNode.pre.next = curNode.next;
            curNode.next.pre = curNode.pre;
        }

        public void addNode(Node curNode) {
            curNode.next = first.next;
            first.next.pre = curNode;
            first.next = curNode;
            curNode.pre = first;
            curNode.list = this;
        }
    }
}
