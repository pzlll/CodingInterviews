package bitmanipulation;

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
}
