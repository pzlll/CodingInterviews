package bitmanipulation;

public class Solution {
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
