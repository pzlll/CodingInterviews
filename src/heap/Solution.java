package heap;

import java.lang.reflect.Array;
import java.util.*;

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

    /**
     * 解题思路：使用哈系表存储对应单词的频率
     * 使用优先队列的小顶堆找出前K个单词
     * 优先队列的使用：默认是小顶堆，以o1为插入值，返回结果为正值则表示o1比o2大，返回结果为负值则表示o1比o2小
     * @param words
     * @param k
     * @return
     */
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> freq = new HashMap<>();
        int n = words.length;
        for (int i = 0; i < n; i++) {
            freq.put(words[i], freq.getOrDefault(words[i], 0) + 1);
        }

        PriorityQueue<Map.Entry<String, Integer>> kQueue = new PriorityQueue<>(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                int v1 = o1.getValue() - o2.getValue();
                if(v1 != 0) {
                    return v1;
                }
                return o2.getKey().compareTo(o1.getKey());
            }
        });

        for (Map.Entry<String, Integer> entry :
             freq.entrySet()) {
            kQueue.offer(entry);
            if(kQueue.size() > k) {
                kQueue.poll();
            }
        }

        String[] ret = new String[k];
        for (int i = (k-1); i >= 0; i--) {
            ret[i] = kQueue.poll().getKey();
        }

        return Arrays.asList(ret);
    }

    /**
     * 解题思路：
     * 哈希表+大顶堆：先遍历字符串，得到每个字符的频率，然后将每个字符存入大顶堆（根据其频率大小比较）
     * 哈希表+桶排序：先遍历字符串，得到每个字符的频率且找到最大频率，设置频率为1-max的桶，
     * 将每个字符根据其频率存入桶中，从大到小遍历桶，得到答案
     *
     * @param s
     * @return
     */
    public String frequencySort(String s) {
        Map<Character, Integer> freq = new HashMap<>();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<Character> priorityQueue = new PriorityQueue<>(new Comparator<Character>() {
            @Override
            public int compare(Character o1, Character o2) {
                return freq.get(o2) - freq.get(o1);
            }
        });

        for (char c :
                freq.keySet()) {
            priorityQueue.offer(c);
        }

        StringBuffer str = new StringBuffer();

        for (int i = 0; i < freq.size(); i++) {
            char c = priorityQueue.poll();
            int count = freq.get(c);

            for (int j = 0; j < count; j++) {
                str.append(c);
            }
        }

        return str.toString();
    }

    class Power{
        private int index;
        private int power;

        public Power(int index, int power) {
            this.index = index;
            this.power = power;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public int getPower() {
            return power;
        }

        public void setPower(int power) {
            this.power = power;
        }
    }

    /**
     * 解题思路：二分查找+堆排序
     * @param mat
     * @param k
     * @return
     */
    public int[] kWeakestRows(int[][] mat, int k) {
        int n = mat.length;
        PriorityQueue<Power> queue = new PriorityQueue<>(new Comparator<Power>() {
            @Override
            public int compare(Power o1, Power o2) {
                return ((o1.getPower() - o2.getPower()) == 0 ? (o2.getIndex() - o1.getIndex()) : (o2.getPower() - o1.getPower()));
            }
        });

        for (int i = 0; i < n; i++) {
            int count = getCount(mat[i]);
            Power power = new Power(i, count);
            queue.add(power);
            if(queue.size() > k) {
                queue.poll();
            }
        }

        int[] ret = new int[k];
        for (int i = k-1; i >= 0; i--) {
            Power p = queue.poll();
            ret[i] = p.getIndex();
        }

        return ret;
    }

    private int getCount(int[] ints) {
        int left = 0;
        int right = ints.length - 1;

        if(ints[right] == 1) {
            return (right + 1);
        }

        while (left < right) {
            int mid = left + (right - left) / 2;
            if(ints[mid] == 1) {
                left = mid + 1;
            }else {
                right = mid;
            }
        }

        return left;

    }

    public int[] smallestK(int[] arr, int k) {
        if(arr.length == 0 || k == 0) {
            return new int[]{};
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        queue.offer(arr[0]);

        for (int i = 1; i < arr.length; i++) {
            if(queue.size() < k || (queue.peek() > arr[i])) {
                queue.offer(arr[i]);
            }
            if(queue.size() > k) {
                queue.poll();
            }
        }

        k = queue.size();
        int[] res = new int[k];

        for (int i = 0; i < k; i++) {
            res[i] = queue.poll();
        }

        return res;
    }

    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n = profits.length;
        int[][] ipos = new int[n][3];
        for (int i = 0; i < n; i++) {
            ipos[i][0] = profits[i];
            ipos[i][1] = capital[i];
            ipos[i][2] = i;
        }

        Arrays.sort(ipos, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return (o1[1] - o2[1] == 0) ? o1[2] - o2[2] : o1[1] - o2[1];
            }
        });

        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return (o2[0] - o1[0] == 0) ? o1[2] - o2[2] : o2[0] - o1[0];
            }
        });

        int i = 0;
        while (k > 0) {
            while (i < n && ipos[i][1] <= w) {
                queue.add(ipos[i]);
                i++;
            }

            if(!queue.isEmpty()) {
                int[] p = queue.poll();
                w += p[0];
                k--;
            }else {
                break;
            }
        }

        return w;
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
