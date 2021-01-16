package com.algorithm.字符串;

public class 长按键入925 {
    //下面是自己想的，边界条件很难想
    // public boolean isLongPressedName(String name, String typed) {
    //     int Sname = 0;
    //     int Stype = 0;
    //     char[] q = name.toCharArray();
    //     char[] p = typed.toCharArray();
    //     while (Sname < q.length && Stype < p.length) {
    //         if (Stype == p.length || p[Stype] != q[Sname]) {
    //             return false;
    //         }
    //         Sname++;
    //         Stype++;
    //         if (Sname == q.length || q[Sname] != q[Sname - 1]) {
    //             while (Stype < p.length && p[Stype] == p[Stype - 1]) {
    //                 Stype++;
    //             }
    //         }
    //     }
    //     return Sname == q.length && Stype == p.length;
    // }
    //答案的思想较为简单
    //主要思路是每一只判断当前条件，简化上面的while循环
    public boolean isLongPressedName(String name, String typed) {
        char[] p = typed.toCharArray();
        char[] q = name.toCharArray();
        int i = 0, j = 0;
        while (i < p.length) {
            if (j < q.length && p[i] == q[j]) {
                i++;
                j++;
            } else if (i > 0 && p[i] == p[i - 1]) {
                i++;
            } else {
                return false;
            }
        }
        return j == q.length;
    }
}
