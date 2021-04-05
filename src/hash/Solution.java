package hash;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution {
    /**
     * 解题思路：把数组元素存储在哈希表中，若存在，则出现次数不止一次
     *
     * @param nums
     * @return
     */
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                return true;
            } else {
                set.add(nums[i]);
            }
        }

        return false;
    }

    /**
     * 解题关键：任何一个int型的数，它的next都是三位数以内，所以它不会无限循环，用哈希表存储其足迹
     * @param n
     * @return
     */
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        int sum = 0;
        while (sum != 1 && !set.contains(sum)) {
            sum = 0;
            set.add(n);
            while (n != 0) {
                sum += ((n % 10) * (n % 10));
                n = n / 10;
            }
            n = sum;

        }
        if(sum != 1) {
            return false;
        }

        return true;
    }

    public int numRabbits(int[] answers) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < answers.length; i++) {
            set.add(answers[i]);
        }

        int ret = 0;
        for (Integer in:
             set) {
            ret += in + 1;
        }

        return ret;
    }

}
