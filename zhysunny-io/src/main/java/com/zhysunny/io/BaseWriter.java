package com.zhysunny.io;

import java.io.*;

/**
 * 文件写入基类
 * @author 章云
 * @date 2019/7/26 13:54
 */
public abstract class BaseWriter {
    protected static final String ENCODING = "UTF-8";
    /**
     * 输出文件
     */
    protected File file;

    protected BaseWriter(String path) {
        this.file = new File(path);
    }

    protected BaseWriter(File file) {
        this.file = file;
    }

}
