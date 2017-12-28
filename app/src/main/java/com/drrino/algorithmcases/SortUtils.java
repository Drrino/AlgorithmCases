package com.drrino.algorithmcases;

import android.util.Log;
import java.util.ArrayList;
import java.util.List;

/**
 * 一、稳定性:
 * 　   稳定：冒泡排序、插入排序、归并排序和基数排序
 * 　　不稳定：选择排序、快速排序、希尔排序、堆排序
 * 二、平均时间复杂度
 * 　　O(n^2):直接插入排序，简单选择排序，冒泡排序。
 * 　　在数据规模较小时（9W内），直接插入排序，简单选择排序差不多。当数据较大时，冒泡排序算法的时间代价最高。性能为O(n^2)的算法基本上是相邻元素进行比较，基本上都是稳定的。
 * 　　O(nlogn):快速排序，归并排序，希尔排序，堆排序。
 * 　　其中，快排是最好的， 其次是归并和希尔，堆排序在数据量很大时效果明显。
 * 三、排序算法的选择
 * 　　1.数据规模较小
 * 　　（1）待排序列基本序的情况下，可以选择直接插入排序；
 * 　　（2）对稳定性不作要求宜用简单选择排序，对稳定性有要求宜用插入或冒泡
 * 　　2.数据规模不是很大
 * 　　（1）完全可以用内存空间，序列杂乱无序，对稳定性没有要求，快速排序，此时要付出log（N）的额外空间。
 * 　　（2）序列本身可能有序，对稳定性有要求，空间允许下，宜用归并排序
 * 　　3.数据规模很大
 * 　　（1）对稳定性有求，则可考虑归并排序。
 * 　　（2）对稳定性没要求，宜用堆排序
 * 　　4.序列初始基本有序（正序），宜用直接插入，冒泡
 */
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
                exchange(a, j, j - 1);
            }
        }
        for (int anA : a) {
            Log.e(TAG, anA + "");
        }
    }

    //=====================================================================================================


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

    //=====================================================================================================


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
                    exchange(a, j, j - h);
                }
            }
            h = h / 3;
        }
        for (int anA : a) {
            Log.e(TAG, anA + "");
        }
    }

    //=====================================================================================================


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

    //=====================================================================================================


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

    //=====================================================================================================


    /**
     * 冒泡排序
     */
    static void BubbleSort(int[] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length - i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    exchange(a, j, j + 1);
                }
            }
        }
        for (int anA : a) {
            Log.e(TAG, anA + "");
        }
    }

    //=====================================================================================================


    /**
     * 快速排序
     */
    static void QuickSort(int[] a) {
        quickSort(a, 0, a.length - 1);

        for (int anA : a) {
            Log.e(TAG, anA + "");
        }
    }


    private static void quickSort(int[] a, int low, int high) {
        if (low < high) {//不加这个判断递归会无法退出导致堆栈溢出异常
            int middle = getMiddle(a, low, high);
            quickSort(a, 0, middle - 1);
            quickSort(a, middle + 1, high);
        }
    }


    private static int getMiddle(int[] a, int low, int high) {
        int temp = a[low];//基准元素
        while (low < high) {
            while (low < high && a[high] >= temp) {
                high--;
            }
            a[low] = a[high];
            while (low < high && a[low] <= temp) {
                low++;
            }
            a[high] = a[low];
        }
        a[low] = temp;
        return low;
    }

    //=====================================================================================================


    /**
     * 归并排序
     */
    static void MergingSort(int[] a) {
        mergeSort(a, 0, a.length - 1);

        for (int anA : a) {
            Log.e(TAG, anA + "");
        }
    }


    private static void mergeSort(int[] a, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;
            mergeSort(a, left, middle);
            mergeSort(a, middle + 1, right);
            merge(a, left, middle, right);
        }
    }


    private static void merge(int[] a, int left, int middle, int right) {
        int[] tmpArr = new int[a.length];
        int mid = middle + 1;//右边起始位置
        int tmp = left;
        int third = left;
        while (left <= middle && mid <= right) {
            if (a[left] <= a[mid]) {
                tmpArr[third++] = a[left++];
            } else {
                tmpArr[third++] = a[mid++];
            }
        }
        while (left <= middle) {
            tmpArr[third++] = a[left++];
        }
        while (mid <= right) {
            tmpArr[third++] = a[mid++];
        }
        while (tmp <= right) {
            a[tmp] = tmpArr[tmp++];
        }
    }

    //=====================================================================================================


    /**
     * 基数排序
     */
    static void RadixSort(int[] array) {
        //查找最大数确定要排序次数
        int max = 0;
        for (int i = 0; i < array.length; i++) {
            if (max < array[i]) {
                max = array[i];
            }
        }
        //判断位数
        int times = 0;
        while (max > 0) {
            max = max / 10;
            times++;
        }
        //十个队列
        List<ArrayList> queue = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ArrayList queue1 = new ArrayList();
            queue.add(queue1);
        }
        //进行times次分配和收集
        for (int i = 0; i < times; i++) {
            for (int j = 0; j < array.length; j++) {
                int x = array[j] % (int) Math.pow(10, i + 1) / (int) Math.pow(10, i);
                ArrayList queue2 = queue.get(x);
                queue2.add(array[j]);
                queue.set(x, queue2);
            }
            int count = 0;
            for (int j = 0; j < 10; j++) {
                while (queue.get(j).size() > 0) {
                    ArrayList<Integer> queue3 = queue.get(j);
                    array[count] = queue3.get(0);
                    queue3.remove(0);
                    count++;
                }
            }
        }

        for (int anA : array) {
            Log.e(TAG, anA + "");
        }
    }
}
