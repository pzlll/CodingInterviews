package array;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class SolutionTest {

    @Test
    public void twoSum() {
        int[] a = {2,7,11,12,24};
        int[] b = {0,1};
        assertArrayEquals(b, new Solution().twoSum(a, 9));
    }

    @Test
    public void findMedianSortedArrays() {
        int[] a = {1,2};
        int[] b = {3,4};
        assertEquals(2.5, new Solution().findMedianSortedArrays(a, b),0.0);
    }

    @Test
    public void maxArea() {
        int[] a = {2,3,4,5,18,17,6};
        new Solution().maxArea(a);
    }

    @Test
    public void rotate() {
        int[] a = {1};
        new Solution().rotate(a, 1);
    }

    @Test
    public void maxProfit() {
        int[] a = {3,3,5,0,0,3,1,4};
        new Solution().maxProfit(a);
    }

    @Test
    public void plusOne() {
        int[] a ={9,9};
        System.out.println(Arrays.toString(new Solution().plusOne(a)));
    }

    @Test
    public void findDuplicate() {
        int[] a = {3,1,3,4,2};
        System.out.println(new Solution().findDuplicate(a));
    }

    @Test
    public void summaryRanges() {
        int[] a = {0,1,2,4,5,7};
        new Solution().summaryRanges(a);
    }

    @Test
    public void sortColors() {
        int[] nums = {2,0,2,1,1,0};
        new Solution().sortColors(nums);
    }

    @Test
    public void smallestStringWithSwaps() {

        String s = "dcab";
        List<List<Integer>> pairs = new ArrayList<>();
        List<Integer> p1 = new ArrayList<>();
        p1.add(0);
        p1.add(3);
        List<Integer> p2 = new ArrayList<>();
        p2.add(1);
        p2.add(2);

        List<Integer> p4 = new ArrayList<>();
        p4.add(0);
        p4.add(2);
        pairs.add(p1);
        pairs.add(p2);
        pairs.add(p4);
        new Solution().smallestStringWithSwaps(s, pairs);
    }

    @Test
    public void prefixesDivBy5() {
        int[] a = {0,1,1};
        new Solution().prefixesDivBy5(a);
    }

    @Test
    public void addToArrayForm() {
        int[] A = {1,2,0,0};
        new Solution().addToArrayForm(A,34);
    }

    @Test
    public void findLengthOfLCIS() {
        int[] a = {1,3,5,4,2,3,4,5};
        new Solution().findLengthOfLCIS(a);
    }

    @Test
    public void fourSumCount() {
        int[] a = {1,2};
        int[] b = {-2,-1};
        int[] c = {-1,2};
        int[] d = {0,2};

        new Solution().fourSumCount(a, b, c, d);
    }
}