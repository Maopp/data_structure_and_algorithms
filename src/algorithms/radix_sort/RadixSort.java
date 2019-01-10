package algorithms.radix_sort;

/**
 * algorithms.radix_sort
 *
 * @Author cat_pp
 * @Date 2019/1/10
 * @Description 基数排序
 */
public class RadixSort {

    public static void main(String[] args) {
        int[] arr = new int[]{5, 3, 0, 6, 8, 2, 6};
        System.err.println("排序前的数组元素：");
        for (int i : arr) {
            System.err.print(i + ",");
        }

        // 对数组进行冒泡排序
        radixSort(arr);

        System.err.println();
        System.err.println("排序后的数组元素：");
        for (int i : arr) {
            System.err.print(i + ",");
        }
        System.err.println();

        // 执行1000000次排序耗时
        long start = System.currentTimeMillis();
        for (int i = 0;i < 1000000;i++) {
            radixSort(arr);
        }
        long end = System.currentTimeMillis();
        System.err.println("执行一百万次耗时：" + (end - start));
    }

    /**
     * 基数排序
     *
     * @param arr 待排序的数组
     */
    private static void radixSort(int[] arr) {
        radixSort(arr, getMaxWeishu(arr));
    }

    /**
     * 基数排序
     * @param arr 待排序的数组
     * @param d 数组最大元素的位数
     */
    private static void radixSort(int[] arr, int d) {
        int[][] array = new int[10][arr.length + 1];
        for (int i = 0; i < 10; i++) {
            // array[i][0]记录第i行数据的个数
            array[i][0] = 0;
        }
        for (int pos = 1; pos <= d; pos++) {
            // 分配过程
            for (int i = 0; i < arr.length; i++) {
                int row = getNumInPos(arr[i], pos);
                int col = ++array[row][0];
                array[row][col] = arr[i];
            }
            // 收集过程
            for (int row = 0, i = 0; row < 10; row++) {
                for (int col = 1; col <= array[row][0]; col++) {
                    arr[i++] = array[row][col];
                }
                // 复位，下一个pos时还需使用
                array[row][0] = 0;
            }
        }
    }

    /**
     * pos=1表示个位，pos=2表示十位
     *
     * @param num
     * @param pos
     * @return
     */
    private static int getNumInPos(int num, int pos) {
        int tmp = 1;
        for (int i = 0; i < pos - 1; i++) {
            tmp *= 10;
        }
        return (num / tmp) % 10;
    }

    /**
     * 求得最大位数d
     *
     * @param arr
     * @return
     */
    public static int getMaxWeishu(int[] arr) {
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        int tmp = 1, d = 1;
        while (true) {
            tmp *= 10;
            if (max / tmp != 0) {
                d++;
            } else {
                break;
            }
        }
        return d;
    }
}