package com.algorithm.前缀和;

/*
* 请你实现一个「数字乘积类」ProductOfNumbers，要求支持下述两种方法：

1. add(int num)

将数字 num 添加到当前数字列表的最后面。
2. getProduct(int k)

返回当前数字列表中，最后 k 个数字的乘积。
你可以假设当前列表中始终 至少 包含 k 个数字。
题目数据保证：任何时候，任一连续数字序列的乘积都在 32-bit 整数范围内，不会溢出。
* */

//数组法效率很低
// class 最后K个数的乘积 {
//     int[] mul;
//     public ProductOfNumbers() {
//         mul = new int[]{};
//     }

//     public void add(int num) {
//         int oriLength = mul.length;
//         int[] arr = new int[oriLength + 1];
//         arr[arr.length - 1] = num;
//         for (int i = arr.length - 2; i >= 0; i--) {
//             arr[i] = mul[i] * num;
//         }
//         mul = arr;
//     }

//     public int getProduct(int k) {
//         return mul[mul.length - k];
//     }
// }

import java.util.ArrayList;
import java.util.List;

//这个方法考虑了0
class 最后K个数的乘积 {

    private List<Integer> products;

    public 最后K个数的乘积() {
        products = new ArrayList<>();
        products.add(1);
    }

    public void add(int num) {
        if (num == 0) {
            products = new ArrayList<>();
            products.add(1);
        } else {
            products.add(products.get(products.size() - 1) * num);
        }
    }

    public int getProduct(int k) {
        if (products.size() <= k) {
            return 0;
        }
        return products.get(products.size() - 1) / products.get(products.size() - 1 - k);
    }
}

/**
 * Your ProductOfNumbers object will be instantiated and called as such:
 * ProductOfNumbers obj = new ProductOfNumbers();
 * obj.add(num);
 * int param_2 = obj.getProduct(k);
 */