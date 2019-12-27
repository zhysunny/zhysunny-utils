package com.zhysunny.io.file;

import java.io.IOException;
import java.util.List;

/**
 * 文件读写
 * @author 章云
 * @date 2019/12/27 20:48
 */
public interface FileReadWrite<E> {

    /**
     * 文件读
     * @return
     */
    List<E> read() throws IOException;

    /**
     * 文件写
     * @param datas
     */
    void write(List<E> datas) throws IOException;

}
