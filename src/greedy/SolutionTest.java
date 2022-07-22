package greedy;

import org.junit.Test;

import static org.junit.Assert.*;

public class SolutionTest {

    @Test
    public void eraseOverlapIntervals() {
        int[][] array = {{0,1},{3,4},{1,2}};
        assertEquals(0, new Solution().eraseOverlapIntervals(array));
    }

    @Test
    public void canPlaceFlowers() {
        int[] a = {1,0,0,0,1};
        assertEquals(true, new Solution().canPlaceFlowers(a, 1));
    }

    @Test
    public void minOperations() {
        //[5,10,8,11,3,15,9,20,18,13]
        //[15,8,2,9,11,20,8,11,7,2]
        new Solution().minOperations(
                new int[]{5,10,8,11,3,15,9,20,18,13},
                new int[] {15,8,2,9,11,20,8,11,7,2});
    }

    @Test
    public void intersectionSizeTwo() {
        new Solution().intersectionSizeTwo(new int[][]{{1,2},{2,3},{2,4},{4,5}});
    }
}