package com.poker.notification.util.threadpool.support;

/**
 * 线程配置
 */
public interface ThreadPoolConfig {

    int core();

    int maxThreads();

    int queueSize();

    int alive();

    String threadName();
}
