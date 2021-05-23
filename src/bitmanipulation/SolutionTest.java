package bitmanipulation;

import org.junit.Test;

import static org.junit.Assert.*;

public class SolutionTest {

    @Test
    public void findMaximumXOR() {
        new Solution().findMaximumXOR(new int[] {8, 10, 2});
    }

    @Test
    public void maximizeXor() {
        int[] nums = {5,2,4,6,6,3};
        int[][] queries = {{12,4},{8,1},{6,3}};
        new Solution().maximizeXor(nums, queries);
    }
}