package dynamicprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

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
                if(j >= n) {
                    continue;
                }
                if(l == 0) {
                    f[i][j] = true;
                    continue;
                }
                boolean flag = s.charAt(i) == s.charAt(j);
                if(flag && f[i+1][j-1]) {
                    f[i][j] = true;
                }else {
                    f[i][j] = false;
                }
            }
        }

        int[] res = new int[n];
        for (int i = 1; i < n; i++) {
            if(f[0][i] == true) {
                res[i] = 0;
                continue;
            }
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < i; j++) {
                if(f[j+1][i] == true) {
                    min = Math.min(min, res[j] + 1);
                }
            }
            res[i] = min;
        }

        return res[n-1];
    }




}
