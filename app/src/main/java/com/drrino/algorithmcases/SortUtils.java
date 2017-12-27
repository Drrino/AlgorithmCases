package com.drrino.algorithmcases;

import android.util.Log;

public class SortUtils {
    private static final String TAG = "Tag";


    //数组交换
    private static void exchange(int[] data, int i, int j) {
        int tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }


    /**
     * 插入排序
     */
    static void InsertSort(int[] a) {
        int N = a.length;
        for (int i = 1; i < N; i++) {
            for (int j = i; j > 0 && a[j] < a[j - 1]; j--) {
                exchange(a, j, j-1);
            }
        }
        for (int anA : a) {
            Log.e(TAG, anA + "");
        }
    }


    /**
     * 二分插入排序
     *
     * 二分法插入排序的思想和直接插入一样，只是找合适的插入位置的方式不同，按二分法找到合适的位置，可以减少比较的次数。
     */
    static void BinaryInsertSort(int[] a) {
        for (int i = 0; i < a.length; i++) {
            int temp = a[i];
            int left = 0;
            int right = i - 1;
            int mid;
            while (left <= right) {
                mid = (left + right) / 2;
                if (temp < a[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            for (int j = i - 1; j >= left; j--) {
                a[j + 1] = a[j];
            }
            if (left != i) {
                a[left] = temp;
            }
        }
        for (int anA : a) {
            Log.e(TAG, anA + "");
        }
    }


    /**
     * 希尔排序
     */
    static void ShellSort(int[] a) {
        int N = a.length;
        int h = 1;
        while (h < N / 3) {
            h = 3 * h + 1;
        }
        while (h >= 1) {
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && a[j] < a[j - h]; j -= h) {
                    exchange(a, j, j-h);
                }
            }
            h = h / 3;
        }
        for (int anA : a) {
            Log.e(TAG, anA + "");
        }
    }


    /**
     * 选择排序
     */
    static void ChooseSort(int[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = i + 1; j < N; j++) {
                if (a[j] < a[min]) {
                    min = j;
                }
            }
            exchange(a, i, min);
        }
        for (int anA : a) {
            Log.e(TAG, anA + "");
        }
    }


    /**
     * 堆排序
     * 堆排序是一种树形选择排序，是对直接选择排序的改进
     */
    static void HeapSort(int[] a) {
        int N = a.length;
        //循环建堆
        for (int i = 0; i < N - 1; i++) {
            buildMaxHeap(a, N - 1 - i);
        }
        for (int anA : a) {
            Log.e(TAG, anA + "");
        }
    }


    //对data数组从0到lastIndex建大顶堆
    private static void buildMaxHeap(int[] data, int lastIndex) {
        //从lastIndex处节点（最后一个节点）的父节点开始
        for (int i = (lastIndex - 1) / 2; i >= 0; i--) {
            //k保存正在判断的节点
            int k = i;
            //如果当前k节点的子节点存在
            while (k * 2 + 1 <= lastIndex) {
                //k节点的左子节点的索引
                int biggerIndex = 2 * k + 1;
                //如果biggerIndex小于lastIndex，即biggerIndex+1代表的k节点的右子节点存在
                if (biggerIndex < lastIndex) {
                    //若果右子节点的值较大
                    if (data[biggerIndex] < data[biggerIndex + 1]) {
                        //biggerIndex总是记录较大子节点的索引
                        biggerIndex++;
                    }
                }
                //如果k节点的值小于其较大的子节点的值
                if (data[k] < data[biggerIndex]) {
                    exchange(data, k, biggerIndex);
                    //将biggerIndex赋予k，开始while循环的下一次循环，重新保证k节点的值大于其左右子节点的值
                    k = biggerIndex;
                } else {
                    break;
                }
            }
        }
    }
}
