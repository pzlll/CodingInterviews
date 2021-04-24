package math;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

import static org.junit.Assert.*;

public class SolutionTest {

    @Test
    public void reverse() {
        assertEquals(321, new Solution().reverse(123));
    }

    @Test
    public void myAtoi() {
        assertEquals(0, new Solution().myAtoi("4192 fere fdfd"));
    }

    @Test
    public void checkStraightLine() {

        int[][] b = {{1,1},{2,2},{3,4},{4,5}};
        new Solution().checkStraightLine(b);
    }

    @Test
    public void rotate() {
        int[][] a = {{1,2,3}, {4,5,6}, {7,8,9}};
        new Solution().rotate(a);

        System.out.println(Arrays.toString(a[0]) + Arrays.toString(a[1]));
    }

    @Test
    public void nthUglyNumber() {
        new Solution().nthUglyNumber(10);
    }
}