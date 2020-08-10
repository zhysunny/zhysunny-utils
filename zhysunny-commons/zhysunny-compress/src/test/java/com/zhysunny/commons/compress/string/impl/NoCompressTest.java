package com.zhysunny.commons.compress.string.impl;

import com.zhysunny.commons.compress.string.AbstractStringCompress;
import org.junit.*;
import static org.assertj.core.api.Assertions.*;

/**
 * NoCompress Test
 */
public class NoCompressTest {

    private AbstractStringCompress compress = new NoCompress();

    @BeforeClass
    public static void beforeClass() throws Exception {
        System.out.println("Test NoCompress Class Start...");
    }

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @AfterClass
    public static void afterClass() throws Exception {
        System.out.println("Test NoCompress Class End...");
    }

    /**
     * Method: compress(String content, String filename)
     */
    @Test
    public void testCompress() throws Exception {
        String content = "ABCDEFGHIJKLMNOPQRSTUVWXYZ_1234567890_abcdefghijklmnopqrstuvwxyz";
        String encode = this.compress.compress(content, "");
        String decode = this.compress.decompress(encode, "");
        assertThat(decode).isEqualTo(content);
    }

    @Test
    public void testCompressEmpty() throws Exception {
        String content = "";
        String encode = this.compress.compress(content, "");
        String decode = this.compress.decompress(encode, "");
        assertThat(this.compress.rate()).isEqualTo(0);
        assertThat(decode).isEqualTo(content);
    }

    @Test
    public void testCompressNull() throws Exception {
        String content = null;
        String encode = this.compress.compress(content, "");
        String decode = this.compress.decompress(encode, "");
        assertThat(this.compress.rate()).isEqualTo(0);
        assertThat(decode).isEqualTo(content);
    }

} 
