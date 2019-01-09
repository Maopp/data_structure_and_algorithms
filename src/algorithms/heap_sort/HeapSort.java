package algorithms.heap_sort;

import com.sun.xml.internal.stream.StaxErrorReporter;

/**
 * algorithms.heap_sort
 *
 * @Author cat_pp
 * @Date 2019/1/8
 * @Description
 */
public class HeapSort {

    public static void main(String[] args) {
        int[] arr = new int[]{5, 3, 0, 6, 8, 2, 6};
        System.err.println("排序前的数组元素：");
        for (int i : arr) {
            System.err.print(i + ",");
        }

        // 对数组进行冒泡排序
        heapSort(arr);

        System.err.println();
        System.err.println("排序后的数组元素：");
        for (int i : arr) {
            System.err.print(i + ",");
        }
        System.err.println();

        // 执行1000000次排序耗时
        long start = System.currentTimeMillis();
        for (int i = 0;i < 1000000;i++) {
            heapSort(arr);
        }
        long end = System.currentTimeMillis();
        System.err.println("执行一百万次耗时：" + (end - start));

        // 获取数组第3大的元素
        int k = 5;
        int val = getNumberK(arr, k);
        System.err.println("第" + k + "大的值是：" + val);
    }

    /**
     * 获取数组中第几大的元素：
     * 用前k个元素构建小顶堆；
     * 遍历剩下的元素与堆顶元素比较，如果大于堆顶元素，那么替换堆顶元素，调整小顶堆；
     * 最后堆顶元素就是第K大的元素
     * 时间复杂度：O(nlogk)
     *
     * @param arr 数据源
     * @param k 第几大的元素
     * @return
     */
    private static int getNumberK(int[] arr, int k) {
        // 用前K个元素构建小顶堆
        buildSmallTopHeap(arr, k);
        // 继续遍历数组，和堆顶元素比较
        for (int i = k;i < arr.length;i++) {
            // 交换元素
            if (arr[i] > arr[0]) {
                swap(arr, 0, i);
            }
            // 构建构建小顶堆
            buildSmallTopHeap(arr, k);
        }
        // 返回堆顶元素
        return arr[0];
    }

    /**
     * 构建小顶堆
     *
     * @param arr 数据源
     * @param size 小顶堆的大小
     */
    private static void buildSmallTopHeap(int[] arr, int size) {
        for (int i = (size - 1 - 1) >> 1;i >= 0;i--) {
            // 从最后一个非叶子节点开始下沉处理
            downAdjust(arr, i, size - 1);
        }
    }

    /**
     * 堆排序
     *
     * @param arr
     */
    private static void heapSort(int[] arr) {
        // 构建大顶堆，从第一个非叶子节点开始
        for (int i = (arr.length >> 1) - 1;i >= 0;i--) {
            buildBigTopHeap(arr, i, arr.length);
        }

        // 交换堆顶和末尾元素 + 构建大顶堆
        for (int i = arr.length - 1;i > 0;i--) {
            // 交换元素
            swap(arr, 0, i);
            // 构建大顶堆：因为前面已经构建了一次大顶堆，所以后面的构建只需要从根节点下沉就可以啦。。
            buildBigTopHeap(arr, 0, i);
        }
    }

    /**
     * 交换数组元素
     *
     * @param arr
     * @param i
     * @param j
     */
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 构建大顶堆
     *
     * @param arr 待调整的堆
     * @param parentIndex 父节点索引
     * @param effectiveLength 堆的有效长度
     */
    private static void buildBigTopHeap(int[] arr, int parentIndex, int effectiveLength) {
        int temp = arr[parentIndex];
        for (int childIndex = (parentIndex << 1) + 1;childIndex < effectiveLength;childIndex = (childIndex << 1) + 1) {
            // 获取叶子节点中较大的节点
            if (childIndex + 1 < effectiveLength && arr[childIndex + 1] > arr[childIndex]) {
                childIndex++;
            }
            // 如果父节点大于叶子节点，跳出循环
            if (temp >= arr[childIndex]) {
                break;
            } else {
                // 不用真正交换数据
                arr[parentIndex] = arr[childIndex];
                parentIndex = childIndex;
            }
        }
        arr[parentIndex] = temp;
    }

    /**
     * 上浮调整（插入节点）
     * 插入到最后一个位置，向上比较
     *
     * @param arr 待调整的堆
     */
    public static void upAdjust(int[] arr) {
        int childIndex = arr.length - 1;
        int parentIndex = (arr.length - 1) >> 1;
        // temp保存插入的叶子节点值，用于最后的赋值
        int temp = arr[childIndex];
        while (childIndex > 0 && temp < arr[parentIndex]) {
            // 无需真正交换，单向赋值即可，循环结束后将temp的值放到最后调整的位置就可以啦。。。
            arr[childIndex] = arr[parentIndex];
            childIndex = parentIndex;
            parentIndex = (childIndex - 1) >> 1;
        }
        arr[childIndex] = temp;
    }

    /**
     * 下沉调整（删除节点）
     * 删除堆顶元素，将堆的最后一个元素添加到堆顶，然后进行下沉调整
     *
     * @param arr 待调整的堆
     * @param parentIndex 要下沉的父节点
     * @param effectiveLength 堆的有效大小
     */
    public static void downAdjust(int[] arr, int parentIndex, int effectiveLength) {
        // temp保存父节点的值，用于最后的赋值
        int temp = arr[parentIndex];
        int childIndex = (parentIndex << 1) + 1;
        while (childIndex < effectiveLength) {
            // 当还有左右子节点时，并且右节点的值小于左节点的值，定位到右节点
            if (childIndex + 1 < effectiveLength && arr[childIndex + 1] < arr[childIndex]) {
                childIndex++;
            }
            // 如果父节点的值小于左右子节点的值，跳出循环
            if (temp < arr[childIndex]) {
                break;
            }
            // 无需真正交换，单向赋值即可，循环结束后将temp的值放到最后调整的位置就可以啦。。。
            arr[parentIndex] = arr[childIndex];
            parentIndex = childIndex;
            childIndex = parentIndex << 1 + 1;
        }
        arr[parentIndex] = temp;
    }

    /**
     * 构建堆（堆的自我调整）
     *
     * @param arr 待调整的堆
     */
    public static void buildHeap(int[] arr) {
        // 从最后一个非叶子节点开始，依次下沉调整
        for(int i = arr.length >> 1;i >= 0;i--) {
            downAdjust(arr, i, arr.length - 1);
        }
    }
}
