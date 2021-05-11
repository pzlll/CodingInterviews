package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    /**
     * 判断当前字符与单词对应位置是否匹配
     * 匹配：1.最后一个字符则返回true，2.还有其他字符则遍历四个方向（未被访问的）
     * 不匹配，返回false
     *
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        boolean[][] visited = new boolean[board.length][board[0].length];
        if (word == null || word.length() == 0) {
            return true;
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {

                boolean flag = check(i, j, 0, board, word, visited);
                if (flag) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean check(int i, int j, int k, char[][] board, String word, boolean[][] visited) {
        if (k == word.length() - 1 && board[i][j] == word.charAt(k)) {
            return true;
        }
        if (board[i][j] != word.charAt(k)) {
            return false;
        } else {
            boolean flag = false;
            visited[i][j] = true;
            if (i > 0 && !visited[i - 1][j]) {
                flag = check(i - 1, j, k + 1, board, word, visited);
                if (flag) {
                    return true;
                }
            }
            if (i < board.length - 1 && !visited[i + 1][j]) {
                flag = check(i + 1, j, k + 1, board, word, visited);
                if (flag) {
                    return true;
                }
            }
            if (j > 0 && !visited[i][j - 1]) {
                flag = check(i, j - 1, k + 1, board, word, visited);
                if (flag) {
                    return true;
                }
            }
            if (j < board[0].length - 1 && !visited[i][j + 1]) {
                flag = check(i, j + 1, k + 1, board, word, visited);
                if (flag) {
                    return true;
                }
            }
        }

        visited[i][j] = false;
        return false;

    }

    public List<String> findWords(char[][] board, String[] words) {
        boolean[][] visited = new boolean[board.length][board[0].length];
        List<String> list = new ArrayList<>();
        for (String word :
                words) {
            if (word == null || word.length() == 0) {
                continue;
            }
            for (int i = 0; i < visited.length; i++) {
                Arrays.fill(visited[i], false);
            }

            boolean flag = false;
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    flag = check(i, j, 0, board, word, visited);
                    if (flag) {
                        list.add(word);
                        break;
                    }
                }
                if (flag) {
                    break;
                }
            }
        }

        return list;

    }

    private List<Integer> t = new ArrayList<>();
    private List<List<Integer>> array = new ArrayList<>();

    /**
     * 解题思路：对数组进行排序，n个元素，则有2的n次方种组合
     * 若前一元素与当前元素相同，且前一元素不选择，则放弃当前组合
     * 目的是为了避免重复，使得元素{1，1，1}的组合为{{}， {1}， {1，1}， {1，1，1}}
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
////        dfs(false, 0, nums);
        int n = nums.length;
        boolean flag = true;
        for (int mask = 0; mask < (1<<n); mask++) {
            t.clear();
            flag = true;
            for (int i = 0; i < n; i++) {
                if((mask & (1<<i)) != 0) {
                    if(i > 0 && ((mask & (1<<(i-1))) == 0) && nums[i - 1] == nums[i]){
                        flag = false;
                        break;
                    }
                    t.add(nums[i]);
                }
            }
            if(flag) {
                array.add(new ArrayList<>(t));

            }

        }


        return array;
    }

    private void dfs(boolean flag, int cur, int[] nums) {
        if (cur == nums.length) {
            array.add(new ArrayList<>(t));
            return;
        }

        dfs(false, cur + 1, nums);
        if (!flag && cur > 0 && nums[cur - 1] == nums[cur]) {
            return;
        }
        t.add(nums[cur]);
        dfs(true, cur + 1, nums);
        t.remove(t.size() - 1);
    }

    public int minimumTimeRequired(int[] jobs, int k) {
        int min = 0;
        int max = 0;
        int n = jobs.length;
        for (int i = 0; i < n; i++) {
            min = Math.max(min, jobs[i]);
            max += jobs[i];
        }


        while(min < max) {
            int[] workVol = new int[k];
            int mid = (max + min) / 2;
            if(backTracking(workVol, mid, 0, jobs)) {
               max = mid;
            }else {
                min = mid + 1;
            }
        }
        return min;


    }

    private boolean backTracking(int[] workVol, int limit, int i, int[] jobs) {
        if(i == jobs.length) {
            return true;
        }
        for (int j = 0; j < workVol.length; j++) {
            if((workVol[j] + jobs[i]) <= limit) {
                workVol[j] += jobs[i];
                if(backTracking(workVol, limit, i+1, jobs)) {
                    return true;
                }else if(workVol[j] == limit) {
                    workVol[j] -= jobs[i];
                    return false;
                }
                workVol[j] -= jobs[i];
            }
            if(workVol[j] == 0) {
                return false;
            }
        }

        return false;
    }
}
