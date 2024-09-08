package com.coding.sort;

public class Sort {
    public static void main(String[] args) {
        int[] arr =  {9,8,2,1,3,2,434,52,11,1,2,3};
    }

    public int[] mergeSortBU(int[] arr) {
        if (arr.length < 2) return arr;
        int n = arr.length;
        int[] tmpArr = new int[n];
        for(int gap = 1; gap < n; gap *= 2) { // 基本分区合并(随着间隔的成倍增长，一一合并，二二合并，四四合并...)
            // 每次l都从0开始，
            for(int l = 0; l < n - gap; l += 2 * gap) { // leftEnd = left+gap-1; rightEnd = left+2*gap-1;
                int r = Math.min(l + 2 * gap - 1, n - 1); // 防止最后一次合并越界
                merge(arr, tmpArr, l, l + gap - 1, r);
            }
        }
        return arr;
    }

    public int[] mergeSortBB(int[] arr) {
        if (arr.length < 2) {
            return arr;
        }
        int n =  arr.length;
        int[] tmpArr = new int[n];
        for (int gap = 1; gap < n; gap *= 2) {
            for (int l = 0; l < n; l += 2*gap) {
                // 1 2 3 4 5
                int r = Math.min(l + 2*gap - 1, n);
                merge(arr, tmpArr, l, l + gap - 1, r);
            }
        }
        return arr;
    }


    public int[] mergeSort(int[] arr) {
        int l = 0, n = arr.length;
        int[] tmpArr = new int[n];
        mergeSort(arr, tmpArr, l, n - 1);
        return arr;
    }

    public int[] mergeSort(int[] arr, int[] tmpArr, int l, int r) {
        if (l < r) {
            int c = (r - l) / 2 + l;
            mergeSort(arr, tmpArr, l, c);
            mergeSort(arr, tmpArr, c + 1, r);
            merge(arr, tmpArr, l, c, r);
        }
        return arr;
    }

    public void merge(int[] arr, int[] tmpArr, int l, int c, int r) {
        int lBak = l;
        int rh = c + 1;
        int start = l;
        while (l <= c && rh <= r) {
            tmpArr[start++] = arr[l] <= arr[rh] ? arr[l++] : arr[rh++];
        }
        while (l <= c) {
            tmpArr[start++] = arr[l++];
        }
        while (rh <= c) {
            tmpArr[start++] = arr[rh++];
        }
        for (; lBak <= r; lBak++ ) {
            arr[lBak] = tmpArr[lBak];
        }
    }

    public int[] mergeSort2(int[] arr) {
        if (arr.length < 2) {
            return arr;
        }
        int[] tmpArr = new int[arr.length];
        mergeSort2(arr, tmpArr, 0, arr.length - 1);
        return arr;
    }

    private void mergeSort2(int[] arr, int[] tmpArr, int l, int r) {
        if (l < r) {
            int c = l + (r - l) / 2;
            mergeSort2(arr, tmpArr, l, c);
            mergeSort2(arr, tmpArr, c + 1, r);
            merge2(arr, tmpArr, l, c, r);
        }
    }

    public int[] mergeSortBU2(int[] arr) {
        if (arr.length < 2) {
            return arr;
        }
        int n = arr.length;
        int[] tmpArr = new int[n];
        for (int gap = 1; gap < n; gap *= 2) {
            for (int l = 0; l < n - gap; l += 2 * gap) {
                int r = Math.min(l + 2 * gap - 1, n - 1);
                merge2(arr, tmpArr, l, l + gap - 1, r);
            }
        }
        return arr;
    }

    private void merge2(int[] arr, int[] tmpArr, int l, int c, int r) {
        int lh = l, rh = c + 1, h = l;
        while (lh <= c && rh <= r) {
            tmpArr[h++] = arr[lh] <= arr[rh] ? arr[lh++] : arr[rh++];
        }
        while (lh <= c) {
            tmpArr[h++] = arr[lh++];
        }
        while (rh <= r) {
            tmpArr[h++] = arr[rh++];
        }
        for (; l <= r; l++) {
            arr[l] = tmpArr[l];
        }
    }

    public int[] insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && temp < arr[j - 1]) {
                arr[j] = arr[j - 1];
                j--;
            }
            if (j != i) {
                arr[j] = temp;
            }
        }
        return arr;
    }

    public int[] selectSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[minIdx] > arr[j]) {
                    minIdx = j;
                }
            }
            swap(arr, i, minIdx);
        }
        return arr;
    }

    public int[] bubbleSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
                // 设定一个标记，若为true，则表示此次循环没有进行交换，也就是待排序列已经有序，排序已经完成。
                boolean flag = true;

                for (int j = 0; j < arr.length - i; j++) {
                    if (arr[j] > arr[j + 1]) {
                        int tmp = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = tmp;

                        flag = false;
                    }
                }

                if (flag) {
                    break;
                }
        }
        return arr;
    }

    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
