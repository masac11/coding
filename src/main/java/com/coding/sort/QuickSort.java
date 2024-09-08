package com.coding.sort;

import java.util.Arrays;

public class QuickSort {

    /*
     * 快速排序
     *
     * 参数说明:
     *     a -- 待排序的数组
     *     l -- 数组的左边界(例如，从起始位置开始排序，则l=0)
     *     r -- 数组的右边界(例如，排序截至到数组末尾，则r=a.length-1)
     */
    public static void quickSort(int[] arr, int startIndex, int endIndex) {
        if (startIndex < endIndex) {
            int pivot = partition(arr, startIndex, endIndex);
            quickSort(arr, startIndex, pivot - 1);
            quickSort(arr, pivot + 1, endIndex);
        }
    }

    public static int partition(int[] arr, int startIndex, int endIndex) {
            int pivot = arr[startIndex];
            int left = startIndex;
            int right = endIndex;
            while (left < right) {
                while (left < right && arr[right] > pivot) {
                    right--;
                }
                while (left < right && arr[left] <= pivot) {
                    left++;
                }
                if (left < right) {
                    swap(arr, left, right);
                }
            }
            //第一轮完成，让left和right重合的位置和基准交换，返回基准的位置
            swap(arr, startIndex, left);
            return left;
    }

    public static void swap(int[] arr, int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }

    public static void main(String[] args) {
        int i;
        int[] a = {30,40,60,10,20,50};
        quickSort(a, 0, a.length - 1);
        int[] b = Arrays.copyOf(a, a.length);
        Arrays.sort(b);
        System.out.println(Arrays.equals(b, a));
    }
}
