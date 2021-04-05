package stackandqueue;


import java.util.*;

public class Solution {
    //  用两个栈实现一个队列
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        if (!stack2.isEmpty()) {
            return stack2.pop();
        }
        if (stack1.isEmpty()) {
            return -1;
        }

        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }

        return stack2.pop();
    }

    //    滑动窗口的最大值
    public ArrayList<Integer> maxInWindows(int[] num, int size) {
        ArrayList<Integer> maxs = new ArrayList<>();

        if (num.length == 0 || size == 0 || num.length < size) {
            return maxs;
        }

        ArrayDeque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < num.length; i++) {
            int boundary = i - size + 1;
            if (!deque.isEmpty() && boundary > deque.peekFirst()) {
                deque.pollFirst();
            }

            while (!deque.isEmpty() && num[deque.peekLast()] <= num[i]) {
                deque.pollLast();
            }
            deque.add(i);

            if (boundary >= 0) {
                maxs.add(num[deque.peekFirst()]);
            }
        }

        return maxs;
    }


    /**
     * 解题思路：遍历数组，将元素压入栈，若遇到大于栈顶的元素，则栈顶的下一个比它大的元素找到，若小于等于栈顶元素，则入栈
     * 需要遍历2 * n + 1个元素，才能找到所有元素的下一个比它大的元素
     *
     * @param nums
     * @return
     */
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Arrays.fill(res, -1);
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < 2 * n - 1; i++) {
            if (stack.isEmpty()) {
                stack.push(i % n);
            } else {
                if (nums[i % n] <= nums[stack.peek()]) {
                    stack.push(i % n);
                } else {
                    while (!stack.isEmpty() && nums[i % n] > nums[stack.peek()]) {
                        res[stack.pop()] = nums[i % n];
                    }
                    stack.push(i % n);
                }
            }
        }

        return res;
    }

    /**
     * 解题思路：使用栈保存前面扫描的元素，每检索一个元素，查看栈顶元素是否相同，若相同，则删除栈顶元素，若不同，则插入该元素到栈
     *
     * @param S
     * @return
     */
    public String removeDuplicates(String S) {
        StringBuilder stringBuilder = new StringBuilder();
        int top = -1;
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            if (top >= 0 && (stringBuilder.charAt(top) == c)) {
                stringBuilder.deleteCharAt(top);
                top--;
            } else {
                stringBuilder.append(c);
                top++;
            }
        }

        return stringBuilder.toString();
    }

    /**
     * 解题思路：因为运算符只有+和-，所以每次对数字进行累加只需考虑是＋还是-
     * 维护一个当前符号（+1、-1），其数值与数字前一运算符和当前栈的符号相关
     * 维护一个栈，存储多个括号累计的符号，若遇到（则将当前符号入栈，若遇到）则出栈并将内容赋给当前符号
     *
     * @param s
     * @return
     */
    public int calculate(String s) {
        Stack<Integer> ops = new Stack<>();

        int i = 0;
        int n = s.length();
        ops.push(1);
        int sign = 1;
        int res = 0;

        while (i < n) {
            char c = s.charAt(i);
            if (s.charAt(i) == ' ') {
                i++;
            } else if (s.charAt(i) == '+') {
                sign = ops.peek();
                i++;
            } else if (s.charAt(i) == '-') {
                sign = -ops.peek();
                i++;
            } else if (s.charAt(i) == '(') {
                ops.push(sign);
                i++;
            } else if (s.charAt(i) == ')') {
                sign = ops.pop();
                i++;
            } else {
                long sum = 0;
                while (i < n && Character.isDigit(s.charAt(i))) {
                    sum = sum * 10 + s.charAt(i) - '0';
                    i++;
                }
                res += sign * sum;
            }
        }

        return res;
    }


    /**
     * 解题思路：使用栈维护当前节点的剩余槽位
     * 根节点的槽位为1
     * 若当前遍历的为空节点，则栈顶节点的槽位减一
     * 若当前遍历的为非空节点，则栈顶节点的槽位减一，当前节点的槽位为2压入栈
     * 若栈顶节点的槽位为0，则出栈
     * 若遍历未结束，栈空，或遍历结束，栈非空，则非序列化树
     *
     * @param preorder
     * @return
     */
    public boolean isValidSerialization(String preorder) {
        Stack<Integer> stack = new Stack<>();

        stack.push(1);

        int i = 0;
        int n = preorder.length();
        while (i < n) {
            if (stack.isEmpty()) {
                return false;
            } else if (preorder.charAt(i) == ',') {
                i++;
            } else if (preorder.charAt(i) == '#') {
                int count = stack.pop();
                count--;
                if (count > 0) {
                    stack.push(count);
                }
                i++;
            } else {
                while (i < n && preorder.charAt(i) != ',') {
                    i++;
                }
                int count = stack.pop();
                count--;
                if (count > 0) {
                    stack.push(count);
                }
                stack.push(2);
                i++;
            }
        }
        if (!stack.isEmpty()) {
            return false;
        }

        return true;

    }

    /**
     * 解题思路：乘除先计算，加减存入栈
     * 遍历每个元素，若当前元素为符号或为最后一个数字，则根据前一运算符判断
     * 若为乘除，则将栈顶元素与当前元素前的数字进行计算，若为加减，则将前元素前的数字入栈
     * @param s
     * @return
     */
    public int calculate2(String s) {
        Stack<Integer> digit = new Stack<>();

        int n = s.length();
        int num = 0;
        char pre = '+';
        for (int i = 0; i < n; i++) {
            if(Character.isDigit(s.charAt(i))) {
                num = num * 10 + s.charAt(i) - '0';
            }
            if((!Character.isDigit(s.charAt(i)) && s.charAt(i)!= ' ') || i == (n-1)) {
                switch (pre) {
                    case '+':
                        digit.push(num);
                        break;
                    case '-':
                        digit.push(-num);
                        break;
                    case '*':
                        digit.push(digit.pop() * num);
                        break;
                    case '/':
                        digit.push(digit.pop() / num);
                        break;
                }
                num = 0;
                pre = s.charAt(i);
            }
        }

        int res = 0;
        while (!digit.isEmpty()) {
            res += digit.pop();
        }

        return res;
    }
}