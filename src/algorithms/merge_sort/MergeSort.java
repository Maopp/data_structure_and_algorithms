package algorithms.merge_sort;

/**
 * algorithms.merge_sort
 *
 * @Author cat_pp
 * @Date 2019/1/7
 * @Description 归并排序
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] arr = new int[]{5, 3, 0, 6, 8, 2, 6};
        System.err.println("排序前的数组元素：");
        for (int i : arr) {
            System.err.print(i + ",");
        }

        // 对数组进行冒泡排序
        mergeSort(arr);

        System.err.println();
        System.err.println("排序后的数组元素：");
        for (int i : arr) {
            System.err.print(i + ",");
        }
        System.err.println();

        // 执行1000000次排序耗时
        long start = System.currentTimeMillis();
        for (int i = 0;i < 1000000;i++) {
            mergeSort(arr);
        }
        long end = System.currentTimeMillis();
        System.err.println("执行一百万次耗时：" + (end - start));
    }

    /**
     * 归并排序
     *
     * @param arr
     */
    private static void mergeSort(int[] arr) {
        // 先创建一个和原数组长度相同的数组，避免归并排序过程中频繁开辟数组空间
        int[] tempArr = new int[arr.length];
        // 递归归并排序
        sort(arr, 0, arr.length - 1, tempArr);
    }

    /**
     * 递归排序
     *
     * @param arr 待排序的数组
     * @param left 数组最小索引值
     * @param right 数组最大索引值
     * @param tempArr 临时数组
     */
    private static void sort(int[] arr, int left, int right, int[] tempArr) {
        if (left < right) {
            int mid = (left + right) >> 1;
            // 左子序列归并排序
            sort(arr, left, mid, tempArr);
            // 右子序列归并排序
            sort(arr, mid + 1, right, tempArr);
            // 将两个有序子序列合并
            merge(arr, left, mid, right, tempArr);
        }
    }

    /**
     * 合并左右两个有序子序列
     *
     * @param arr 原数组
     * @param left 左子序列最小索引
     * @param mid 两个子序列中间索引
     * @param right 右子序列最大索引
     * @param tempArr 临时数组
     */
    private static void merge(int[] arr, int left, int mid, int right, int[] tempArr) {
        // 左子序列指针
        int i = left;
        // 右子序列指针
        int j = mid + 1;
        // 临时数组指针
        int t = 0;

        // 将数组元素添加到临时数组中
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                tempArr[t++] = arr[i++];
            } else {
                tempArr[t++] = arr[j++];
            }
        }

        // 将左子序列剩余元素添加到临时数组
        while (i <= mid) {
            tempArr[t++] = arr[i++];
        }
        // 将右子序列剩余元素添加到临时数组
        while (j <= right) {
            tempArr[t++] = arr[j++];
        }

        t = 0;

        // 将临时数组元组拷贝到原数组中
        while (left <= right) {
            arr[left++] = tempArr[t++];
        }
    }
}
