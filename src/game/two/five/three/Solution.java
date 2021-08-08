package game.two.five.three;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution {

    public boolean isPrefixString(String s, String[] words) {
        int i = 0;
        for (int j = 0; j < words.length; j++) {
            for (int k = 0; k < words[j].length(); k++) {
                if(i == s.length() || s.charAt(i) != words[j].charAt(k)) {
                    return false;
                }
                i++;
            }
            if(i == s.length()) {
                return true;
            }
        }

        return i == s.length();
    }

    public int minStoneSum(int[] piles, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        for (int i = 0; i < piles.length; i++) {
            queue.offer(piles[i]);
        }

        while (k > 0) {
            Integer p = queue.poll();
            p = p - (int)Math.floor(p/2.0);
            queue.offer(p);
            k--;
        }

        int ret = 0;
        while (!queue.isEmpty()) {
            ret += queue.poll();
        }

        return ret;
    }

    /**
     * 解题思路：贪心
     * 从左到右遍历，若遇到左括号，则栈加一
     * 若遇到右括号且栈不为零，则栈减一，若栈为零，则左括号加一，交换次数加一（从后面拿一个左括号与这个右括号交换）
     * 返回交换次数
     * @param s
     * @return
     */
    public int minSwaps(String s) {
        int[] cnt = new int[s.length()];
        int n = s.length();

        cnt[0] = (s.charAt(0) == '[' ? 1 : -1);
        int min = cnt[0];
        for (int i = 0; i < n; i++) {
            cnt[i] = cnt[i-1] + (s.charAt(i) == '[' ? 1 : -1);
            min = Math.min(min, cnt[i]);
        }


        return -(int)Math.floor(min/2.0);
    }
}
