package game.two.four.nine;

import java.util.*;

public class Solution {

    public int[] getConcatenation(int[] nums) {
        int n = nums.length;
        int m = n * 2;
        int[] ret = new int[m];
        for (int i = 0; i < n; i++) {
            ret[i] = nums[i];
            ret[i + n] = nums[i];
        }

        return ret;
    }

    public int countPalindromicSubsequence(String s) {
        HashSet<Character>[] set = new HashSet[26];
        for (int i = 0; i < 26; i++) {
            set[i] = new HashSet<>();
        }

        int n = s.length();
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            Integer j = map.get(c);
            if (j == null) {
                map.put(c, i);
            } else {
                for (int k = j + 1; k < i; k++) {
                    set[c - 'a'].add(s.charAt(k));

                }
                map.put(c, i - 1);
            }
        }

        int count = 0;
        for (int i = 0; i < 26; i++) {
            count += set[i].size();
        }

        return count;
    }



    public int colorTheGrid(int m, int n) {
        Set<Integer> rows = new HashSet<>();

        for (int i = 0; i < Math.pow(3, m); i++) {
            if(isGoodRow(i, m)) {
                rows.add(i);
            }
        }

        Map<Integer, List<Integer>> rowToRow = new HashMap<>();

        for (Integer row :
                rows) {
            List<Integer> list = new ArrayList<>();
            
            for (Integer r :
                    rows) {
                
                if (canTogether(row, r, m)) {
                    list.add(r);
                }
            }

            rowToRow.put(row, list);
        }
        
        Map<Integer, Integer> f = new HashMap<>();
        for (Integer row :
                rows) {
            f.put(row, 1);
        }

        for (int i = 1; i < n; i++) {
            Map<Integer, Integer> g = new HashMap<>();



            for (Integer row :
                    f.keySet()) {
                List<Integer> list = rowToRow.get(row);
                for (Integer in :
                        list) {
                    g.put(in, g.getOrDefault(in, 0) + f.get(row) % 1000000007);
                }
            }
            f = g;
        }
        
        int count = 0;


        for (Integer in :
                f.values()) {
            count = (count + in) % 1000000007;
        }


        return count;
    }

    private boolean canTogether(Integer a, Integer b, int m) {
        for (int i = 0; i < m; i++) {
            if((a%3) == (b%3)) {
                return false;
            }
            a /= 3;
            b /= 3;
        }
        
        return true;
    }

    private boolean isGoodRow(int i, int m) {
        int p = -1;
        for(int j = 0; j < m; j++) {
            int q = i % 3;
            i /= 3;
            if(p == q) {
                return false;
            }
            p = q;
        }

        return true;
    }


}
