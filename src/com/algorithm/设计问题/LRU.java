package com.algorithm.设计问题;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

public class LRU {
    Map<Integer, Node> map;
    int capacity;
    LinkedList<Node> cache;

    public LRU(int capacity) {
        this.map = new HashMap<>();
        this.capacity = capacity;
        this.cache = new LinkedList<>();
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        Node node = map.get(key);
        put(key, node.value);
        return node.value;
    }

    public void put(int key, int value) {
        Node node = new Node(key, value);
        if (map.containsKey(key)) {
            cache.remove(map.get(key));
            cache.addFirst(node);
            map.put(key, node);
        } else {
            if (cache.size() == this.capacity) {
                Node last = cache.removeLast();
                map.remove(last.key);
            }
            cache.addFirst(node);
            map.put(key, node);
        }
    }

    class Node {
        int key;
        int value;
        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}
// LinkedHashMap法， 注意要继承重写，不能直接new LinkedList
//class LRUCache extends LinkedHashMap<Integer, Integer>{
//    private int capacity;
//    public LRUCache(int capacity) {
//        //想要使用删除最后的订购模式， 重写为true
//        super(capacity, 0.75F, true);
//        this.capacity = capacity;
//    }
//
//    public int get(int key) {
//        int res = super.getOrDefault(key, -1);
//        return res;
//    }
//
//    public void put(int key, int value) {
//        super.put(key, value);
//    }
//
//    @Override
//    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
//        return this.size() > this.capacity;
//    }
//}