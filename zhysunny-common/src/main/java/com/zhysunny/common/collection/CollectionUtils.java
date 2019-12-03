package com.zhysunny.common.collection;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 集合操作类
 * @author 章云
 * @date 2019/10/25 19:09
 */
public class CollectionUtils {

    /**
     * 先去掉null元素，再排序
     * 要求集合长须，元素顺序相同返回true
     * 不考虑实现类的不同和顺序不同
     * @param collection1
     * @param collection2
     * @return
     */
    public static <T> boolean equalsCollection(Collection<T> collection1, Collection<T> collection2) {
        List<T> list1 = collection1.stream().filter(Objects::nonNull).collect(Collectors.toList());
        Collections.sort(list1, null);
        List<T> list2 = collection2.stream().filter(Objects::nonNull).collect(Collectors.toList());
        Collections.sort(list2, null);
        if (list1.size() != list2.size()) {
            return false;
        }
        int size = list1.size();
        for (int i = 0; i < size; i++) {
            if (!list1.get(i).equals(list2.get(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 要求两个集合，长度相同，元素相同，返回true，不考虑实现类的不同和顺序不同
     * @param map1
     * @param map2
     * @return
     */
    public static <K, V> boolean equalsMap(Map<K, V> map1, Map<K, V> map2) {
        if (map1.size() != map2.size()) {
            return false;
        }
        for (Map.Entry<K, V> entry : map1.entrySet()) {
            K key = entry.getKey();
            V value = entry.getValue();
            if (!map2.containsKey(key)) {
                return false;
            }
            if (value == null && map2.get(key) != null) {
                return false;
            }
            if (value != null && !value.equals(map2.get(key))) {
                return false;
            }
        }
        return true;
    }

}
