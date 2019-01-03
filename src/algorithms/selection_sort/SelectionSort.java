package algorithms.selection_sort;

/**
 * algorithms.selection_sort
 *
 * @Author cat_pp
 * @Date 2019/1/3
 * @Description 选择排序
 */
public class SelectionSort {

    public static void main(String[] args) {
        int[] arr = new int[]{5, 3, 0, 6, 8, 2, 6};
        System.err.println("排序前的数组元素：");
        for (int i : arr) {
            System.err.print(i + ",");
        }

        // 对数组进行选择排序
        selectionSort(arr);

        System.err.println();
        System.err.println("排序后的数组元素：");
        for (int i : arr) {
            System.err.print(i + ",");
        }
        System.err.println();

        // 执行1000000次排序耗时
        long start = System.currentTimeMillis();
        for (int i = 0;i < 1000000;i++) {
            selectionSort(arr);
        }
        long end = System.currentTimeMillis();
        System.err.println("执行一百万次耗时：" + (end - start));
    }

    /**
     * 选择排序
     * 外循环是执行排序的趟数，内循环是每趟排序两两比较的次数
     *
     * @param arr 待排序的数组
     */
    private static void selectionSort(int[] arr) {
        // 选择排序
        /*for (int i = 0;i < arr.length;i++) {
            for (int j = i + 1;j < arr.length;j++) {
                // 交换位置
                if (arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }*/
        // 优化算法：节省交换时间，内循环只记录最小值的索引，内循环结束再交换
        for (int i = 0;i < arr.length - 1;i++) {
            int k = i;
            for (int j = i + 1;j < arr.length;j++) {
                // 记录最小值索引
                if (arr[i] > arr[j]) {
                    k = j;
                }
            }
            // 交换位置
            if (i != k) {
                int temp = arr[i];
                arr[i] = arr[k];
                arr[k] = temp;
            }
        }
    }
}
