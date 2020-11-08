package com.zhysunny.common.collection;

import com.zhysunny.common.lang.ThreadUtils;
import org.apache.commons.lang3.math.NumberUtils;
import java.util.Map;

/**
 * @author zhysunny
 * @date 2020/11/7 22:01
 */
public class MapUtils {
    private static final int NUMBER = 10000;
    private static final ThreadGroup THREAD_GROUP = new ThreadGroup("mygroup");

    public static int threadSafety(final Map<String, String> map) {
        return threadSafety(map, THREAD_GROUP, NUMBER);
    }

    public static int threadSafety(final Map<String, String> map, ThreadGroup threadGroup, int number) {
        for (int index = 0; index < number; index++) {
            new Thread(threadGroup, () -> {
                ThreadUtils.sleep((long)(Math.random() * 2));
                String name = Thread.currentThread().getName();
                map.put(name, name);
            }, String.valueOf(index)).start();
        }
        while (threadGroup.activeCount() > 0) {
            ThreadUtils.sleep(10);
        }
        return map.size();
    }
}
