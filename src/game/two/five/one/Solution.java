package game.two.five.one;

public class Solution {
    public int getLucky(String s, int k) {
        char[] ch = s.toCharArray();

        int sum = 0;
        boolean flag = false;
        for (int i = 0; i < ch.length; i++) {
            int count = (ch[i] - 'a' + 1);
            if(count >= 10) {
                sum += count % 10;
                sum += (count / 10) % 10;
            }else {
                sum += count;
            }

        }

        System.out.println(sum);
        for (int i = 0; i < k; i++) {
            if(sum < 10) {
                return (int)sum;
            }

            int temp = sum;
            sum = 0;
            while (temp > 0) {
                sum += temp % 10;
                temp /= 10;
            }

        }

        return sum;

    }

    public String maximumNumber(String num, int[] change) {
        char[] ch = num.toCharArray();

        StringBuffer str = new StringBuffer();

        int n = ch.length;

        int i = 0;
        while (i < n) {
            int k = (ch[i] - '0');
            if(k < change[k]) {
                str.append(change[k]);
                break;
            }else {
                str.append(k);
            }
            i++;
        }

        int j = i + 1;
        while (i < n && j < n) {
            int k = (ch[j] - '0');
            if(k < change[k]) {
                str.append(change[k]);
                j++;
            }else {
                str.append(k);
                j++;
                break;
            }

        }

        while (j < n) {
            str.append((ch[j] - '0'));
            j++;
        }



        return str.toString();
    }
}
