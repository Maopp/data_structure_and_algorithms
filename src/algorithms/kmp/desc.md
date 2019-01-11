# KMP算法
> KMP是三位大牛：D.E.Knuth、J.H.Morris和V.R.Pratt同时发现的。其中第一位就是《计算机程序设计艺术》的作者！！
KMP算法要解决的问题就是在字符串（也叫主串）中的模式（pattern）定位问题。说简单点就是我们平时常说的**关键字搜索**。
模式串就是关键字（接下来称它为P），如果它在一个主串（接下来称为T）中出现，就返回它的具体位置，否则返回-1（常用手段）。

## 原理：
> 当匹配失败时，j要移动的下一个位置k。存在着这样的性质：**最前面的k个字符和j之前的最后k个字符是一样的**。
![辅助图解](/src/images/algorithms/kmp/kmp算法辅助图解.png)
当T[i] != P[j]时
有T[i-j ~ i-1] == P[0 ~ j-1]
由P[0 ~ k-1] == P[j-k ~ j-1]
必然：T[i-k ~ i-1] == P[0 ~ k-1]

## 怎么求这个（这些）k呢？
> 因为在P的每一个位置都可能发生不匹配，也就是说我们要计算每一个位置j对应的k，所以用一个数组next来保存，
**next[j] = k，表示当T[i] != P[j]时，j指针的下一个位置**。
```$xslt
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
```
> ps:**next[j]的值（也就是k）表示，当P[j] != T[i]时，j指针的下一步移动位置。**

- 当j为0时，如果这时候不匹配，怎么办？
    - j已经在最左边了，不可能再移动了，这时候要应该是i指针后移。所以在代码中才会有next[0] = -1;这个初始化。
    ![辅助图解](/src/images/algorithms/kmp/第一个字符就不匹配啦。。.png)  
- 当j为1的时候呢？
    - 显然，j指针一定是后移到0位置的。因为它前面也就只有这一个位置了。。
    ![辅助图解](/src/images/algorithms/kmp/第二个字符就不匹配啦。。.png) 
- **当p[k] == p[j]时**：
    - 解释代码：
        ```$xslt
        next[++j] = ++k;
        ```
    ![辅助图解](/src/images/algorithms/kmp/p[k]==p[j]_1.png) ![辅助图解](/src/images/algorithms/kmp/p[k]==p[j]_2.png) 
    - 对比上图可以看到：
        - 当P[k] == P[j]时，有next[j+1] == next[j] + 1
        证明：
        > 因为在P[j]之前已经有P[0 ~ k-1] == p[j-k ~ j-1]。（next[j] == k）
          这时候现有P[k] == P[j]，我们是不是可以得到P[0 ~ k-1] + P[k] == p[j-k ~ j-1] + P[j]。
          即：P[0 ~ k] == P[j-k ~ j]，即next[j+1] == k + 1 == next[j] + 1。
 - **当p[k] != p[j]时**：
    - 解释代码：
        ```$xslt
        k = next[k];
        ```
    ![辅助图解](/src/images/algorithms/kmp/p[k]!=p[j]_1.png) ![辅助图解](/src/images/algorithms/kmp/p[k]!=p[j]_2.png) 
    > 像上边的例子，我们已经不可能找到[ A，B，A，B ]这个最长的后缀串了，但我们还是可能找到[ A，B ]、[ B ]这样的前缀串的。
    所以这个过程像不像在定位[ A，B，A，C ]这个串，当C和主串不一样了（也就是k位置不一样了），那当然是把指针移动到next[k]啦。
