package com.zhysunny.io.conf;

import static org.junit.Assert.*;

import com.zhysunny.io.properties.constant.Constant;
import org.junit.*;

import java.util.Arrays;

/**
 * Configuration Test.
 * @author 章云
 * @date 2019/8/2 10:03
 */
public class ConfigurationTest {

    @BeforeClass
    public static void beforeClass() throws Exception {
        System.out.println("Test Configuration Class Start...");
    }

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @AfterClass
    public static void afterClass() throws Exception {
        System.out.println("Test Configuration Class End...");
    }

    @Test
    public void testMain() throws Exception {
        new Configuration(Constant.class);
        System.out.println(Constant.value);
        System.out.println(Constant.value1);
        System.out.println(Constant.value2);
        System.out.println(Arrays.toString(Constant.array));
    }

}
