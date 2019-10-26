package com.zhysunny.common.collection;

import static com.zhysunny.common.collection.CollectionUtils.*;
import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * CollectionUtils Test
 */
public class CollectionUtilsTest {

    @BeforeClass
    public static void beforeClass() throws Exception {
        System.out.println("Test CollectionUtils Class Start...");
    }

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @AfterClass
    public static void afterClass() throws Exception {
        System.out.println("Test CollectionUtils Class End...");
    }

    /**
     * Method: equalsCollection(Collection<T> collection1, Collection<T> collection2)
     */
    @Test
    public void testEqualsCollection() throws Exception {
        List<Integer> list1 = Arrays.asList(1, null, 5, 7);
        System.out.println("list1:" + list1.getClass() + list1);
        // list1和list2长度不相同
        List<Integer> list2 = Arrays.asList(1, null, 5, 7, 9);
        System.out.println("list2:" + list2.getClass() + list2);
        assertFalse("长度不相同", equalsCollection(list1, list2));
        // list1和list2长度相同，元素不同
        List<Integer> list3 = Arrays.asList(1, null, 5, 9);
        System.out.println("list2:" + list3.getClass() + list3);
        assertFalse("长度相同，元素不同", equalsCollection(list1, list3));
        // list1和set4元素相同，顺序不相同，实现类不相同，集合不相同
        Set<Integer> set4 = list1.stream().collect(Collectors.toSet());
        System.out.println("set4:" + set4.getClass() + set4);
        assertTrue("元素相同，顺序不相同，实现类不相同，集合不相同", equalsCollection(list1, set4));
    }

    /**
     * Method: equalsMap(Map<K, V> map1, Map<K, V> map2)
     */
    @Test
    public void testEqualsMap() throws Exception {
        Map<String, Integer> map1 = new HashMap<>();
        map1.put("1", 1);
        map1.put(null, 2);
        map1.put("3", null);
        map1.put("4", 4);
        // map1和map2长度不同
        Map<String, Integer> map2 = new HashMap<>();
        map2.put("1", 1);
        map2.put(null, 2);
        map2.put("3", null);
        assertFalse("长度不相同", equalsMap(map1, map2));
        // map1和map3长度相同，有不同的key
        Map<String, Integer> map3 = new HashMap<>();
        map3.put("1", 1);
        map3.put(null, 2);
        map3.put("3", null);
        map3.put("5", 4);
        assertFalse("长度相同，有不同的key", equalsMap(map1, map3));
        // map1和map4长度相同，有不同的value
        Map<String, Integer> map4 = new HashMap<>();
        map4.put("1", 1);
        map4.put(null, 2);
        map4.put("3", null);
        map4.put("4", 5);
        assertFalse("长度相同，有不同的value", equalsMap(map1, map4));
        // map1和map5长度相同，键值对相同，不考虑实现类和顺序
        // 注意TreeMap的键不能为null
        Map<String, Integer> map5 = new LinkedHashMap<>();
        map5.put("1", 1);
        map5.put("4", 4);
        map5.put("3", null);
        map5.put(null, 2);
        assertTrue("长度相同，键值对相同，不考虑实现类和顺序", equalsMap(map1, map5));
    }

} 
