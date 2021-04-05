package string;

import java.util.*;

public class Solution {
    /**
     * 动态规划
     * 利用小的计算结果推演出大的
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        if (s == null) {
            return null;
        }
        String str = "";
        boolean[][] isPR = new boolean[s.length()][s.length()];
        for (int l = 0; l < s.length(); l++) {
            for (int i = 0; i + l < s.length(); i++) {
                int j = i + l;
                boolean temp = s.charAt(i) == s.charAt(j);
                if (j == i) {
                    isPR[i][j] = true;
                } else if (j - i == 1 && temp) {
                    isPR[i][j] = true;
                } else if (isPR[i + 1][j - 1] == true && temp) {
                    isPR[i][j] = true;
                } else {
                    isPR[i][j] = false;
                }
                if (isPR[i][j] == true && str.length() < (j - i + 1)) {
                    str = s.substring(i, j + 1);
                }

            }
        }

        return str;
    }

    public List<List<Integer>> largeGroupPositions(String s) {
        List<List<Integer>> list = new ArrayList<>();

        if (s == null) {
            return list;
        }

        int i = 0;
        int j = i + 1;
        int count = 0;
        while (i < s.length() && j < s.length()) {
            if (s.charAt(j) == s.charAt(i)) {
                count++;
                j++;
            } else {
                if (count >= 2) {
                    List<Integer> l = new ArrayList<>();
                    l.add(i);
                    l.add(j - 1);
                    list.add(l);
                }
                i = j;
                j++;
                count = 0;
            }
        }

        if (count >= 2) {
            List<Integer> l = new ArrayList<>();
            l.add(i);
            l.add(j - 1);
            list.add(l);
        }

        return list;
    }

    public boolean isMatch(String s, String p) {
        int n = s.length();
        int m = p.length();
        boolean[][] f = new boolean[n + 1][m + 1];

        f[0][0] = true;
        for (int i = 0; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (p.charAt(j - 1) == '*') {
                    f[i][j] = f[i][j - 2];
                    if (isMatch(s, p, i, j - 1)) {
                        f[i][j] = f[i][j] || f[i - 1][j];
                    }
                } else {
                    if (isMatch(s, p, i, j)) {
                        f[i][j] = f[i - 1][j - 1];
                    }
                }
            }
        }

        return f[n][m];
    }

    private boolean isMatch(String s, String p, int i, int j) {
        if (i == 0) {
            return false;
        }
        if (p.charAt(j - 1) == '.') {
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }

    public int romanToInt(String s) {
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                case 'I':
                    if (i + 1 < s.length() && (s.charAt(i + 1) == 'V' || s.charAt(i + 1) == 'X')) {
                        sum -= 1;
                    } else {
                        sum += 1;
                    }
                    break;
                case 'V':
                    sum += 5;
                    break;
                case 'X':
                    if (i + 1 < s.length() && (s.charAt(i + 1) == 'L' || s.charAt(i + 1) == 'C')) {
                        sum -= 10;
                    } else {
                        sum += 10;
                    }
                    break;
                case 'L':
                    sum += 50;
                    break;
                case 'C':
                    if (i + 1 < s.length() && (s.charAt(i + 1) == 'D' || s.charAt(i + 1) == 'M')) {
                        sum -= 100;
                    } else {
                        sum += 100;
                    }
                    break;
                case 'D':
                    sum += 500;
                    break;
                case 'M':
                    sum += 1000;
                    break;
                default:
                    break;
            }
        }
        return sum;

    }

    public String longestCommonPrefix(String[] strs) {

        int min = Integer.MAX_VALUE;
        if (strs.length == 0) {
            return "";
        }
        for (int i = 0; i < strs.length; i++) {
            if (strs[i] == null) {
                return "";
            }
            if (strs[i].length() < min) {
                min = strs[i].length();
            }
        }

        if (min == 0) {
            return "";
        }

        int i = 0;
        for (i = 0; i < min; i++) {
            char ch = strs[0].charAt(i);
            boolean flag = true;

            for (int j = 1; j < strs.length; j++) {
                if (strs[j].charAt(i) != ch) {
                    flag = false;
                    break;
                }
            }

            if (!flag) {
                break;
            }

        }

        if (i == 0) {
            return "";
        } else {
            return strs[0].substring(0, i);
        }
    }

    /**
     * 栈应用
     *
     * @param tokens
     * @return
     */
    public int evalRPN(String[] tokens) {
        int sum = 0;
        Stack<Integer> stack = new Stack<>();
        int i = 0;
        while (i < tokens.length) {
            String s = tokens[i];
            if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")) {
                if (!stack.isEmpty() && stack.size() >= 2) {
                    int b = stack.pop();
                    int a = stack.pop();
                    switch (s) {
                        case "+":
                            sum = a + b;
                            stack.push(sum);
                            break;
                        case "-":
                            sum = a - b;
                            stack.push(sum);
                            break;
                        case "*":
                            sum = a * b;
                            stack.push(sum);
                            break;
                        case "/":
                            sum = a / b;
                            stack.push(sum);
                    }
                } else {
                    return -1;
                }
            } else {
                Integer temp = Integer.valueOf(s);
                stack.push(temp);
            }
            i++;
        }
        if (!stack.isEmpty()) {
            sum = stack.pop();
        }
        return sum;
    }

    /**
     * 动态规划
     *
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        //将字典元素存入哈希表
        Set<String> set = new HashSet<>();
        for (String str :
                wordDict) {
            set.add(str);
        }

        boolean[] record = new boolean[s.length() + 1];
        record[0] = true;

        //用recode数组记录之前的状态
        //建立转移方程：record[i] = recode[j] && s[j+1, i](是否在字典中)
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (record[j] && set.contains(s.substring(j, i))) {
                    record[i] = true;
                    break;
                }
            }
        }

        return record[s.length()];

    }

    /**
     * 使用双指针对字符串进行判断
     * @param s
     * @return
     */
    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }

        int i = 0;
        int j = s.length() - 1;

        while (i < j) {
            while (!isNumOrAlpha(s.charAt(i)) && i < j) {
                i++;
            }
            while (!isNumOrAlpha(s.charAt(j)) && i < j) {
                j--;
            }

            if(i<j){
                Character s1 = Character.toLowerCase(s.charAt(i));
                Character s2 = Character.toLowerCase(s.charAt(j));
                if(!s1.equals(s2)) {
                    return false;
                }

            }
            i++;
            j--;
        }

        return true;
    }

    //可使用Character.isLetterOrDigit() 并且不需要函数调用
    private boolean isNumOrAlpha(char j) {

        if(Character.isLetter(j) || Character.isDigit(j)){
            return true;
        }

        return false;
    }

    /**
     * 解题思路：1.使用字符拼接   2.使用散列表存放映射
     * @param n
     * @return
     */
    public List<String> fizzBuzz(int n) {
        List<String> result = new ArrayList<>();
        Map<Integer, String> map = new TreeMap<>();
        map.put(3,"Fizz");
        map.put(5,"Buzz");
        
        for (int i = 1; i <= n; i++) {
            StringBuilder str = new StringBuilder();
            for (Integer key :
                    map.keySet()) {
                if(i % key == 0) {
                    str.append(map.get(key));
                }
            }

            if(str.length() == 0) {
                str.append(i);
            }

            result.add(str.toString());
        }
        return result;
    }

}
