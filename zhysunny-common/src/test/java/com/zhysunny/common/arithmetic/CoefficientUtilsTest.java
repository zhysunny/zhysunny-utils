package com.zhysunny.common.arithmetic;

import static org.junit.Assert.*;
import com.zhysunny.common.collection.CollectionUtils;
import org.junit.*;
import java.util.Arrays;
import java.util.List;

/**
 * CoefficientUtils Test
 * @author 章云
 * @date 2019/10/25 23:40
 */
public class CoefficientUtilsTest {

    private int n1;
    private int n2;
    private int n3;

    @BeforeClass
    public static void beforeClass() throws Exception {
        System.out.println("Test CoefficientUtils Class Start...");
    }

    @Before
    public void before() throws Exception {
        n1 = 56;
        n2 = 76;
        n3 = 8;
    }

    @After
    public void after() throws Exception {
    }

    @AfterClass
    public static void afterClass() throws Exception {
        System.out.println("Test CoefficientUtils Class End...");
    }

    /**
     * Method: decomposition(int n)
     */
    @Test
    public void testDecomposition() throws Exception {
        List<Integer> result = CoefficientUtils.decomposition(n1);
        List<Integer> answer = Arrays.asList(2, 2, 2, 7);
        System.out.println(n1 + "的质因数" + result);
        assertTrue(n1 + "的质因数", CollectionUtils.equalsCollection(result, answer));
    }

    /**
     * Method: maxApproximate(int m, int n)
     */
    @Test
    public void testMaxApproximate() throws Exception {
        int result = CoefficientUtils.maxApproximate(n1, n2);
        System.out.println(n1 + "和" + n2 + "的最大公约数：" + result);
        assertEquals(n1 + "和" + n2 + "的最大公约数", result, 4);
    }

    /**
     * Method: minMultiple(int m, int n)
     */
    @Test
    public void testMinMultiple() throws Exception {
        int result = CoefficientUtils.minMultiple(n1, n2);
        System.out.println(n1 + "和" + n2 + "的最小公倍数：" + result);
        assertEquals(n1 + "和" + n2 + "的最小公倍数", result, 1064);
        result = CoefficientUtils.minMultiple(n1, n3);
        System.out.println(n1 + "和" + n3 + "的最小公倍数：" + result);
        assertEquals(n1 + "和" + n3 + "的最小公倍数", result, 56);
    }

}
