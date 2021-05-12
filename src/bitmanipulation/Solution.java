package bitmanipulation;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    /**
     * 解题思路：
     * 方法一：使用哈希表存储键值对，遍历哈希表，找到值为1的键
     * 方法二：使用位运算，对于32位（int）的每一位，将其加起来并和3取余得到该位的值，再将每一位合并可得结果
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry :
                map.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }

        return -1;
    }

    /**
     * 解题思路：利用异或性质
     * 任意整数和自身做异或运算的结果都等于 00，即 x ^ x = 0；
     * 任意整数和 00 做异或运算的结果都等于其自身，即 x ^ 0 = 0 ^ x = x。
     *
     * @param encoded
     * @param first
     * @return
     */
    public int[] decode(int[] encoded, int first) {
        int[] ans = new int[encoded.length + 1];
        ans[0] = first;
        for (int i = 0; i < encoded.length; i++) {
            ans[i + 1] = ans[i] ^ encoded[i];
        }

        return ans;

    }


    /**
     * 解题思路：利用异或性质
     * 1. x⊕x=0；
     * 2. 4i⊕(4i+1)⊕(4i+2)⊕(4i+3)=0
     * 方法：1.公式为start⊕(start+2i)⊕(start+4i)⊕⋯⊕(start+2(n−1))
     *          处理最后一位（若start和n为奇数，则为1,否则为0）
     *      2.将公式转化为(s⊕(s+1)⊕(s+2)⊕⋯⊕(s+n−1))×2+e
     *      3.函数sumXor(x): 0⊕1⊕2⊕⋯⊕x
     *                          x           x=4k
     *                          1           x=4k+1
     *                          x+1         x=4k+2
     *                          0           x=4k+3
     *      4.公式转化为(sumXor(s−1)⊕sumXor(s+n−1))*2+e
     * @param n
     * @param start
     * @return
     */
    public int xorOperation(int n, int start) {
//        int res = 0;
//        for (int i = 0; i < n; i++) {
//            res ^= (start + 2 * i);
//        }
//
//        return res;
        int res = 0;
        int s = start / 2;
        int e = (start & 1) & (n & 1);
        res = (sumXor(s - 1) ^ sumXor(s + n - 1)) * 2 + e;
        return res;
    }

    private int sumXor(int k) {
        switch (k % 4) {
            case 0:
                return k;
            case 1:
                return 1;
            case 2:
                return (k + 1);
            case 3:
                return 0;
        }
        return 0;
    }

    /**
     * 解题思路：1.perm数组是前n个正整数的排列
     *         2.encoded数组的奇数元素异或的结果是除了perm[0]以外的全部元素的异或运算结果
     * @param encoded
     * @return
     */
    public int[] decode(int[] encoded) {
        int n = encoded.length + 1;
        int x = 0;
        for (int i = 0; i < n; i++) {
            x ^= (i + 1);
        }

        int y = 0;
        for (int i = 1; i < encoded.length; i += 2) {
            y ^= encoded[i];
        }

        int first = x ^ y;
        int[] ans = new int[n];
        ans[0] = first;
        for (int i = 1; i < n; i++) {
            ans[i] = ans[i - 1] ^ encoded[i - 1];
        }

        return ans;
    }

    /**
     * 解题思路：设arr长度为n，queries长度为m，则最坏时间复杂度为O（nm），可优化每次查询的时间
     *          使用前缀和数组存储数据，优化查询时间
     *          对于前缀和数组xors的每个元素i，其值为前i-1个数的异或之和
     *          对于给定位置的（left,right）异或和，可由前缀和xors[left]^xors[right+1]得出
     *          时间复杂度为O（m）
     * @param arr
     * @param queries
     * @return
     */
    public int[] xorQueries(int[] arr, int[][] queries) {

        int n = arr.length;
        int[] xors = new int[n];
        for (int i = 1; i <= n; i++) {
            xors[i] = arr[i - 1] ^ xors[i - 1];
        }

        int m = queries.length;
        int[] ans = new int[m];
        for (int i = 0; i < m; i++) {
            ans[i] = xors[queries[i][0]] ^ xors[queries[i][1]];
        }

        return ans;
    }
}