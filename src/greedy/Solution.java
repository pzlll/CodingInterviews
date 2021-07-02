package greedy;

import java.util.Arrays;

public class Solution {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);

        int i = 0;
        int j = 0;
        for (j = 0; j < s.length && i < g.length; j++) {
            if(g[i] <= s[j]) {
                i++;
            }
        }

        return i;
    }

    public int eraseOverlapIntervals(int[][] intervals) {

        for (int i = 0; i < intervals.length-1; i++) {
            int min = i;
            for (int j = i+1; j < intervals.length; j++) {
                if (intervals[j][0] < intervals[min][0]) {
                    min = j;
                }
            }

            int temp1 = intervals[i][0];
            int temp2 = intervals[i][1];
            intervals[i][0] = intervals[min][0];
            intervals[i][1] = intervals[min][1];

            intervals[min][0] = temp1;
            intervals[min][1] = temp2;
        }

        int[] f = new int[intervals.length];
        for (int i = 0; i < f.length; i++) {
            f[i] = 1;
        }

        for (int i = 1; i < intervals.length; i++) {
            for (int j = 0; j < intervals.length; j++) {
                if(intervals[j][1] <= intervals[i][0]) {
                    f[i] = Math.max(f[i], f[j] + 1);
                }
            }
        }

        int max = 0;
        for (int i = 0; i < f.length; i++) {
            if(f[i] > max)
                max = f[i];
        }
        return intervals.length - max;
    }

    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int num = 0;
        if (n < 0) {
            return false;
        }

        if(flowerbed == null || n == 0) {
            return true;
        }

        if(flowerbed.length == 0) {
            if(n == 0)
                return true;
            else
                return false;
        }

        int value = flowerbed[0];

        for (int i = 0; i < flowerbed.length; i++) {
            if(flowerbed[i] == 0) {
                int flag1 = 0;
                if((i-1 >= 0 && flowerbed[i-1] == 0) || i-1 <0) {
                    if ((i+1 < flowerbed.length && flowerbed[i+1] == 0) || i+1 >= flowerbed.length) {
                        flowerbed[i] = 1;
                        num++;
                    }
                }

            }
        }
        if (num >= n)
            return true;
        else
            return false;
    }

    public int maxIceCream(int[] costs, int coins) {
//        Arrays.sort(costs);

        int[] freq = new int[100001];

        int n = costs.length;
        for (int i = 0; i < n; i++) {
            freq[costs[i]]++;
        }

        int ret = 0;
        int sum = 0;

        for (int i = 1; i <= 100000; i++) {
            if(freq[i] == 0) {
                continue;
            }
            if(i <= coins) {
                int count = Math.min(freq[i], coins / i);
                coins -= count * i;
            }
        }
        for (int i = 0; i < costs.length; i++) {
            sum += costs[i];
            if(sum <= coins) {
                ret++;
            }else {
                break;
            }
        }

        return ret;
    }
}
