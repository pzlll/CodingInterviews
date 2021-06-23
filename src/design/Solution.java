package design;

import java.util.Random;

public class Solution {
    private int[] array;
    private int[] original;
    private Random random;

    public Solution(int[] nums) {
        array = nums;
        original = nums.clone();
        random = new Random();
    }

    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        array = original;
        original = array.clone();
        return array;
    }

    /** Returns a random shuffling of the array. */
    /**
     * 解题思路：
     * i从0到n-1遍历，每次从i到n-1随机选一个值进行交换
     * @return
     */
    public int[] shuffle() {
        for (int i = 0; i < array.length; i++) {
            int j = i + (int)(random.nextDouble() * (array.length - i));
            swap(i, j);
        }

        return array;
    }

    private void swap(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
