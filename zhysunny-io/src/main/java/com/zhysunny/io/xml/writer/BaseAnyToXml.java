package com.zhysunny.io.xml.writer;

import java.io.Writer;

/**
 * 其他数据结构写入xml的基类，当外部使用自己的数据结构时，可继承这个基类
 * @author 章云
 * @date 2019/8/23 16:40
 */
public abstract class BaseAnyToXml {

    /**
     * 通过实现这个方法，可以自由控制xml输出的数据结构
     * @param out
     * @param params
     * @return
     * @throws Exception
     */
    public abstract void write(Writer out, Object... params) throws Exception;

}
