package searchandsort;

import org.junit.Test;

import java.util.*;

public class Solution {

    public int minNumberInRotateArray(int[] array) {
        int size = array.length;

        if (size == 0) {
            return 0;
        }

        if (array[0] == array[size - 1]) {
            int min = array[0];
            for (int i = 1; i < size; i++) {
                if (array[i] < min) {
                    return array[i];
                }
            }
            return min;
        }

        int left = 0;
        int right = size - 1;

        int mid = left;

        while (array[left] >= array[right]) {
            mid = (left + right) / 2;
            if (array[mid] >= array[left]) {
                left = mid;
            } else {
                right = mid;
            }

            mid = right;

            if (right - left <= 1) {
                mid = right;
                break;
            }
        }

        return array[mid];
    }

    public int findKthLargest(int[] nums, int k) {

        int position = quickSort(nums, 0, nums.length - 1, k);


        return nums[position];


    }

    private int quickSort(int[] nums, int start, int end, int k) {
        if (start >= end) {
            return start;
        }

        int i = start;
        int j = end;
        int temp = nums[i];
        int position = 0;
        while (i < j) {
            while (i < j && nums[j] >= temp) {
                j--;
            }
            nums[i] = nums[j];

            while (i < j && nums[i] < temp) {
                i++;
            }
            nums[j] = nums[i];
        }

        nums[i] = temp;

        if (nums.length - i == k) {
            position = i;
        } else if (nums.length - i > k) {
            position = quickSort(nums, i + 1, end, k);
        } else {
            position = quickSort(nums, start, i - 1, k);
        }

        return position;
    }

    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> num = new ArrayList<>();
        if (nums.length == 0) {
            return num;
        }

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            Integer key = nums[i];
            Integer value = map.get(key);
            if (value == null) {
                map.put(key, 1);
            } else {
                map.put(key, value + 1);
            }
        }


        Collection<Integer> values = map.values();

        int max = 1;

        for (Integer value :
                values) {
            if (value > max) {
                max = value;
            }
        }

        ArrayList<Integer>[] buckets = new ArrayList[max + 1];

        Set<Integer> keys = map.keySet();

        for (Integer key :
                keys) {
            Integer value = map.get(key);
            if (buckets[value] == null) {
                buckets[value] = new ArrayList<>();
            }

            buckets[value].add(key);
        }

        for (int i = buckets.length - 1; i >= 0; i--) {
            if (k > 0) {
                if (buckets[i] != null) {
                    for (Integer temp : buckets[i]) {
                        num.add(temp);
                        k--;
                        if (k <= 0) {
                            break;
                        }
                    }
                }

            }

        }


        return num;

    }

    public String frequencySort(String s) {
        char[] chars = s.toCharArray();

        HashMap<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < chars.length; i++) {
            map.put(chars[i], map.getOrDefault(chars[i], 0) + 1);
        }

        ArrayList<Character>[] buckets = new ArrayList[chars.length];

        for (Character c :
                map.keySet()) {
            int value = map.get(c);
            if (buckets[value] == null) {
                buckets[value] = new ArrayList<Character>();
            }

            buckets[value].add(c);
        }

        StringBuilder str = new StringBuilder();

        for (int i = buckets.length - 1; i >= 0; i--) {
            if (buckets[i] != null) {
                for (Character c :
                        buckets[i]) {
                    int temp = i;
                    while (temp > 0) {
                        str.append(c);
                        temp--;
                    }
                }
            }

        }

        return str.toString();
    }

    /**
     * 排序，临界点判断
     *
     * @param nums
     */
    public void wiggleSort(int[] nums) {
        quickSort(nums);
        int[] small = new int[(nums.length + 1) / 2];
        int[] large = new int[nums.length / 2];

        for (int i = 0; i <= (nums.length - 1) / 2; i++) {
            small[i] = nums[i];
        }

        for (int i = (nums.length + 1) / 2, j = 0; i < nums.length; i++, j++) {
            large[j] = nums[i];
        }

        reverse(small);
        reverse(large);

        for (int i = 0, j = 0, k = 0; i < nums.length; i++) {
            if (i % 2 == 0) {
                nums[i] = small[j];
                j++;
            } else {
                nums[i] = large[k];
                k++;
            }
        }


    }

    private void reverse(int[] small) {
        for (int i = 0; i < (small.length) / 2; i++) {
            int temp = small[i];
            small[i] = small[small.length - 1 - i];
            small[small.length - 1 - i] = temp;
        }
    }

    private void quickSort(int[] nums) {
        quickSort(0, nums.length - 1, nums);

    }

    private void quickSort(int i, int i1, int[] nums) {
        if (i >= i1) {
            return;
        }
        int start = i;
        int end = i1;
        int temp = nums[i];
        while (i < i1) {
            while (i1 > i && nums[i1] >= temp) {
                i1--;
            }
            nums[i] = nums[i1];
            while (i < i1 && nums[i] <= temp) {
                i++;
            }
            nums[i1] = nums[i];
        }

        nums[i1] = temp;

        quickSort(start, i1 - 1, nums);
        quickSort(i1 + 1, end, nums);
    }

//    public double[] medianSlidingWindow(int[] nums, int k) {
//
//    }
//
//    class DualHeap {
//        private PriorityQueue<Integer> small;
//        private PriorityQueue<Integer> large;
//        private Map<Integer, Integer> freq;
//
//        public DualHeap(int k) {
//
//        }
//    }

    /**
     * 解题关键：自定义排序，先转化为字符串，用ab和ba的字符串大小来判断a和b的大小
     *
     * @param nums
     * @return
     */
    public String largestNumber(int[] nums) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            list.add(Integer.toString(nums[i]));
        }

        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String s1 = o1 + o2;
                String s2 = o2 + o1;
                return s2.compareTo(s1);
            }
        });

        if (list.get(0).equals("0")) {
            return "0";
        }

        StringBuilder str = new StringBuilder();
        for (String s :
                list) {
            str.append(s);
        }
        return str.toString();
    }

    /**
     * 解题思路：查找元素i前面k个元素，看是否有在区间（x-t, x+t）之间，若有，则返回成功
     * 使用二叉树存储前k个元素，并进行查找
     * @param nums
     * @param k
     * @param t
     * @return
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Long> treeSet = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            Long ceiling = treeSet.ceiling((long) nums[i] - (long) t);
            if (ceiling != null && ceiling <=  ((long)nums[i] + (long)t)) {
                return true;
            }
            treeSet.add((long) nums[i]);
            if (treeSet.size() > k) {
                treeSet.remove((long) nums[i - k]);
            }
        }

        return false;
    }
}
