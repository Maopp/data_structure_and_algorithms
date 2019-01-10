package algorithms.bucket_sort;

import java.util.ArrayList;
import java.util.Collections;

/**
 * algorithms.bucket_sort
 *
 * @Author cat_pp
 * @Date 2019/1/10
 * @Description 桶排序
 */
public class BucketSort {

    public static void main(String[] args) {
        int[] arr = new int[]{5, 3, 0, 6, 8, 2, 6};
        System.err.println("排序前的数组元素：");
        for (int i : arr) {
            System.err.print(i + ",");
        }

        // 对数组进行冒泡排序
        bucketSort(arr);

        System.err.println();
        System.err.println("排序后的数组元素：");
        for (int i : arr) {
            System.err.print(i + ",");
        }
        System.err.println();

        // 执行1000000次排序耗时
        long start = System.currentTimeMillis();
        for (int i = 0;i < 1000000;i++) {
            bucketSort(arr);
        }
        long end = System.currentTimeMillis();
        System.err.println("执行一百万次耗时：" + (end - start));
    }

    /**
     * 桶排序
     *
     * @param arr 待排序的数组
     */
    private static void bucketSort(int[] arr) {
        int min = 0, max = 0;
        for (int i = 0;i < arr.length;i++) {
            min = arr[i] < min ? arr[i] : min;
            max = arr[i] > max ? arr[i] : max;
        }

        // 桶数
        int bucketNum = (max - min) / arr.length + 1;
        ArrayList<ArrayList<Integer>> bucketArr = new ArrayList<>(bucketNum);
        for (int i = 0;i < bucketNum;i++) {
            bucketArr.add(new ArrayList<Integer>());
        }

        // 将每个元素放入桶
        for (int i = 0;i < arr.length;i++) {
            int num = (arr[i] - min) / arr.length;
            bucketArr.get(num).add(arr[i]);
        }

        // 对每个桶进行排序
        for (int i = 0;i < bucketArr.size();i++) {
            Collections.sort(bucketArr.get(i));
        }

        int tempIndex = 0;
        for (int i = 0;i < bucketArr.size();i++) {
            for (int j = 0;j < bucketArr.get(i).size();j++) {
                arr[tempIndex++] = bucketArr.get(i).get(j);
            }
        }
    }
}
