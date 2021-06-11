package linkedlist;


import javax.xml.soap.Node;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

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

    /**
     * 解题思路：两种方法
     *      1.使用哈希表存储链表A的元素，遍历链表B，若哈希表包含该元素，则说明找到相同元素的头节点
     *      2.使用双指针，指针pa指向链表A的头节点，指针pb指向链表B的头节点，判断pa是否等于pb，
     *      若不等，则pa、pb指向下个节点，pa为空时，下个节点指向链表B，pb为空时，下个节点指向链表A
     *      ，若相等且非空，则找到对应头节点，若相等且空，则两个链表不相交
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int la = 0;
        int lb = 0;
        ListNode pa = headA;
        ListNode pb = headB;
        while (pa != null) {
            la++;
            pa = pa.next;
        }

        while (pb != null) {
            lb++;
            pb = pb.next;
        }

        pa = headA;
        pb = headB;
        while(la != lb) {
            if(la > lb) {
                pa = pa.next;
                la--;
            }else {
                pb = pb.next;
                lb--;
            }
        }

        while (pa != null && pb != null) {
            if(pa == pb){
                return pa;
            }
            pa = pa.next;
            pb = pb.next;
        }

        return null;
    }

    /**
     * 解题思路：
     *      方法二：使用递归求解
     *      1.若当前节点为空，则返回空节点
     *      2.当前节点的next域由本函数递归得到，参数是当前节点的next域
     *      3.根据当前节点与参数值是否相同，返回当前节点或其next域
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {
        ListNode p = new ListNode(1);
        p.next = head;
        ListNode q = p;
        while(q.next != null) {
            if(q.next.val == val) {
                q.next = q.next.next;
            }else {
                q = q.next;
            }
        }

        return p.next;
    }

    /**
     * 解题思路：
     *      1.求链表长度，计算正数的位置再遍历进行删除
     *      2.存入栈，倒数第n个则出栈n次，此时栈顶为前驱节点
     *      3.使用快慢指针，指针first比second快n个节点（相隔n-1个节点），
     *          若first走到尽头，则second所指为倒数第n个节点
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {


        ListNode dump = new ListNode(0);
        dump.next = head;
        ListNode first, second;
        first = head;
        second = dump;
        for (int i = 0; i < n; i++) {
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }

        second.next = second.next.next;

        return dump.next;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode p = new ListNode(-150);
        ListNode ans = p;

        while(l1 != null && l2 != null) {
            if(l1.val <= l2.val) {
                p.next = l1;
                l1 = l1.next;
            }else {
                p.next = l2;
                l2 = l2.next;
            }
            p = p.next;

        }
//
//        if(l1 != null) {
//            p.next = l1;
//        }
//
//        if(l2 != null) {
//            p.next = l2;
//        }
//
//        if(l1 == null) {
//            return l2;
//        }else if(l2 == null){
//            return  l1;
//        }else if(l1.val < l2.val) {
//            l1.next = mergeTwoLists(l1.next, l2);
//            return l1;
//        }else {
//            l2.next = mergeTwoLists(l1, l2.next);
//            return l2;
//        }

        return ans.next;
    }


    /**
     * 解题思路：
     * 找到中间结点
     * 翻转后半段结点
     * 互相比较，若不同则返回失败
     * 再次翻转后半段结点
     * 返回成功
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        ListNode first = head;
        ListNode second = head;

        Stack<Integer> stack = new Stack<>();

        while(first != null && first.next != null) {
            stack.push(second.val);

            second = second.next;
            first = first.next.next;
        }
        if(first != null) {
            second = second.next;
        }

        while (!stack.isEmpty()) {
            if(second == null) {
                return false;
            }
            if(second.val != stack.pop()){
                return false;
            }
            second = second.next;
        }

        return true;
    }

    public boolean hasCycle(ListNode head) {
        if(head == null) {
            return false;
        }
        ListNode first = head.next;
        ListNode second = head;
        while (first != null && first.next != null) {
            if(first == second) {
                return true;
            }
            second = second.next;
            first = first.next.next;
        }

        return false;
    }


}
