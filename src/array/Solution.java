package array;

import java.util.*;

public class Solution {
    // Parameters:
    //    numbers:     an array of integers
    //    length:      the length of array numbers
    //    duplication: (Output) the duplicated number in the array number,length of duplication array is 1,so using duplication[0] = ? in implementation;
    //                  Here duplication like pointor in C/C++, duplication[0] equal *duplication in C/C++
    //    这里要特别注意~返回任意重复的一个，赋值duplication[0]
    // Return value:       true if the input is valid, and there are some duplications in the array number
    //                     otherwise false
    public boolean duplicate(int numbers[], int length, int[] duplication) {
//        HashSet<Integer> numberSet = new HashSet<>();
//        if(length == 0) {
//            return false;
//        }
//
//        for (int i = 0; i < numbers.length; i++) {
//            boolean temp = numberSet.add(numbers[i]);
//            if(!temp) {
//                duplication[0] = numbers[i];
//                return true;
//            }
//        }
//
//        return false;

        if (length == 0) {
            return false;
        }
        for (int i = 0; i < numbers.length; i++) {
            int index = numbers[i];
            if (index >= length) {
                index -= length;
            }
            if (numbers[index] >= length) {
                duplication[0] = index;
                return true;
            }

            numbers[index] += length;
        }

        return false;
    }

