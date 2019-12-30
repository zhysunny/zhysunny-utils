package com.zhysunny.io.file;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import static java.util.stream.Collectors.*;

/**
 * NIO读写
 * @author 章云
 * @date 2019/12/27 20:48
 */
public class NioFileReadWrite implements FileReadWrite<String> {

    private File file;

    public NioFileReadWrite(File file) {
        this.file = file;
    }

    public NioFileReadWrite(String filepath) {
        this.file = new File(filepath);
    }

    @Override
    public List<String> read() throws IOException {
        final List<String> datas = new ArrayList<>();
        if (!file.exists()) {
            return datas;
        }
        try {
            datas.addAll(Files.lines(Paths.get(file.getAbsolutePath())).collect(toList()));
        } catch (IOException e) {
            throw e;
        }
        return datas;
    }

    @Override
    public void write(List<String> datas) throws IOException {
        try {
            Files.write(Paths.get(file.getAbsolutePath()), datas.stream().collect(toList()));
        } catch (IOException e) {
            throw e;
        }
    }
}
