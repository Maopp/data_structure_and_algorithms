package algorithms.bubble_sort;

/**
 * algorithms.bubble_sort
 *
 * @Author cat_pp
 * @Date 2019/1/3
 * @Description 冒泡排序
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] arr = new int[]{5, 3, 0, 6, 8, 2, 6};
        System.err.println("排序前的数组元素：");
        for (int i : arr) {
            System.err.print(i + ",");
        }

        // 对数组进行冒泡排序
        bubbleSort(arr);

        System.err.println();
        System.err.println("排序后的数组元素：");
        for (int i : arr) {
            System.err.print(i + ",");
        }
        System.err.println();

        // 执行1000000次排序耗时
        long start = System.currentTimeMillis();
        for (int i = 0;i < 1000000;i++) {
            bubbleSort(arr);
        }
        long end = System.currentTimeMillis();
        System.err.println("执行一百万次耗时：" + (end - start));
    }

    /**
     * 冒泡排序
     * 外层循环是执行比较的趟数，内层循环是每趟比较过程中两两比较的次数
     *
     * @param arr 待排序的数组
     */
    private static void bubbleSort(int[] arr) {
        for (int i = 1; i < arr.length;i++) {
            // 内层循环要从索引为0处开始，不然回丢失最后一个元素
            for (int j = 0;j < arr.length - i;j++) {
                // 交换位置
                if (arr[j] >= arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
}
