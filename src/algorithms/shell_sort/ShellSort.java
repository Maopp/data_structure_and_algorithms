package algorithms.shell_sort;

import jdk.nashorn.tools.Shell;

/**
 * algorithms.shell_sort
 *
 * @Author cat_pp
 * @Date 2019/1/4
 * @Description 希尔排序
 */
public class ShellSort {

    public static void main(String[] args) {
        int[] arr = new int[]{5, 3, 0, 6, 8, 2, 6};
//        int[] arr = new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        System.err.println("排序前的数组元素：");
        for (int i : arr) {
            System.err.print(i + ",");
        }

        // 对数组进行冒泡排序
        shellSort(arr);

        System.err.println();
        System.err.println("排序后的数组元素：");
        for (int i : arr) {
            System.err.print(i + ",");
        }
        System.err.println();

        // 执行1000000次排序耗时
        long start = System.currentTimeMillis();
        for (int i = 0;i < 1000000;i++) {
            shellSort(arr);
        }
        long end = System.currentTimeMillis();
        System.err.println("执行一百万次耗时：" + (end - start));
    }

    /**
     * 希尔排序：不给定初始增量值
     *
     * @param arr 待排序的数组
     */
    private static void shellSort(int[] arr) {
        shellSort(arr, arr.length);
    }

    /**
     * 希尔排序：给定增量
     *
     * @param arr 待排序的数组
     * @param gap 增量
     */
    private static void shellSort(int[] arr, int gap) {

        if (gap >= arr.length) {
            gap = arr.length >> 1;
        }
        while (gap > 0) {
            for (int i = gap;i < arr.length;i++) {
                // 判断条件一定要是“>=0”，不然第一个数无法比较
                for (int j = i;j - gap >= 0;j -= gap) {
                    if (arr[j] < arr[j - gap]) {
                        int temp = arr[j];
                        arr[j] = arr[j - gap];
                        arr[j - gap] = temp;
                    } else {
                        break;
                    }
                }
            }
            // 增量减少操作放在排序之后，如果放在排序之前的话，while条件应当“>1”，不然会出现gap=0的情况
            gap >>= 1;
        }

    }
}
