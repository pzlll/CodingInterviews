package searchandsort;

import org.junit.Test;

import java.util.Arrays;

public class Sort {
    public static void bubbleSort(int[] t) {
        for (int i = 0; i < t.length - 1; i++) {
            for (int j = 0; j <t.length - 1 - i ; j++) {
                if(t[j] > t[j+1]) {
                    int temp = t[j];
                    t[j] = t[j+1];
                    t[j+1] = temp;
                }
            }
        }

    }

    public static void selectSort(int[] t) {
        for (int i = 0; i < t.length - 1 ; i++) {
            int min = i;
            for (int j = i+1; j < t.length; j++) {
                if(t[j] < t[min]) {
                    min = j;
                }
            }
            if(min != i) {
                int temp = t[min];
                t[min] = t[i];
                t[i] = temp;
            }
        }
    }

    public static void insertSort(int[] t) {
        int length = t.length;

        for (int i = 1; i < length; i++) {
            int temp = t[i];
            int index = i - 1;
            while(index >= 0 && t[index] > temp) {
                t[index+1] = t[index];
                index--;
            }
            t[index+1] = temp;
        }
    }

    public static void hillSort(int[] t) {
        int length = t.length;

        for (int i = length/2; i > 0; i = i/2) {
            for (int j = i; j < length; j++) {
                int temp = t[j];
                int index = j - i;
                while(index >= 0 && t[index] > temp) {
                    t[index + i] = t[index];
                    index -= i;
                }
                t[index + i] = temp;
            }

        }
    }

    public static void mergeSort(int[] t) {
        int start = 0;
        int end = t.length-1;;
        mergeSort(t, start, end);
    }

    private static void mergeSort(int[] t, int start, int end) {
        if(start >= end) {
            return ;
        }

        int mid = (start + end) / 2;

        mergeSort(t, start, mid);
        mergeSort(t, mid+1, end);

        int[] k = new int[end - start + 1];

        int i = start;
        int j = mid + 1;
        int index = 0;

        while(i <= mid && j <= end) {
            if (t[i] < t[j]) {
                k[index++] = t[i++];
            }else {
                k[index++] = t[j++];
            }
        }
        while(i <= mid) {
            k[index++] = t[i++];
        }
        while(j <= end) {
            k[index++] = t[j++];
        }

        for (i = 0, j = start; i < k.length; i++, j++) {
            t[j] = k[i];
        }




    }

    public static void quickSort(int[] t) {
        if(t.length <= 0) {
            return;
        }
        quickSort(t, 0, t.length-1);
    }

    private static void quickSort(int[] t, int start, int end) {
        if(start >= end) {
            return;
        }
        int temp = t[start];

        int i = start;
        int j = end;

        while(i < j) {
            while(i < j && t[j] >= temp) {
                j--;
            }
            t[i] = t[j];
            while(i < j && t[i] < temp) {
                i++;
            }
            t[j] = t[i];

        }
        t[i] = temp;

        quickSort(t, start, i-1);
        quickSort(t, i+1, end);

    }

    public static void heapSort(int[] t) {
        MaxHeap(t);
        int length = t.length - 1;
        System.out.println(Arrays.toString(t));
        for (int i = 0; i < length; i++) {
            swap(t, 0, length-i);
            adjustHeap(t, 0, length-i);
        }
    }

    private static void MaxHeap(int[] t) {
        for (int i = t.length/2; i >= 0; i--) {
            adjustHeap(t, i, t.length-1);
        }
    }

    private static void adjustHeap(int[] t, int i, int length) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int flag = i;

        if(left < length && t[flag] < t[left]) {
            flag = left;
        }

        if(right < length && t[flag] < t[right]) {
            flag = right;
        }

        if(flag != i) {
            swap(t, i, flag);
            adjustHeap(t, flag, length);

        }
    }

    public static void countingSort(int[] t) {
        int min = 0;
        int max = 20;

        int length = max - min + 1;

        int[] bucket = new int[length];
        int index = 0;

        for (int i = 0; i < t.length; i++) {
            bucket[t[i]]++;
        }

        int i = 0;

        while (index < length) {
            while(bucket[index] > 0) {
                t[i++] = index;
                bucket[index]--;
            }
            index++;
        }
    }

//    @Test
//    public void test() {
//
//    }

    private static void swap(int[] t, int i, int flag) {
        int temp = t[i];
        t[i] = t[flag];
        t[flag] = temp;
    }
}
