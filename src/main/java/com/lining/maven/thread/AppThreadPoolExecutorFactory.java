package com.lining.maven.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class AppThreadPoolExecutorFactory {

    public static ThreadPoolExecutor createThreadPool(String name, int corePoolSize, int maximumPoolSize, BlockingQueue<Runnable> workQueue) {
        return new ThreadPoolExecutor(corePoolSize, maximumPoolSize, 1, TimeUnit.SECONDS, workQueue, new AppThreadFactory(name));
    }

    public static ScheduledThreadPoolExecutor createScheduledThreadPool(String name, int corePoolSize, BlockingQueue<Runnable> workQueue) {
        return new ScheduledThreadPoolExecutor(corePoolSize, new AppThreadFactory(name));
    }


}
