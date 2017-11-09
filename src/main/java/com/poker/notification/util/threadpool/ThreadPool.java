package com.poker.notification.util.threadpool;
import com.poker.notification.util.threadpool.support.ThreadPoolConfig;
import java.util.concurrent.Executor;

/**
 * 线程池
 */
public interface ThreadPool {

    Executor getExecutor(ThreadPoolConfig threadConfig);
}
