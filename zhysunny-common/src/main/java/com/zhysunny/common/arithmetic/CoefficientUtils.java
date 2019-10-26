package com.zhysunny.common.arithmetic;

import java.util.ArrayList;
import java.util.List;

/**
 * 因数
 * @author 章云
 * @date 2019/7/27 14:44
 */
public class CoefficientUtils {
    /**
     * 分解质因数
     * @param n
     * @return
     */
    public static List<Integer> decomposition(int n) {
        List<Integer> list = new ArrayList<>();
        list = decomposition(n, list);
        return list;
    }

    private static List<Integer> decomposition(int n, List<Integer> list) {
        for (int i = 2; i <= n; i++) {
            if (i < n && n % i == 0) {
                n = n / i;
                list.add(i);
                decomposition(n, list);
                // 由于重新调用方法，所以需跳出循环重新开始
                break;
            }
            // 打印最后一个质数
            if (i == n) {
                list.add(i);
            }
        }
        return list;
    }

    /**
     * 最大公约数
     * @param m
     * @param n
     * @return
     */
    public static int maxApproximate(int m, int n) {
        if (m >= n) {
            int r = m % n;
            if (r != 0) {
                m = n;
                n = r;
                return maxApproximate(m, n);
            } else {
                return n;
            }
        } else {
            return maxApproximate(n, m);
        }
    }

    /**
     * 最小公倍数
     * @param m
     * @param n
     * @return
     */
    public static int minMultiple(int m, int n) {
        if (m >= n) {
            int r = m % n;
            if (r != 0) {
                return m * n / maxApproximate(m, n);
            } else {
                return m;
            }
        } else {
            return minMultiple(n, m);
        }
    }
}