package stackandqueue;

public class MyCircularDeque {
    private int[] queue;
    private int length;
    private int front;
    private int tail;
    public MyCircularDeque(int k) {
        length = k + 1;
        queue = new int[k + 1];
        front = tail = 0;
    }

    public boolean insertFront(int value) {
        if(isFull()) {
            return false;
        }
        front = (front - 1 + length) % length;
        queue[front] = value;
        return true;
    }

    public boolean insertLast(int value) {
        if(isFull()) {
            return false;
        }
        queue[tail] = value;
        tail = (tail + 1) % length;
        return true;
    }

    public boolean deleteFront() {
        if(isEmpty()) {
            return false;
        }
        front = (front + 1) % length;
        return true;
    }

    public boolean deleteLast() {
        if(isEmpty()) {
            return false;
        }
        tail = (tail - 1 +  length) % length;
        return true;
    }

    public int getFront() {
        if(isEmpty()) {
            return -1;
        }
        return queue[front];
    }

    public int getRear() {
        if(isEmpty()) {
            return -1;
        }
        return queue[(tail - 1 + length) % length];
    }

    public boolean isEmpty() {
        return tail == front;
    }

    public boolean isFull() {
        return  ((tail + 1) % length) == front;

    }
}
