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


}
