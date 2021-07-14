package binarysearch;

import org.junit.Test;

import static org.junit.Assert.*;

public class SolutionTest {

    @Test
    public void searchMatrix() {
        int[][] mat = {{1,3,5,7},{10,11,16,20},{23,30,34,60}};
        new Solution().searchMatrix(mat, 13);
    }

    @Test
    public void minAbsoluteSumDiff() {
        new Solution().minAbsoluteSumDiff(new int[]{1,10, 4,4,2,7},
                new int[]{9,3,5,1,7,4});
    }
}