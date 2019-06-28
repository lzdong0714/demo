package com.example.base;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

public class WebTest {

    private static final ThreadFactory THREAD_FACTORY =
            new ThreadFactoryBuilder().setNameFormat("task-pool-%d").build();

    private static ExecutorService FUTURE_TASK_EXECUTOR = new ThreadPoolExecutor(
            3, 10, 60L, TimeUnit.SECONDS,new LinkedBlockingDeque<>(), THREAD_FACTORY
    );

    public static <V> Future<V> submit(Callable<V> task) {
        return FUTURE_TASK_EXECUTOR.submit(task);
    }
}