    public int[] multiply(int[] A) {
        if (A.length == 0) {
            return A;
        }

        int length = A.length;
        int[] B = new int[length];

        int sum = 1;
        Stack<Integer> stack = new Stack<>();
        stack.push(sum);
        for (int i = 1; i < length; i++) {
            stack.push(sum * A[i - 1]);
            sum *= A[i - 1];
        }

        sum = 1;
        for (int i = length - 1; i >= 0; i--) {
            B[i] = stack.pop() * sum;
            sum *= A[i];
        }


        return B;
    }

    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(target - nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i]) && map.get(nums[i]) != i) {
                result[0] = i;
                result[1] = map.get(nums[i]);
                break;
            }
        }

        return result;
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length1 = nums1.length;
        int length2 = nums2.length;

        double result = 0;

        int length = length1 + length2;

        if (length % 2 == 0) {
            result = getKElement(nums1, nums2, (length / 2)) + getKElement(nums1, nums2, (length / 2) + 1);
            result = result / 2;
        } else {
            result = getKElement(nums1, nums2, (length / 2 + 1));
        }

        return result;
    }

    public double getKElement(int[] nums1, int[] nums2, int k) {
        int index1 = 0;
        int index2 = 0;
        int length1 = nums1.length;
        int length2 = nums2.length;

        while (true) {
            if (index1 == length1) {
                return nums2[index2 + k - 1];
            }

            if (index2 == length2) {
                return nums1[index1 + k - 1];
            }

            if (k == 1) {
                return nums1[index1] < nums2[index2] ? nums1[index1] : nums2[index2];
            }

            int half = k / 2;
            int newIndex1 = Math.min(length1, index1 + half) - 1;
            int newIndex2 = Math.min(length2, index2 + half) - 1;

            if (nums1[newIndex1] <= nums2[newIndex2]) {
                k = k - ((newIndex1 - index1) + 1);
                index1 = newIndex1 + 1;
            } else {
                k = k - ((newIndex2 - index2) + 1);
                index2 = newIndex2 + 1;
            }


        }
    }

    public int fib(int N) {
        int sum = 0;
        if (N <= 0) {
            return 0;
        }
        if (N == 1) {
            return 1;
        }
        int a = 0;
        int b = 1;
        while (N > 1) {
            sum = a + b;
            a = b;
            b = sum;
            N--;
        }

        return sum;
    }


    public int maxArea(int[] height) {
        if (height.length == 0) {
            return 0;
        }
        int start = 0;
        int end = height.length - 1;
        int maxArea = Math.min(height[start], height[end]) * (end - start);

        int i = start;
        int j = end;

        while (i <= j) {
            if (height[i] <= height[j]) {

                if (Math.min(height[i], height[j]) * (j - i) > maxArea) {
                    maxArea = Math.min(height[i], height[j]) * (j - i);
                } else {
                    i++;
                }

            } else {
                if (Math.min(height[i], height[j]) * (j - i) > maxArea) {
                    maxArea = Math.min(height[i], height[j]) * (j - i);
                } else {
                    j--;
                }

            }
        }

        return maxArea;
    }

    public void rotate(int[] nums, int k) {
        if (k <= 0) {
            return;
        }
        k = k % nums.length;

        rotateArray(nums, 0, nums.length - 1);

        rotateArray(nums, 0, k - 1);
        rotateArray(nums, k, nums.length - 1);
    }

    private void rotateArray(int[] array, int start, int end) {
        if (start >= end) {
            return;
        }
        for (int i = 0; i <= (end - start) / 2; i++) {
            int temp = array[start + i];
            array[start + i] = array[end - i];
            array[end - i] = temp;

        }
    }

    public boolean increasingTriplet(int[] nums) {
        if (nums.length < 3) {
            return false;
        }
        int small = Integer.MIN_VALUE;
        int mid = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < small) {
                small = nums[i];
            } else if (nums[i] < mid) {
                mid = nums[i];
            } else if (nums[i] > mid) {
                return true;
            }
        }

        return false;

    }

    /**
     * 解题思路：
     * 1. 动态规划
     * 第i天有两种状态：持有股票和无股票，分别计算这两种状态对应的最大利润
     * 当天持有股票的最大利润等于 前一天持有股票 和 前一天无股票减去当天股价 的最大值
     * 当天无股票的最大利润等于 前一天无股票 和 前一天持有股票加上当天股价 的最大值
     * 使用两个临时变量优化空间复杂度
     * 2. 贪心算法
     * 若某段时间股票一直升，则它们之间的差值为利润的最大值
     * 若股票一直跌，则不买入
     * 优化：利润等于 两天的差值 和 零 的最大值
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length < 2) {
            return 0;
        }

        int n = prices.length;
        int[][] dp = new int[n][2];
        int a = 0;
        int b = -prices[0];
        int c= 0, d = 0;
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < n; i++) {
            c = Math.max(a, b + prices[i]);
            d = Math.max(b, a - prices[i]);
            a = c;
            b = d;
        }

        return c > 0 ? c:0;

    }

    /**
     * 普通数组问题
     *
     * @param digits
     * @return
     */
    public int[] plusOne(int[] digits) {
        if (digits == null || digits.length == 0) {
            return digits;
        }
        int[] newDigits = new int[digits.length + 1];


        digits[digits.length - 1] += 1;
        if (digits[digits.length - 1] >= 10) {
            digits[digits.length - 1] = 0;
            int i = digits.length - 2;
            int carry = 1;
            while (i >= 0 && carry == 1) {
                digits[i] += 1;
                if (digits[i] < 10) {
                    carry = 0;
                    break;
                } else {
                    digits[i] = 0;
                }
                i--;
            }
            if (i < 0) {
                for (int j = 0; j < digits.length; j++) {
                    newDigits[j + 1] = digits[j];
                }
                newDigits[0] = 1;
                return newDigits;
            }
        }
        return digits;
    }

    public int findDuplicate(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length - 1;
        while (nums[n] != nums[nums[n] - 1]) {
            int temp = nums[n];
            int index = nums[n] - 1;
            nums[n] = nums[index];
            nums[index] = temp;

        }

        return nums[n];
    }

    public List<String> summaryRanges(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }

        int start = 0;
        int end = 0;
        int value = nums[0];
        List<String> ranges = new ArrayList<>();
        for (int i = 1; i <= nums.length; i++) {
            ++value;
            if (i == nums.length || value != nums[i]) {
                end = i - 1;
                if (start == end) {
                    ranges.add(String.valueOf(nums[start]));
                } else {
                    ranges.add(nums[start] + "->" + nums[end]);
                }
                if (i < nums.length)
                    value = nums[i];
                start = end = i;
            }

        }


        return ranges;

    }

    public void sortColors(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }

        int p0 = 0;
        int p1 = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                int temp = nums[i];
                nums[i] = nums[p1];
                nums[p1] = temp;
                p1++;
            }
            if (nums[i] == 0) {
                int temp = nums[i];
                nums[i] = nums[p0];
                nums[p0] = temp;
                if (p0 < p1) {
                    temp = nums[i];
                    nums[i] = nums[p1];
                    nums[p1] = temp;
                }
                p0++;
                p1++;
            }
        }

