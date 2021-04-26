package doublepointer;

import org.junit.Test;

import static org.junit.Assert.*;

public class SolutionTest {

    @Test
    public void characterReplacement() {
        String s = "AABABBA";
        new Solution().characterReplacement(s, 1);
    }

    @Test
    public void minSubArrayLen() {
        int[] a = {5,1,3,5,10,7,4,9,2,8};
        new Solution().minSubArrayLen(15,a);
    }

    @Test
    public void equalSubstring() {
        String s = "krpgjbjjznpzdfy";

        String t = "nxargkbydxmsgby";
        new Solution().equalSubstring(s,t,14);
    }

    @Test
    public void maxScore() {
        int[] a = {100,40,17,9,73,75};
        int k = 3;
        new Solution().maxScore(a, k);
    }

    @Test
    public void strStr() {
        new Solution().strStr("needle","ee");
    }
}