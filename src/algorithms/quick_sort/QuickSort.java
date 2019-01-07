package algorithms.quick_sort;

/**
 * algorithms.quick_sort
 *
 * @Author cat_pp
 * @Date 2019/1/7
 * @Description 快速排序
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] arr = new int[]{5, 3, 0, 6, 8, 2, 6};
        System.err.println("排序前的数组元素：");
        for (int i : arr) {
            System.err.print(i + ",");
        }

        // 对数组进行冒泡排序
        quickSort(arr);

        System.err.println();
        System.err.println("排序后的数组元素：");
        for (int i : arr) {
            System.err.print(i + ",");
        }
        System.err.println();

        // 执行1000000次排序耗时
        long start = System.currentTimeMillis();
        for (int i = 0;i < 1000000;i++) {
            quickSort(arr);
        }
        long end = System.currentTimeMillis();
        System.err.println("执行一百万次耗时：" + (end - start));
    }

    /**
     * 快速排序
     *
     * @param arr
     */
    private static void quickSort(int[] arr) {
        // 递归排序
        sort(arr, 0, arr.length - 1);
    }

    private static void sort(int[] arr, int left, int right) {
        if (left < right) {
            // 获取枢纽值，并将其放在当前待处理序列末尾
            dealPivot(arr, left, right);
            // 枢纽值被放在序列末尾
            int pivot = right - 1;
            // 左指针
            int i = left;
            // 右指针
            int j = right - 1;
//            while (true) {
            while (i < j) {
                while (arr[++i] < arr[pivot]) {
                }
                while (j > left && arr[--j] > arr[pivot]) {
                }
                if (i < j) {
                    swap(arr, i, j);
                } else {
                    break;
                }
            }
            // 当i>j时，交换枢纽值和大于枢纽值的值
            if (i < right - 1) {
                swap(arr, i, right - 1);
            }
            sort(arr, left, i - 1);
            sort(arr, i + 1, right);
        }
    }

    private static void dealPivot(int[] arr, int left, int right) {
        int mid = (left + right) >> 1;
        if (arr[left] > arr[mid]) {
            swap(arr, left, mid);
        }
        if (arr[left] > arr[right]) {
            swap(arr, left, right);
        }
        if (arr[mid] > arr[right]) {
            swap(arr, right, mid);
        }
        swap(arr, right - 1, mid);
    }

    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

}
