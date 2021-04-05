package cn;

import java.util.Stack;

public class No9 {

}

class CQueue {

    private Stack<Integer> first;
    private Stack<Integer> second;

    public CQueue() {
        first = new Stack<>();
        second = new Stack<>();
    }

    public void appendTail(int value) {
        first.push(value);
    }

    public int deleteHead() {
        if(!second.isEmpty()) {
            int value = second.pop();
            return value;
        }

        if(!first.isEmpty()) {
            while(!first.isEmpty()) {
                second.push(first.pop());
            }

            int value = second.pop();
            return value;
        }

        return -1;
    }
}
