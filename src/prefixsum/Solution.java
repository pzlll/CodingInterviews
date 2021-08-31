package prefixsum;

public class Solution {
    /**
     * 解题思路：差分数组
     * 将各航班增加的人数填入差分数组，最后通过前缀和求得每个航班的人数
     * 差分数组的第 i 个数即为原数组的第 i-1 个元素和第 i 个元素的差值，也就是说我们对差分数组求前缀和即可得到原数组。
     * @param bookings
     * @param n
     * @return
     */
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] dp = new int[n];
        for (int i = 0; i < bookings.length; i++) {
            int l = bookings[i][0];
            int r = bookings[i][1];
            int num = bookings[i][2];

            dp[l-1] += num;
            if(r != n) {
                dp[r] -= num;
            }
        }

        int[] res = new int[n];
        res[0] = dp[0];
        for (int i = 1; i < n; i++) {
            res[i] = res[i-1] + dp[i];
        }

        return res;
    }
}
