package com.algorithm.排序;

/*
* 给你两个数组，arr1 和 arr2，

arr2 中的元素各不相同
arr2 中的每个元素都出现在 arr1 中
对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。未在 arr2 中出现过的元素需要按照升序放在 arr1 的末尾。

 

示例：

输入：arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
输出：[2,2,2,1,4,3,3,9,6,7,19]

* */
// 两种做法 计数排序和自定义比较器
//自定义比较器
// class 数组的相对排序 {
//     Map<Integer, Integer> map = new HashMap<>();
//     public int[] relativeSortArray(int[] arr1, int[] arr2) {
//         for (int i = 0; i < arr2.length; i++) {
//             map.put(arr2[i], i);
//         }
//         Integer[] tmp = new Integer[arr1.length];
//         for (int i = 0; i < arr1.length; i++) {
//             tmp[i] = Integer.valueOf(arr1[i]);
//         }
//         Arrays.sort(tmp, new myComparator());
//         for (int i = 0; i < arr1.length; i++) {
//             arr1[i] = tmp[i].intValue();
//         }
//         return arr1;
//         // return Arrays.stream(tmp).mapToInt(Integer::intValue).toArray();
//     }
//     class myComparator implements Comparator<Integer> {
//         public int compare(Integer n1, Integer n2) {
//             if (map.containsKey(n1) && map.containsKey(n2)) {
//                 return map.get(n1) - map.get(n2);
//             }
//             if (map.containsKey(n1)) {
//                 return -1;
//             } else if (map.containsKey(n2)) {
//                 return 1;
//             }
//             return n1 - n2;
//         }
//     }
// }
//计数排序
class 数组的相对排序 {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int length = 0;
        for (int n: arr1) {
            length = Math.max(length, n);
        }
        int[] count = new int[length + 1];
        int index = 0;
        for (int n: arr1) {
            count[n] += 1;
        }
        int[] res = new int[arr1.length];
        for (int i = 0; i < arr2.length; i++) {
            while (count[arr2[i]] > 0) {
                res[index++] = arr2[i];
                count[arr2[i]]--;
            }
        }
        for (int i = 0; i < count.length; i++) {
            while (count[i] > 0) {
                res[index++] = i;
                count[i]--;
            }
        }
        return res;
    }
}