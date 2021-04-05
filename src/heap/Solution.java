package heap;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(k <=0 || k > nums.length) {
            return null;
        }

        int[] result = new int[nums.length - k + 1];
        int index = 0;

        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] != o2[0] ? o2[0]-o1[0]:o2[1]-o1[1];
            }
        });

        for (int i = 0; i < k; i++) {
            queue.add(new int[]{nums[i], i});
        }
        result[index++] = queue.peek()[0];
        for (int i = k; i < nums.length; i++) {
            queue.add(new int[]{nums[i], i});
            while (queue.peek()[1] <= i - k) {
                queue.poll();
            }
            result[index++] = queue.peek()[0];
        }



        return result;
    }

    public void adjustHeap2(Compound[] compounds, int length, int point) {
        Compound temp = compounds[point];
        while((point-1)/2 >= 0 && point > 0){
            if(temp.getValue() > compounds[(point-1)/2].getValue()) {
                compounds[point] = compounds[(point-1)/2];
                point = (point-1) / 2;
            }else {
                compounds[point] = temp;
                break;
            }
        }
        if(temp.getValue() > compounds[0].getValue()) {
            compounds[0] = temp;
        }
    }

    public void adjustHeap(Compound[] compounds, int length, int point) {
        Compound temp = compounds[point];
        while((point*2 + 1) < length) {
            int max = point*2+1;
            if((point*2+2) < length) {
                if(compounds[point*2+2].getValue() > compounds[max].getValue() ) {
                    max = point*2 + 2;
                }
            }
            if(temp.getValue() < compounds[max].getValue()) {
                compounds[point] = compounds[max];
                point = max;
            }else {
                compounds[point] = temp;
                break;
            }
        }
        if(compounds[point].getValue() > temp.getValue()) {
            compounds[point] = temp;
        }

    }
}
class Compound {
    private Integer index;
    private Integer value;
    public Compound(Integer index, Integer value) {
        this.index = index;
        this.value = value;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
