package algorithms.kmp;

/**
 * algorithms.kmp
 *
 * @Author cat_pp
 * @Date 2019/1/11
 * @Description KMP算法
 */
public class Kmp {

    public static void main(String[] args) {

        String mainStr = "abcdabcefgabcde";
        String patternStr = "abcde";
        // 普通方法
        int matchIndex = commonMethod(mainStr, patternStr);
        System.err.println("第一次出现关键字的位置为：" + matchIndex);

        // 执行1000000次
        long start = System.currentTimeMillis();
        for (int i = 0;i < 1000000;i++) {
            commonMethod(mainStr, patternStr);
        }
        long end = System.currentTimeMillis();
        System.err.println("普通方法执行一百万次所用时间：" + (end - start));

        // KMP算法
        int matchIndexKmp = kmpMethod(mainStr, patternStr);
        System.err.println("第一次出现关键字的位置为：" + matchIndexKmp);
        // 执行1000000次
        long start1 = System.currentTimeMillis();
        for (int i = 0;i < 1000000;i++) {
            commonMethod(mainStr, patternStr);
        }
        long end1 = System.currentTimeMillis();
        System.err.println("KMP方法执行一百万次所用时间：" + (end1 - start1));
    }

    /**
     * KMP算法
     * @param mainStr 主串
     * @param patternStr 模式串（关键字）
     */
    private static int kmpMethod(String mainStr, String patternStr) {
        char[] t = mainStr.toCharArray();
        char[] p = patternStr.toCharArray();
        int i = 0;
        int j = 0;
        int[] next = getNext(patternStr);
        while (i < t.length && j < p.length) {
            // 当j == -1时，要移动主串的指针，模式串的指针也要初始化为0
            if (j == -1 || t[i] == p[j]) {
                i++;
                j++;
            } else {
                // i不需要回溯啦。。。
//                i = i - j + 1;
                // j回到指针位置k
                j = next[j];
            }
        }
        if (j == p.length) {
            return i - j;
        } else {
            return  -1;
        }
    }

    /**
     * 获取每个字符对应的k，j可以移动到k位置进行比较，前面的字符不需要比较啦。。next[j] = k
     * P[0 ~ k-1] == P[j-k ~ j-1]
     *
     * @param patternStr 模式串
     * @return
     */
    private static int[] getNext(String patternStr) {
        char[] pattern = patternStr.toCharArray();
        int[] next = new int[pattern.length];
        // 因为当第一个字符就不匹配的时候，应该是主串的指针向后移动，所以next[0] = -1
        next[0] = -1;
        int j = 0;
        int k = -1;
        while (j < pattern.length - 1) {
            if (k == -1 || pattern[j] == pattern[k]) {
                next[++j] = ++k;
            } else {
                k = next[k];
            }
        }
        return next;
    }

    /**
     * 普通查找方法
     *
     * @param mainStr 主串
     * @param patternStr 模式串（关键字）
     */
    private static int commonMethod(String mainStr, String patternStr) {
        char[] main = mainStr.toCharArray();
        char[] pattern = patternStr.toCharArray();
        int i = 0, j = 0;
        while (i < main.length && j < pattern.length) {
            // 当字符匹配的时候，主串和模式串指针向后移动
            if (main[i] == pattern[j]) {
                i++;
                j++;
            }
            // 当字符不匹配的时候，主串指针退回到开始位置+1，模式串指针初始化为0
            else {
                i = i - j + 1;
                j = 0;
            }
        }
        // 当j的值==模式串长度时，证明查找到关键字，返回模式串出现的位置，否则返回-1
        if (pattern.length == j) {
            return i - j;
        } else {
            return -1;
        }
    }

}
