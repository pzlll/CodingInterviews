package stackandqueue;

import org.junit.Test;

import static org.junit.Assert.*;

public class SolutionTest {

    @Test
    public void calculate() {
        new Solution().calculate(" 2-1 + 2 ");
    }

    @Test
    public void calculate2() {
        new Solution().calculate2("3+2*2");
    }
}