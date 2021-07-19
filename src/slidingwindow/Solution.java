package slidingwindow;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Solution {
    public int maxTurbulenceSize(int[] arr) {
        int left = 0;
        int max = 0;
        int right = 0;
        while (right < arr.length - 1) {
            if (left == right) {
                if (arr[right] != arr[right + 1]) {
                    max = Math.max(max, right - left + 1);
                    right++;

                } else {
                    left++;
                    right++;
                }
            } else {
                if (arr[right] > arr[right - 1] && arr[right] > arr[right + 1]) {
                    max = Math.max(max, (right - left + 2));
                    right++;
                } else if (arr[right] < arr[right - 1] && arr[right] < arr[right + 1]) {
                    max = Math.max(max, (right - left + 2));
                    right++;
                } else {
                    left = right;
                }
            }
        }

        return max;
    }

    /**
     * 解题关键：把恰好K个转换成最多K个减去最多K-1个
     * 解题思路：使用双指针，保证滑动窗口内最多K个整数的情况下，增加新元素得到的区间个数
     *
     * @param A
     * @param K
     * @return
     */
    public int subarraysWithKDistinct(int[] A, int K) {
        return atMostKDistinct(A, K) - atMostKDistinct(A, K - 1);

    }

    private int atMostKDistinct(int[] A, int K) {
        Map<Integer, Integer> map = new HashMap<>();
        int num = 0;
        int left = 0;
        int right = 0;
        while (right < A.length) {
            map.put(A[right], map.getOrDefault(A[right], 0) + 1);
            while (map.size() > K) {
                int value = map.get(A[left]) - 1;
                if (value == 0) {
                    map.remove(A[left]);
                } else {
                    map.put(A[left], value);
                }
                left++;
            }
            right++;
            num += right - left;
        }

        return num;
    }

    public boolean checkInclusion(String s1, String s2) {
        int[] cnt = new int[26];
        int n = s1.length();
        int m = s2.length();
        if (n > m) {
            return false;
        }
        for (int i = 0; i < n; i++) {
            --cnt[s1.charAt(i) - 'a'];
        }

        int left = 0;
        int right = 0;
        while (right < m) {
            int ch = s2.charAt(right) - 'a';
            ++cnt[ch];
            while (cnt[ch] > 0) {
                --cnt[s2.charAt(left) - 'a'];
                left++;
            }
            if (right - left + 1 == n) {
                return true;
            }
            right++;
        }

        return false;
    }

    /**
     * 解题思路：移动右指针，把0转为1，计算前缀和，若差值大于K，则移动左指针
     *
     * @param A
     * @param K
     * @return
     */
    public int longestOnes(int[] A, int K) {
        int left = 0;
        int right = 0;
        int k = K;
        int max = 0;
        if (K == 0) {
            while (right < A.length) {
                if (A[right] == 0) {
                    right++;
                    left = right;
                } else {
                    max = Math.max(max, (right - left + 1));
                    right++;
                }

            }
        } else {
            while (right < A.length) {
                if (A[right] == 0) {
                    k--;
                }
                if (k >= 0) {
                    max = Math.max(max, (right - left + 1));
                } else {
                    while (k < 0) {
                        if (A[left] == 0) {
                            k++;
                        }
                        left++;
                    }
                }


                right++;
            }
        }
        return max;

    }

    /**
     * 解题思路：对于每个元素，根据它的值判断是否需要翻转，每次翻转为长度K的区间
     * 优化：不需要对元素翻转，用一个数记录元素翻转次数，若当前元素与计数值之和为偶数，则计数值加一，元素加K位置翻转次数减一（使用差分数组）
     *
     * @param A
     * @param K
     * @return
     */
    public int minKBitFlips(int[] A, int K) {
        int ans = 0;
        int n = A.length;
        int[] diff = new int[n + 1];
        int record = 0;
        for (int i = 0; i < n; i++) {
            record += diff[i];
            if ((record + A[i]) % 2 == 0) {
                if ((i + K) > n) {
                    return -1;
                }
                ++ans;
                record++;
                diff[i + K]--;
            }
        }
        return ans;
    }

    /**
     * 解题思路：获取数组频数最大值，使用滑动窗口，移动右指针知道窗口内出现频数最大值，移动左指针，记录最小连续子数组，直到频数变小
     *
     * @param nums
     * @return
     */
    public int findShortestSubArray(int[] nums) {
        int maxLength = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        for (Integer num :
                map.keySet()) {
            maxLength = Math.max(maxLength, map.get(num));
        }

        int left = 0;
        int right = 0;
        Map<Integer, Integer> map2 = new HashMap<>();
        int minLength = Integer.MAX_VALUE;
        while (right < nums.length) {
            Integer value = map2.getOrDefault(nums[right], 0) + 1;
            map2.put(nums[right], value);
            while (value == maxLength) {
                minLength = Math.min(minLength, (right - left + 1));
                Integer value2 = map2.get(nums[left]) - 1;
                if (value2 == 0) {
                    map2.remove(nums[left]);
                } else {
                    map2.put(nums[left], value2);
                }
                if (nums[left] == nums[right]) {
                    value--;
                }
                left++;
            }
            right++;
        }

        return minLength;
    }

    /**
     * 解题思路：使用双指针，移动右指针，若为O，则更新左指针，若为1，则更新连续1的个数
     *
     * @param nums
     * @return
     */
    public int findMaxConsecutiveOnes(int[] nums) {
        int maxlength = 0;
        int left = 0;
        int right = 0;
        while (right < nums.length) {
            if (nums[right] == 0) {
                right++;
                left = right;
            } else {
                maxlength = Math.max(maxlength, (right - left + 1));
                right++;
            }
        }
        return maxlength;
    }

    /**
     * 解题思路：使用有序集合查找和更新最大值和最小值
     *
     * @param nums
     * @param limit
     * @return
     */
    public int longestSubarray(int[] nums, int limit) {
        int left = 0;
        int right = 0;
        int length = 0;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        while (right < nums.length) {
            map.put(nums[right], map.getOrDefault(nums[right], 0) + 1);
            while ((map.lastKey() - map.firstKey()) > limit) {
                map.put(nums[left], map.get(nums[left]) - 1);
                if (map.get(nums[left]) == 0) {
                    map.remove(nums[left]);
                }
                left++;
            }
            length = Math.max(length, (right - left + 1));
            right++;
        }

        return length;
    }

    /**
     * 解题思路：求出无技巧时总的满意的客户，使用滑动窗口，通过比较窗口内使用技巧后增加的顾客数量，找出最大值，即可找到答案
     * 技巧：将判断换成乘法运算，左右指针因为窗口固定，可换成一个指针加偏移量
     * @param customers
     * @param grumpy
     * @param X
     * @return
     */
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int result = 0;
        int maxCust = 0;
        for (int i = 0; i < customers.length; i++) {
            if(grumpy[i] == 0) {
                maxCust += customers[i];
            }
        }

        int left = 0;
        int right = 0;
        for (right = 0; right<X; right++) {
            if(grumpy[right] == 1) {
                maxCust += customers[right];
            }
        }
        result = Math.max(result, maxCust);

        for (right = X; right<grumpy.length; right++) {
            if(grumpy[right] == 1) {
                maxCust += customers[right];
            }
            if(grumpy[left] == 1) {
                maxCust -= customers[left];
            }
            result = Math.max(result, maxCust);
            left++;
        }
        return result;
    }

    public int longestSubstring(String s, int k) {
        int left = 0;
        int right = 0;
        Map<Character, Integer> map = new HashMap<>();
        int res = 0;
        while (right < s.length()) {
            Character c = s.charAt(right);
            Integer count = map.getOrDefault(c, 0)+1;
            map.put(c, count);
            if(count > k) {
                while (s.charAt(left) != c) {
                    Integer count2 = map.get(s.charAt(left));
                    count2--;
                    if(count2 == 0) {
                        map.remove(s.charAt(left));
                    }else {
                        map.put(s.charAt(left), count2);
                    }
                    left++;
                }
            }else {
                res = Math.max(res, right-left+1);
            }
            right++;
        }

        return res;
    }

    /**
     * 解题思路：排序+滑动窗口+前缀和
     * @param nums
     * @param k
     * @return
     */
    public int maxFrequency(int[] nums, int k) {
        int n = nums.length;

        int i = 0;
        int j = 0;
        long[] sum = new long[n + 1];
        Arrays.sort(nums);
        for (int l = 1; l < (n + 1); l++) {
            sum[l] = sum[l-1] + nums[l-1];
        }

        int max = 0;
        while (j < n) {
            long val = (j-i)*(long)nums[j] - (sum[j] - sum[i-1]);
            if(val > k) {
                while (i < j) {
                    i++;
                    val = (j-i)*(long)nums[j] - (sum[j] - sum[i-1]);
                    if(val <= k) {
                        break;
                    }
                }
            }

            max = Math.max(max, (j-i+1));
            j++;
        }

        return max;
    }
}
