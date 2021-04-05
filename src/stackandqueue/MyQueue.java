package stackandqueue;

import java.util.Stack;

public class MyQueue {

    private Stack<Integer> in;
    private Stack<Integer> out;

    /** Initialize your data structure here. */
    public MyQueue() {
        in = new Stack<>();
        out = new Stack<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        in.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if(out.isEmpty() && in.isEmpty()) {
            return -1;
        }
        if(out.isEmpty()) {
            while(!in.isEmpty()) {
                out.push(in.pop());
            }
        }
        return out.pop();
    }

    /** Get the front element. */
    public int peek() {
        if(out.isEmpty() && in.isEmpty()) {
            return -1;
        }
        if(out.isEmpty()) {
            while(!in.isEmpty()) {
                out.push(in.pop());
            }
        }
        return out.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        if(out.isEmpty() && in.isEmpty()) {
            return true;
        }else {
            return false;
        }
    }
}
