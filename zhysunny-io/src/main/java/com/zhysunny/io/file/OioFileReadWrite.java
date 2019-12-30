package com.zhysunny.io.file;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 阻塞IO读写
 * @author 章云
 * @date 2019/12/27 20:48
 */
public class OioFileReadWrite implements FileReadWrite<String> {

    private File file;

    public OioFileReadWrite(File file) {
        this.file = file;
    }

    public OioFileReadWrite(String filepath) {
        this.file = new File(filepath);
    }

    @Override
    public List<String> read() throws IOException {
        final List<String> datas = new ArrayList<>();
        if (!file.exists()) {
            return datas;
        }
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.length() == 0) {
                    continue;
                }
                datas.add(line);
            }
        } catch (IOException e) {
            throw e;
        }
        return datas;
    }

    @Override
    public void write(List<String> datas) throws IOException {
        try (final FileOutputStream fos = new FileOutputStream(file);) {
            for (String data : datas) {
                fos.write((data + "\n").getBytes());
            }
        } catch (IOException e) {
            throw e;
        }
    }
}
