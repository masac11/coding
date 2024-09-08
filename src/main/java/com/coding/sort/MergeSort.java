package com.coding.sort;

public class MergeSort {


    public static void mergeSort(int[] nums, int[] tmpArr, int l, int r) {
        if (l < r) {
            int c = (r - l) / 2 + l;
            mergeSort(nums, tmpArr, l, c);
            mergeSort(nums, tmpArr, c + 1, r);
            merge(nums, tmpArr, l, c, r);
        }
    }

    public static void mergeSort2(int[] nums, int[] tmpArr) {
        int len = nums.length;
        for (int gap = 1; gap < len; gap *= 2) {
            for (int l = 0; l < len - gap; l += 2 * gap) {
                int r = Math.min(len, l + 2 * gap - 1);
                merge(nums, tmpArr, l, l + gap - 1, r);
            }
        }
    }

    public static void merge(int[] nums, int[] tmpArr, int l, int c, int r) {
        int lBak = l;
        int rh = c + 1;
        int start = l;
        while (l <= c && rh <= r) {
            tmpArr[start++] = nums[l] < nums[rh] ? nums[l++] : nums[rh++];
        }
        while (l <= c) {
            tmpArr[start++] = nums[l++];
        }
        while (rh <= r) {
            tmpArr[start++] = nums[rh++];
        }
        for (; lBak < r + 1; lBak++) {
            nums[lBak] = tmpArr[lBak];
        }
    }

    public static void swap(int[] nums, int l, int r) {
        int temp = nums[l];
        nums[l] = nums[r];
        nums[r] = temp;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3,2,11,3,2,5,7,88};
    }

}
