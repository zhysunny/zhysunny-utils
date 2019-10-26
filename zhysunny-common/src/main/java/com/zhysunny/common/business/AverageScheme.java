package com.zhysunny.common.business;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 数据重分布平均分配方案
 * @author 章云
 * @date 2019/7/27 14:43
 */
public class AverageScheme {
    /**
     * 数据重分布平均分配方案
     * @param dataMap
     * @param set
     * @return
     */
    public static List<Map<String, String>> getScheme(Map<String, Integer> dataMap, Set<String> set) {
        // 获取所有的key
        Set<String> allKey = new HashSet<>(set);
        allKey.addAll(dataMap.keySet());
        // 不包含的key补0
        for (String key : allKey) {
            if (!dataMap.containsKey(key)) {
                dataMap.put(key, 0);
            }
        }
        // 求和
        int total = 0;
        for (Entry<String, Integer> entry : dataMap.entrySet()) {
            total += entry.getValue();
        }
        // 求平均值
        int avg = total / set.size();
        // 得到目标的列表
        Map<String, Integer> toMap = new LinkedHashMap<>(allKey.size());
        // 余数
        int count = total % set.size();
        int i = 0;
        for (String key : set) {
            if (i < count) {
                toMap.put(key, avg + 1);
            } else {
                toMap.put(key, avg);
            }
            i++;
        }
        // 不包含的key补0
        for (String key : allKey) {
            if (!toMap.containsKey(key)) {
                toMap.put(key, 0);
            }
        }
        if (dataMap.size() != toMap.size()) {
            return null;
        }
        List<Map<String, String>> list = new ArrayList<>();
        // 求每个元素需要平衡的增减量
        Map<String, Integer> fromMap = new LinkedHashMap<>(dataMap.size());
        for (String key : dataMap.keySet()) {
            fromMap.put(key, dataMap.get(key) - toMap.get(key));
        }
        calculate(fromMap, list);
        return list;
    }

    /**
     * 根据fromMap制定方案
     * @param fromMap
     * @param list
     */
    private static void calculate(Map<String, Integer> fromMap, List<Map<String, String>> list) {
        boolean flag = false;
        for (Entry<String, Integer> entry : fromMap.entrySet()) {
            if (entry.getValue() != 0) {
                // 只要含有不为0的数就执行方法
                flag = true;
                break;
            }
        }
        if (flag) {
            Map<String, String> toMap = new LinkedHashMap<>();
            int max = 0;
            String maxKey = null;
            int min = 0;
            String minKey = null;
            for (String key : fromMap.keySet()) {
                // 取第一个正数
                if (fromMap.get(key) > 0 && max == 0) {
                    max = fromMap.get(key);
                    maxKey = key;
                }
                // 取第一个负数
                if (fromMap.get(key) < 0 && min == 0) {
                    min = fromMap.get(key);
                    minKey = key;
                }
            }
            toMap.put("fromKey", maxKey);
            toMap.put("toKey", minKey);
            int sum = max + min;
            if (sum >= 0) {
                toMap.put("count", String.valueOf(-min));
                fromMap.put(maxKey, sum);
                fromMap.put(minKey, 0);
            } else {
                toMap.put("count", String.valueOf(max));
                fromMap.put(maxKey, 0);
                fromMap.put(minKey, sum);
            }
            // 获取一种方案
            list.add(toMap);
            calculate(fromMap, list);
        }
    }

    public static void main(String[] args) {
        Map<String, Integer> dataMap = new LinkedHashMap<>();
        dataMap.put("1", 100);
        dataMap.put("2", 67);
        dataMap.put("3", 94);
        dataMap.put("4", 504);
        dataMap.put("5", 398);
        Set<String> set = new HashSet<String>();
        set.add("8");
        set.add("5");
        List<Map<String, String>> schemes = AverageScheme.getScheme(dataMap, set);
        for (Map<String, String> scheme : schemes) {
            System.out.println(scheme);
        }
    }

}
