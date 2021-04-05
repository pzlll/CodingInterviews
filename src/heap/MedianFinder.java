package heap;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 解题思路：定义两个堆，大顶堆存储小的元素，小顶堆存储大的元素
 * 每次添加元素，判断其与两边堆顶元素的大小，并调整两边的数量相等或左边大于右边一个元素
 * 根据大顶堆小顶堆元素个数，返回中位数
 */
public class MedianFinder {

    private PriorityQueue<Integer> small;
    private PriorityQueue<Integer> large;

    public MedianFinder() {
        small = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        large = new PriorityQueue<>();
    }

    public void addNum(int num) {
        if (small.isEmpty()) {
            small.add(num);
        } else if (num <= small.peek()) {
            small.add(num);
            if (small.size() > large.size() + 1) {
                large.add(small.poll());
            }
        } else if (large.isEmpty()) {
            large.add(num);
        } else {
            large.add(num);
            if (large.size() > small.size()) {
                small.add(large.poll());
            }

        }
    }

    public double findMedian() {
        if (small.size() > large.size()) {
            return small.peek();
        } else {
            return (small.peek() + large.peek()) / 2.0;
        }
    }
}
