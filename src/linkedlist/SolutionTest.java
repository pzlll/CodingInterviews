package linkedlist;

import org.junit.Test;

import static org.junit.Assert.*;



public class SolutionTest {

    @Test
    public void addTwoNumbers() {
        ListNode l1 = new ListNode(9);
        ListNode l2 = new ListNode(9);
        ListNode l3 = new ListNode(1);
        l2.next = l3;
        l1.next = l2;
        ListNode list1 = l1;
        ListNode l4 = new ListNode(1);
        ListNode l5 = new ListNode(9);
        ListNode l6 = new ListNode(4);
//        l5.next = l6;
//        l4.next = l5;
        ListNode list2 = l4;
        assertEquals(list2, new Solution().addTwoNumbers(list1, list2));
    }

    @Test
    public void partition() {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(4);
        ListNode l3 = new ListNode(3);
        l2.next = l3;
        l1.next = l2;
        ListNode list1 = l1;
        ListNode l4 = new ListNode(2);
        ListNode l5 = new ListNode(5);
        ListNode l6 = new ListNode(2);
        l5.next = l6;
        l4.next = l5;
        l3.next = l4;
        assertEquals(list1, new Solution().partition(list1, 3));
    }
}