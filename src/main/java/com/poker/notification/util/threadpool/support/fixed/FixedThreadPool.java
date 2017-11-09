package com.poker.notification.util.threadpool.support.fixed;
import com.poker.notification.util.threadpool.ThreadPool;
import com.poker.notification.util.threadpool.support.AbortPolicyWithReport;
import com.poker.notification.util.threadpool.support.NamedThreadFactory;
import com.poker.notification.util.threadpool.support.ThreadPoolConfig;

import java.util.concurrent.*;

/**
 *  此线程池启动时即创建固定大小的线程数，不做任何伸缩
 */
public class FixedThreadPool implements ThreadPool {

    public Executor getExecutor(ThreadPoolConfig threadConfig) {

        String name = threadConfig.threadName();
        int threads = threadConfig.maxThreads();
        int queues = threadConfig.queueSize();
        ThreadPoolExecutor executor =  new ThreadPoolExecutor(threads, threads, 0, TimeUnit.MILLISECONDS,
                queues == 0 ? new SynchronousQueue<Runnable>() :
                        (queues < 0 ? new LinkedBlockingQueue<Runnable>()
                                : new LinkedBlockingQueue<Runnable>(queues)),
                new NamedThreadFactory(name, true), new AbortPolicyWithReport(name));

        executor.prestartAllCoreThreads();
        return executor;
    }
}
