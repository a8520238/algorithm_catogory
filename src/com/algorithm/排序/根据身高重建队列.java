package com.algorithm.排序;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
* 假设有打乱顺序的一群人站成一个队列。 每个人由一个整数对(h, k)表示，其中h是这个人的身高，k是排在这个人前面且身高大于或等于h的人数。 编写一个算法来重建这个队列。

注意：
总人数少于1100人。

示例

输入:
[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]

输出:
[[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
*/

public class 根据身高重建队列 {
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (x, y) -> x[0] == y[0] ? x[1] - y[1] :y[0] - x[0]);
        //这道题的关键在于要想到list按下标插入
        List<int[]> list = new ArrayList<>();
        for (int[] arr: people) {
            list.add(arr[1], arr);
        }
        return list.toArray(new int[0][]);
    }
}
