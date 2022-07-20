package doublepointer;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.*;

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}

public class Solution {
    // 乐扣 167. 两数之和 II - 输入有序数组
    public int[] twoSum(int[] numbers, int target) {
        int big = numbers.length - 1;
        int small = 0;

        int[] result = new int[2];

        boolean flag = true;

        while (flag) {
            if (numbers[big] + numbers[small] == target) {
                flag = false;
                result[0] = small + 1;
                result[1] = big + 1;
            } else if (numbers[big] + numbers[small] > target) {
                big--;
            } else {
                small++;
            }
        }

        return result;
    }

    // 乐扣 633. 平方数之和
    public boolean judgeSquareSum(int c) {
        int small = 1;
        int big = (int) Math.sqrt(c);
        while (small <= big) {
            if (small * small + big * big == c) {
                return true;
            } else if (small * small + big * big > c) {
                big--;
            } else {
                small++;
            }

        }

        return false;
    }

    //乐扣 345. 反转字符串中的元音字母
    public String reverseVowels(String s) {
        char[] chars = s.toCharArray();

        ArrayList<Character> list = new ArrayList<>();
        list.add('a');
        list.add('e');
        list.add('i');
        list.add('o');
        list.add('u');
        list.add('A');
        list.add('E');
        list.add('I');
        list.add('O');
        list.add('U');

        int i = 0, j = chars.length - 1;
        while (i < j) {
            while (i < j && !list.contains(chars[i])) {
                i++;
            }
            while (i < j && !list.contains(chars[j])) {
                j--;
            }

            if (i >= j) {
                break;
            }

            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
            i++;
            j--;

        }

        StringBuilder str = new StringBuilder();

        for (int k = 0; k < chars.length; k++) {
            str.append(chars[k]);
        }

        return str.toString();

    }

