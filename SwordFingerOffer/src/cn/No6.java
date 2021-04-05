package cn;

import java.util.Stack;

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class No6 {
    public int[] reversePrint(ListNode head) {
        Stack<Integer> stack = new Stack<>();

        int length = 0;

        while (head != null) {
            stack.push(head.val);
            head = head.next;
            length++;
        }

        int[] nums = new int[length];

        for (int i = 0; i < length; i++) {
            nums[i] = stack.pop();
        }

        return nums;
    }
}
