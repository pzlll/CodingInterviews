package game;

import org.junit.Test;

public class SolutionTest {

    @Test
    public void subsetXORSum() {
        new Solution().subsetXORSum(new int[]{3, 4,5,6,7,8});

    }


    @Test
    public void checkZeroOnes() {
        new Solution().checkZeroOnes("1101");
    }

    @Test
    public void minSpeedOnTime() {
        new Solution().minSpeedOnTime(new int[]{1,1,100000}, 2.01);
    }

    @Test
    public void canReach() {
        new Solution().canReach("00111010", 3 , 5);
    }

    @Test
    public void assignTasks() {
        new Solution().assignTasks(new int[]{3,3,2}, new int[]{1,2,3,2,1,2});
    }

    @Test
    public void findRotation() {
        new Solution().findRotation(new int[][]{{1,1},{0,1}}, new int[][]{{1,1}, {1, 0}});
    }

    @Test
    public void minFlips() {
        new Solution().minFlips("01001001101");
    }

    @Test
    public void maximumRemovals() {
        new Solution().maximumRemovals("abcbddddd", "abcd", new int[]{3,2,1,4,5,6});
    }

    @Test
    public void wonderfulSubstrings() {
        new Solution().wonderfulSubstrings("aabb");
    }

    @Test
    public void eliminateMaximum() {
        new Solution().eliminateMaximum(new int[]{4,3,3,3,4}, new int[]{1,1,1,1,4});
    }

    @Test
    public void maximumGroups() {
        new Solution().maximumGroups(new int[]{5,3,1,0,2,4,5});
    }

    @Test
    public void closestMeetingNode() {
        new Solution().closestMeetingNode(new int[]{5,3,1,0,2,4,5}, 2,3);
    }

    @Test
    public void smallestNumber() {
        new Solution().smallestNumber("DDDDDDDD");
    }
}