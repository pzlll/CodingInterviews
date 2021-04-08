package binarysearch;

public class Solution {
    /**
     * 解题思路：通过元素与下一元素的大小关系可确定峰值元素位置
     * 若当前元素小于下一元素，则峰值在右侧
     * 若当前元素大于下一元素，则峰值在左侧（包含当前元素）
     * @param nums
     * @return
     */
    public int findPeakElement(int[] nums) {
        int start = 0;
        int end = nums.length-1;
        while(start < end) {
            int mid = start + (end -start)/2;
            if(nums[mid] < nums[mid+1]) {
                end = mid;
            }else {
                start = mid+1;
            }
        }
        return start;
    }

    /**
     * 解题思路：使用二分查找，先对列查找，找到起始元素小于目标值的行，然后对其行进行二分查找
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
        int end = matrix.length-1;
        while(start <= end) {
            int mid = (end + start) / 2;
            if(matrix[mid] == target) {
                return true;
            }else if(matrix[mid] < target) {
                start = mid + 1;
            }else {
                end = mid - 1;
            }
        }

        return false;
    }

    private int searchForColumn(int[][] matrix, int target) {
        int start = 0;
        int end  = matrix.length - 1;
        while(start < end) {
            //由于选值偏向小的一行，所以确保剩余两行时，中间值偏向大的一行
            int mid = (end + start + 1) / 2;
            if(matrix[mid][0] <= target) {
                start = mid;
            }else{
                end = mid - 1;
            }
        }

        return start;

    }

    /**
     *解题思路：观察数组发现，最小值左边元素比最右元素大，最小值右边元素比最右元素小
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
        while(left < right) {
            int mid = (right - left) / 2 + left;
            if(nums[mid] >= nums[left]) {
                if(nums[left] < nums[right]) {
                    right = mid - 1;
                }else {
                    left = mid + 1;
                }
            }else {
                right = mid;
            }
        }

        return nums[left];
    }


}
