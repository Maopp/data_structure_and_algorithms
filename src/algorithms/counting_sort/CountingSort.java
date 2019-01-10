package algorithms.counting_sort;

/**
 * algorithms.counting_sort
 *
 * @Author cat_pp
 * @Date 2019/1/9
 * @Description 计数排序
 */
public class CountingSort {

    public static void main(String[] args) {
        int[] arr = new int[]{5, 3, 0, 6, 8, 2, 6};
        System.err.println("排序前的数组元素：");
        for (int i : arr) {
            System.err.print(i + ",");
        }

        // 对数组进行冒泡排序
        countingSort(arr);

        System.err.println();
        System.err.println("排序后的数组元素：");
        for (int i : arr) {
            System.err.print(i + ",");
        }
        System.err.println();

        // 执行1000000次排序耗时
        long start = System.currentTimeMillis();
        for (int i = 0;i < 1000000;i++) {
            countingSort(arr);
        }
        long end = System.currentTimeMillis();
        System.err.println("执行一百万次耗时：" + (end - start));
    }

    /**
     * 计数排序
     *
     * @param arr 待排序的数组
     */
    private static void countingSort(int[] arr) {
        // 查询数组中的最大值和最小值
        int min = 0, max = 0;
        for (int i = 0;i < arr.length;i++) {
            min = arr[i] < min ? arr[i] : min;
            max = arr[i] > max ? arr[i] : max;
        }
        // 数组最大值和最小值的差
        int k = max - min;
        // 计数数组
        int[] countArr = new int[k + 1];
        // 将原数组中的值，以元素值为下表存放到计数数组中，计数数组中存放的是原数组元素出现的次数
        for (int i = 0;i < arr.length;i++) {
            countArr[arr[i]] += 1;
        }
        // 从计数数组中取出数据放入原数组

        /*int tempIndex = 0;
        for (int i = 0;i < countArr.length;i++) {
            if (countArr[i] > 0) {
                for (int j = 0;j < countArr[i];j++) {
                    arr[tempIndex++] = i;
                }
            }
        }*/

        /*int sum = 0;
        int[] B = new int[arr.length];
        // 修改C数组
        for(int i = 0;i < k + 1;i++)
        {
            sum+=countArr[i];
            countArr[i]=sum;
        }
        // 遍历A数组，构造B数组
        for(int i = arr.length - 1;i >= 0;i--)
        {
            // 将A中该元素放到排序后数组B中指定的位置
            B[countArr[arr[i]]-1]=arr[i];
            // 将C中该元素-1，方便存放下一个同样大小的元素
            countArr[arr[i]]--;
        }*/
    }
}
