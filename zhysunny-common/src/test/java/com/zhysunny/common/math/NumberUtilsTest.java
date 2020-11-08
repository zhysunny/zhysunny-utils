package com.zhysunny.common.math;

import org.junit.*;
import static org.assertj.core.api.Assertions.*;

/**
 * NumberUtils Test
 */
public class NumberUtilsTest {

    @BeforeClass
    public static void beforeClass() throws Exception {
        System.out.println("Test NumberUtils Class Start...");
    }

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @AfterClass
    public static void afterClass() throws Exception {
        System.out.println("Test NumberUtils Class End...");
    }

    @Test
    public void testIsOdd() throws Exception {
        assertThat(NumberUtils.isOdd(0)).isFalse();
        assertThat(NumberUtils.isOdd(1)).isTrue();
        assertThat(NumberUtils.isOdd(-1)).isTrue();
        assertThat(NumberUtils.isOdd(15)).isTrue();
        assertThat(NumberUtils.isOdd(-15)).isTrue();
        assertThat(NumberUtils.isOdd(4)).isFalse();
    }

    @Test
    public void testIsEven() throws Exception {
        assertThat(NumberUtils.isEven(0)).isTrue();
        assertThat(NumberUtils.isEven(2)).isTrue();
        assertThat(NumberUtils.isEven(-2)).isTrue();
        assertThat(NumberUtils.isEven(16)).isTrue();
        assertThat(NumberUtils.isEven(-16)).isTrue();
        assertThat(NumberUtils.isEven(3)).isFalse();
    }

    @Test
    public void testAbs() throws Exception {
        assertThat(NumberUtils.abs(0)).isEqualTo(0);
        assertThat(NumberUtils.abs(5)).isEqualTo(5);
        assertThat(NumberUtils.abs(-5)).isEqualTo(5);
        assertThat(NumberUtils.abs(Integer.MAX_VALUE)).isEqualTo(Integer.MAX_VALUE);
        assertThat(NumberUtils.abs(Integer.MIN_VALUE + 1)).isEqualTo(Integer.MAX_VALUE);

        assertThat(NumberUtils.abs(Integer.MIN_VALUE)).isEqualTo(Integer.MIN_VALUE);
    }

} 
