package com.zhysunny.common.arithmetic;

import static org.junit.Assert.*;
import org.junit.*;

/**
 * HexadecimalUtils Test
 * @author 章云
 * @date 2019/10/26 7:24
 */
public class HexadecimalUtilsTest {

    private int n1;
    private String nHex2;
    private String nHex8;
    private String nHex16;

    @BeforeClass
    public static void beforeClass() throws Exception {
        System.out.println("Test HexadecimalUtils Class Start...");
    }

    @Before
    public void before() throws Exception {
        n1 = 89;
        nHex2 = "1011001";
        nHex8 = "131";
        nHex16 = "59";
    }

    @After
    public void after() throws Exception {
    }

    @AfterClass
    public static void afterClass() throws Exception {
        System.out.println("Test HexadecimalUtils Class End...");
    }

    /**
     * Method: getHexadecimal(int param, int number)
     */
    @Test
    public void testGetHexadecimal() throws Exception {
        String hex2 = HexadecimalUtils.getHexadecimal(n1, 2);
        System.out.println(n1 + "的2进制数：" + hex2);
        assertEquals(n1 + "的2进制数", hex2, nHex2);
        String hex8 = HexadecimalUtils.getHexadecimal(n1, 8);
        System.out.println(n1 + "的8进制数：" + hex8);
        assertEquals(n1 + "的8进制数", hex8, nHex8);
        String hex16 = HexadecimalUtils.getHexadecimal(n1, 16);
        System.out.println(n1 + "的16进制数：" + hex16);
        assertEquals(n1 + "的16进制数", hex16, nHex16);
    }

    /**
     * Method: getTenHexadecimal(String param, int number)
     */
    @Test
    public void testGetTenHexadecimal() throws Exception {
        int tenHex2 = HexadecimalUtils.getTenHexadecimal(nHex2, 2);
        System.out.println("2进制数" + nHex2 + "的10进制数：" + tenHex2);
        assertEquals("2进制数" + nHex2 + "的10进制数", tenHex2, n1);
        int tenHex8 = HexadecimalUtils.getTenHexadecimal(nHex8, 8);
        System.out.println("8进制数" + nHex8 + "的10进制数：" + tenHex8);
        assertEquals("8进制数" + nHex8 + "的10进制数", tenHex8, n1);
        int tenHex16 = HexadecimalUtils.getTenHexadecimal(nHex16, 16);
        System.out.println("16进制数" + nHex16 + "的10进制数：" + tenHex16);
        assertEquals("16进制数" + nHex16 + "的10进制数", tenHex16, n1);
    }

}
