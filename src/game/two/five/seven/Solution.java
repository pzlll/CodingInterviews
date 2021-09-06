package game.two.five.seven;

public class Solution {
    public int countQuadruplets(int[] nums) {
        int n = nums.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    for (int l = k + 1; l < n; l++) {
                        if(nums[i] + nums[j] + nums[k] == nums[l]) {
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }

    public int numberOfWeakCharacters(int[][] properties) {
        int[] max = new int[100001];
        int n = properties.length;
        for (int i = 0; i < n; i++) {
            int a = properties[i][0];
            max[a] = Math.max(max[a], properties[i][1]);

        }

        int maxVal = max[100000];
        for (int i = 99999; i >= 0; i--) {
            int temp = max[i];
            max[i] = maxVal;
            maxVal = Math.max(maxVal, temp);
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            if(properties[i][1] == 100000) {
                continue;
            }
            if(properties[i][1] < max[properties[i][0]]) {
                count++;
            }
        }

        return count;
    }

    public int firstDayBeenInAllRooms(int[] nextVisit) {
        int day = -1;
        int n = nextVisit.length;
        int[] visited = new int[n];
        int[] dp = new int[n];
        int count = 0;

        int index = 0;
        while (count < n) {
            day++;
            if(visited[index] == 0) {
                dp[index] = day - dp[nextVisit[index]];
                count++;
            }
            visited[index]++;

            if(visited[index] % 2 == 0) {
                index = (index+1) % n;
            }else {
                index = nextVisit[index];
            }
        }

        return day;
    }
}
