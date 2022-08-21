package string;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SolutionTest {

    @Test
    public void longestPalindrome() {
        String s = "babad";
        assertEquals("bab", new Solution().longestPalindrome(s));
    }

    @Test
    public void largeGroupPositions() {
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> a = new ArrayList<>();
        a.add(3);
        a.add(6);
        list.add(a);
        String s = "abcdddeeeeaabbbcd";
        assertEquals(list, new Solution().largeGroupPositions(s));
    }

    @Test
    public void isMatch() {

        String s = "mississippi";
        String p = "mis*is*p*.";
        assertEquals(true, new Solution().isMatch(s, p));

    }

    @Test
    public void romanToInt() {
        String s= "MCMXCIV";
        System.out.println(new Solution().romanToInt(s));
    }

    @Test
    public void longestCommonPrefix() {

        String[] strs = new String[3];
        strs[0] = "flowers";
        strs[1] = "flow";
        strs[2] = "flight";
        new Solution().longestCommonPrefix(strs);
    }

    @Test
    public void evalRPN() {
        String[] str = {"4", "13", "5", "/", "+"};
        new Solution().evalRPN(str);
    }

    @Test
    public void wordBreak() {
        String s = "leetcode";
        String[] st = {"leet","code"};
        List<String> list = new ArrayList<>();
        list.add(st[0]);
        list.add(st[1]);
        new Solution().wordBreak(s, list);
    }


    @Test
    public void isPalindrome() {
        String s = "S1as";
        new Solution().isPalindrome(s);
    }

    @Test
    public void fizzBuzz() {
        new Solution().fizzBuzz(1);
    }

    @Test
    public void isPrefixOfWord() {
        new Solution().isPrefixOfWord("hello from the other side", "they");
    }
}