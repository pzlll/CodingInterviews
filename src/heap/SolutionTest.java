package heap;

import org.junit.Test;

import static org.junit.Assert.*;

public class SolutionTest {

    @Test
    public void maxSlidingWindow() {

        int[] a = {9,10,9,-7,-4,-8,2,-6};
        int[] b = {1};
        assertArrayEquals(b, new Solution().maxSlidingWindow(a, 5));
    }

    @Test
    public void smallestK() {
        new Solution().smallestK(new int[]{1,3,5,7,2,4,6,8}, 4);
    }
}