package heap;

import java.util.PriorityQueue;

/**
 * 解题思路：使用堆排序，用大小为k的小顶堆，返回堆顶元素
 */
public class KthLargest {

    private PriorityQueue<Integer> queue;
    private int num;

    public KthLargest(int k, int[] nums) {
        queue = new PriorityQueue<>();
        num = k;
        for (int i = 0; i < nums.length; i++) {
            if(queue.size() < k) {
                queue.offer(nums[i]);
            }else if(queue.peek() < nums[i]) {
                queue.poll();
                queue.offer(nums[i]);
            }
        }
    }

    public int add(int val) {

        if(queue.isEmpty() || queue.size() < num) {
            queue.offer(val);
            return queue.peek();
        }
        if(val <= queue.peek()) {
            return queue.peek();
        }else {
            queue.poll();
            queue.offer(val);
            return queue.peek();
        }
    }
}

