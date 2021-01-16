package com.algorithm.数组;

/*
* 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。

示例:

输入: [0,1,0,3,12]
输出: [1,3,12,0,0]
说明:

必须在原数组上操作，不能拷贝额外的数组。
尽量减少操作次数。

* */

//第一种思想，交换0和非0，不好想
class 移动零 {
    public void moveZeroes(int[] nums) {
        int zero = 0;
        int noZero = -1;
        while (zero < nums.length && noZero < nums.length) {
            //先交换，效率高
            if (noZero != -1) {
                nums[zero++] = nums[noZero];
                nums[noZero++] = 0;
            }
            while(zero < nums.length && nums[zero] != 0) {
                zero++;
            }
            noZero = noZero == -1? zero + 1: noZero;
            while (noZero < nums.length && nums[noZero] == 0) {
                noZero++;
            }
            //放在后面交换，效率低
            // if (noZero >= nums.length) {
            //     break;
            // }
            // nums[zero] = nums[noZero];
            // nums[noZero] = 0;
        }
    }
}

//第二种思想，如果当前位置不是0，记录值为该值，如果是0，有效长度减一
// class 移动零 {
//     public void moveZeroes(int[] nums) {
//         int length = nums.length;
//         int j = -1;
//         for (int i = 0; i < nums.length; i++) {
//             if (nums[i] != 0) {
//                 j++;
//                 nums[j] = nums[i];
//             }
//             else {
//                 length--;
//             }
//         }
//         for (int i = 0; i < nums.length-length; i++) {
//             nums[nums.length-1-i] = 0;
//         }

//     }
// }

//先初始化版本的思想1
// class 移动零 {
//     public void moveZeroes(int[] nums) {
//         int zero = nums.length;
//         for (int i = 0; i < nums.length; i++) {
//             if (nums[i] == 0) {
//                 zero = i;
//                 break;
//             }
//         }
//         int num = zero;
//         while (num < nums.length && nums[num] == 0) {
//             num++;
//         }
//         while (zero < nums.length && num < nums.length) {
//             nums[zero++] = nums[num];
//             nums[num++] = 0;
//             while (zero < nums.length && nums[zero] != 0) {
//                 zero++;
//             }
//             while (num < nums.length && nums[num] == 0) {
//                 num++;
//             }
//         }
//     }
// }