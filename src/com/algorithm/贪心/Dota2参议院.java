package com.algorithm.贪心;

public class Dota2参议院 {
    //两个队列避免了双重for的问题
    // public String predictPartyVictory(String senate) {
    //     int n = senate.length();
    //     Queue<Integer> radiant = new LinkedList<>();
    //     Queue<Integer> dire = new LinkedList<>();
    //     for (int i = 0; i < n; i++) {
    //         if (senate.charAt(i) == 'R') {
    //             radiant.offer(i);
    //         } else {
    //             dire.offer(i);
    //         }
    //     }
    //     while (!radiant.isEmpty() && !dire.isEmpty()) {
    //         int radiantIndex = radiant.poll(), direIndex = dire.poll();
    //         if (radiantIndex < direIndex) {
    //             radiant.offer(radiantIndex + n);
    //         } else {
    //             dire.offer(direIndex + n);
    //         }
    //     }
    //     return !radiant.isEmpty()? "Radiant": "Dire";
    // }
//消灭的策略是，尽量消灭自己后面的对手，因为前面的对手已经使用过权利了，而后序的对手依然可以使用权利消灭自己的同伴！
    public String predictPartyVictory(String senate) {
        // R = true表示本轮循环结束后，字符串里依然有R。D同理
        boolean R = true;
        boolean D = true;
        // 当flag大于0时，R在D前出现，R可以消灭D。当flag小于0时，D在R前出现，D可以消灭R
        int flag = 0;
        char[] str = senate.toCharArray();
        // 当flag大于0时，R在D前出现，R可以消灭D。当flag小于0时，D在R前出现，D可以消灭R
        while (R && D) {
            R = false;
            D = false;
            for (int i = 0; i < str.length; i++) {
                if (str[i] == 'R') {
                    if (flag < 0) {
                        str[i] = 0;// 消灭R，R此时为false
                    } else {
                        R = true;// 如果没被消灭，本轮循环结束有R
                    }
                    flag++;
                }
                if (str[i] == 'D') {
                    if (flag > 0) {
                        str[i] = 0;
                    } else {
                        D = true;
                    }
                    flag--;
                }
            }
        }
        // 循环结束之后，R和D只能有一个为true
        return R == true? "Radiant": "Dire";
    }
}
