package dynamicprogramming;

import org.omg.CORBA.INTERNAL;

import java.lang.reflect.Array;
import java.util.*;

public class Solution {
    public int trap(int[] height) {
        if (height.length <= 2) {
            return 0;
        }
        int[] leftMax = new int[height.length];
        int[] rightMax = new int[height.length];

        int max = 0;
        for (int i = 0; i < height.length; i++) {
            if (max < height[i]) {
                max = height[i];
            }
            leftMax[i] = max;
        }

        max = 0;
        for (int i = height.length - 1; i >= 0; i--) {
            if (max < height[i]) {
                max = height[i];
            }
            rightMax[i] = max;
        }

        int result = 0;
        for (int i = 1; i < height.length - 1; i++) {
            result += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return result;
    }

    public int rob(int[] nums) {
        boolean[] used = new boolean[nums.length];

        if (nums.length == 0) {
            return 0;
        }

        int[] result = new int[nums.length];
        result[0] = nums[0];
        used[0] = true;

        for (int i = 1; i < nums.length; i++) {
            if (used[i - 1]) {
                if (i == 1) {
                    if (nums[i] > nums[i - 1]) {
                        result[i] = nums[i];
                        used[i] = true;
                    } else {
                        result[i] = result[i - 1];
                        used[i] = false;
                    }
                } else {
                    if ((result[i - 2] + nums[i]) > result[i - 1]) {
                        result[i] = result[i - 2] + nums[i];
                        used[i] = true;
                    } else {
                        result[i] = result[i - 1];
                        used[i] = false;
                    }
                }
            } else {
                result[i] = result[i - 1] + nums[i];
                used[i] = true;
            }
        }
        return result[nums.length - 1];
    }

    public int maxSubArray(int[] nums) {
        int temp = nums[0];
        int max = temp;
        for (int i = 1; i < nums.length; i++) {

            temp = Math.max(temp + nums[i], nums[i]);
            if (temp > max) {
                max = temp;
            }
        }


        return max;

//        int n = nums.length;
//        int[] sum = new int[n+1];
//        for (int i = 1; i < n+1; i++) {
//            sum[i] = sum[i-1] + nums[i-1];
//        }
//
//        int min = 0;
//        int max = Integer.MIN_VALUE;
//
//        for (int i = 1; i <= n; i++) {
//            max = Math.max(sum[i] - min, max);
//            min = Math.min(min, sum[i]);
//        }
//
//        return max;
    }

    /**
     * 解题思路：
     * 方法一：使用动态规划状态转移方程 count[i] = Max(count[0]..count[i-1]) 前提是前面元素小于该元素
     * 方法二：使用贪心算法+二分查找：维护最小值数组 若元素大于该数组的元素，则加入，否则替换里面的元素
     */
    public int lengthOfLIS(int[] nums) {
        int[] count = new int[nums.length];
        Arrays.fill(count, 1);

        for (int i = 1; i < nums.length; i++) {
            int max = -1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    if (max == -1) {
                        max = j;
                    } else if (count[max] < count[j]) {
                        max = j;
                    }
                }
            }
            if (max != -1) {
                count[i] = count[max] + 1;
            }

        }

        int max = 0;
        for (int i = 0; i < count.length; i++) {
            max = Math.max(max, count[i]);
        }

