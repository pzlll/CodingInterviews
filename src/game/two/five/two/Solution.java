package game.two.five.two;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Solution {

    public boolean isThree(int n) {
        int count = 0;
        for (int i = 1; i <= n; i++) {
            if(count > 3) {
                return false;
            }
            if((n % i) == 0) {
                count++;
            }
        }

        return count == 3;
    }

    /**
     * 解题思路：贪心+隔板
     * 总数：sum 最大值：max 除最大值的总数：rem
     * 若数组的最大值可以完成，则rem大于等于max-1（隔板模拟），其余的数可以插在最大值的间隔中，保证不相邻
     * 若数组的最大值不可以完成，将rem穿插在max的前rem+1个中，总数为2*rem+1
     * @param milestones
     * @return
     */
    public long numberOfWeeks(int[] milestones) {
        int n = milestones.length;
        int max = 0;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, milestones[i]);
            sum += milestones[i];
        }

        int rem = sum - max;
        return (rem >= (max - 1) ? sum : 2 * rem + 1);


    }

    /**
     * 解题思路：数学+二分
     * 找到苹果个数与边长i的计算公式
     * 第i个正方形的苹果个数：12*i*i
     * 总的苹果个数：i*（i+1）*（2*i+1）/6*12
     * 使用二分查找加快速度
     * @param neededApples
     * @return
     */
    public long minimumPerimeter(long neededApples) {
        int i = 1;
        long sum = 3;
        long t = 12 * i * i;

        long left = 1;
        long right = 100000000;
        while (left < right) {
            long mid = (right + left) / 2;
            if((mid * (mid+1) * (2*mid + 1) / 6) >= neededApples) {
                right = mid;
            }else {
                left = mid + 1;
            }
        }


        return left * 8;
    }
}
