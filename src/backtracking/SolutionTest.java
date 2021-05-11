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
}