package dynamicprogramming;

import org.junit.Test;

import static org.junit.Assert.*;

public class SolutionTest {

    @Test
    public void lengthOfLIS() {
        int[] a = {10,9,2,5,3,7,101,18};
        new Solution().lengthOfLIS(a);
    }

    @Test
    public void minCut() {
        new Solution().minCut("aab");
    }

    @Test
    public void numWays() {
        new Solution().numWays(6, 13);
    }

    @Test
    public void strangePrinter() {
        new Solution().strangePrinter("aaabbb");
    }

    @Test
    public void findMaxForm() {
        new Solution().findMaxForm(new String[]{"10", "0", "1"}, 1 ,1);
    }

    @Test
    public void numSquares() {
        new Solution().numSquares(12);
    }
}