        return max;
    }

    /**
     * 解题思路：方法一：利用之前的元素来更新当前元素，维护当前元素的最高有效位，当前元素的1个数等于该元素减去最高有效位的值对应的元素加1
     * 技巧：x&(x-1)等于x去掉最后一个1的值，可用来判断1的个数
     * 方法二：元素x的1个数等于元素x/2(x>>1)的1个数加上x%2的值（==x&1）
     * 方法三：元素x的1个数等于元素x去掉最后一个1（x&(x-1)）的1个数加一
     *
     * @param num
     * @return
     */
    public int[] countBits(int num) {
        int[] res = new int[num + 1];
        int highBit = 0;
        for (int i = 1; i <= num; i++) {
            if ((i & (i - 1)) == 0) {
                highBit = i;
            }
            res[i] = res[i - highBit] + 1;
        }

        return res;
    }

    /**
     * 解题思路：双下标转为单下标（对第一个下标升序排序，对第二个下标降序排序）
     * 最长单调递增子序列（对第一个下标升序排序）
     * 防止第一个下标引起的错误（对第二个下标降序排序）
     *
     * @param envelopes
     * @return
     */
    public int maxEnvelopes(int[][] envelopes) {
        int res = 0;
        int n = envelopes.length;
        int[] f = new int[n];
        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0]) {
                    return o1[0] - o2[0];
                } else {
                    return o2[1] - o1[1];
                }
            }
        });
        Arrays.fill(f, 1);
        for (int i = 0; i < envelopes.length; i++) {
            int max = 1;
            for (int j = 0; j < i; j++) {
                if (envelopes[j][1] < envelopes[i][1]) {
                    max = Math.max(max, f[j] + 1);
                }
            }
            f[i] = max;
        }

        for (int i = 0; i < n; i++) {
            res = Math.max(res, f[i]);
        }

        return res;

    }

    private boolean[][] f;
    private List<List<String>> res;
    private List<String> ans;

    /**
     * 解题思路：回溯法+动态规划预处理
     * 动态规划预处理：使用二维数组存储对应下标i、j组成的字符串是否为回文串
     * 若i>j，则为回文串
     * 若i=j，则为回文串
     * 若i+1=j且s[i]=s[j]，则为回文串
     * 若i+1<j且s[i]=s[j]、f[i+1][j-1]=true，则为回文串
     * 回溯法：从下标i开始搜索，长度j依次递增
     * 搜索时，若该子串为回文串，则添加该子串到当前集，并从下标j+1开始搜索，即当前集继续搜索是否能构成结果集，搜索完毕后，将该子串从当前集删除
     * 每次搜索到字符串长度时，将结果添加进结果集
     *
     * @param s
     * @return
     */
    public List<List<String>> partition(String s) {
        int n = s.length();
        f = new boolean[n][n];
        ans = new ArrayList<>();
        res = new ArrayList<>();

        for (int l = 0; l < n; l++) {
            for (int i = 0; i < n; i++) {
                int j = i + l;
                if (j >= n) {
                    break;
                }
                if (j == i) {
                    f[i][j] = true;
                    continue;
                }
                boolean equals = s.charAt(i) == s.charAt(j);
                if (j - i == 1) {
                    f[i][j] = equals;
                } else {
                    f[i][j] = f[i + 1][j - 1] && equals;
                }
            }
        }

        dfs(s, 0);

        return res;
    }

    private void dfs(String s, int i) {
        if (i == s.length()) {
            res.add(new ArrayList<>(ans));
            return;
        }
        for (int j = i; j < s.length(); j++) {
            if (f[i][j]) {
                ans.add(s.substring(i, j + 1));
                dfs(s, j + 1);
                ans.remove(ans.size() - 1);
            }
        }
    }


    /**
     * 解题思路：使用动态规划，首先，使用二维数组存储下标i到j的子串是否为回文串
     * 接着，使用一维数组存储下标0到i的子串分割的最少次数
     * 对于下标0到i的子串，先判断其是否为回文串，若是，则分割次数为0，若不是，则使用状态转移方程，找到子串分割最小值
     * 状态转移方程：res[i] = min(res[j] + 1) (if f[j+1][i] is true)
     *
     * @param s
     * @return
     */
    public int minCut(String s) {
        int n = s.length();
        f = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(f[i], true);
        }
        for (int l = 0; l < n; l++) {
            for (int i = 0; i < n; i++) {
                int j = i + l;
                if (j >= n) {
                    continue;
                }
                if (l == 0) {
                    f[i][j] = true;
                    continue;
                }
                boolean flag = s.charAt(i) == s.charAt(j);
                if (flag && f[i + 1][j - 1]) {
                    f[i][j] = true;
                } else {
                    f[i][j] = false;
                }
            }
        }

        int[] res = new int[n];
        for (int i = 1; i < n; i++) {
            if (f[0][i] == true) {
                res[i] = 0;
                continue;
            }
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < i; j++) {
                if (f[j + 1][i] == true) {
                    min = Math.min(min, res[j] + 1);
                }
            }
            res[i] = min;
        }

        return res[n - 1];
    }

    /**
     * 解题思路：若a是整除区间的最小值的约数或整除区间的最小值的倍数，则a可加入该区间
     * 1. 对数组进行排序
     * 2. 两层循环，找出以元素i为最大整数的区间大小
     * 3. 找到最大区间值
     * 4. 逆序寻找该区间的每个元素
     *
     * @param nums
     * @return
     */
    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0) {
                    dp[i] = Math.max(dp[i], (dp[j] + 1));
                }
            }
        }

        List<Integer> ret = new ArrayList<>();
        int maxSize = 0;
        int maxIndex = 0;
        for (int i = 0; i < n; i++) {
            if (maxSize < dp[i]) {
                maxSize = dp[i];
                maxIndex = i;
            }
        }
        if (maxSize == 0) {
            return ret;
        }
        int i = maxIndex;
        while (maxSize > 0) {
            ret.add(nums[i]);
            maxSize--;
            if (maxSize == 0) {
                break;
            }
            int j = i - 1;
            while (((nums[i] % nums[j]) != 0) || (dp[j] != maxSize)) {
                j--;
            }
            i = j;
        }

        return ret;
    }

    private int memo[][][];
    String s1, s2;

    /**
     * 解题思路：若s1和s2相等，则互为扰乱字符串
     * 若长度不等，或者长度相等但其中某一字符个数不等，则s1和s2不为扰乱字符串
     * 若以上情况都不是，则对s1和s2进行切割
     * 从长度1到长度n-1进行判断
     * 1.s1和s2长度为i互为扰乱字符串且s1和s2长度为n-i互为扰乱字符串，则s1和s2互为扰乱字符串
     * 2.交换s1的两个子串，1和s2长度为i互为扰乱字符串且s1和s2长度为n-i互为扰乱字符串，则s1和s2互为扰乱字符串
     * 若都不是，则s1和s2不为扰乱字符串
     *
     * @param s1
     * @param s2
     * @return
     */
    public boolean isScramble(String s1, String s2) {
        if (s1.equals(s2)) {
            return true;
        }
        if (s1.length() != s2.length()) {
            return false;
        }
        int length = s1.length();
        memo = new int[length][length][length + 1];
        this.s1 = s1;
        this.s2 = s2;
        return dfs(0, 0, length);
    }

    private boolean dfs(int i, int j, int length) {
        if (memo[i][j][length] != 0) {
            return memo[i][j][length] == 1;
        }
        if (s1.substring(i, i + length).equals(s2.substring(j, j + length))) {
            memo[i][j][length] = 1;
            return true;
        }
        if (!isSimilar(i, j, length)) {
            memo[i][j][length] = -1;
            return false;
        }

        for (int k = 1; k < length; k++) {
            if (dfs(i, j, k) && dfs((i + k), (j + k), (length - k))) {
                return true;
            }
            if (dfs(i, (j + length - k), k) && dfs((i + k), j, (length - k))) {
                return true;
            }
        }
        memo[i][j][length] = -1;
        return false;
    }

    private boolean isSimilar(int i, int j, int length) {
        Map<Character, Integer> freq = new HashMap<>();
        for (int k = 0; k < length; k++) {
            char c = s1.charAt(i + k);
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }
        for (int k = 0; k < length; k++) {
            char c = s1.charAt(i + k);
            freq.put(c, freq.getOrDefault(c, 0) - 1);
        }
        for (Map.Entry<Character, Integer> entry :
                freq.entrySet()) {
            int value = entry.getValue();
            if (value != 0) {
                return false;

            }
        }

        return true;
    }

    /**
     * 解题思路：使用动态规划进行状态转移，若元素i是数字1-9，则结果加上元素i-1的数量
     * 若元素i-1非零且元素（i-1，i）小于等于26，则结果加上元素i-2的数量
     * 优化：1. 使用元素0作为前元素判断，其值为空
     * 2. 使用三个元素代替元素i-2，i-1，i节省辅助数组空间
     * 注意：s 只包含数字，并且可能包含前导零
     *
     * @param s
     * @return
     */
    public int numDecodings(String s) {
        int a = 1;
        int b = 1;
        int c = 0;
        for (int i = 1; i < (s.length() + 1); i++) {
            c = 0;
            if (s.charAt(i - 1) != '0') {
                c += b;
            }
            if ((i > 1) && (s.charAt(i - 2) != '0') && (((s.charAt(i - 2) - '0') * 10 + (s.charAt(i - 1) - '0') <= 26))) {
                c += a;
            }
            a = b;
            b = c;
        }
        return c;
    }

    public int combinationSum4(int[] nums, int target) {
        int n = nums.length;
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int j = 0; j < n; j++) {
                if (nums[j] <= i) {
                    dp[i] += dp[i - nums[j]];
                }
            }
        }

        return dp[target];
    }

    private Boolean[][] rec;

    public boolean canCross(int[] stones) {

        int n = stones.length;
        rec = new Boolean[n][n];
        return dfs2(stones, 0, 0);


    }

    private boolean dfs2(int[] stones, int i, int dist) {
        if (i == stones.length - 1) {
            return true;
        }
        if (rec[i][dist] != null) {
            return rec[i][dist];
        }

        for (int j = (dist - 1); j <= (1 + dist); j++) {
            if (j > 0) {
                int k = Arrays.binarySearch(stones, i + 1, stones.length, j + stones[i]);
                if (k >= 0 && dfs2(stones, k, j)) {
                    return rec[i][dist] = true;
                }
            }
        }

        return rec[i][dist] = false;
    }


    public int minCost(int[] houses, int[][] cost, int m, int n, int target) {
        for (int i = 0; i < m; i++) {
            houses[i]--;
        }

        int[][][] dp = new int[m][n][target];

        int max = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(dp[i][j], max);
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (houses[i] != -1 && houses[i] != j) {
                    continue;
                }

                for (int k = 0; k < target; k++) {
                    for (int j0 = 0; j0 < n; j0++) {
                        if (j0 == j) {
                            if (i == 0) {
                                if (k == 0) {
                                    dp[i][j][k] = 0;

                                }
                            } else {
                                dp[i][j][k] = Math.min(dp[i][j][k], dp[i - 1][j][k]);

                            }
                        } else {
                            if (i != 0 && k != 0) {
                                dp[i][j][k] = Math.min(dp[i][j][k], dp[i - 1][j0][k - 1]);

                            }
                        }
                    }
                    if (dp[i][j][k] != max && houses[i] == -1) {
                        dp[i][j][k] += cost[i][j];
                    }
                }
            }
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            ans = Math.min(ans, dp[m - 1][i][target - 1]);
        }

        return ans == max ? -1 : ans;
    }

    /**
     * 解题思路：
     * 方法一：使用数组，数组下标为数字的值，其值为该数字的总和，对其进行选择，
     * 方法：rob dp[i] = max(dp[i-2] + v[i], dp[i-1])
     * 方法二：对数组进行排序，将数组分为多个连续子数组
     * 对于每个连续子数组，使用方法rob选择该子数组的最大值
     *
     * @param nums
     * @return
     */
    public int deleteAndEarn(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int max = 0;
        int n = nums.length;
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, nums[i]);
            if (nums[i] < 0) {
                return -1;
            }
        }
        int[] sum = new int[max + 1];
        for (int i = 0; i < n; i++) {
            sum[nums[i]] += nums[i];
        }

