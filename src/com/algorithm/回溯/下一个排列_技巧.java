package com.algorithm.回溯;

import java.util.Arrays;
/*
* 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。

如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。

必须原地修改，只允许使用额外常数空间。

以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1

*/
public class 下一个排列_技巧 {
    //这道题的思路，1.先从后往前找，找到第一个非降序的值下标ex。3.再找ex后最后一个大于num[ex]的值做交换。3.将ex后的部分升序排列
    public void nextPermutation(int[] nums) {
        int cur = nums.length - 1;
        while (cur >= 1) {
            if (nums[cur] <= nums[cur - 1]) {
                cur--;
                continue;
            } else {
                int ex = cur - 1;
                while (cur < nums.length - 1) {
                    if (nums[cur + 1] > nums[ex]) {
                        cur++;
                    } else {
                        break;
                    }
                }
                int tmp = nums[cur];
                nums[cur] = nums[ex];
                nums[ex] = tmp;
                cur = ex + 1;
                break;
            }
        }
        Arrays.sort(nums, cur, nums.length);
    }
}
