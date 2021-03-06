package com.zhysunny.common.collection;

import com.zhysunny.common.lang.ThreadUtils;
import org.apache.commons.collections4.MapUtils;
import java.util.Collection;
import java.util.Collections;

/**
 * @author zhysunny
 * @date 2020/11/7 21:04
 */
public class CollectionUtils {
    private static final int NUMBER = 10000;
    private static final ThreadGroup THREAD_GROUP = new ThreadGroup("mygroup");

    public static int threadSafety(final Collection<String> collection) {
        return threadSafety(collection, THREAD_GROUP, NUMBER);
    }

    public static int threadSafety(final Collection<String> collection, ThreadGroup threadGroup, int number) {
        for (int index = 0; index < number; index++) {
            new Thread(threadGroup, () -> {
                ThreadUtils.sleep((long)(Math.random() * 2));
                collection.add(Thread.currentThread().getName());
            }, String.valueOf(index)).start();
        }
        while (threadGroup.activeCount() > 0) {
            ThreadUtils.sleep(10);
        }
        return collection.size();
    }
}
