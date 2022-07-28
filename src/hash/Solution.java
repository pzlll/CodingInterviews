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
     *
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
        if (sum != 1) {
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
        for (Integer in :
                set) {
            ret += in + 1;
        }

        return ret;
    }

    /**
     * 解题思路：求砖块的最小值转化为求边缘的最大值
     * 遍历每一行，通过每一行的元素，将其对应的边缘数量加一
     * 使用哈系表存储对应的边缘的数量
     *
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
     *
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

    /**
     * 解题思路：
     * 对于每个点，计算出经过该点的直线包含的顶点最大数量
     * 对于固定一个点i，若其余两个点j、k与其在同一顶点上，则他们的i与j的斜率等于i与k的斜率
     * 对于固定一个点i，使用哈希表存储不同斜率及其数量，每遍历一个顶点，更新一次最大值
     * 使用二元组（mx,my）存储斜率
     * 保证斜率的分母为正，使用公约数简化分子分母
     * 因为分子和分母位数之和小于int范围，使用第n位存储分子，高n位存储分母
     *
     * @param points
     * @return
     */
    public int maxPoints(int[][] points) {
        int n = points.length;

        if (n <= 2) {
            return n;
        }

        int ret = 0;
        for (int i = 0; i < n; i++) {
            if (ret >= (n - i) || ret > n / 2) {
                return ret;
            }
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int j = i + 1; j < n; j++) {
                int x = points[i][0] - points[j][0];
                int y = points[i][1] - points[j][1];
                if (x == 0) {
                    y = 1;
                } else if (y == 0) {
                    x = 1;
                }
                if (y < 0) {
                    x = -x;
                    y = -y;
                }

                int mx = x / gcd(Math.abs(x), Math.abs(y));
                int my = y / gcd(Math.abs(x), Math.abs(y));

                int index = mx + 10000 + 2 * 10000 * my;
                map.put(index, map.getOrDefault(index, 0) + 1);
            }

            int maxn = 0;
            for (Map.Entry<Integer, Integer> entry :
                    map.entrySet()) {
                int value = entry.getValue();
                maxn = Math.max(maxn, value + 1);
            }

            ret = Math.max(ret, maxn);
        }

        return ret;
    }

    private int gcd(int x, int y) {
        while (x * y != 0) {
            if (x > y) {
                x %= y;
            } else {
                y %= x;
            }
        }

        return x > y ? x : y;
    }

    public int countPairs(int[] deliciousness) {
        int n = deliciousness.length;


        int max = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            max = Math.max(max, deliciousness[i]);
        }

        max *= 2;

        int p = 1;
        int ans = 0;
        while (p <= max) {
            Map<Integer,Integer> freq = new HashMap<>();
            for (int i = 0; i < n; i++) {
                if(p - deliciousness[i] < 0) {
                    continue;
                }
                ans = ans + freq.getOrDefault(p - deliciousness[i], 0);
                freq.put(deliciousness[i], freq.getOrDefault(deliciousness[i], 0) + 1);
            }

            p <<= 1;
        }

        return ans;
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for (String s :
                strs) {
            char[] chs = s.toCharArray();
            int[] count = new int[26];

            for (int i = 0; i < chs.length; i++) {
                count[chs[i] - 'a']++;
            }

            StringBuffer str = new StringBuffer();
            for (int i = 0; i < 26; i++) {

                if(count[i] > 0) {
                    str.append((char)('a' + i));
                    str.append(count[i]);
                }
            }

            String t = str.toString();
            List<String> list = map.getOrDefault(t, new ArrayList<>());
            list.add(s);
            map.put(t, list);
        }

        List<List<String>> ret = new ArrayList<>();
        for (List<String> list :
                map.values()) {
            ret.add(list);
        }

        return ret;
    }

    public int numberOfBoomerangs(int[][] points) {
        int count = 0;
        int n = points.length;
        for (int i = 0; i < n; i++) {
            Map<Double, Integer> map = new HashMap<>();

            for (int j = 0; j < n; j++) {
                double dist = Math.pow(Math.abs(points[i][0] - points[j][0]), 2) +
                        Math.pow(Math.abs(points[i][1] - points[j][1]), 2);
                map.put(dist, map.getOrDefault(dist, 0) + 1);
                if(map.get(dist) > 1) {
                    count += (map.get(dist) - 1) * 2;
                }
            }
        }

        return count;
    }

    public boolean isValidSudoku(char[][] board) {
        boolean[][] rows = new boolean[9][9];
        boolean[][] cols = new boolean[9][9];
        boolean[][] mazes = new boolean[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if(board[i][j] == '.') {
                    continue;
                }
                int num = board[i][j] - '0';

                if(rows[i][num-1] || cols[j][num-1] || mazes[(i / 3) * 3 + j / 3][num-1]) {
                    return false;
                }

                rows[i][num-1] = true;
                cols[j][num-1] = true;
                mazes[(i / 3) * 3 + j / 3][num-1] = true;
            }
        }

        return true;

    }

    public int findLHS(int[] nums) {
        HashMap<Integer,Integer> map = new HashMap<>();

        int n = nums.length;
        for (int i = 0; i < n; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        int length = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            if(map.get(key - 1) != null) {
                length = Math.max(length, value + map.get(key -1));
            }else if(map.get(key + 1) != null) {
                length = Math.max(length, value + map.get(key + 1));
            }
        }

        return length;
    }

    public boolean buddyStrings(String s, String goal) {
        Map<Character, Integer> freq = new HashMap<>();
        Set<Character> diff = new HashSet<>();


        int j = 0;
        int count = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            freq.put(s.charAt(i), freq.getOrDefault(s.charAt(i), 0) + 1);

            if(s.charAt(i) != goal.charAt(i)) {
                if(count == 0) {
                    j = i;
                }else if(count == 1) {
                    return s.charAt(j) == goal.charAt(i) && s.charAt(i) == goal.charAt(j);
                }else {
                    return false;
                }
                count++;
            }

        }
        if(diff.size() == 0) {
            for (Map.Entry<Character, Integer> entry :
                    freq.entrySet()) {
                if (entry.getValue() > 1) {
                    return true;
                }
            }

            return false;
        }
        return diff.size() == 2;

    }

    public List<String> findAndReplacePattern(String[] words, String pattern) {
        Map<Character, List<Integer>> freq = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            List<Integer> orDefault = freq.getOrDefault(pattern.charAt(i), new ArrayList<>());
            orDefault.add(i);
            freq.put(pattern.charAt(i), orDefault);
        }

        List<String> ret = new ArrayList<>();




        for (int i = 0; i < words.length; i++) {
            boolean flag = true;
            String word = words[i];

            Set<Character> seen = new HashSet<>();

            for (Map.Entry<Character, List<Integer>> entry :
                    freq.entrySet()) {
                List<Integer> value = entry.getValue();
                int index = value.get(0);
                if(seen.contains(word.charAt(index))) {
                    flag = false;
                    break;
                }
                seen.add(word.charAt(index));

                for (int j = 1; j < value.size(); j++) {
                    int index2 = value.get(j);
                    if(word.charAt(index) != word.charAt(index2)) {
                        flag = false;
                        break;
                    }
                }
                if(!flag) {
                    break;
                }
            }

            if(flag) {
                ret.add(word);
            }


        }

        return ret;
    }

    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int n = nums1.length;
        int[] nums5 = new int[n * n];
        int[] nums6 = new int[n * n];

        int k = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                nums5[k] = nums1[i] + nums2[j];
                nums6[k] = nums3[i] + nums4[j];
                k++;
            }
        }

        Map<Integer, Integer> freq = new HashMap<>();
        for (int i = 0; i < n * n; i++) {
            freq.put(nums6[i], freq.getOrDefault(nums6[i], 0) + 1);
        }

        int count = 0;
        for (int i = 0; i < n * n; i++) {
            int value = -nums5[i];
            if(freq.get(value) != null) {
                count += freq.get(value);

            }
        }



        return count;

    }


    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        for(int i = 0; i < n; i++) {
            if(nums[i] <= 0) {
                nums[i] = n + 1;
            }
        }

        for(int i = 0; i < n; i++) {
            if(Math.abs(nums[i]) >= 1 && Math.abs(nums[i]) <= n) {
                if(nums[Math.abs(nums[i]) - 1] > 0) {
                    nums[Math.abs(nums[i]) - 1] = -nums[Math.abs(nums[i]) - 1];
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if(nums[i] > 0) {
                return i + 1;
            }
        }

        return n + 1;


    }

    public int[] arrayRankTransform(int[] arr) {
        if(arr.length == 0) {
            return arr;
        }

        int[] temp = new int[arr.length];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = arr[i];
        }

        Arrays.sort(arr);

        int i = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(arr[i], i+1);

        i++;

        for (int j = 1; j < arr.length; j++) {
            if(arr[j] > arr[j - 1]) {
                map.put(arr[j], i+1);
                i++;
            }
        }

        int[] res = new int[arr.length];

        for (int j = 0; j < arr.length; j++) {
            res[j] = map.get(temp[j]);
        }

        return res;
    }


}
