package dynamicprogramming;

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

        if (nums == null || nums.length == 0) {
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
    }

    /**
     * 解题思路：
     * 方法一：使用动态规划状态转移方程 count[i] = Max(count[0]..count[i-1]) 前提是前面元素小于该元素
     * 方法二：使用贪心算法+二分查找：维护最小值数组 若元素大于该数组的元素，则加入，否则替换里面的元素
     *
     * @param nums
     * @return
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
                if(j == i) {
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
                if(nums[i] % nums[j] == 0) {
                    dp[i] = Math.max(dp[i], (dp[j] + 1));
                }
            }
        }

        List<Integer> ret = new ArrayList<>();
        int maxSize = 0;
        int maxIndex = 0;
        for (int i = 0; i < n; i++) {
            if(maxSize < dp[i]) {
                maxSize = dp[i];
                maxIndex = i;
            }
        }
        if(maxSize == 0) {
            return ret;
        }
        int i = maxIndex;
        while(maxSize > 0) {
            ret.add(nums[i]);
            maxSize--;
            if(maxSize == 0) {
                break;
            }
            int j = i - 1;
            while(((nums[i] % nums[j]) != 0) || (dp[j]!=maxSize)) {
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

}
