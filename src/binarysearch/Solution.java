package binarysearch;

import java.util.*;

public class Solution {
    public Solution() {
    }

    /**
     * 解题思路：通过元素与下一元素的大小关系可确定峰值元素位置
     * 若当前元素小于下一元素，则峰值在右侧
     * 若当前元素大于下一元素，则峰值在左侧（包含当前元素）
     *
     * @param nums
     * @return
     */
    public int findPeakElement(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] < nums[mid + 1]) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }

    /**
     * 解题思路：使用二分查找，先对列查找，找到起始元素小于目标值的行，然后对其行进行二分查找
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int index = searchForColumn(matrix, target);

        return searchForTarget(matrix[index], target);

    }

    private boolean searchForTarget(int[] matrix, int target) {
        int start = 0;
        int end = matrix.length - 1;
        while (start <= end) {
            int mid = (end + start) / 2;
            if (matrix[mid] == target) {
                return true;
            } else if (matrix[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return false;
    }

    private int searchForColumn(int[][] matrix, int target) {
        int start = 0;
        int end = matrix.length - 1;
        while (start < end) {
            //由于选值偏向小的一行，所以确保剩余两行时，中间值偏向大的一行
            int mid = (end + start + 1) / 2;
            if (matrix[mid][0] <= target) {
                start = mid;
            } else {
                end = mid - 1;
            }
        }

        return start;

    }

    /**
     * 解题思路：观察数组发现，最小值左边元素比最右元素大，最小值右边元素比最右元素小
     * 使用二分查找，若中间值大于最右元素，则查找右子区间（不包含中间值）
     * 若中间值小于最右元素，则查找左子区间（包含中间值）
     * 直到区间长度为一，则找到最小值
     *
     * @param nums
     * @return
     */
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = (right - left) / 2 + left;
            if (nums[mid] >= nums[left]) {
                if (nums[left] < nums[right]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                right = mid;
            }
        }

        return nums[left];
    }

    /**
     * 解题思路：若左，中和右指针的元素相同，则无法判定最小值在哪边，所以左指针向后移，右指针向前移
     * 若中间元素小于等于最右元素，则最小值在左边（包括中间元素）
     * 若中间元素大于最右元素，则最小值在右边（不包括中间元素）
     *
     * @param nums
     * @return
     */
    public int findMin2(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = (right - left) / 2 + left;
            if (nums[mid] == nums[right] && nums[left] == nums[right]) {
                left++;
                right--;
            } else if (nums[mid] <= nums[right]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return nums[left];
    }

    /**
     * 解题思路：1.对行设置上下界限
     * 2.用数组存储每列的前缀和
     * 3.把区间Sr - Sl<=k的最大值转化为S1 >=Sr - k的最小值
     * 4.用二叉树的ceiling作为第三步查找的工具
     *
     * @param matrix
     * @param k
     * @return
     */
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < m; i++) {
            int[] sum = new int[n];
            for (int j = i; j < m; j++) {
                for (int l = 0; l < n; l++) {
                    sum[l] += matrix[j][l];
                }
                TreeSet<Integer> set = new TreeSet<>();
                int s = 0;
                set.add(0);
                for (int v :
                        sum) {
                    s += v;
                    Integer ceiling = set.ceiling(s - k);
                    if (ceiling != null) {
                        max = Math.max(max, s - ceiling);
                    }
                    set.add(s);
                }
            }
        }

        return max;
    }

    public boolean search(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = (end - start) / 2 + start;
            if (nums[mid] == target) {
                return true;
            }
            if (nums[start] == nums[end] && nums[start] == nums[mid]) {
                start = start + 1;
                end = end - 1;
            } else if (nums[start] <= nums[mid]) {
                if (target >= nums[start] && target < nums[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {
                if (target > nums[mid] && target <= nums[end]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }

        return false;
    }

    public int removeDuplicates(int[] nums) {
        int i = 1;
        int j = 1;
        while (j < nums.length) {
            if (nums[j] != nums[j -
                    1]) {
                nums[i] = nums[j];
                i++;
            }
            j++;
        }
        return i;
    }

    /**
     * 解题思路：找出在D天内的最小值
     * 1.使用二分查找
     * 2.定左右边界
     * 3.判定中间值，若所需天数小于等于D，则保留左半区间和中间值，若所需天数大于D，则保留右半区间
     *
     * @param weights
     * @param D
     * @return
     */
    public int shipWithinDays(int[] weights, int D) {
        int left = 0;
        int right = 0;
        for (int i = 0; i < weights.length; i++) {
            left = Math.max(left, weights[i]);
            right += weights[i];
        }

        while (left < right) {
            int mid = (right - left) / 2 + left;
            int day = 1;
            int sum = 0;
            for (int i = 0; i < weights.length; i++) {
                if ((sum + weights[i]) > mid) {
                    day++;
                    sum = 0;
                } else {

                }
                sum += weights[i];
            }

            if (day > D) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    /**
     * 解题思路：若制作m束花的最小天数是d，则大于d也能制作m束花，小于d则不能制作m束花，考虑用二分查找
     * 二分查找的下限天数是数组最小值，上限天数是数组最大值
     * 对于特定天数，需编写函数判断该天数能否制作m束花
     * 函数实现：由于制作一束花需要连续k多花，所以使用flower变量存储收集到的花
     * 若这朵花所需天数小于等于特定天数，花朵数量加一，若收集到的花为k朵，则制作一束花
     * 若这朵花所需天数大于特定天数，则之前收集到的花不能制作成一束花，花朵数量清零
     *
     * @param bloomDay
     * @param m
     * @param k
     * @return
     */
    public int minDays(int[] bloomDay, int m, int k) {
        int n = bloomDay.length;
        if ((m * k) > n) {
            return -1;
        }

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            min = Math.min(min, bloomDay[i]);
            max = Math.max(max, bloomDay[i]);
        }

        while (min < max) {
            int mid = (max + min) / 2;
            if (canMake(bloomDay, mid, m, k)) {
                max = mid;
            } else {
                min = mid + 1;
            }
        }

        return min;
    }

    private boolean canMake(int[] bloomDay, int day, int m, int k) {
        int flowers = 0;
        int butch = 0;
        int length = bloomDay.length;
        for (int i = 0; i < length && butch < m; i++) {
            if (bloomDay[i] <= day) {
                flowers++;
                if (flowers == k) {
                    flowers = 0;
                    butch++;
                }
            } else {
                flowers = 0;
            }
        }

        return butch == m ? true : false;
    }

    public boolean xorGame(int[] nums) {
        if ((nums.length % 2) == 0) {
            return true;
        }

        int res = 0;

        for (int i = 0; i < nums.length; i++) {
            res ^= nums[i];
        }

        if (res == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 解题思路：二分查找
     * @param n
     * @return
     */
    public int firstBadVersion(int n) {
        int left = 1;
        int right = n;
        while(left < right) {
//            计算溢出
//            int mid = (right + left) / 2;
            int mid = left + (right - left) / 2;
            if(isBadVersion(mid)) {
                right = mid;
            }else {
                left = mid + 1;
            }
        }

        return left;
    }

    private boolean isBadVersion(int mid) {
        return false;
    }

    public int guessNumber(int n) {
        int left = 1;
        int right = n;
        while (left < right) {
            int mid = left + (right - left) / 2;
            int num = guess(mid);
            if(num == 0) {
                return mid;
            }else if(num < 0) {
                right = mid - 1;
            }else {
                left = mid + 1;
            }
        }

        return left;
    }

    private int guess(int num) {
        return 0;
    }


    public int peakIndexInMountainArray(int[] arr) {
        int n = arr.length;
        int left = 0;
        int right = n-1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if(arr[mid] < arr[mid + 1]) {
                left = mid + 1;
            }else {
                right = mid;
            }
        }

        return left;
    }

    public String smallestGoodBase(String n) {
        long nVal = Long.parseLong(n);
        int mMax = (int)Math.floor(Math.log(nVal) / Math.log(2));
        for(int m = mMax; m > 1; m--) {
            int k = (int) Math.pow(nVal, 1.0/m);
            long mul = 1, sum = 1;
            for (int i = 0; i < m; i++) {
                mul *= k;
                sum += mul;
            }

            if(nVal == sum) {
                return Integer.toString(k);
            }
        }



        return Long.toString(nVal-1);
    }

    public int hIndex(int[] citations) {
        int n = citations.length;

        int left = 0;
        int right = citations.length - 1;

        if(citations[right] == 0) {
            return 0;
        }

        while (left < right) {
            int mid = left +(right - left) / 2;
            if((n - mid) <= citations[mid]) {
                right = mid;
            }else {
                left = mid + 1;
            }
        }

        return left;
    }

    public int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
        int ret = 0;
        int sum = 0;
        int MOD = 1000000007;
        int n = nums1.length;

        int[] rec = new int[n];

        for (int i = 0; i < n; i++) {
            rec[i] = nums1[i];

        }

        Arrays.sort(rec);
        int maxn = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {

            int a = Math.abs(nums1[i] - nums2[i]);
            sum = (sum + a) % MOD;

            int j = binarySearchDiff(rec, nums2[i]);

            if(j < n) {
                maxn = Math.max(maxn, a - (Math.abs(rec[j] - nums2[i])));
            }

            if(j > 0) {
                maxn = Math.max(maxn, a - (Math.abs(rec[j-1] - nums2[i])));
            }

        }

        return (sum - maxn + MOD) % MOD;
    }

    private int binarySearchDiff(int[] rec, int target) {

        int low = 0;
        int high = rec.length -1;
        if(rec[high] < target) {
            return high + 1;
        }

        while (low < high) {
            int mid = low + (high - low) /2 ;

            if(rec[mid] < target) {
                low = mid + 1;
            }else {
                high = mid;
            }
        }

        return low;
    }

    public int search2(int[] nums, int target) {
        int n = nums.length;
        int left = 0;
        int right = n - 1;
        if(n == 0 || nums[left] > target || nums[right] < target) {
            return 0;
        }
        int large = n;


        while (left <= right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] <= target) {
                left = mid + 1;
            }else {
                right = mid - 1;
                large = mid;
            }
        }


        left = 0;
        right = n - 1;
        int small = n;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] < target) {
                left = mid + 1;
            }else {
                right = mid - 1;
                small = mid;
            }
        }

        return large - small;

    }

    private double[] rate;
    private int length;

    /**
     * 解题思路：前缀和+二分查找
     * 选择的数在[1, total]之间
     *
     * @param w
     */
    public Solution(int[] w) {
        int n = w.length;
        length = n;
        rate = new double[n];
        int sum = 0;
        double[] f = new double[n];
        f[0] = w[0];
        sum += w[0];
        for (int i = 1; i < n; i++) {
            sum += w[i];
            f[i] = f[i-1] + w[i];
        }
        for (int i = 0; i < n; i++) {
            rate[i] = f[i]/sum;
        }
    }

    public int pickIndex() {
        Random random = new Random();
        double d = random.nextDouble();
        int left = 0;
        int right = length - 1;
        while (left < right) {
            int mid = (right + left)/2;
            if(rate[mid] >= d) {
                right = mid;
            }else {
                left = mid + 1;
            }
        }

        return left;
    }

    /**
     * 解题思路：模拟+二分查找
     * 模拟：选择1-n中任意一个位置
     * 若该位置大于左右的值，则返回下标
     * 若大于左小于右，则右侧一定有峰值
     * 若小于左大于右，则左侧一定有峰值
     * 若小于左小于右，则两侧都有峰值
     * @param nums
     * @return
     */
    public int findPeakElement2(int[] nums) {
        int left = 0;
        int n = nums.length;
        int right = n - 1;
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            boolean r = (mid + 1 == n) ? true : nums[mid] > nums[mid + 1];
            boolean l = (mid - 1 == 0) ? true : nums[mid] > nums[mid - 1];
            if(l && r) {
                return mid;
            }
            if(l) {
                left = mid + 1;
            }else {
                right = mid - 1;
            }


        }

        return -1;
    }


    public int[] searchRange(int[] nums, int target) {
        int n = nums.length;
        int leftIdx = binarySearchRange(nums, target, true);
        int rightIdx = binarySearchRange(nums, target, false) - 1;

        if(leftIdx <= rightIdx && rightIdx < nums.length && nums[leftIdx] == target && nums[rightIdx] == target) {
            return new int[]{leftIdx, rightIdx};
        }

        return new int[]{-1, -1};

    }

    private int binarySearchRange(int[] nums, int target, boolean flag) {

        int left = 0;
        int right = nums.length - 1;
        int ans = nums.length;
        while (left <= right) {
            int mid = (left + right) / 2;
            if(nums[mid] > target || (flag && nums[mid] >= target)) {
                right = mid - 1;
                ans = mid;
            }else {
                left = mid + 1;
            }
        }

        return ans;
    }

    public boolean canJump(int[] nums) {
        int n = nums.length;
        int max = 0;
        for (int i = 0; i < n; i++) {
            if(max < i) {
                return false;
            }
            max = Math.max(max, nums[i] + i);
        }

        return max >= (n - 1);
    }
}
