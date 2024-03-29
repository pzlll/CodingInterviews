package greedy;

import java.lang.reflect.Array;
import java.util.*;

import static org.apache.commons.lang3.ArrayUtils.swap;

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

    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        int n = arr.length;
        Arrays.sort(arr);
        arr[0] = 1;
        List<List<String>> list = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            arr[i] = Math.min(arr[i], arr[i-1] + 1);
        }

        return arr[n-1];
    }

    /**
     * 解题思路：最长公共子序列->最长递增子序列
     * 哈希表+贪心+二分查找
     * @param target
     * @param arr
     * @return
     */
    public int minOperations(int[] target, int[] arr) {
        int n = target.length;

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(target[i], i);
        }

        int m = 0;
        for (int i = 0; i < arr.length; i++) {
            if(map.get(arr[i]) != null) {
                m++;
            }
        }

        if(m == 0) {
            return n;
        }
        int[] temp = new int[m];
        int j = 0;
        for (int i = 0; i < arr.length; i++) {
            if(map.get(arr[i]) != null) {
                temp[j++] = map.get(arr[i]);
            }
        }

        int[] dp = new int[m+1];
        int len = 1;
        dp[1] = temp[0];
        for (int i = 1; i < m; i++) {
            if(temp[i] > dp[len]) {
                dp[++len] = temp[i];
            }else {
                int left = 1;
                int right = len;
                while (left < right) {
                    int mid = (left + right) / 2;
                    if(dp[mid] >= temp[i]) {
                        right = mid;
                    }else {
                        left = mid + 1;
                    }
                }
                dp[left] = temp[i];
            }
        }




        return n - len;


    }

    /**
     * 解题思路：贪心
     * 要使需要的船数尽可能地少，应当使载两人的船尽可能地多。
     *
     * 设 people 的长度为 n。考虑体重最轻的人：
     *
     * 若他不能与体重最重的人同乘一艘船，那么体重最重的人无法与任何人同乘一艘船，此时应单独分配一艘船给体重最重的人。从 people 中去掉体重最重的人后，我们缩小了问题的规模，变成求解剩余n−1 个人所需的最小船数，将其加一即为原问题的答案。
     * 若他能与体重最重的人同乘一艘船，那么他能与其余任何人同乘一艘船，为了尽可能地利用船的承载重量，选择与体重最重的人同乘一艘船是最优的。从 people 中去掉体重最轻和体重最重的人后，我们缩小了问题的规模，变成求解剩余 n-2 个人所需的最小船数，将其加一即为原问题的答案。
     * @param people
     * @param limit
     * @return
     */
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);

        int i = 0;
        int j = people.length-1;

        int count = 0;
        while (i <= j) {
            if(i == j) {
                count++;
                break;
            }else {
                if((people[i] + people[j]) <= limit) {
                    i++;
                    j--;
                    count++;
                }else {
                    j--;
                    count++;
                }
            }
        }

        return count;
    }

    public int balancedStringSplit(String s) {
        int d = 0;

        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            d += (s.charAt(i) == 'L' ? 1 : -1);
            count += (d == 0 ? 1 : 0);

        }

        return count;
    }

    public boolean stoneGameIX(int[] stones) {
        int sum = 0;
        int count = 0;
        int temp = 0;

        for (int i = 0; i < stones.length; i++) {
            if(stones[i] % 3 != 0) {
                temp++;
            }

            sum += stones[i];
            count++;
        }

        if(temp == 0) {
            return false;
        }
        if(sum % 3 != 0) {
            return false;
        }

        return count % 2 == 0;
    }

    public int intersectionSizeTwo(int[][] intervals) {
        int n = intervals.length;

        List<Integer>[] temp = new List[n];

        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] == o2[0]) {
                    return o2[1] - o1[1];
                }
                return o1[0] - o2[0];
            }
        });

        for (int i = 0; i < n; i++) {
            temp[i] = new ArrayList<>();
        }

        int res = 0;

        for (int i = n - 1; i >= 0; i--) {
            for(int j = intervals[i][0], k = temp[i].size(); k < 2; k++,j++) {
                res++;
                help(intervals, temp, i - 1, j);
            }
        }

        return res;
    }

    private void help(int[][] intervals, List<Integer>[] temp,  int pos, int num) {
        for (int i = pos; i >= 0; i--) {
            if(intervals[i][1] < num) {
                break;
            }
            temp[i].add(num);
        }
    }

    public int maximumSwap(int num) {
        char[] chars = String.valueOf(num).toCharArray();

        int max = chars.length - 1;
        int j = max - 1;

        int maxValue = num;

        while (j >= 0) {
            if(chars[j] > chars[max]) {
                max = j;
            }else if(chars[j] < chars[max]) {
                swap(chars, j, max);
                maxValue = Math.max(maxValue, Integer.parseInt(new String(chars)));
                swap(chars,max, j);
            }
        }

        return maxValue;
    }

    public void swap(char[] c, int i, int j) {
        char temp = c[i];
        c[i] = c[j];
        c[j] = temp;
    }
}
