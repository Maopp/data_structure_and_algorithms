package algorithms.insertion_sort;

/**
 * algorithms.insertion_sort
 *
 * @Author cat_pp
 * @Date 2019/1/3
 * @Description
 */
public class InsertionSort {

    public static void main(String[] args) {
        int[] arr = new int[]{5, 3, 0, 6, 8, 2, 6};
        System.err.println("排序前的数组元素：");
        for (int i : arr) {
            System.err.print(i + ",");
        }

        // 对数组进行冒泡排序
        insertionSort(arr);

        System.err.println();
        System.err.println("排序后的数组元素：");
        for (int i : arr) {
            System.err.print(i + ",");
        }
        System.err.println();

        // 执行1000000次排序耗时
        long start = System.currentTimeMillis();
        for (int i = 0;i < 1000000;i++) {
            insertionSort(arr);
        }
        long end = System.currentTimeMillis();
        System.err.println("执行一百万次耗时：" + (end - start));
    }

    /**
     * 插入排序
     *
     * @param arr 待排序的数组
     */
    private static void insertionSort(int[] arr) {

        for (int i = 1;i < arr.length;i++) {
            // 从当前数组往前扫描，因为前面的序列是有序的，只需要确定当前索引的值的位置即可
            for (int j = i;j > 0;j--) {
                if (arr[j] < arr[j - 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                } else {
                    // 不符合条件跳出内循环，提升效率
                    break;
                }
            }
        }
    }
}
