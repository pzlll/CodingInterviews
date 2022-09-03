package divideandconquer;

import java.util.List;

public class Solution {



    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    /**
     * 解题思路：（分治法）统计各字符的频数，遍历字符串，若某一字符的频数小于k，则递归求其左右子串最大值
     * 若所有字符频数都大于等于k，则返回字符串长度
     * @param s
     * @param k
     * @return
     */
    public int longestSubstring(String s, int k) {
        return findLongestSubstring(s, 0, s.length()-1, k);
    }

    private int findLongestSubstring(String s, int start, int end, int k) {
        int[] count = new int[26];
        for (int i = start; i <= end; i++) {
            count[s.charAt(i)-'a']++;
        }
        for (int i = start; i <= end; i++) {
            int index = s.charAt(i)-'a';
            if(count[index] > 0 && count[index] < k) {
                return Math.max(findLongestSubstring(s,start,i-1,k), findLongestSubstring(s,i+1,end,k));
            }
        }

        return end - start +1;
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length == 0) {
            return null;
        }


        ListNode res = divideLists(lists, 0, lists.length -1);

        return res;
    }

    private ListNode divideLists(ListNode[] lists, int start, int end) {
        if(start == end) {
            return lists[start];
        }

        int mid = (start + end) / 2;

        ListNode left = divideLists(lists, start, mid);
        ListNode right = divideLists(lists, mid + 1, end);

        ListNode p = new ListNode();
        ListNode root =  p;

        while (left != null && right != null) {
            if(left.val < right.val) {
                p.next = left;
                p = p.next;
                left = left.next;
            }else {
                p.next = right;
                p = p.next;
                right = right.next;
            }
        }

        if(left != null) {
            p.next = left;
        }

        if(right != null) {
            p.next = right;
        }

        return root.next;

    }
}
