package hash;

import java.lang.reflect.Array;
import java.util.*;

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

    /**
     * 解题思路：求砖块的最小值转化为求边缘的最大值
     * 遍历每一行，通过每一行的元素，将其对应的边缘数量加一
     * 使用哈系表存储对应的边缘的数量
     * @param wall
     * @return
     */
    public int leastBricks(List<List<Integer>> wall) {
        int n = wall.size();


        Map<Integer, Integer> map = new HashMap<>();
        for (List<Integer> list :
                wall) {
            int index = 0;
            for (int i = 0; i < (list.size() - 1); i++) {
                index += list.get(i);
                map.put(index, map.getOrDefault(index, 0) + 1);
            }
            
        }

        int max = 0;
        for (Integer v :
                map.values()) {
            max = Math.max(max, v);
        }

        return n - max;
    }

    /**
     * 解题思路：
     * 1. 判断两字符串长度是否相等
     * 2. 用长度为26的数组（哈希表）统计字符串s中字符的个数
     * 3. 遍历字符串t，每遇到一个字符则减去数组对应字符的个数，若个数小于0，则两字符串不匹配
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] table = new int[26];
        for (int i = 0; i < s.length(); i++) {
            table[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            table[t.charAt(i) - 'a']--;
            if (table[t.charAt(i) - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }




}
