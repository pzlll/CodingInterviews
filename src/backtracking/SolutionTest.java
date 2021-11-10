package backtracking;

import org.junit.Test;

import static org.junit.Assert.*;

public class SolutionTest {

    @Test
    public void exist() {
        char[][] chars = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        new Solution().exist(chars, "SEE");
    }

    @Test
    public void minimumTimeRequired() {
        new Solution().minimumTimeRequired(new int[]{6518448,8819833,7991995,7454298,2087579,380625,4031400,2905811,4901241,8480231,7750692,3544254},4);
    }

    @Test
    public void readBinaryWatch() {
        new Solution().readBinaryWatch(2);
    }

    @Test
    public void openLock() {
        String[] s = {"0201","0101","0102","1212","2002"};
        new Solution().openLock(new String[]{}, "8888");
    }

    @Test
    public void letterCombinations() {
        new Solution().letterCombinations("23");
    }

    @Test
    public void allPathsSourceTarget() {
        new Solution().allPathsSourceTarget(new int[][]{{1,2}, {3}, {3}, {}});
    }

    @Test
    public void subsets() {
        new Solution().subsets(new int[]{1, 2 ,3});
    }
}