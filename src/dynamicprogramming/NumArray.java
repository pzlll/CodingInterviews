package dynamicprogramming;

public class NumArray {

    private int[] sum;

    /**
     * 解题思路：使用前缀和，创建长度为n+1的数组，下标i存储0~i-1元素之和
     * @param nums
     */
    public NumArray(int[] nums) {
        sum = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            sum[i + 1] = sum[i] + nums[i];
        }

    }

    public int sumRange(int i, int j) {
        return sum[j + 1] - sum[i];

    }
}
