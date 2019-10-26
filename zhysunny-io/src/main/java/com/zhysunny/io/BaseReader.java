package com.zhysunny.io;

import java.io.File;
import java.net.URL;

/**
 * 文件读取基类
 * @author 章云
 * @date 2019/7/25 17:09
 */
public abstract class BaseReader {

    /**
     * 输入实例，支持URL，InputStream，File，String(转成File)
     */
    protected Object[] names;

    protected BaseReader(File file) {
        names = new Object[1];
        this.names[0] = file;
    }

    protected BaseReader(String path) {
        this(new File(path));
    }

    protected BaseReader(URL url) {
        names = new Object[1];
        this.names[0] = url;
    }

    protected BaseReader(Object... names) {
        this.names = names;
    }

    /**
     * 用于读取输出的文件流
     * @return 返回当前对象
     * @throws Exception
     */
    public abstract BaseReader builder() throws Exception;

}
