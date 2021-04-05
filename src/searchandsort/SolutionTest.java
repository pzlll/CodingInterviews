package searchandsort;

import org.junit.Test;

import static org.junit.Assert.*;

public class SolutionTest {

    @Test
    public void wiggleSort() {
        int[] a = {1, 5, 2, 4, 3};
        new Solution().wiggleSort(a);
    }
}