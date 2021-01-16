package com.algorithm.设计问题;

import java.util.*;

/*设计一个支持在平均 时间复杂度 O(1) 下， 执行以下操作的数据结构。

注意: 允许出现重复元素。

insert(val)：向集合中插入元素 val。
remove(val)：当 val 存在时，从集合中移除一个 val。
getRandom：从现有集合中随机获取一个元素。每个元素被返回的概率应该与其在集合中的数量呈线性相关。
*/
public class O1时间插入_删除和获取随机元素_允许重复 {
    List<Integer> list;
    Map<Integer, Set<Integer>> map;
    Random rand;
    /** Initialize your data structure here. */
    public O1时间插入_删除和获取随机元素_允许重复() {
        list = new ArrayList<>();
        map = new HashMap<>();
        rand = new Random();
    }

    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        list.add(val);
        if (!map.containsKey(val)) {
            map.put(val, new HashSet<Integer>());
        }
        map.get(val).add(list.size() - 1);
        return map.get(val).size() == 1;
    }

    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        Set<Integer> set = map.get(val);
        if (set == null || set.size() == 0) {
            return false;
        }
        Iterator<Integer> it = set.iterator();
        //要删除的下标
        int index = it.next();
        //最后的元素，与要删除的交换
        int change = list.get(list.size() - 1);
        list.set(index, change);
        //删除要删的index
        set.remove(index);
        //删除修改的值原来存储的index
        Set<Integer> changeSet = map.get(change);
        changeSet.remove(list.size() - 1);
        if (index < list.size() - 1) {
            changeSet.add(index);
        }
        list.remove(list.size() - 1);
        return true;
    }

    /** Get a random element from the collection. */
    public int getRandom() {
        int index = rand.nextInt(list.size());
        return list.get(index);
    }
}
