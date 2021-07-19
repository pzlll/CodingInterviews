package game.two.five.zero;

import org.junit.Test;

import static org.junit.Assert.*;

public class SolutionTest {

    @Test
    public void maxPoints() {
        int[][] a = {{4,1,0,4,0}, {1,0,4,0,5}, {1,3,0,4,1}, {4,4,0,4,0}};
        new Solution().maxPoints(a);
    }
}