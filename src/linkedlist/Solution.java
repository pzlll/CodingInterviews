package linkedlist;


import javax.xml.soap.Node;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.List;

class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}
public class Solution {
    public ListNode EntryNodeOfLoop(ListNode pHead)
    {
//        HashSet<ListNode> listNodes = new HashSet<>();
////
////        while(pHead != null) {
////            if(!listNodes.add(pHead)) {
////                return pHead;
////            }
////            pHead = pHead.next;
////        }
////
////        return null;
        ListNode meetingNode = findMeetingNode(pHead);
        if (meetingNode == null) {
            return null;
        }

        int loopLength = 1;
        ListNode loopNode = meetingNode.next;
        while(loopNode != meetingNode) {
            loopNode = loopNode.next;
            loopLength++;
        }

        ListNode fast = pHead;
        while(loopLength > 0) {
            fast = fast.next;
            loopLength--;
        }

        ListNode slow = pHead;

        while(fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }

        return slow;
    }

    private ListNode findMeetingNode(ListNode pHead) {
        if(pHead == null) {
            return null;
        }
        ListNode slow = pHead.next;
        if(slow == null) {
            return null;
        }

        ListNode fast = slow.next;

        while(slow != null && fast != null) {
            if(slow == fast) {
                return slow;
            }

            slow = slow.next;
            fast = fast.next;

            if(fast != null) {
                fast = fast.next;
            }
        }

        return null;

    }

    public ListNode deleteDuplication(ListNode pHead)
    {
        if(pHead == null || pHead.next == null) {
            return pHead;
        }
        ListNode head = new ListNode(0);
        head.next = pHead;

        ListNode pre = head;
        ListNode last = head.next;

        while(last != null) {
            if(last.next != null && last.val == last.next.val) {
                while(last.next != null && last.val == last.next.val) {
                    last = last.next;
                }
                pre.next = last.next;
                last = last.next;
            }else {
                pre = pre.next;
                last = last.next;
            }
        }

        return head.next;

    }


    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode list = new ListNode(0);

        if(l1 == null && l2 == null)
            return null;

        ListNode p = list;
        int carry = 0;

        while(l1 != null && l2 != null) {
            p.next = new ListNode(0);
            int sum = l1.val + l2.val + carry;
            carry = sum / 10;
            sum = sum % 10;
            p.next.val = sum;
            p = p.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        while(l1 != null) {
            p.next = new ListNode(0);
            int sum = l1.val + carry;
            carry = sum / 10;
            sum = sum % 10;
            p.next.val = sum;
            p = p.next;
            l1 = l1.next;
        }

        while(l2 != null) {
            p.next = new ListNode(0);
            int sum = l2.val + carry;
            carry = sum / 10;
            sum = sum % 10;
            p.next.val = sum;
            p = p.next;
            l2 = l2.next;
        }
        if(carry == 1) {
            p.next = new ListNode(1);
        }

        return list.next;
    }

    public ListNode partition(ListNode head, int x) {
        ListNode l1 = null;
        ListNode l2 = null;
        if(head == null) {
            return l1;
        }

        l1 = new ListNode(0);
        l2 = new ListNode(0);

        ListNode l2Tail = l2;
        ListNode l1Tail = l1;
        ListNode p = head;

        while(p != null) {
            ListNode s = p;
            p = p.next;
            if(s.val >= x) {
                s.next = null;
                l2Tail.next = s;
                l2Tail = l2Tail.next;
            }else{
                s.next = null;
                l1Tail.next = s;
                l1Tail = l1Tail.next;
            }
        }

        l1Tail.next = l2.next;

        return l1.next;



    }

    /**
     * 解题思路：给的参数是当前节点，因为无法得到当前节点的前一节点，所以把下一节点的值赋值到当前节点，删除下一节点实现
     * @param node
     */
    public void deleteNode(ListNode node) {

        node.val = node.next.val;
        node.next = node.next.next;
    }


}
