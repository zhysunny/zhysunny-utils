package com.zhysunny.common.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 集合操作类
 * @author 章云
 * @date 2019/7/27 14:57
 */
public class CollectionSplitUtils {

    /**
     * 计算list元素重复的个数
     * @param list
     * @return
     */
    public static <K> Map<K, Integer> listCount(List<K> list) {
        Map<K, Integer> map = new HashMap<K, Integer>();
        if (list.size() > 0) {
            for (K key : list) {
                if (map.containsKey(key)) {
                    map.put(key, map.get(key).intValue() + 1);
                } else {
                    map.put(key, 1);
                }
            }
        }
        return map;
    }

    /**
     * 拆分集合
     * @param <T>
     * @param dataList 要拆分的集合
     * @param count    每个集合的元素个数
     * @return 返回拆分后的各个集合
     */
    public static <T> List<List<T>> split(List<T> dataList, int count) {
        List<List<T>> result = new ArrayList<>(count);
        int size = dataList.size();
        int avg = size / count;
        int last = size % count;
        for (int i = 0; i < count; i++) {
            List<T> itemList = null;
            if (i < last) {
                itemList = new ArrayList<T>(avg + 1);
                for (int j = 0; j < (avg + 1); j++) {
                    itemList.add(dataList.get(i * (avg + 1) + j));
                }
            } else {
                itemList = new ArrayList<T>(avg);
                for (int j = 0; j < avg; j++) {
                    itemList.add(dataList.get(i * avg + j + last));
                }
            }
            result.add(itemList);
        }
        return result;
    }

    /**
     * 集合重分布
     * @param dataList
     * @param count
     * @return
     */
    public static <T> List<List<T>> splitList(List<List<T>> dataList, int count) {
        List<T> items = new ArrayList<T>();
        for (List<T> list : dataList) {
            for (T t : list) {
                items.add(t);
            }
        }
        return split(items, count);
    }

    /**
     * 拆分集合
     * @param dataList list集合，元素map，每个map一个键值对，每个键值对为String,List
     * @param count    拆分后dataList的长度
     * @return
     */
    public static <T> List<Map<String, List<T>>> splitListMap(List<Map<String, List<T>>> dataList, int count) {
        List<Map<String, List<T>>> result = new ArrayList<Map<String, List<T>>>(count);
        int total = 0;
        for (Map<String, List<T>> dataMap : dataList) {
            for (Entry<String, List<T>> entry : dataMap.entrySet()) {
                total += entry.getValue().size();
            }
        }
        int avg = total / count;
        if (avg == 0) {
            return dataList;
        }
        for (Map<String, List<T>> dataMap : dataList) {
            for (Entry<String, List<T>> entry : dataMap.entrySet()) {
                List<T> list = entry.getValue();
                double coef = list.size() / (double) avg;
                if (coef < 1.5) {
                    result.add(dataMap);
                } else {
                    // 四舍五入
                    int num = (int) (coef + 0.5);
                    List<List<T>> splitList = split(list, num);
                    for (List<T> l : splitList) {
                        Map<String, List<T>> map = new HashMap<String, List<T>>();
                        map.put(entry.getKey(), l);
                        result.add(map);
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<List<String>> dataList = new ArrayList<>();
        dataList.add(getList(1));
        dataList.add(getList(1));
        dataList.add(getList(1));
        dataList.add(getList(1));
        dataList.add(getList(1));
        int count = 8;
        for (List<String> list : dataList) {
            System.out.println(list.size());
        }
        System.out.println("------------------------------------------");
        dataList = splitList(dataList, count);
        for (List<String> list : dataList) {
            System.out.println(list.size());
        }
    }

    private static List<String> getList(int count) {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < count; i++) {
            list.add("test");
        }
        return list;
    }

}