//        int[] res = new int[max + 3];
        int s = 0;
        int m = 0;
        int l = 0;
        for (int i = 0; i < (max + 1); i++) {
            l = Math.max(s + sum[i], m);
            s = m;
            m = l;
        }


        return l;
    }

    /**
     * 解题思路：使用动态规划，第i步第j位置的方案数dp[i][j] = dp[i-1][j-1] + dp[i-1][j] + dp[i-1][j+1]
     * 优化： 1.使用两个数组存储相邻步数
     * 2.数组大小为min(step/2+1, arrlen)
     *
     * @param steps
     * @param arrLen
     * @return
     */
    public int numWays(int steps, int arrLen) {
        int mould = 1000000007;
        int n = Math.min(steps / 2 + 1, arrLen);
        int[] ans = new int[n];
        ans[0] = 1;
        int[] bns = new int[n];
        for (int i = 0; i < steps; i++) {
            for (int j = 0; j < n; j++) {
                bns[j] = ans[j];
                if (j > 0) {
                    bns[j] = (bns[j] + ans[j - 1]) % mould;
                }
                if (j < (n - 1)) {
                    bns[j] = (bns[j] + ans[j + 1]) % mould;
                }
            }
            ans = Arrays.copyOf(bns, arrLen);
        }

        return ans[0];
    }

    /**
     * 解题思路：使用动态规划
     * 转移方程： 若s[i] == s[j]，则区间（i,j）与区间（i,j-1）的填充次数相同（最早填充元素i）
     * 若s[i] ！= s[j]，则区间（i，j）的填充次数等于区间内所有子区间（（i,k）,（k+1,j））次数的最小值
     * 技巧：所求dp数组为正三角数组，为方便计算，可从i升序，j降序的顺序计算
     *
     * @param s
     * @return
     */
    public int strangePrinter(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        for (int i = (n - 1); i >= 0; i--) {

            for (int j = (i + 1); j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i][j - 1];
                    continue;
                }
                int min = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    min = Math.min(min, dp[i][k] + dp[k + 1][j]);
                }
                dp[i][j] = min;
            }

        }

        return dp[0][n - 1];
    }

    /**
     * 解题思路：前缀和+哈希表优化
     * 假设二维数组行为n，列为m
     * 枚举所有行的上下界，对于给定的行（i,j），用数组sum存储对应列的和
     * 数组sum中的元素k表示第k列从第i行到第j行之和
     * 问题转化为求一维数组中和为target的连续子数组的个数
     * 使用前缀和+哈希表优化可将其复杂度将为O（m）
     * 将前缀和存储到哈希表中，若表中存在键为（pre[k] - target），则其值为以元素K为尾，值为target的子数组个数
     * 同时把pre[k]存入哈希表
     *
     * @param matrix
     * @param target
     * @return
     */
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int n = matrix.length;
        int m = matrix[0].length;
        int count = 0;


        for (int i = 0; i < n; i++) {
            int[] sum = new int[m];
            for (int j = i; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    sum[k] += matrix[j][k];
                }
                Map<Integer, Integer> map = new HashMap<>();
                int total = 0;
                map.put(0, 1);
                for (int k = 0; k < m; k++) {
                    total += sum[k];
                    if (map.get(total - target) != null) {
                        count += map.get(total - target);
                    }
                    map.put(total, map.getOrDefault(total, 0) + 1);

                }
            }
        }

        return count;
    }

    /**
     * 解题思路：
     * 如果两个整数m、n满足n-m能被k整除，那么n和m对k同余
     * 将对应前缀和的存入哈希表中，边存边判断，若两个前缀和的余数相等并且间隔大于等于2，则找到答案
     * 时间复杂度为o(n)，空间复杂度为o(k)
     *
     * @param nums
     * @param k
     * @return
     */
    public boolean checkSubarraySum(int[] nums, int k) {
        int n = nums.length;
        int total = 0;

        Map<Integer, Integer> map = new HashMap<>();
        map.put(total, 0);

        for (int i = 1; i <= n; i++) {
            total += nums[i - 1];
            total %= k;
            if (map.containsKey(total)) {
                if ((i - map.get(total)) >= 2) {
                    return true;
                }
            } else {
                map.put(total, i);
            }
        }

        return false;
    }

    public int findMaxLength(int[] nums) {

        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        int length = 0;
        map.put(0, -1);
        for (int i = 0; i < nums.length; i++) {
            sum += (nums[i] == 0 ? -1 : 1);
            if (map.containsKey(sum)) {
                length = Math.max(length, (i - map.get(sum)));
            } else {
                map.put(sum, i);
            }
        }

        return length;
    }

    /**
     * 解题思路：动态规划
     * 使用数组的dp[i][j][k]存储前i个字符串在0的个数为j、1的个数为k的情况下可获得最大的子集
     * 当i=0时，dp[i][j][k] = 0
     * 当i>0时，记当前字符串0的个数为s，1的个数为t，则
     * 若s > j || t > k，dp[i][j][k] = dp[i-1][j][k]
     * 否则，dp[i][j][k] = dp[i-1][j][k]、dp[i-1][j-s][k-t]最大值
     * 结果为dp[l][m][n]的值
     * 优化：dp使用二维数组、从后往前查找，且当j < s || k < t时，无需查找，使用上一个字符串得到的结果
     *
     * @param strs
     * @param m
     * @param n
     * @return
     */
    public int findMaxForm(String[] strs, int m, int n) {
        int l = strs.length;
        int[][] dp = new int[m + 1][n + 1];


        for (int i = 1; i <= l; i++) {
            int s = 0;
            int t = 0;
            for (int j = 0; j < strs[i - 1].length(); j++) {
                if (strs[i - 1].charAt(j) == '0') {
                    s++;
                } else {
                    t++;
                }
            }

            for (int j = m; j >= s; j--) {
                for (int k = n; k >= t; k--) {
                    dp[j][k] = Math.max(dp[j][k], dp[j - s][k - t] + 1);

                }
            }
        }

        return dp[m][n];
    }

    /**
     * 解题思路：在数组中选取一部分元素为正数，另一部分元素为负数，使得其总和为target值，求其方案数
     * 设负数总和为neg，总和为sum，则sum - neg - neg = target
     * neg = (sum - target) / 2
     * 题目保证数组元素为非负整数，若neg为负数或非整数，则方案数为0
     * 转化为背包问题，从数组中取出部分元素，使其总和为(sum - target) / 2，求其方案数
     * dp[i][j]:从前i个元素中取部分元素，其总和为j的方案数
     * 当j=0时（总和为零），dp[i][0] = 1
     * 当i=0、j!=0时，dp[0][j] = 0
     * 元素个数从少到多遍历
     * 总和从小到大遍历
     * 状态转移方程：
     * 当前遍历的元素值num[i-1]大于总和j:dp[i][j] = dp[i-1][j]
     * 当前遍历的元素值num[i-1]小于等于总和j:dp[i][j] = dp[i-1][j] + dp[i-1][j-num[i-1]]
     * <p>
     * 优化:
     * 把二维数组转为一维滑动数组
     * 元素个数从少到多遍历
     * 总和从大到num[i]遍历（总和小于num[i]时，其值与上次得到的值相同）(需要用到前面的元素，逆序遍历防止覆盖)
     * 状态转移方程：
     * dp[j] += dp[j-num[i-1]]
     *
     * @param nums
     * @param target
     * @return
     */
    public int findTargetSumWays(int[] nums, int target) {
        int n = nums.length;

        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
        }


        int neg = sum - target;
        if (neg < 0 || (neg % 2) != 0) {
            return 0;
        }


        neg /= 2;


        int[] dp = new int[neg + 1];

        dp[0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = neg; j >= Math.max(0, nums[i - 1]); j--) {
                dp[j] = dp[j] + dp[j - nums[i - 1]];
            }

        }


        return dp[neg];
    }

    /**
     * 解题思路：每次两两碰撞，直到剩下一个石头或无石头，保证石头重量最小
     * 相当于在所有石头中，取一部分石头为正数。一部分石头为负数，两者相加，使得所得值为最小非负数
     * 该值即为所求结果（可证明合理性）
     * 设取负数为neg，总和为sum
     * 则最小值 = sum - neg - neg = sum - (2 * neg)
     * 要保证该值最小，则neg需在不超过（sum / 2）的范围内取最大值
     * 转化为背包问题，从数组中取出重量不超过（sum/2）的最大值
     *
     * @param stones
     * @return
     */
    public int lastStoneWeightII(int[] stones) {
        int n = stones.length;

        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += stones[i];
        }

        int neg = sum / 2;

        boolean[] dp = new boolean[neg + 1];

        dp[0] = true;

        for (int i = 1; i <= n; i++) {
            for (int j = neg; j >= stones[i - 1]; j--) {
                dp[j] |= dp[j - stones[i - 1]];

            }
        }

        int max = 0;

        for (int i = neg; i >= 0; i--) {
            if (dp[i]) {
                max = i;
                break;
            }

        }

        return (sum - 2 * max);

    }

    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        int m = group.length;
        int[][] dp = new int[n + 1][minProfit + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }

        int va = 1000000000 + 7;

        for (int i = 1; i <= m; i++) {
            int cost = group[i - 1];
            int pro = profit[i - 1];
            for (int j = n; j >= cost; j--) {
                for (int k = minProfit; k >= 0; k--) {
                    if (k > pro) {
                        dp[j][k] += dp[j - cost][k - pro];
                    } else {
                        dp[j][k] += dp[j - cost][0];
                    }

                    dp[j][k] %= va;

                }
            }


        }

        return dp[n][minProfit];
    }

    /**
     * 解题思路：
     * 先遍历coin再遍历金额
     * 在计算dp[i]的值时，可以确保金额之和等于i的硬币面额的顺序，由于顺序确定，因此不会重复计算不同的排列。
     *
     * @param amount
     * @param coins
     * @return
     */
    public int change(int amount, int[] coins) {
        int n = coins.length;
        Arrays.sort(coins);
        int[] dp = new int[amount + 1];
        dp[0] = 1;

        for (int i = 0; i < n; i++) {

            int val = coins[i];
            for (int j = coins[i]; j <= amount; j++) {
                dp[j] += dp[j - val];
            }
        }

        return dp[amount];
    }

    /**
     * 解题思路：动态规划
     * dp[i]:第i个值至少需要多少个完全平方数相加
     * 从小到大遍历完全平方数，更新dp[n]需要的数目
     * 两层循环：
     * 1.i从1到i*i<=n
     * 2.j从i*i到n
     * 每次dp[j]从原先的值和dp[j - i*i]之间选最小值
     *
     * @param n
     * @return
     */
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, n);

        dp[0] = 0;

        for (int i = 1; (i * i) <= n; i++) {
            int val = i * i;
            for (int j = val; j <= n; j++) {
                dp[j] = Math.min(dp[j - val] + 1, dp[j]);
            }
        }

        return dp[n];
    }

    /**
     * 解题思路：
     * 1.使用动态规划求出符合target值的最大位数
     * 2.在每次选择该位时，做标记，随后获得最大位数后，从后往前查找，将相应下标加入字符串中
     * @param cost
     * @param target
     * @return
     */
    public String largestNumber(int[] cost, int target) {
        int n = cost.length;
        if(target == 0) {
            return "0";
        }

        int[] dp = new int[target + 1];

        dp[0] = 0;
        for (int i = 1; i <= target; i++) {
            dp[i] = Integer.MIN_VALUE;
        }

        for (int i = 1; i <= n; i++) {
            int val = cost[i-1];
            for (int j = val; j <= target; j++) {
                dp[j] = Math.max(dp[j], dp[j-val] + 1);

            }

        }

        if(dp[target] <= 0) {
            return "0";
        }


        StringBuffer str = new StringBuffer();
        for(int i = 8, j = target; i>= 0; i--) {
            for(int c = cost[i]; j>=c && dp[j] == (dp[j-c] + 1); j-=c) {
                str.append(i+1);
            }
        }

        return str.toString();

    }

    /**
     * 解题思路：
     * 递归：每次一方可以选择任意一边，记录双方的石头总数之差，总共有2‘n种可能
     * 动态规划：使用dp数组存储从下标i-j当前方与另一方的石头总数之差
     * i>j：无意义
     * i=j：值为piles[i]
     * i<j：当前方可选择i或者j，取最大值
     * 优化：使用一维数组
     * @param piles
     * @return
     */
    public boolean stoneGame(int[] piles) {
        int n = piles.length;
        int[] dp = new int[n];

//        for (int i = 0; i < n; i++) {
//            dp[i][i] = piles[i];
//        }

        for (int i = (n-1); i >= 0; i--) {
            for (int j = (i + 1); j < n; j++) {

                int max1 = (i+1) == j ? piles[i+1]:dp[j];
                int max2 = (j-1) == i ? piles[i]:dp[j-1];

                dp[j] = Math.max(piles[i] - max1, piles[j] - max2);
            }
        }
//        for (int i = (n-1); i >= 0; i--) {
//            for (int j = (i+1); j < n; j++) {
//                dp[i][j] = Math.max(piles[i] - dp[i+1][j], piles[j] - dp[i][j-1]);
//            }
//        }

        if(dp[n-1] > 0) {
            return true;
        }else {
            return false;
        }
    }

    public int numSubarraysWithSum(int[] nums, int goal) {
        int n = nums.length;
        int[] sum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + nums[i-1];
        }

        int ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        for (int i = 1; i <= n; i++) {
            int a = sum[i];
            Integer te = map.get(a - goal);
            ans = ans + (te == null ? 0 : te);
            map.put(a, map.getOrDefault(a, 0) + 1);
        }


        return ans;
    }

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> list = new ArrayList<>();
        if(numRows <= 0) {
            return list;
        }
        List<Integer> l1 = new ArrayList<>();
        l1.add(1);
        list.add(l1);
        if(numRows == 1) {
            return list;
        }

        for (int i = 1; i < numRows; i++) {
            List<Integer> l2 = new ArrayList<>();
            l2.add(1);

            for (int j = 0; j < (l1.size() - 1); j++) {
                l2.add(l1.get(j) + l1.get(j+1));
            }

            l2.add(1);

            list.add(l2);
            l1 = l2;

        }

        return list;
    }

    /**
     * 解题思路：差分数组＋前缀和
     * @param ranges
     * @param left
     * @param right
     * @return
     */
    public boolean isCovered(int[][] ranges, int left, int right) {
        int n = ranges.length;

        int max = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            max = Math.max(max, ranges[i][1]);
        }

        int[] diff = new int[max + 1];

        for (int i = 0; i < n; i++) {
            diff[ranges[i][0]]++;
            if(ranges[i][1] < max) {
                diff[ranges[i][1] + 1]--;
            }
        }

        int sum = 0;
        for (int i = 1; i <= max; i++) {
            sum = sum + diff[i];
            if(i >= left && i <= right && (sum == 0)) {
                return false;
            }
        }

        return true;
    }

    public int tribonacci(int n) {
        if(n == 0 || n == 1){
            return n;
        }

        if(n == 2) {
            return 1;
        }


        int a = 0;
        int b = 1;
        int c = 1;
        int d = 0;

        while (n > 2) {
            d = a + b + c;
            a = b;
            b = c;
            c = d;
            n--;
        }

        return d;
    }

    /**
     * 解题思路：
     * 动态规划
     * 使用三维数组存储对应天数 A的个数 连续L的个数
     * 第i天的状态由第i-1天的状态获得
     * @param n
     * @return
     */
    public int checkRecord(int n) {
        int[][] dp = new int[2][3];

        dp[0][0] = 1;

        int mod = 1000000007;

        for (int i = 1; i <= n; i++) {
            int a = 0;
            int b = 0;
            for (int k = 0; k < 3; k++) {
                a += dp[0][k];
                a %= mod;
            }
            b = a;
            for (int k = 0; k < 3; k++) {
                b += dp[1][k];
                b %= mod;
            }

            for (int j = 0; j < 2; j++) {
                for (int k = 2; k >= 1; k--) {
                    dp[j][k] = dp[j][k-1];
                    dp[j][k] %= mod;
                }
            }

            dp[0][0] = a;
            dp[1][0] = b;

        }

        int res = 0;
        for (int j = 0; j < 2; j++) {
            for (int k = 0; k < 3; k++) {
                res += dp[j][k];
                res %= mod;
            }
        }

        return res;
    }

    /**
     * 解题思路：动态规划
     * 使用动态数组存储第i次遍历之后，所有单元格的路径数量
     * 若第i次，某单元格的路径数量为a，此时判断其相邻四个单元格，若出界，则对应出边界的路径数量加a
     * 若不出界，则在第i+1次遍历后，相邻的每个单元格的路径数量加a
     *
     * @param m
     * @param n
     * @param maxMove
     * @param startRow
     * @param startColumn
     * @return
     */
    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        int[][] dp = new int[m][n];

        dp[startRow][startColumn] = 1;
        int res = 0;
        int mod = 1000000007;

        for (int i = 1; i <= maxMove; i++) {
            int[][] dpNew = new int[m][n];
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < n; k++) {
                    if(dp[j][k] != 0) {
                        if((k-1) >= 0) {
                            dpNew[j][k-1] += dp[j][k];
                            dpNew[j][k-1] %= mod;
                        }else {
                            res += dp[j][k];
                            res %= mod;
                        }

                        if((k+1) < n) {
                            dpNew[j][k+1] += dp[j][k];
                            dpNew[j][k+1] %= mod;
                        }else {
                            res += dp[j][k];
                            res %= mod;
                        }

                        if((j-1) >= 0) {
                            dpNew[j-1][k] += dp[j][k];
                            dpNew[j-1][k] %= mod;
                        }else {
                            res += dp[j][k];
                            res %= mod;
                        }

                        if((j+1) < m) {
                            dpNew[j+1][k] += dp[j][k];
                            dpNew[j+1][k] %= mod;
                        }else {
                            res += dp[j][k];
                            res %= mod;
                        }
                    }
                }
            }
            dp = dpNew;
        }
        return res;
    }

    public int sumOddLengthSubarrays(int[] arr) {
        int[] dp = new int[arr.length + 1];
        int n = arr.length;
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1] + arr[i - 1];
        }

        int sum = 0;

        for(int i = 1; i <= n; i += 2) {
            for (int j = i; j <= n; j += 1) {
                sum += (dp[j] - dp[j - i]);

            }
        }

        return sum;
    }

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[src] = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= k; i++) {
            int[] temp = new int[n];
            Arrays.fill(temp, Integer.MAX_VALUE);
            temp[src] = 0;

            for (int j = 0; j < flights.length; j++) {
                int a = flights[j][0];
                int b = flights[j][1];
                int cost = flights[j][2];

                if(dp[a] == Integer.MAX_VALUE) {
                    continue;
                }

                temp[b] = Math.min(temp[b], dp[a] + cost);



            }
            dp = temp;
            min = Math.min(min, dp[dst]);
        }

        return (min == Integer.MAX_VALUE ? -1: min);
    }

    public int fib(int n) {
        if(n == 1 || n == 0) {
            return  n;
        }

        int a = 0;
        int b = 1;
        int c = 0;
        int mod = 1000000007;
        while (n >= 2) {
            c = (a + b) % mod;

            a = b;

            b = c;


            n--;
        }

        return c;


    }

    public int minSteps(int n) {
        int[] dp = new int[n+1];

        Arrays.fill(dp, Integer.MAX_VALUE);

        dp[1] = 0;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; (double)j <= Math.sqrt((double)i); j++) {
                if(i % j == 0) {
                    dp[i] = Math.min(dp[i], dp[j] + i / j);
                    dp[i] = Math.min(dp[i], dp[i/j] + j);
                }
            }
        }

        return dp[n];
    }

    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        int []dp = new int[n];
        int []cnt = new int[n];

        int max = 0;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            cnt[i] = 1;
            for (int j = 0; j < i; j++) {
                if(nums[j] < nums[i]) {
                    if(dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        cnt[i] = cnt[j];
                    }else if(dp[j] + 1 == dp[i]){
                        cnt[i] += cnt[j];
                    }
                }
                max = Math.max(max, dp[i]);
            }
        }

        int res = 0;
        for (int i = 0; i < n; i++) {
            if(dp[i] == max) {
                res += cnt[i];
            }
        }

        return res;
    }

    public int countVowelPermutation(int n) {
        long a1 = 1, a2 = 0;
        long e1 = 1, e2 = 0;
        long i1 = 1, i2 = 0;
        long o1 = 1, o2 = 0;
        long u1 = 1, u2 = 0;
        long mod = 1000000007;

        for (int i = 1; i < n; i++) {
            e2 += (a1 + i1)%mod;
            a2 += (e1 + i1 + u1)%mod;
            i2 += (e1 + o1)%mod;
            o2 += i1;
            u2 += (i1 + o1)%mod;

            a1 = a2;
            e1 = e2;
            i1 = i2;
            o1 = o2;
            u1 = u2;
            a2 = 0;
            e2 = 0;
            i2 = 0;
            o2 = 0;
            u2 = 0;
        }

        long res = 0;
        res = (res + a1) % mod;
        res = (res + e1) % mod;
        res = (res + i1) % mod;
        res = (res + o1) % mod;
        res = (res + u1) % mod;
        return (int)res;




    }

}
