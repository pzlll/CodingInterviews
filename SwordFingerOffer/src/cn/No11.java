package cn;

public class No11 {
    public int minArray(int[] numbers) {
        if(numbers == null || numbers.length == 0) {
            return 0;
        }

        int start = 0;
        int end = numbers.length - 1;
        int mid = (start + end)/2;

        while(start < end && numbers[start] >= numbers[end]) {



            if(numbers[mid] < numbers[start]) {
                end = mid;
                mid = (start + end) / 2;
            }

            if(numbers[mid] > numbers[start]) {

                start = mid;
                mid = (start + end) / 2;
            }

            if(numbers[mid] == numbers[start]) {
                if(mid == start) {
                    start = end;
                    break;
                }

                return find(numbers,start, end);
            }

        }

        return numbers[start];
    }

    private int find(int[] numbers, int mid, int end) {
        for (int i = mid; i < end; i++) {
            if(numbers[i] > numbers[i+1]) {
                return numbers[i+1];
            }
        }

        return numbers[mid];
    }
}
