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
    public void search() {
        int[] a = {0,0,1,1,2,0};
        new Solution().search(a, 2);
    }
}