    //乐扣 680. 验证回文字符串 Ⅱ
    public boolean validPalindrome(String s) {
        for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) {
                return isPalindrome(s, i, j - 1) || isPalindrome(s, i + 1, j);
            }
        }

        return true;
    }

    private boolean isPalindrome(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i++) != s.charAt(j--)) {
                return false;
            }
        }

        return true;
    }

    public boolean isPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            while(i < j && !Character.isLetterOrDigit(s.charAt(i))) {
                i++;
            }
            while(i < j && !Character.isLetterOrDigit(s.charAt(j))) {
                j--;
            }

            if(i < j) {
                if(Character.isDigit(s.charAt(i)) || Character.isDigit(s.charAt(j))) {
                    if(s.charAt(i) != s.charAt(j)) {
                        return false;
                    }
                }

                char c1 = Character.toLowerCase(s.charAt(i));
                char c2 = Character.toLowerCase(s.charAt(j));
                if(c1 != c2) {
                    return false;
                }
                i++;
                j--;
            }
        }

        return true;
    }

    /**
     * 解题思路：利用双指针，从后往前填充数组A，比较两个数组，元素值大的进行填充
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int k = n + m - 1;
        int i = m - 1;
        int j = n - 1;
        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[k] = nums1[i];
                i--;
            } else {
                nums1[k] = nums2[j];
                j--;
            }
            k--;
        }
        while (j >= 0) {
            nums1[k] = nums2[j];
            j--;
            k--;
        }

    }

    //乐扣 141. 环形链表
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        ListNode fast = head;
        ListNode slow = head;
        while (fast != null) {
            fast = fast.next;

            if (fast == slow) {
                return true;
            }

            if (fast == null) {
                return false;
            }

            fast = fast.next;
            slow = slow.next;
        }

        return false;
    }

    //乐扣 524. 通过删除字母匹配到字典里最长单词
    public String findLongestWord(String s, List<String> d) {
        boolean match = false;
        String longestWord = null;
        for (int i = 0; i < d.size(); i++) {
            String str = d.get(i);
            match = isMatcc(s, str);
            if (match) {
                if (longestWord == null) {
                    longestWord = str;
                } else {
                    if (str.length() > longestWord.length()) {
                        longestWord = str;
                    } else if (str.length() == longestWord.length()) {
                        int c = str.compareTo(longestWord);
                        if (c < 0) {
                            longestWord = str;
                        }
                    }
                }
            }
        }

        return longestWord;
    }

    private boolean isMatcc(String s, String str) {
        int i = 0;
        int j = 0;
        while (i < s.length() && j < str.length()) {
            if (s.charAt(i) == str.charAt(j)) {
                i++;
                j++;
            } else {
                i++;
            }
        }

        if (j < str.length()) {
            return false;
        } else {
            return true;
        }
    }

    public void moveZeroes(int[] nums) {
        int i = 0;
        int j = 0;
        while (j < nums.length) {
            if (nums[j] != 0) {
                nums[i] = nums[j];
                if (j != i) {
                    nums[j] = 0;
                }
                i++;
            }
            j++;
        }

    }

    public int characterReplacement(String s, int k) {
        if (s == null || s.length() == 0 || k > s.length()) {
            return 0;
        }
        int i = 0;
        int j = 0;
        int max = 0;
        int index = 0;
        int[] counts = new int[26];
        while (j < s.length()) {
            int k1 = s.charAt(j) - 'A';
            counts[k1]++;

            for (int l = 0; l < counts.length; l++) {
                if (counts[l] > counts[index]) {
                    index = l;
                }
            }
            if ((j - i + 1) - counts[index] > k) {
                counts[s.charAt(i) - 'A']--;
                i++;

            } else if ((j - i + 1) > max) {
                max = (j - i + 1);
            }
            j++;
        }

        return max;
    }

    /**
     * 解题思路：用双指针i,j进行寻找，若指针j对应的元素在区间内没出现，则加入，若出现了，则指针i加一，并将对应元素移除
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        HashMap<Character, Integer> map = new HashMap<>();
        int max = 1;
        char[] str = s.toCharArray();
        map.put(str[0], 0);
        for (int i = 0, j = i + 1; j < s.length() && i < s.length(); j++) {

            if (!map.containsKey(str[j])) {
                map.put(str[j], j);
                if (j - i + 1 > max) {
                    max = j - i + 1;
                }
            } else {
                map.remove(str[i]);
                i++;
                j--;
            }
        }

        return max;

    }

    /**
     * 解题思路：维护两个指针i,j，当前和若小于s，则j加一，若大于等于s，则比较当前长度与最小长度，同时当前和减去指针i的元素，并将i加一
     *
     * @param s
     * @param nums
     * @return
     */
    public int minSubArrayLen(int s, int[] nums) {
        int i = 0;
        int j = 0;
        int length = Integer.MAX_VALUE;
        int sum = 0;
        while (i < nums.length) {
            while (j < nums.length && sum < s) {
                sum += nums[j];
                j++;
            }
            if (sum >= s) {
                length = Math.min(length, j - i);

            }

            sum -= nums[i];
            i++;
        }


        if (length == Integer.MAX_VALUE)
            return 0;
        return length;
    }

    /**
     * 解题思路：把差值用数组存储，用双指针遍历，如果小于总和则j++，如果大于则i++直到小于总和并重新比较，更新最大长度
     *
     * @param s
     * @param t
     * @param maxCost
     * @return
     */
    public int equalSubstring(String s, String t, int maxCost) {
        if (s == null || t == null) {
            return 0;
        }
        int[] cost = new int[s.length()];
        for (int i = 0; i < cost.length; i++) {
            cost[i] = Math.abs((s.charAt(i) - t.charAt(i)));
        }

        int sum = 0;
        int length = 0;
        for (int i = 0, j = 0; j < cost.length && i < cost.length; j++) {
            if ((sum + cost[j]) > maxCost) {

                while (i < cost.length && (sum + cost[j]) > maxCost) {
                    sum -= cost[i];
                    i++;
                }
                j--;
            } else {
                sum += cost[j];
                if ((j - i + 1) > length) {
                    length = j - i + 1;
                }
            }
        }

        return length;
    }

    /**
     * 解题思路：使用滑动窗口找n-k区间中最小的总和，用数组总和减去，则是首尾k区间的最大值
     *
     * @param cardPoints
     * @param k
     * @return
     */
    public int maxScore(int[] cardPoints, int k) {
        int n = cardPoints.length;
        int windowSize = n - k;
        int sum = 0;
        for (int i = 0; i < windowSize; i++) {
            sum += cardPoints[i];
        }
        int min = sum;
        for (int i = windowSize; i < n; i++) {
            sum += cardPoints[i] - cardPoints[i - windowSize];
            min = Math.min(sum, min);
        }

        return Arrays.stream(cardPoints).sum() - min;
//        int[] sum = new int[k+1];
//        int result = Integer.MIN_VALUE;
//        sum[0] = 0;
//        for (int i = 1; i <= k; i++) {
//            sum[i] = sum[i-1] + cardPoints[i-1];
//        }
//        result = sum[k];
//
//        int temp = 0;
//        int length = cardPoints.length;
//        for (int i = 0; i < k; i++) {
//            if((temp + cardPoints[length-1-i] + sum[k-i-1]) > result) {
//                result = temp + cardPoints[length-1-i] + sum[k-i-1];
//            }
//            temp += cardPoints[length-1-i];
//        }
//        return result;
    }

    /**
     * 解题思路：使用双指针，快指针指向当前元素，慢指针指向待存入的元素
     * 若快指针所指元素不等于慢指针的前第二个元素，则进行插入
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        if(n <= 2) {
            return n;
        }
        int slow = 2;
        int fast = 2;
        while(fast < n) {
            if(nums[slow - 2] != nums[fast]) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }

    /**
     * 解题思路：使用kmp算法对模式串查找最长前缀并存储
     * 用双指针进行匹配
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        if(needle == null || needle.length() == 0) {
            return 0;
        }
        int[] ret = new int[needle.length()];
        calKMP(needle,ret);

        int i = 0, j = 0;
        while(i < haystack.length() && j < needle.length()) {
            if(j == -1) {
                i++;
                j = 0;
            }else if(haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
            }else{
                j = ret[j];
            }
        }
        if(j == needle.length()) {
            return (i - j);
        }else {
            return -1;
        }
    }

    private void calKMP(String needle, int[] ret) {
        int i = 0;
        int j = -1;
        ret[0] = -1;
        while((i+1) < needle.length()) {
            if(j == -1) {
                ret[++i] = 0;
                j = 0;

            }else if(needle.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
                ret[i] = j;


            }else {
                j = ret[j];
            }
        }

    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();

        Arrays.sort(nums);

        int n = nums.length;
        int pre1 = -1;
        for (int i = 0; i < n; i++) {
            if(i != 0 && pre1 == nums[i]) {
                continue;
            }
            int pre2 = -1;
            for (int j = i + 1,  k = n-1; j < n && j < k; j++) {
                if(j != (i + 1) && pre2 == nums[j]) {
                    continue;
                }

                while (j < k && ((nums[i] + nums[j] +nums[k]) != 0)) {
                    if((nums[i] + nums[j] +nums[k]) < 0) {
                        j++;
                    }else {
                        k--;
                    }
                }

                if (j < k && (nums[i] + nums[j] +nums[k]) == 0) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[j]);
                    list.add(nums[k]);
                    ret.add(list);
                }
                pre2 = nums[j];
            }
            pre1 = nums[i];
        }

        return ret;
    }

    public int minPairSum(int[] nums) {
        Arrays.sort(nums);

        int n = nums.length;

        int max = 0;
        for (int i = 0; i < n/2; i++) {
            max = Math.max(max, nums[i] + nums[n-1-i]);
        }

        return max;
    }

    /**
     * 解题思路：遍历数组，把当前遍历的元素作为等差数组结尾并计算其个数，利用前一个元素遍历的结果降低复杂度
     * @param nums
     * @return
     */
    public int numberOfArithmeticSlices(int[] nums) {
        int n = nums.length;
        if(n < 2) {
            return 0;
        }
        int sum = 0;
        int t = 0;
        int d = nums[1] - nums[0];
        for (int i = 2; i < n; i++) {
            if((nums[i] - nums[i-1]) == d) {
                t++;
            }else {
                d = nums[i] - nums[i-1];
                t = 0;
            }
            sum += t;
        }

        return sum;

    }

    /**
     * 解题思路：双指针
     * @param chars
     * @return
     */
    public int compress(char[] chars) {
        int i = 0;
        int left = 0;
        int right = 0;
        int n = chars.length;
        while(right < n) {
            while (right < n && chars[left] == chars[right]) {
                right++;
            }


            int count = right - left;
            chars[i++] = chars[left];
            if(count > 1) {
                int a = i;


                while (count != 0) {
                    int k = count % 10;
                    count /= 10;
                    chars[i++] = (char) (k + '0');
                }

                reverseCharArray(chars, a, i-1);
            }


            left = right;
        }

        return i;
    }

    private void reverseCharArray(char[] chars, int left, int right) {
        for (int i = 0; i <= (right - left)/2; i++) {
            char temp = chars[i + left];
            chars[left + i] = chars[right - i];
            chars[right - i] = temp;
        }
    }

    public String reverseStr(String s, int k) {
        char[] ch = s.toCharArray();
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            if(k > (j - i + 1)) {
                reverseString(ch, i, j);

            }else {
                reverseString(ch, i, i + k -1);
            }

            i += 2 * k;
        }


        return String.valueOf(ch);
    }

    private void reverseString(char[] ch, int left, int right) {
        while (left < right) {
            char c = ch[left];
            ch[left] = ch[right];
            ch[right] = c;
            left++;
            right--;
        }
    }

    public int compareVersion(String version1, String version2) {
        int i = 0;
        int j = 0;
        int n = version1.length();
        int m = version2.length();

        while (i < n && j < m) {
            int sum1 = 0;
            int sum2 = 0;
            while (i < n && version1.charAt(i) != '.') {
                sum1 = sum1 * 10 + (version1.charAt(i) - '0');
                i++;
            }

            while (j < m && version2.charAt(j) != '.') {
                sum2 = sum2 * 10 + (version2.charAt(j) - '0');
                j++;
            }
            i++;
            j++;

            if(sum1 > sum2) {
                return 1;
            }else if(sum1 < sum2) {
                return -1;
            }
        }

        return 0;
    }

    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode p = head;
        ListNode q = head;
        while (k > 0 && q != null) {
            q = q.next;
            k--;
        }

        while (q != null) {
            q = q.next;
            p = p.next;
        }

        return p;
    }

    public String findLongestWord2(String s, List<String> dictionary) {
        List<String> ret = new ArrayList<>();

        for (String dict :
                dictionary) {
            if(isBelongs(s, dict)) {
                ret.add(dict);
            }
        }

        Collections.sort(ret, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o1.length() == o2.length()) ? o1.compareTo(o2) : o2.length() - o1.length();
            }
        });

        return (ret.size() ==0) ? "" : ret.get(0);
    }

    private boolean isBelongs(String s, String dict) {
        int n = s.length();
        int m = dict.length();

        int i = 0;
        int j = 0;
        while (i < n && j < m) {
            if(s.charAt(i) == dict.charAt(j)) {
                j++;
            }
            i++;
        }

        return j == m;
    }

    public String reverseOnlyLetters(String s) {
        char[] chars = s.toCharArray();
        int start = 0;
        int end = chars.length - 1;
        while (start < end) {
            while (start < end && !Character.isAlphabetic(chars[start])) {
                start++;
            }
            while (start < end && !Character.isAlphabetic(chars[end])) {
                end--;
            }

            char temp = chars[start];
            chars[start] = chars[end];
            chars[end] = temp;
        }

        return new String(chars);

    }

    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;
        while (left < right) {
            maxArea = Math.max(maxArea, (right - left) * Math.min(height[left], height[right]));

            if(height[left] <= height[right]) {
                left++;
            }else {
                right++;
            }
        }

        return maxArea;
    }

}
