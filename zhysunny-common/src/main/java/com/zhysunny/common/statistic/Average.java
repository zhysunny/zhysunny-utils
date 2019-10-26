package com.zhysunny.common.statistic;

import java.util.Collections;
import java.util.List;

/**
 * 统计指标
 * @author 章云
 * @date 2019/7/27 15:30
 */
public class Average {

    private Average() {}

    /**
     * 中位数
     * @param list
     * @return
     */
    public static double getMedian(List<Double> list) {
        Collections.sort(list);
        int len = list.size();
        double result = 0;
        if (len % 2 == 1) {
            int index = len / 2;
            result = list.get(index);
        } else {
            int index1 = len / 2 - 1;
            int index2 = len / 2;
            result = (list.get(index1) + list.get(index2)) / 2.0;
        }
        return result;
    }

}