//        int i = 0;
//        int j = nums.length - 1;
//        while (i < j) {
//            while (i < j && nums[i] < 1) i++;
//            while (i < j && nums[j] >= 1) j--;
//            int temp = nums[i];
//            nums[i] = nums[j];
//            nums[j] =temp;
//        }
//
//        j = nums.length - 1;
//        while (i < j){
//            while (i < j && nums[i] == 1) i++;
//            while (i < j && nums[j] == 2) j--;
//            int temp = nums[i];
//            nums[i] = nums[j];
//            nums[j] =temp;
//        }


    }

    /**
     * 使用并查集和小顶堆
     *
     * @param s
     * @param pairs
     * @return
     */
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        if (s == null || s.length() == 0) {
            return null;
        }

        char[] ch = s.toCharArray();
        int[] parent = new int[ch.length];

        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
        for (List<Integer> pair :
                pairs) {
            int a = pair.get(0);
            int b = pair.get(1);
            if (findParent(a, parent) != findParent(b, parent)) {
                parent[findParent(a, parent)] = findParent(b, parent);
            }
        }
        Map<Integer, PriorityQueue<Character>> map = new HashMap<>();

        for (int i = 0; i < parent.length; i++) {
            int ancestor = findParent(i, parent);
            PriorityQueue<Character> p = map.get(ancestor);
            if (p == null) {
                p = new PriorityQueue<>();
                p.add(ch[i]);
                map.put(ancestor, p);
            } else {
                p.add(ch[i]);
            }
        }

        StringBuilder str = new StringBuilder();
        for (int i = 0; i < parent.length; i++) {
            PriorityQueue<Character> p = map.get(parent[i]);
            str.append(p.poll());
        }


        return str.toString();
    }


    private int findParent(int a, int[] parent) {
        if (parent[a] != a) {
            parent[a] = findParent(parent[a], parent);
        }
        return parent[a];
    }


    /**
     * 普通数组处理问题，考虑数值溢出问题
     *
     * @param A
     * @return
     */
    public List<Boolean> prefixesDivBy5(int[] A) {
        List<Boolean> result = new ArrayList<>();
        if (A == null || A.length == 0) {
            return result;
        }

        int sum = 0;
        for (int i = 0; i < A.length; i++) {
            sum = sum << 1 + A[i];
            sum = sum % 5;
            if (sum == 0) {
                result.add(true);
            } else {
                result.add(false);
            }
        }

        return result;
    }

    /**
     * 数组加法
     */
    public List<Integer> addToArrayForm(int[] A, int K) {
        Stack<Integer> stack = new Stack<>();
        while (K != 0) {
            stack.push(K % 10);
            K = K / 10;
        }

        int[] B = new int[stack.size()];
        for (int i = 0; i < B.length; i++) {
            B[i] = stack.pop();
        }

        int[] C = new int[Math.max(A.length, B.length) + 1];
        int carry = 0;
        for (int i = A.length - 1, j = B.length - 1, k = C.length - 1; i >= 0 || j >= 0; i--, j--, k--) {
            if (i < 0) {
                C[k] = B[j] + carry;
                carry = C[k] / 10;
                C[k] = C[k] % 10;
            } else if (j < 0) {
                C[k] = A[i] + carry;
                carry = C[k] / 10;
                C[k] = C[k] % 10;
            } else {
                C[k] = A[i] + B[j] + carry;
                carry = C[k] / 10;
                C[k] = C[k] % 10;
            }

        }
        List<Integer> list = new ArrayList<>();
        if (carry == 1) {
            list.add(1);
        }


        for (int i = 1; i < C.length; i++) {
            list.add(C[i]);
        }

        return list;


    }

    /**
     * 重点：处理数组个数，越界等问题
     *
     * @param nums
     * @return
     */
    public int findLengthOfLCIS(int[] nums) {
        int max = 0;
        int start = 0;
        for (int k = 0; k < nums.length; k++) {
            if (k > 0 && nums[k] <= nums[k - 1]) {
                start = k;
            }
            max = Math.max(max, k - start + 1);
        }


        return max;
    }

    /**
     * 存储元素对，i*（i-1）/ 2=0+1+2+3+...+(i-1)
     *
     * @param dominoes
     * @return
     */
    public int numEquivDominoPairs(int[][] dominoes) {
        Map<Domino, Integer> map = new HashMap<>();

        for (int i = 0; i < dominoes.length; i++) {
            Domino domino = new Domino(dominoes[i][0], dominoes[i][1]);
            if (map.containsKey(domino)) {
                Integer integer = map.get(domino);
                integer++;
                map.put(domino, integer);
            } else {
                map.put(domino, 1);
            }
        }

        int max = 0;
        for (Integer i :
                map.values()) {
            if (i > 1) {
                max = max + (i - 1) * i / 2;
            }
        }

        return max;
    }

    class Domino {
        private int a;
        private int b;

        public Domino(int a, int b) {
            this.a = Math.min(a, b);
            this.b = Math.max(a, b);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Domino domino = (Domino) o;
            return a == domino.a &&
                    b == domino.b;
        }

        @Override
        public int hashCode() {
            return Objects.hash(a, b);
        }
    }

    /**
     * 解题思路：找出中心索引对应的方程式 sum = total - sum - num[i] -> 2*sum+num[i]=total
     *
     * @param nums
     * @return
     */
    public int pivotIndex(int[] nums) {
        int[] sum = new int[nums.length];
        if (nums.length == 0) {
            return -1;
        }

        //可优化
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }

        for (int i = 0; i < nums.length; i++) {
            if ((sum[i] - nums[i]) == (sum[nums.length - 1] - sum[i])) {
                return i;
            }
        }

        return -1;
    }

    /**
     * 解题思路：用哈希表存储第一个表的元素以及个数，再遍历第二个表去找出并集
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return intersect(nums2, nums1);
        }

        List<Integer> list = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            int count = map.getOrDefault(nums1[i], 0) + 1;
            map.put(nums1[i], count);
        }

        for (int j = 0; j < nums2.length; j++) {
            Integer count = map.getOrDefault(nums2[j], 0);
            if (count > 0) {
                list.add(nums2[j]);
                if (--count > 0) {
                    map.put(nums2[j], count);
                } else {
                    map.remove(nums2[j]);
                }
            }
        }


        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }

        return result;
    }

    /**
     * 解题思路：用第一行第一列存储对应为零的数
     *
     * @param matrix
     */
    public void setZeroes(int[][] matrix) {
        int temp = 1;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    if (j == 0) {
                        temp = 0;
                    } else {
                        matrix[0][j] = 0;
                    }
                }
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        if (temp == 0) {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }


    }

    /**
     * 将A、B数组中元素的组合存储在HashMap中，再遍历C、D数组，如果两者之和等于零，则将计数器加上对应的键的值
     *
     * @param A
     * @param B
     * @param C
     * @param D
     * @return
     */
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        int n = A.length;

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map.put(A[i] + B[j], map.getOrDefault(A[i] + B[j], 0) + 1);

            }
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int temp = -(C[i] + D[j]);
                if (map.containsKey(temp)) {
                    count += map.get(temp);
                }
            }
        }

        return count;
    }

    /**
     * 解题思路：数学方程式：sumA - x + y = sumB + x - y
     * 把B数组的元素放入哈希表，便于查找
     *
     * @param A
     * @param B
     * @return
     */
    public int[] fairCandySwap(int[] A, int[] B) {
        int sumA = 0;
        int sumB = 0;
        for (int i = 0; i < A.length; i++) {
            sumA += A[i];
        }

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < B.length; i++) {
            sumB += B[i];
            set.add(B[i]);
        }

        for (int i = 0; i < A.length; i++) {
            int temp = sumB - sumA + 2 * A[i];
            if (temp % 2 == 0) {
                if (set.contains(temp / 2)) {
                    return new int[]{A[i], temp / 2};
                }
            }

        }

        return new int[]{};
    }

    /**
     * 解题思路：使用滑动窗口，长度为k，每次进入一个，删除队首，更新其最大平均数
     *
     * @param nums
     * @param k
     * @return
     */
    public double findMaxAverage(int[] nums, int k) {
        if (nums.length < k) {
            return 0;
        }
        double sum = 0;
        double maxAvg = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        maxAvg = sum / k;
        for (int i = k; i < nums.length; i++) {
            sum += nums[i] - nums[i - k];
            maxAvg = Math.max(sum / k, maxAvg);
        }

        return maxAvg;
    }

    public boolean checkPossibility(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                int x = nums[i];
                nums[i] = nums[i + 1];
                if (isSorted(nums)) {
                    return true;
                }
                nums[i] = x;
                nums[i + 1] = x;
                return isSorted(nums);
            }
        }

        return true;
    }

    private boolean isSorted(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 解题思路：把数组元素转化为一维，再转化为新的二维数组
     *
     * @param nums
     * @param r
     * @param c
     * @return
     */
    public int[][] matrixReshape(int[][] nums, int r, int c) {
        int n = nums.length;
        int m = nums[0].length;
        if (n * m != r * c) {
            return nums;
        }

        int k = 0;
        int l = 0;
        int[][] res = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (l == m) {
                    k++;
                    l = 0;
                }
                res[i][j] = nums[k][l];
                l++;
            }
        }

        return res;
    }

    /**
     * 解题思路：排序求和
     *
     * @param nums
     * @return
     */
    public int arrayPairSum(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        int res = 0;
        for (int i = 0; i < nums.length; i += 2) {
            res += nums[i];
        }
        return res;
    }

    private void quickSort(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }
        int position = quickSort2(nums, start, end);
        quickSort(nums, start, position - 1);
        quickSort(nums, position + 1, end);
    }

    private int quickSort2(int[] nums, int start, int end) {
        if (start >= end) {
            return start;
        }

        int position = nums[start];
        while (start < end) {
            while (start < end && nums[end] >= position) {
                end--;
            }
            nums[start] = nums[end];
            while (start < end && nums[start] <= position) {
                start++;
            }
            nums[end] = nums[start];
        }

        nums[start] = position;
        return start;
    }

    /**
     * 解题思路：遍历数组，若该数出现，则对应下标的数值加n，最后统计没出现的元素
     *
     * @param nums
     * @return
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> result = new ArrayList<>();
        int n = nums.length;
        for (int i = 0; i < nums.length; i++) {
            nums[(nums[i] - 1) % n] += n;
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= n) {
                result.add(i);
            }
        }
        return result;
    }

    /**
     * 解题思路：遍历数组（忽略第0行和第0列），每个元素与其左上角元素进行比较
     *
     * @param matrix
     * @return
     */
    public boolean isToeplitzMatrix(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;

        for (int i = 0, j = 0; j < m; j++) {
            int value = matrix[i][j];
            int row = i;
            int col = j;
            while (isInside(row, col, n, m)) {
                if (matrix[row][col] != value) {
                    return false;
                }
                row++;
                col++;
            }
        }

        for (int i = 0, j = 0; i < n; i++) {
            int value = matrix[i][j];
            int col = j;
            int row = i;
            while (isInside(row, col, n, m)) {
                if (matrix[row][col] != value) {
                    return false;
                }
                row++;
                col++;
            }
        }

        return true;
    }

    private boolean isInside(int row, int col, int n, int m) {
        if (row >= n || col >= m) {
            return false;
        }
        return true;
    }

    /**
     * 解题思路：运用性质（每个数字等于上一行的左右两个数字之和）
     * 使用集合存储前面的元素供使用
     * 空间优化：使用两个集合存储前一行和当前行的元素
     * 使用一个集合，从后开始赋值
     *
     * @param rowIndex
     * @return
     */
    public List<Integer> getRow(int rowIndex) {
        List<List<Integer>> array = new ArrayList<>();
        for (int i = 0; i <= rowIndex; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    row.add(1);
                } else {
                    row.add(array.get(i - 1).get(j - 1) + array.get(i - 1).get(j));
                }
            }
            array.add(row);
        }

        return array.get(rowIndex);
    }

    /**
     * 优化思路：使用双指针，通过比较可知，若左右指针的元素相同，则翻转值，若不相同，则不变，若n是奇数，则指针下标相等时翻转，一次遍历即可完成
     * @param A
     * @return
     */
    public int[][] flipAndInvertImage(int[][] A) {
        int n = A.length;
        int m = A[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m / 2; j++) {
                int temp = A[i][j];
                A[i][j] = A[i][m - 1 - j];
                A[i][m - 1 - j] = temp;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                A[i][j] ^= 1;
            }
        }

        return A;
    }

    /**
     * 解题思路：分情况讨论，若有负有正，则最大值可能是三个最大正数的乘积或两个最小负数和最大正数的乘积
     * 其余情况最大值都是三个最大正数的乘积
     * 解决方案一：对数组排序，求出三个最大正数的乘积、两个最小负数和最大正数的乘积，得出最大值
     * 解决方案二：直接遍历数组，寻找三个最大正数和两个最小负数，得出最大值
     * @param nums
     * @return
     */
    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;

        return Math.max(nums[n-1]*nums[n-2]*nums[n-3],nums[0]*nums[1]*nums[n-1]);
    }

    /**
     * 解题思路：把n行m列转为m行n列，遍历数组，把原数组第i行的元素赋值到新数组的第i列
     * @param matrix
     * @return
     */
    public int[][] transpose(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] res = new int[m][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                res[j][i] = matrix[i][j];
            }
        }

        return res;
    }

    /**
     * 解题思路：遍历数组 A，若既遇到了 A[i]>A[i+1] 又遇到了 A[i']<A[i'+1] ，则说明 A 既不是单调递增的，也不是单调递减的。

     * @param A
     * @return
     */
    public boolean isMonotonic(int[] A) {
        Map<String, Boolean> map = new HashMap<>();
        for (int i = 0; i < A.length-1; i++) {
            if(A[i] == A[i+1]) {
                continue;
            }else if(A[i] < A[i+1]) {
                if(map.get("flag") == null) {
                    map.put("flag", true);
                }
                if(!map.get("flag")) {
                    return false;
                }
            }else {
                if(map.get("flag") == null) {
                    map.put("flag", false);
                }
                if(map.get("flag")) {
                    return false;
                }
            }
        }
        return true;
    }

    public int majorityElement(int[] nums) {
        int n = nums.length;

        int key = 0;
        int count = 0;
        for (int i = 0; i < n; i++) {
            if(count == 0) {
                key = nums[i];
            }
            count = count + (nums[i] == key ? 1 : -1);

        }

        count = 0;
        for (int i = 0; i < n; i++) {
            if(nums[i] == key) {
                count++;
            }
        }



        return (count > n/2 ? key : -1);

    }

}
